package com.wula.stumanage.pojo.combination;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wula.stumanage.pojo.Course;
import lombok.Data;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Data
@TableName("course")
public class CourseAndTeach extends Course {
    @TableField(exist = false)
    private Object teachMessage;

    /**
     * 课程名
     */
    private String courseName;
    private Integer techNo;
}
