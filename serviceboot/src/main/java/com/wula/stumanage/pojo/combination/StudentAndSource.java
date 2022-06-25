package com.wula.stumanage.pojo.combination;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wula.stumanage.pojo.Student;
import com.wula.stumanage.pojo.TSource;
import lombok.Data;

import java.util.List;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Data
@TableName("student")
public class StudentAndSource extends Student {

    @TableField(exist = false)
    private List<TSource> tSource;
}
