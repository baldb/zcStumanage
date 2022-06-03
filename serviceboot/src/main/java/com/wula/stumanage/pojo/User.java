package com.wula.stumanage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("user")
public class User {

    @TableId(value = "uid",type = IdType.AUTO)
    private Integer Uid;
    private String Password;
    @TableField("username")
    private String userName;
    private String identity;
    private Date createTime;
    private Date updateTime;



}
