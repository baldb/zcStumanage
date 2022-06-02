package com.wula.stumanage.utils;

import com.wula.stumanage.pojo.utils.PictureUtil;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Data
public class UploadPhotoUtil {
    private PictureUtil pictureUtil;
    private String picName;
    public  void uploadPhoto(MultipartFile headerImg){
        if(!headerImg.isEmpty()){
            //保存到文件服务器，OSS对象存储服务器
//            String originalFilename = headerImg.getOriginalFilename();//原始文件名
            //1. 提取生成文件名 uuid + 上传图片的后缀
            pictureUtil=new PictureUtil();
            try {
                pictureUtil.setPicName(headerImg.getOriginalFilename());//将文件名UUID后与路径拼接起来存储
                picName=pictureUtil.getPicName();
                headerImg.transferTo(new File(pictureUtil.getFilePath())); //实现文件上传功能，原理
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
