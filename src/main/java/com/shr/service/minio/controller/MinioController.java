package com.shr.service.minio.controller;

import com.shr.service.minio.controller.common.BaseController;
import com.shr.service.minio.entity.AddFileEntity;
import com.shr.service.minio.entity.DeleteFileEntity;
import com.shr.service.minio.entity.MinioEntity;
import com.shr.service.minio.entity.common.ConstantInterface;
import com.shr.service.minio.entity.common.HttpResponse;
import com.shr.service.minio.service.MinioServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：206612
 * @date ：Created in 2021/1/15 15:02
 * @description：MinioController
 */
@RestController
@RequestMapping("/minio")
@Api(tags = "Minio")
@Slf4j
public class MinioController extends BaseController {
    @Autowired
    private MinioServiceImpl minioService;

    private MinioController()
    {
    }

    @GetMapping("/all")
    @ApiOperation("获取所有Bucket")
    public HttpResponse<List<MinioEntity>> getAll()
    {
        sendInfoLog("MinioController getAll start.", "");
        List<MinioEntity> minioEntities = minioService.getAllBucket();
        return checkResult(minioEntities);
    }

    @PostMapping("/addbucket")
    @ApiOperation("新增bucket")
    public HttpResponse<String> addBucket(@RequestBody MinioEntity minioEntity)
    {
        sendInfoLog("MinioController addBucket start, entity is ", minioEntity.toString());
        return checkResult(minioService.addBucket(minioEntity));
    }

    @DeleteMapping("/deletebucket/{bucket}")
    @ApiOperation("删除bucket")
    public HttpResponse<String> deleteBucket(@PathVariable(name = "bucket") String bucket)
    {
        sendInfoLog("MinioController deleteBucket start, bucket is ", bucket);
        minioService.deleteBucket(bucket);
        return checkResult(ConstantInterface.OK);
    }

    @GetMapping("/listobjectsbybucket/{bucket}")
    @ApiOperation("查看Bucket下的所有文件")
    public HttpResponse<List<MinioEntity>> listObjectsByBucket(@PathVariable(name = "bucket") String bucket)
    {
        sendInfoLog("MinioController listObjectsByBucket start, bucket is ", bucket);
        List<MinioEntity> minioEntities = minioService.listObjectsByBucket(bucket);
        return checkResult(minioEntities);
    }

    @PostMapping("/getfilesbypath")
    @ApiOperation("根据path查看所有文件")
    public HttpResponse<List<MinioEntity>> getFilesByPath(@RequestBody MinioEntity minioEntity)
    {
        sendInfoLog("MinioController getFilesByPath start, entity is ", minioEntity.toString());
        List<MinioEntity> minioEntities = minioService.getFilesByPath(minioEntity);
        return checkResult(minioEntities);
    }

    @PostMapping("/getfilebypath")
    @ApiOperation("根据path查看单个文件")
    public HttpResponse<String> getFileByPath(@RequestBody MinioEntity minioEntity)
    {
        sendInfoLog("MinioController getFileByPath start, entity is ", minioEntity.toString());
        String link = minioService.getFileByPath(minioEntity);
        return checkResult(link);
    }

    @PostMapping("/addfile")
    @ApiOperation("添加文件")
    public HttpResponse<String> addFile(@RequestBody AddFileEntity addFileEntity) throws IOException
    {
        sendInfoLog("MinioController addFile start, entity is ", addFileEntity.toString());
        return checkResult(minioService.addFile(addFileEntity));
    }

    @PostMapping("/deletefiles")
    @ApiOperation("删除文件")
    public HttpResponse<String> deleteFiles(@RequestBody DeleteFileEntity deleteFileEntity)
    {
        sendInfoLog("MinioController deleteFiles start, entity is ", deleteFileEntity.toString());
        return checkResult(minioService.deleteFiles(deleteFileEntity));
    }

}
