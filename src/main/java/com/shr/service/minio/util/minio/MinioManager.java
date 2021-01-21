package com.shr.service.minio.util.minio;

import com.shr.service.minio.config.MinioConfig;
import com.shr.service.minio.controller.common.LogSenderController;
import com.shr.service.minio.entity.MinioEntity;
import com.shr.service.minio.entity.common.ConstantInterface;
import com.shr.service.minio.util.common.CommonUtil;
import io.minio.*;
import io.minio.errors.BucketPolicyTooLargeException;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.MinioException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：206612
 * @date ：Created in 2021/1/15 15:27
 * @description：MinioManager
 */
@Component
@Slf4j
public class MinioManager extends LogSenderController {
    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private MinioConfig minioConfig;

    public List<Bucket> listBuckets()
    {
        MinioClient minioClient = getMinioClient();
        try {
            // 列出所有存储桶
            return minioClient.listBuckets();

        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public InputStream getFile(String bucket, String fileName) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException
    {
        createBucket(bucket);
        MinioClient minioClient = getMinioClient();
        return minioClient.getObject(GetObjectArgs.builder()
                .bucket(bucket)
                .object(fileName)
                .build());
    }

    public void deleteBucket(String bucket) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException
    {
        MinioClient minioClient = getMinioClient();
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucket).build());
    }

    public String putFile(File file, String fileName, String bucket) throws IOException, InvalidResponseException,
            InvalidKeyException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException, BucketPolicyTooLargeException
    {
        addFile(file, fileName, bucket);
        return getFileLink(bucket + ConstantInterface.SPACE_SEPARATOR + fileName);
    }

    /**
     * 上传文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param stream     文件流
     * @throws Exception
     */
    public void addImage(String bucketName, String objectName, InputStream stream) throws Exception
    {
        MinioClient minioClient = getMinioClient();
        createBucket(bucketName);
        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                stream, -1, 10485760)
                .contentType("image/jpeg")
                .build());
    }

    public void addFile(File htmlFile, String fileName, String bucket) throws IOException, InvalidKeyException,
            InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException, BucketPolicyTooLargeException
    {
        MinioClient minioClient = getMinioClient();
        createBucket(bucket);
        InputStream inputStream = java.nio.file.Files.newInputStream(htmlFile.toPath());
        minioClient.putObject(PutObjectArgs.builder().bucket(bucket).object(fileName).stream(inputStream, -1,
                10485760).build());
    }

    public void createBucket(String bucket) throws ErrorResponseException,
            InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException
    {
        MinioClient minioClient = getMinioClient();
        boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        if (!isExist) {
            // 创建bucket
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }

        //添加可读策略
        String policyJson = ConstantInterface.BUCKET_POLICY.replace("bucketname", bucket);
        minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucket).config(policyJson).build());
    }

    public boolean bucketHasExist(String bucket) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException
    {
        MinioClient minioClient = getMinioClient();
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
    }

    public String getFileLink(String path)
    {
        if (StringUtils.isBlank(path)) {
            return "";
        }
        String host = "http://" + minioConfig.getEndPoint() + ":" + minioConfig.getPort() + ConstantInterface.SLASH_SEPARATOR;
        String[] imagePath = path.split(ConstantInterface.SPACE_SEPARATOR);
        return host + commonUtil.getFileNameEncode(imagePath[0] + ConstantInterface.SLASH_SEPARATOR + imagePath[1]);
    }

    private MinioClient getMinioClient()
    {
        return MinioClient.builder()
                .endpoint(minioConfig.getEndPoint(), Integer.parseInt(minioConfig.getPort()), false)
                .credentials(minioConfig.getAccessKey(), minioConfig.getSecretKey())
                .build();

    }

    public List<MinioEntity> listObjects(String bucket)
    {
        List<MinioEntity> result = new ArrayList<>();
        try {
            MinioClient minioClient = getMinioClient();
            Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder()
                    .bucket(bucket)
                    .build());
            results.forEach(e -> {
                try {
                    Item item = e.get();
                    String s = item.objectName();
                    long size = item.size();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            });

            return result;
        } catch (Exception e) {

        }

        return result;
    }

    public Iterable<Result<Item>> listObjectsByBucket(String bucket)
    {
        try {
            MinioClient minioClient = getMinioClient();
            // 检查列出bucket里的对象是否存在。
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
            if (found) {
                // 列出bucket里的对象
                return minioClient.listObjects(ListObjectsArgs.builder().bucket(bucket).recursive(true).build());
            }
            else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteObjects(List<String> fileNames, String bucket)
    {
        try {
            if (CollectionUtils.isEmpty(fileNames) || StringUtils.isBlank(bucket) || !bucketHasExist(bucket)) {
                return;
            }
            List<DeleteObject> objects = new LinkedList<>();
            for (String fileName : fileNames) {
                objects.add(new DeleteObject(fileName));
            }
            MinioClient minioClient = getMinioClient();
            Iterable<Result<DeleteError>> results =
                    minioClient.removeObjects(
                            RemoveObjectsArgs.builder().bucket(bucket).objects(objects).build());
            for (Result<DeleteError> result : results) {
                DeleteError error = result.get();
                System.out.println(
                        "Error in deleting object " + error.objectName() + "; " + error.message());
            }

        } catch (Exception e) {

        }
    }

}