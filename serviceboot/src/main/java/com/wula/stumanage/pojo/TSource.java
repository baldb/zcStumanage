package com.wula.stumanage.pojo;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 成绩表
 */
@Data
@TableName("source")
public class TSource {
    @TableId(value = "stu_no",type = IdType.AUTO)
    private Integer stuNo;
//    @TableId(value = "course_id")
    private Integer courseId;
    @TableField(exist = false)
    private String courseName;
    private Float Score;
    private Date createTime;
    private Date updateTime;
}
