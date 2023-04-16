FROM data.vm:8082/jdk/openjdk:17.0.1
WORKDIR /root
COPY target/mockmocker-*.jar /app/mockmocker.jar

CMD ["java", \
     "-XX:InitialRAMPercentage=50.0", "-XX:MaxRAMPercentage=50.0", \
     "-XX:+UseG1GC", "-XX:+HeapDumpOnOutOfMemoryError", \
     "-jar", "/app/mockmocker.jar"]
