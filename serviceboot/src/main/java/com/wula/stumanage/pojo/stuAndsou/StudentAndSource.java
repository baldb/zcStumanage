package com.wula.stumanage.pojo.stuAndsou;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wula.stumanage.pojo.Student;
import com.wula.stumanage.pojo.TSource;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
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
