package com.wula.stumanage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Data
@TableName("teacher")
public class Teacher {
    @TableId(value = "teach_no",type = IdType.AUTO)
    private long teachNo;
    private String Sex;
    private Integer Age;
    private String teachName;
    private String teachPic;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 修改日期
     */
    private Date updateTime;

}
