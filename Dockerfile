FROM registry.cn-hangzhou.aliyuncs.com/laoweng/openjdk:8-jre-alpine
WORKDIR /root
COPY target/mockmocker-0.0.1-SNAPSHOT.jar /app/mockmocker.jar

CMD ["java", \
     "-XX:InitialRAMPercentage=50.0", "-XX:MaxRAMPercentage=50.0", \
     "-XX:+UseG1GC", "-XX:+HeapDumpOnOutOfMemoryError", \
     "-jar", "/app/mockmocker.jar"]
