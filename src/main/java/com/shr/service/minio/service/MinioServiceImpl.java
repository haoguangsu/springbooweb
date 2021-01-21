package com.shr.service.minio.service;

import com.shr.service.minio.controller.common.LogSenderController;
import com.shr.service.minio.entity.AddFileEntity;
import com.shr.service.minio.entity.DeleteFileEntity;
import com.shr.service.minio.entity.MinioEntity;
import com.shr.service.minio.entity.common.ConstantInterface;
import com.shr.service.minio.entity.common.ErrorCodeList;
import com.shr.service.minio.util.common.CommonUtil;
import com.shr.service.minio.util.minio.MinioManager;
import io.minio.Result;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MinioServiceImpl extends LogSenderController {

    @Autowired
    private MinioManager minioManager;
    @Autowired
    private CommonUtil commonUtil;

    public List<MinioEntity> getAllBucket()
    {
        List<Bucket> bucketList = minioManager.listBuckets();
        if (CollectionUtils.isEmpty(bucketList)) {
            return new ArrayList<>();
        }
        List<MinioEntity> result = new ArrayList<>(bucketList.size());

        for (Bucket bucket : bucketList) {
            MinioEntity entity = new MinioEntity();
            entity.setBucket(bucket.name());
            entity.setCreationDate(bucket.creationDate().toString());
            result.add(entity);
        }
        return result;
    }

    public List<MinioEntity> listObjectsByBucket(String bucket)
    {
        List<MinioEntity> result = new ArrayList<>();
        try {
            Iterable<Result<Item>> results = minioManager.listObjectsByBucket(bucket);
            if (Objects.isNull(results)) {
                return result;
            }
            for (Result<Item> items : results) {
                Item item = items.get();
                MinioEntity entity = new MinioEntity();
                entity.setFileName(item.objectName());
                entity.setIsFile(!item.isDir());
                entity.setSize(item.size());
                result.add(entity);
            }
        } catch (Exception e) {

        }
        return result;
    }

    public List<MinioEntity> getFilesByPath(MinioEntity minioEntity)
    {
        List<MinioEntity> result = new ArrayList<>();
        if (Objects.isNull(minioEntity) || StringUtils.isEmpty(minioEntity.getPath())) {
            return result;
        }
        String path = minioEntity.getPath();
        try {
            String[] split = path.split(ConstantInterface.SLASH_SEPARATOR);
            String bucket = split[0];
            List<MinioEntity> minioEntities = listObjectsByBucket(bucket);
            if (CollectionUtils.isEmpty(minioEntities)) {
                return result;
            }
            for (MinioEntity entity : minioEntities) {
                String fileName = bucket + ConstantInterface.SLASH_SEPARATOR + entity.getFileName();
                if (fileName.indexOf(path) >= 0) {
                    String substring = fileName.substring(path.length() + 1);
                    String[] split1 = substring.split(ConstantInterface.SLASH_SEPARATOR);
                    MinioEntity temp = new MinioEntity();
                    if (split1.length > 1) {
                        temp.setFileName(split1[0]);
                        temp.setIsFile(Boolean.FALSE);
                    }
                    else {
                        temp.setFileName(split1[0]);
                        temp.setIsFile(Boolean.TRUE);
                    }
                    result.add(temp);
                }
            }
            return result.stream().distinct().collect(Collectors.toList());

        } catch (Exception e) {

        }
        return null;
    }

    public String getFileByPath(MinioEntity minioEntity)
    {
        if (Objects.isNull(minioEntity) || StringUtils.isEmpty(minioEntity.getPath())) {
            return null;
        }
        try {
            String path = minioEntity.getPath();
            String bucket = path.substring(0, path.indexOf("/"));
            String fileName = path.substring(path.indexOf("/") + 1);
            return minioManager.getFileLink(bucket + ConstantInterface.SPACE_SEPARATOR + fileName);
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteBucket(String bucket)
    {
        try {
            minioManager.deleteBucket(bucket);
        } catch (Exception e) {

        }
    }

    public String addBucket(MinioEntity minioEntity)
    {
        if (Objects.isNull(minioEntity) || StringUtils.isEmpty(minioEntity.getBucket())) {
            return null;
        }

        try {
            String bucket = minioEntity.getBucket();

            if (minioManager.bucketHasExist(bucket)) {
                return ErrorCodeList.NAME_EXIST;
            }
            if (!commonUtil.checkBucketName(bucket)) {
                return ErrorCodeList.NAME_ERROR;
            }
            minioManager.createBucket(bucket);
        } catch (Exception e) {
            return null;
        }
        return ConstantInterface.OK;
    }

    public String addFile(AddFileEntity addFileEntity) throws IOException
    {
        OutputStream fos = null;
        File htmlFile = null;
        try {
            byte[] input = addFileEntity.getBytes();
            String fileName = addFileEntity.getFileName();
            String bucket = addFileEntity.getBucket();

            htmlFile = File.createTempFile(UUID.randomUUID().toString(), addFileEntity.getSuffix());

            fos = java.nio.file.Files.newOutputStream(htmlFile.toPath());
            fos.write(input);
            minioManager.addFile(htmlFile, fileName, bucket);
        } catch (Exception e) {
            sendErrorLog("ExcelUtil write error ", e.getMessage());
            return null;
        } finally {
            if (Objects.nonNull(fos)) {
                fos.flush();
                fos.close();
            }
            if (Objects.nonNull(htmlFile) && htmlFile.delete()) {
                //do nothing
            }
        }
        return ConstantInterface.OK;
    }

    public String deleteFiles(DeleteFileEntity deleteFileEntity)
    {
        try {
            minioManager.deleteObjects(deleteFileEntity.getFileNames(), deleteFileEntity.getBucket());
            return ConstantInterface.OK;
        } catch (Exception e) {
            return null;
        }
    }

}