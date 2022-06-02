package com.wula.stumanage.pojo.utils;

import com.wula.stumanage.utils.FileNameUtil;
import lombok.Data;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Data
public class PictureUtil {
    private String Path="/Users/linyi/Desktop/zcStuMange/serviceboot/src/main/resources/images/";
    private String picName;

    public void setPicName(String pic) {
        this.picName = FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(pic);
        ;
    }
    public String getFilePath(){
        return Path+picName;
    }
}
