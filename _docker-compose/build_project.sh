#!/bin/bash

# 这里设置整个项目所在服务器的路径
basePath="/home/guns-cloud-parent-master"

# 构建maven  jar包
docker run -it --rm --name guns-cloud-maven -v $basePath:/apps -v $basePath/_docker-compose/maven_settings.xml:/maven_settings.xml -w /apps maven:3.6-jdk-8 mvn -B -s /maven_settings.xml package -Dmaven.test.skip -P docker

# 拷贝jar到各个dockerfile的路径
cp $basePath/sys-app/target/sys-app.jar $basePath/_docker-compose/3.guns-cloud-system/
cp $basePath/gateway/target/gateway.jar $basePath/_docker-compose/4.guns-cloud-gateway/
cp $basePath/auth-app/target/auth-app.jar $basePath/_docker-compose/5.guns-cloud-auth/
cp $basePath/workflow-app/target/workflow-app.jar $basePath/_docker-compose/7.guns-cloud-workflow/

# wait脚本变为可执行
/bin/chmod +x ./wait-for-it.sh

# 启动项目
docker-compose up -d
