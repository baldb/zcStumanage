package com.wula.stumanage.pojo.votest;

import com.wula.stumanage.pojo.Student;
import lombok.Data;

import java.util.Date;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Data
public class StuAndSou  extends Student {
    private Integer stuNo;
    private Integer courseId;
    private Float Source;
    private Date createTime;
    private Date updateTime;
}
