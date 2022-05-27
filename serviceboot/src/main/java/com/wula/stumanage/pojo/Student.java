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
@TableName("student")
public class Student {

    @TableId(value = "stu_no",type = IdType.AUTO)
    private Integer stuNo;

    private String stuName;

    private String Sex;

    private Integer Phone;

    private String Email;

    private Date Birth;

    private String Address;

    private String Pic;

    private Integer classId;
    /**
     * 入学日期
     */
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
