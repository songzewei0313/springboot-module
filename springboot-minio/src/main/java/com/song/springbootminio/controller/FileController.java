package com.song.springbootminio.controller;

import com.song.springbootminio.utils.MinIoUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author szw
 * @date 2022/6/2 14:03
 */
@Api(tags = "文件操作")
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private MinIoUtils minIoUtils;
    @Value("${minio.endpoint}")
    private String address;
    @Value("${minio.bucketName}")
    private String bucketName;

    @ApiOperation("minio上传文件")
    @PostMapping("/upload")
    public Object upload(MultipartFile file) {

        List<String> upload = minIoUtils.upload(new MultipartFile[]{file});

        return address + "/" + bucketName + "/" + upload.get(0);
    }
}
