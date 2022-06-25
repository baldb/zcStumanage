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
 * @TableName zc_class
 */
@TableName(value ="zc_class")
@Data
public class ZcClass implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer classId;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 班主任id
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
    private static final long serialVersionUID = 1L;
}