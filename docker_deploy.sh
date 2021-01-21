#!/bin/sh
docker build --tag="minioservice" .
docker save -o minioservice.tar minioservice
chmod +r minioservice.tar
cp -rf minioservice.tar /nomad/files