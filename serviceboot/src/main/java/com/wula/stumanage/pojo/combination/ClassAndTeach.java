package com.wula.stumanage.pojo.combination;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wula.stumanage.pojo.ZcClass;
import lombok.Data;

import java.util.List;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Data
@TableName("zc_class")
public class ClassAndTeach extends ZcClass {
    @TableField(exist = false)
    private Object teachMessage;
    /**
     * 班级名称
     */
    private String className;
}
