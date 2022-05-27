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
@Table(name = "student")
public class Student {
    @Id
    private Integer stuNo;

    private String stuName;

    @Column(name = "sex")
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
