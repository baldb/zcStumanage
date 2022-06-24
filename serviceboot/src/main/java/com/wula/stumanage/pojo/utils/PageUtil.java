package com.wula.stumanage.pojo.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wula.stumanage.pojo.Student;
import com.wula.stumanage.pojo.ZcClass;
import com.wula.stumanage.service.IStudentService;
import com.wula.stumanage.service.ITeacherService;
import com.wula.stumanage.service.ZcClassService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Data
public class PageUtil<T> {

    //一共有几页
    private Long Pages;

    //一共有多少条条数据
    private int total;

    //当前输出的是第几页
    private Long page;

    //默认每页输出10条数据
    private int offSet = 10;


    //查询结果
    private List<T> Records;

}
