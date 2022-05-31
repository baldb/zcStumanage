package com.wula.stumanage.pojo;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 课程表
 */
@Data
@TableName("course")
public class TCourse {
    @TableId(value = "course_id",type = IdType.AUTO)
    private Integer courseId;
    private String courseName;
    private Integer techNo;
    private Date createTime;
    private Date updateTime;

}
