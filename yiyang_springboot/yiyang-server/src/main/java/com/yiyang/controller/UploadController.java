package com.yiyang.controller;

import com.yiyang.exception.BizException;
import com.yiyang.pojo.vo.Result;
import com.yiyang.utils.AliyunOSSUtils;
import com.yiyang.utils.BizCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/upload")
@Tag(name = "UploadController", description = "文件上传")
public class UploadController {
    @PostMapping
    public Result<String> upload(@RequestParam("file") MultipartFile multipartFile) {
        String filename = multipartFile.getOriginalFilename();
        // 获取文件的扩展名 .jpg .mp3 .mp4 .....
        String ext = filename.substring(filename.lastIndexOf("."));
        // 获取文件名
        filename = filename.substring(0, filename.lastIndexOf("."));
        // 处理文件重名问题，源文件名和系统时间组合使用
        filename = filename + "_" + System.currentTimeMillis() + ext;
        try {
            InputStream in = multipartFile.getInputStream();
            String uploadPath = AliyunOSSUtils.uploadFile(in, filename);
            return Result.success(BizCode.SUCCESS, uploadPath);
        } catch (IOException e) {
            throw new BizException(BizCode.FILE_UPLOAD_ERROR);
        }
    }
}
