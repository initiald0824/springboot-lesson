package com.spark.springbootlesson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

/**
 * @author initiald0824
 * @date 2019/7/2 19:38
 */
@Controller
public class UploadController {
    /**
     * 提取上传方法为公共方法
     * @param uploadDir 上传文件目录
     * @param file 上传对象
     * @throws Exception
     */
    private void executeUpload(String uploadDir, MultipartFile file) throws Exception {
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String filename = UUID.randomUUID() + suffix;
        File serverFile = new File(uploadDir + filename);
        file.transferTo(serverFile);
    }

    /**
     * 上传文件方法
     * @param request
     * @param file 前台上传的文件对象
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest request, MultipartFile file) {
        try {
            String uploadDir = request.getSession().getServletContext().getRealPath("/") + "upload/";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            executeUpload(uploadDir, file);
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }

    @RequestMapping(value = "/uploads", method = RequestMethod.POST)
    @ResponseBody
    public String uploads(HttpServletRequest request, MultipartFile[] file) {
        try {
            String uploadDir = request.getSession().getServletContext().getRealPath("/") + "upload/";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            for (int i = 0; i < file.length; i++) {
                if (file[i] != null) {
                    executeUpload(uploadDir, file[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }
}
