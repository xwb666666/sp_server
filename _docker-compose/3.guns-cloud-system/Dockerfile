FROM openjdk:8-jre-stretch
WORKDIR /app
COPY sys-app.jar .

ENV SERVER_PORT   8001
ENV REGISTER_HOST guns-cloud-nacos:8848
ENV DB_DRIVER     com.mysql.cj.jdbc.Driver
ENV DB_URL        jdbc:mysql://guns-cloud-mysql:3306/guns_cloud_system?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=CTT
ENV DB_USERNAME   root
ENV DB_PASSWORD   root
ENV REDIS_HOST    guns-cloud-redis
ENV REDIS_PORT    6379

ENTRYPOINT ["wait-for-it.sh", "guns-cloud-nacos:8848", "--timeout=0", "--", "java", "-jar","-Xms1024m","-Xmx1024m","-Xss1024K","-XX:MetaspaceSize=512m","-XX:MaxMetaspaceSize=512m","/app/sys-app.jar"]
CMD ["--spring.profiles.active=docker"]