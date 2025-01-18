#FROM openjdk
#EXPOSE 8083
#ADD *.jar /app.jar
#RUN bash -c 'touch /app.jar'
#ENTRYPOINT ["java","-jar","/app.jar", "--server.port=8061"]