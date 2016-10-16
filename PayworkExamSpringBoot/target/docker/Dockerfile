FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER jeag2002@payworks.com
ADD payworkexamspringboot-0.0.1.jar payworkexamspringboot.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]