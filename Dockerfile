FROM  nexus.sp5000.com:8082/repository/sieyuan_docker_host/shrbase:0.3
WORKDIR /app
COPY ./target/MinioService-1.0.2.RELEASE.jar  /app
EXPOSE  18003
CMD ["java","-Duser.timezone=Asia/Shanghai","-Dfile.encoding=UTF-8","-Dsun.jnu.encoding=UTF-8","-jar","MinioService-1.0.2.RELEASE.jar"]