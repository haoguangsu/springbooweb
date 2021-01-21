#!/bin/sh

# The remote path should be: 
# http://10.16.196.83:8081/repository/sieyuan_release_pack/cameraservice/camerasvc_deploy.tar

readonly TAR_FILE_NAME="minioservicesvc_deploy.tar.gz"
readonly USR_PASSWORD="admin:SYhr_5000"
readonly SERVICE_NAME="minioservice"
readonly NEXUS_HOST="http://nexus.sp5000.com:8081/repository/sieyuan_release_pack"

# pack the following files into camerasvc_deploy.tar.gz
tar -czvf ${TAR_FILE_NAME} \
    ./target/MinioService-1.0.2.RELEASE.jar \
    ./deploy-java.nomad

# DELETE the old file
curl -v -X "DELETE" --user ${USR_PASSWORD} ${NEXUS_HOST}/${SERVICE_NAME}/${TAR_FILE_NAME}

# POST the camerasvc_deploy.tar to Nexus
curl -v --user ${USR_PASSWORD} --upload-file \
    ./${TAR_FILE_NAME} \
    ${NEXUS_HOST}/${SERVICE_NAME}/${TAR_FILE_NAME}