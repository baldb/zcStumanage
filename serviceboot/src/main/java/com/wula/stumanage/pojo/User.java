package com.wula.stumanage.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Data
@Table(name = "user")
public class User {
    @Id
    private Integer Uid;
    private String Password;
    @Column(name = "username")
    private String userName;
    private String identity;
    private Date createTime;
    private Date updateTime;


}
