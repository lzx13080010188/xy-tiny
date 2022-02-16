package com.xr.tiny.common.minio;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.util.List;

/**
 * @author : lzx
 * @version : V1.0
 * @date : 2022/2/13 下午6:18
 * @description :
 */
@RestController
@Slf4j
@RequestMapping("/minio")
public class MinloController {


    @Value("${minio.bucketName}")
    private String bucketName;

    @Value("${minio.endpoint}")
    private String address;


    @Autowired
    private MinioUtils minIoUtil;




    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public String  upload(MultipartFile multipartFile) {
        List<String> upload = minIoUtil.upload(new MultipartFile[]{multipartFile});
        return address+"/"+bucketName+"/"+upload.get(0);
    }


    @ApiOperation(value = "文件下载")
    @GetMapping("/download")
    public void  upload(String filename,HttpServletResponse response) {
        ResponseEntity<byte[]> download = minIoUtil.download(filename);
        response.setCharacterEncoding("utf-8");
        //设置响应的内容类型
        response.setContentType("text/plain");
        //设置文件的名称和格式
        response.addHeader("Content-Disposition","attachment;filename="
                + filename);
        BufferedOutputStream buff = null;
        ServletOutputStream outStr = null;
        try {
            outStr = response.getOutputStream();
            buff = new BufferedOutputStream(outStr);
            buff.write(download.getBody());
            buff.flush();
            buff.close();
        } catch (Exception e) {
            log.error("导出文件文件出错:{}",e);
        } finally {try {
            buff.close();
            outStr.close();
        } catch (Exception e) {
            log.error("关闭流对象出错 e:{}",e);
        }
        }

    }


}
