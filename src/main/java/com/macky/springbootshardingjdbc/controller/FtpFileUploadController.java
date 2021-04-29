package com.macky.springbootshardingjdbc.controller;

import com.macky.springbootshardingjdbc.util.FtpFileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by nishuai on 2017/12/26.
 */
@CrossOrigin
@Controller
public class FtpFileUploadController {

    //ftp处理文件上传
    @PostMapping(value = "/ftpuploadimg")
    public @ResponseBody
    String uploadImg(@RequestParam("file") MultipartFile file,
                     HttpServletRequest request) throws IOException {

        String fileName = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        String filePath = null;


        Boolean flag = FtpFileUtil.uploadFile(fileName, inputStream);
        if (flag == true) {
            System.out.println("ftp上传成功！");
            filePath = fileName;
        }


        return filePath;
        // 该路径图片名称，前端框架可用ngnix指定的路径+filePath,即可访问到ngnix图片服务器中的图片
    }


    public static void main(String[] args) {
        try {
            File file = new File("D:\\xml\\CN_WLJK_ERTA_1p0_2311071028094_20210212143817313.xml");
            InputStream is = new FileInputStream(file);
            String images = upload("CN_WLJK_ERTA_1p0_2311071028094_20210212143817313.xml", is);
            System.out.println(images);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param fileName
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static String upload(String fileName, InputStream inputStream) throws IOException {
        String filePath = null;

        Boolean flag = FtpFileUtil.uploadFile(fileName, inputStream);
        if (flag == true) {
            System.out.println("ftp上传成功！");
            filePath = fileName;
        }

        return filePath;
    }
}
