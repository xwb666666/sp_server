FROM openjdk:8-jre-stretch
WORKDIR /app
COPY gateway.jar .

ENV SERVER_PORT     8000
ENV REGISTER_HOST   guns-cloud-nacos:8848
ENV REDIS_HOST      guns-cloud-redis
ENV REDIS_PORT      6379

ENTRYPOINT ["wait-for-it.sh", "guns-cloud-system:8001", "--timeout=0", "--", "java", "-jar","-Xms1024m","-Xmx1024m","-Xss1024K","-XX:MetaspaceSize=512m","-XX:MaxMetaspaceSize=512m","/app/gateway.jar"]
CMD ["--spring.profiles.active=docker"]