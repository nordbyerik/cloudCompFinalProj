package guiProj;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.JarURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.google.api.services.dataproc.Dataproc;
import com.google.api.services.dataproc.model.Job;
import com.google.api.services.dataproc.model.SubmitJobRequest;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.api.services.dataproc.model.HadoopJob;
import com.google.api.services.dataproc.model.JobReference;
import com.google.api.services.dataproc.model.JobPlacement;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.*;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.http.javanet.NetHttpTransport;

import com.google.api.client.auth.oauth2.*;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.testing.auth.oauth2.MockGoogleCredential;

import javax.swing.JScrollBar;
import javax.swing.JTextPane;


public class Frame1 {

	private JFrame frame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frame1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		GoogleCredentials credential;
		
		//HttpRequestInitializer requestInitializer = new HttpRequestInitializer.intitialize(credential);
		JButton btnNewButton = new JButton("Tolstoy");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					//Set up credentials
					GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("/usr/local/coastal-fiber-273519-5fc071667ed6.json")).createScoped(
							  java.util.Arrays.asList("https://www.googleapis.com/auth/cloud-platform"));
					HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);
				    
					//Create Dataproc builder
					Dataproc dataproc = new Dataproc.Builder(new NetHttpTransport(),new JacksonFactory(), requestInitializer).setApplicationName("Please work").build();
					String curJobId = "json-agg-job-" + UUID.randomUUID().toString();
					Job jobSnapshot = null;
					
					//Set up job
					Dataproc.Projects.Regions.Jobs.Submit mid = dataproc.projects().regions().jobs().submit(
							  "coastal-fiber-273519", "us-west1", new SubmitJobRequest()
					          .setJob(new Job()
					              .setReference(new JobReference()
					                   .setJobId(curJobId))
					              .setPlacement(new JobPlacement()
					                  .setClusterName("pittcloudproj"))
					              .setHadoopJob(new HadoopJob()
					                  .setMainClass("WordCount")
					                  .setJarFileUris(ImmutableList.of("gs://dataproc-staging-us-west1-1074180864086-fedgkxmx/JAR/wc.jar"))
					                  .setArgs(ImmutableList.of(
					                      "gs://dataproc-staging-us-west1-1074180864086-fedgkxmx/Data/Tolstoy", "gs://dataproc-staging-us-west1-1074180864086-fedgkxmx/Out" )))));
					
					Job JobSnapshot = mid.execute();
					
				}catch(Exception e) {e.printStackTrace();;}
				
			}
		});
		btnNewButton.setBounds(29, 80, 86, 25);
		frame.getContentPane().add(btnNewButton);
		
		
		
		
		
		
		
		
		
		JButton btnShakespeare = new JButton("Shakespeare");
		btnShakespeare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					//Set up credentials
					GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("/usr/local/coastal-fiber-273519-5fc071667ed6.json")).createScoped(
							  java.util.Arrays.asList("https://www.googleapis.com/auth/cloud-platform"));
					HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);
				
					//Create Dataproc builder
					Dataproc dataproc = new Dataproc.Builder(new NetHttpTransport(),new JacksonFactory(), requestInitializer).setApplicationName("Please work").build();
					String curJobId = "json-agg-job-" + UUID.randomUUID().toString();
					Job jobSnapshot = null;
					
					//Set up job
					Dataproc.Projects.Regions.Jobs.Submit mid = dataproc.projects().regions().jobs().submit(
							  "coastal-fiber-273519", "us-west1", new SubmitJobRequest()
					          .setJob(new Job()
					              .setReference(new JobReference()
					                   .setJobId(curJobId))
					              .setPlacement(new JobPlacement()
					                  .setClusterName("pittcloudproj"))
					              .setHadoopJob(new HadoopJob()
					                  .setMainClass("WordCount")
					                  .setJarFileUris(ImmutableList.of("gs://dataproc-staging-us-west1-1074180864086-fedgkxmx/JAR/wc.jar"))
					                  .setArgs(ImmutableList.of(
					                      "gs://dataproc-staging-us-west1-1074180864086-fedgkxmx/Data/Shakespeare", "gs://dataproc-staging-us-west1-1074180864086-fedgkxmx/Out" )))));
					
					Job JobSnapshot = mid.execute();
					
		}catch(Exception e) {e.printStackTrace();;}
			}
		});
		btnShakespeare.setBounds(29, 43, 128, 25);
		frame.getContentPane().add(btnShakespeare);
		
		
		
		
		
		
		JButton btnNewButton_1 = new JButton("Hugo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					//Set up credentials
					GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("/usr/local/coastal-fiber-273519-5fc071667ed6.json")).createScoped(
							  java.util.Arrays.asList("https://www.googleapis.com/auth/cloud-platform"));
					HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);
					System.err.println("Made credentials");

					//Create Dataproc builder
					Dataproc dataproc = new Dataproc.Builder(new NetHttpTransport(),new JacksonFactory(), requestInitializer).setApplicationName("Please work").build();
					String curJobId = "json-agg-job-" + UUID.randomUUID().toString();
					Job jobSnapshot = null;
					
					//Set up job
					Dataproc.Projects.Regions.Jobs.Submit mid = dataproc.projects().regions().jobs().submit(
							  "coastal-fiber-273519", "us-west1", new SubmitJobRequest()
					          .setJob(new Job()
					              .setReference(new JobReference()
					                   .setJobId(curJobId))
					              .setPlacement(new JobPlacement()
					                  .setClusterName("pittcloudproj"))
					              .setHadoopJob(new HadoopJob()
					                  .setMainClass("WordCount")
					                  .setJarFileUris(ImmutableList.of("gs://dataproc-staging-us-west1-1074180864086-fedgkxmx/JAR/wc.jar"))
					                  .setArgs(ImmutableList.of(
					                      "gs://dataproc-staging-us-west1-1074180864086-fedgkxmx/Data/Hugo", "gs://dataproc-staging-us-west1-1074180864086-fedgkxmx/Out" )))));
					
					Job JobSnapshot = mid.execute();
					
		}catch(Exception e) {e.printStackTrace();;}
			}
		});
		btnNewButton_1.setBounds(29, 117, 71, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		
		
		
		
		

		JButton btnNewButton_2 = new JButton("All");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					//Set up credentials
					GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("/usr/local/coastal-fiber-273519-5fc071667ed6.json")).createScoped(
							  java.util.Arrays.asList("https://www.googleapis.com/auth/cloud-platform"));
					HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);
					System.err.println("Made credentials");

					
					//Create Dataproc builder
					Dataproc dataproc = new Dataproc.Builder(new NetHttpTransport(),new JacksonFactory(), requestInitializer).setApplicationName("Please work").build();
					String curJobId = "json-agg-job-" + UUID.randomUUID().toString();
					Job jobSnapshot = null;
					
					//Set up job
					Dataproc.Projects.Regions.Jobs.Submit mid = dataproc.projects().regions().jobs().submit(
							  "coastal-fiber-273519", "us-west1", new SubmitJobRequest()
					          .setJob(new Job()
					              .setReference(new JobReference()
					                   .setJobId(curJobId))
					              .setPlacement(new JobPlacement()
					                  .setClusterName("pittcloudproj"))
					              .setHadoopJob(new HadoopJob()
					                  .setMainClass("WordCount")
					                  .setJarFileUris(ImmutableList.of("gs://dataproc-staging-us-west1-1074180864086-fedgkxmx/JAR/wc.jar"))
					                  .setArgs(ImmutableList.of(
					                      "gs://dataproc-staging-us-west1-1074180864086-fedgkxmx/Data/All", "gs://dataproc-staging-us-west1-1074180864086-fedgkxmx/Out" )))));
					
					//Start job
					Job JobSnapshot = mid.execute();
					
					
				}catch(Exception e) {e.printStackTrace();;}
			}
		});
		btnNewButton_2.setBounds(29, 154, 71, 25);
		frame.getContentPane().add(btnNewButton_2);
		
		
		
		
		JButton btnNewButton_3 = new JButton("Download Inverted Index");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					//Set up credentials
					GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("/usr/local/coastal-fiber-273519-5fc071667ed6.json")).createScoped(
							  java.util.Arrays.asList("https://www.googleapis.com/auth/cloud-platform"));
					HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);
					
					//Download individual files
					StorageOptions options = StorageOptions.newBuilder()
				            .setProjectId("coastal-fiber-273519")
				            .setCredentials(credentials)
				            .build();
					Storage storage = options.getService();
					
					Blob blob0 = storage.get("dataproc-staging-us-west1-1074180864086-fedgkxmx", "Out/part-r-00001");
					System.err.println(blob0.toString());
					ReadChannel readChannel0 = blob0.reader();
					FileOutputStream fileOuputStream0 = new FileOutputStream( "out0.txt" );
					fileOuputStream0.getChannel().transferFrom(readChannel0, 0, Long.MAX_VALUE);
					fileOuputStream0.close();
					
					Blob blob = storage.get("dataproc-staging-us-west1-1074180864086-fedgkxmx", "Out/part-r-00001");
						System.err.println(blob.toString());
						ReadChannel readChannel = blob.reader();
					FileOutputStream fileOuputStream = new FileOutputStream( "out.txt" );
					fileOuputStream.getChannel().transferFrom(readChannel, 0, Long.MAX_VALUE);
					fileOuputStream.close();
					
					Blob blob2 = storage.get("dataproc-staging-us-west1-1074180864086-fedgkxmx", "Out/part-r-00002");
					System.err.println(blob2.toString());
					ReadChannel readChannel2 = blob2.reader();
					FileOutputStream fileOuputStream2 = new FileOutputStream( "out2.txt" );
					fileOuputStream2.getChannel().transferFrom(readChannel2, 0, Long.MAX_VALUE);
					fileOuputStream2.close();
					
					Blob blob3 = storage.get("dataproc-staging-us-west1-1074180864086-fedgkxmx", "Out/part-r-00003");
					System.err.println(blob3.toString());
					ReadChannel readChannel3 = blob3.reader();
					FileOutputStream fileOuputStream3 = new FileOutputStream( "out3.txt" );
					fileOuputStream3.getChannel().transferFrom(readChannel3, 0, Long.MAX_VALUE);
					fileOuputStream3.close();
					
					Blob blob4 = storage.get("dataproc-staging-us-west1-1074180864086-fedgkxmx", "Out/part-r-00004");
					System.err.println(blob4.toString());
					ReadChannel readChannel4 = blob4.reader();
					FileOutputStream fileOuputStream4 = new FileOutputStream( "out4.txt" );
					fileOuputStream4.getChannel().transferFrom(readChannel4, 0, Long.MAX_VALUE);
					fileOuputStream4.close();
					
					
					//Set up individual file paths
					List<Path> inputs = Arrays.asList(
							Paths.get("out0.txt"),
				            Paths.get("out.txt"),
				            Paths.get("out2.txt"),
				            Paths.get("out3.txt"),
				            Paths.get("out4.txt")
				    );
					
					//Combine out.txt files into one large file
				    Path output = Paths.get("combined.txt");
				    Charset charset = StandardCharsets.UTF_8;
				    for (Path path : inputs) {
				        List<String> lines = Files.readAllLines(path, charset);
				        Files.write(output, lines, charset, StandardOpenOption.CREATE,
				                StandardOpenOption.APPEND);
				    }
				    
					
					String inputFile = "combined.txt";
					String outputFile = "sorted.txt";
					String outputFileFinal = "out.txt";
	
					
					//Sort large file
					FileReader fileReader = new FileReader(inputFile);
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					String inputLine;
					List<String> lineList = new ArrayList<String>();
					while ((inputLine = bufferedReader.readLine()) != null) {
						lineList.add(inputLine);
					}
					fileReader.close();
					Collections.sort(lineList);
					
					
					//Write out sorted file
					FileWriter fileWriter = new FileWriter(outputFile);
					PrintWriter out = new PrintWriter(fileWriter);
					for (String outputLine : lineList) {
						out.println(outputLine);
					}
					out.flush();
					out.close();
					fileWriter.close();
					
					//Combine rows and get rid of duplicates
					
					//Set up vars
					String withDuplicates = "sorted.txt";
					FileReader fileReader2 = new FileReader(withDuplicates);
					BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
					String thisLine;
					List<String> allLines = new ArrayList<String>();
					String[] lastLineArray;
					String[] thisLineArray;
					
					//Get first line
					thisLine = bufferedReader2.readLine();
					thisLineArray = thisLine.split(" ");
					lastLineArray = thisLine.split(" ");
					
					
					while ((thisLine = bufferedReader2.readLine()) != null) {
						thisLineArray = thisLine.split(" ");
						
						//If word is the same, add books not included already
						if (thisLineArray[0].compareTo(lastLineArray[0]) == 0) {
							//System.err.println("Same");
							for (String element : lastLineArray) {
								if(!Arrays.asList(thisLineArray).contains(element)){
									int aLen = thisLineArray.length;
								    int bLen = lastLineArray.length;
								    String[] temp = new String[aLen+bLen];
								    System.arraycopy(thisLineArray, 0, temp, 0, aLen);
								    System.arraycopy(element, 0, temp, aLen, bLen);
								    thisLineArray = temp;
								}
							}
						}
						
						//If words dont match, write out old
						else {
							allLines.add(String.join(" ",lastLineArray));
							//System.err.println(String.join(" ",lastLineArray));
						}
						lastLineArray = thisLineArray;
					}
					fileReader2.close();
					
					//Write to text file
					FileWriter fileWriter2 = new FileWriter(outputFileFinal);
					PrintWriter out2 = new PrintWriter(fileWriter2);
					for (String outputLine : allLines) {
						System.err.println(outputLine);
						out2.println(outputLine);
					}
					out2.flush();
					out2.close();
					fileWriter2.close();
				

				}catch(Exception e){e.printStackTrace();}
			}
		});
		btnNewButton_3.setBounds(260, 43, 117, 25);
		frame.getContentPane().add(btnNewButton_3);
		
	}
}
