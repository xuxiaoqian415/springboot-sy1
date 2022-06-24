FROM jdk:8
MAINTAINER xuxiaoqian "1065791875@qq.com"
LABEL Description="springboot-sy1" Vendor="xuxiaoqian" Version="0.0.1"
FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD springboot-sy1-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]