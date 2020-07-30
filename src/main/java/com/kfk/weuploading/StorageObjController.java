package com.kfk.weuploading;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/upload")
@EnableAutoConfiguration
@Api(value="文件上传", tags="文件上传模块")
public class StorageObjController {
//    @Value("${ali.accessKeyId}")
//    private String keyId;
//    @Value("${ali.secret}")
//    private String secret;
//    @Value("${ali.endpoint}")
//    private String endpoint;
//    @Value("${ali.bucketName}")
    private String bucketName = "intern-java-test";

    private String urlPrefix = "http://127.0.0.1:8080/";
    @ApiOperation(value="文件上传接口")
    @PostMapping("/storageObj")
    public String storageObj(@RequestParam MultipartFile file, @RequestParam Integer type) throws Exception {
        //目前这里是写死的本地硬盘路径
        String path = "D:/img";
//        logger.info("path:" + path);
        //获取文件名称
        String fileName = file.getOriginalFilename();
        //获取文件名后缀
        Calendar currTime = Calendar.getInstance();
        String time = String.valueOf(currTime.get(Calendar.YEAR))+String.valueOf((currTime.get(Calendar.MONTH)+1));
        //获取文件名后缀
        String suffix = fileName.substring(file.getOriginalFilename().lastIndexOf("."));
        suffix = suffix.toLowerCase();
//        if(suffix.equals(".jpg") || suffix.equals(".jpeg") || suffix.equals(".png")/* || suffix.equals(".gif")*/){
            fileName = UUID.randomUUID().toString()+suffix;
            File targetFile = new File(path, fileName);
            if(!targetFile.getParentFile().exists()){    //注意，判断父级路径是否存在
                targetFile.getParentFile().mkdirs();
            }
//            long size = 0;
            //保存
            try {
                file.transferTo(targetFile);
//                size = file.getSize();
            } catch (Exception e) {
                e.printStackTrace();
//                return R.error("上传失败！");
            }
            //项目url，这里可以使用常量或者去数据字典获取相应的url前缀；
//            String fileUrl="http://localhost:8080";
//            //文件获取路径
//            fileUrl = fileUrl + "/img/" + fileName;
            return "上传成功";


    }

}