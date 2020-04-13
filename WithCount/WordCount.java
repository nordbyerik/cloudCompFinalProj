import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import java.lang.ProcessBuilder;

public class WordCount{
	public static void main(String[] args)
	throws IOException, ClassNotFoundException, InterruptedException{
		if(args.length != 2){
			System.err.println("Usage: Word Counut <input path> <output path>");
			System.exit(-1);
		}
		
		Job job = new Job();
		job.setJarByClass(WordCount.class);
		job.setJobName("WordCount");

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setMapperClass(WordCountMapper.class);
		//job.setCombinerClass(Reduce.class);
		job.setReducerClass(WordCountReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.waitForCompletion(true);
                
		
	}
}
	class WordCountMapper extends Mapper<LongWritable, Text, Text, Text> {
        	private Text docName = new Text();
        	private final static Text word = new Text();
        	public void map(LongWritable key, Text value, Context context)
                	throws IOException, InterruptedException {
                        	String line = value.toString();
				docName.set(((FileSplit) context.getInputSplit()).getPath().getName());
                        	StringTokenizer tokenizer = new StringTokenizer(line);
                        	while (tokenizer.hasMoreTokens()) {
                                	word.set(tokenizer.nextToken());
                                	context.write(word, docName);
                	}
        	}
	}
	class WordCountReducer extends Reducer<Text, Text, Text, Text>{
		public void reduce(Text key, Iterable<Text> values, Context context)
		throws IOException, InterruptedException{
			Text sum = new Text();
			for (Text value : values)
				sum.set(sum.toString() + " " + value.toString());
			context.write(key, new Text(sum));
		}
	}






