#Setting the base image
FROM  openjdk:17-jdk-slim-buster  as builder

USER Amadou:authors

WORKDIR $HOME/eclipse-workspace/userService
 
ENV JAVA_HOME=/usr/lib/jvm/jdk-17/bin/java

ENV PATH="${JAVA_HOME}/bin:${PATH}"

#Download and Installing Maven
ARG MAVEN_VERSION=3.8.6
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries
#Create  the directories, download maven, remove downloaded file and links
ARG SHA=f790857f3b1f90ae8d16281f902c689e4f136ebe584aba45e4b1fa66c80cba826d3e0e52fdd04ed44b4c66f6d3fe3584a057c26dfcac544a60b301e6d0f91c26
RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
&& echo "Download maven" \
&& curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
\
&& echo "Checking downlog has" \
&& echo "${SHA}  /tmp/apache-maven.tar.gz" |  sha512sum -c - \
\
&& echo "Unziping maven" \

&& tar echo "Cleaning and setting  links" \
&& rm -f /tmp/apache-maven.tar.gz \
&& ln -s /usr/share/maven/bin/mvn  /usr/bin/mvn

# Define environmental variables  required  by Maven, like MAVEN_HOME
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "${JAVA_HOME}/.m2"

FROM ubuntu:latest

ARG JAR_FILE=target/userService-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} userService-0.0.1-SNAPSHOT.jar
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder userService/ ./
COPY --from=builder mvnw pom.xml ./
VOLUME /tmp
ADD ${JAR_FILE} /tmp
RUN chmod +x /tmp/userService-0.0.1-SNAPSHOT.jar
#COPY --from=builder src ./src
# providing a default command
ENTRYPOINT ["java","-jar","userService-0.0.1-SNAPSHOT.jar"]
CMD ["java","-jar","/tmp/userService-0.0.1-SNAPSHOT.jar"]
#CMD ["mvn", "spring-boot:run"]

EXPOSE 8080