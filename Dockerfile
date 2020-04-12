
FROM openjdk:latest

# Set environment
ENV JAVA_HOME /opt/jdk
ENV PATH ${PATH}:${JAVA_HOME}/bin

#Deal with libXext error
RUN yum -y install libXext.i686
RUN yum -y install libX11-devel.x86_64
RUN yum -y install libXext.x86_64
RUN yum -y install libXrender.x86_64
RUN yum -y install libXtst.x86_64

RUN yum -y install wget

#Adding google cloud
RUN curl https://dl.google.com/dl/cloudsdk/release/google-cloud-sdk.tar.gz > /tmp/google-cloud-sdk.tar.gz

RUN mkdir -p /usr/local/gcloud \
  && tar -C /usr/local/gcloud -xvf /tmp/google-cloud-sdk.tar.gz \
  && /usr/local/gcloud/google-cloud-sdk/install.sh

ENV PATH $PATH:/usr/local/gcloud/google-cloud-sdk/bin

#Copy jar for gui
COPY ./Untitled2.jar /usr/local/

#Copy key for google ssh
COPY ./coastal-fiber-273519-5fc071667ed6.json /usr/local/
COPY ./dockerScript /usr/local/

RUN export GOOGLE_APPLICATION_CREDENTIALS="/usr/local/coastal-fiber-273519-5fc071667ed6.json"

#Set up SSH
RUN yum -y install openssh
RUN yum -y install openssh-clients


EXPOSE 1000
RUN gcloud auth activate-service-account --key-file /usr/local/coastal-fiber-273519-5fc071667ed6.json

RUN gcloud compute ssh  nordby_erik3@pittcloudproj-m   --project=coastal-fiber-273519  --zone=us-west1-a


# Start the image with the jar file as the entrypoint
ENTRYPOINT ["java", "-jar", "/usr/local/Untitled2.jar"]
