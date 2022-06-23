package com.wula.stumanage.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Data
@TableName("student")
public class Student {
    @TableId(value = "stu_no")
    private Integer stuNo;

    private String stuName;

    private String Sex;

    private String Phone;

    @TableField(updateStrategy = FieldStrategy.IGNORED) //设置可以为空
    private String Email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "",pattern = "yyyy-MM-dd")
    private Date Birth;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String Address;


    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String Pic;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Integer classId;

    /**
     * 入学日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "",pattern = "yyyy-MM-dd")
    private Date enroTime;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 修改日期
     */
    private Date updateTime;
}
