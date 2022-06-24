package com.wula.stumanage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName course
 */
@TableName(value ="course")
@Data
public class Course implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer courseId;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 授课老师
     */
    private Integer techNo;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    @TableField(exist = false)
    private Teacher teacher;

    @TableField(exist = false)
    private TSource tSource;
    @TableField(exist = false)
    private Student tStudent;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}