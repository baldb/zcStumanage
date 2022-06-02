package com.wula.stumanage;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wula.stumanage.mapper.ISourceMapper;
import com.wula.stumanage.mapper.IStudentAndSourceMapper;
import com.wula.stumanage.pojo.stuAndsou.StudentAndSource;
import com.wula.stumanage.pojo.TSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */

@SpringBootTest
public class MPTest {

    @Autowired
    private ISourceMapper iSourceMapper;

    @Autowired
    private IStudentAndSourceMapper studentMapper;

    @Test
    public void test(){
        Integer no=212106601;
        LambdaQueryWrapper<TSource> tSourceQueryWrapper = new LambdaQueryWrapper<>();
        tSourceQueryWrapper.like(no!=null,TSource::getStuNo,no);
        List<TSource> tSources = iSourceMapper.selectList(tSourceQueryWrapper);
        System.out.println(tSources);

        StudentAndSource studentAndSource = studentMapper.selectById(no);
        studentAndSource.setTSource(tSources);
        System.out.println(studentAndSource);
    }


    @Test
    public void test2(){
        IPage<StudentAndSource> stuInfoPage = new Page<>(4,1);
        IPage<StudentAndSource> iPage1 = studentMapper.selectPage(stuInfoPage,null); //分页后查找的数据
        iPage1.getCurrent();//获取当前输出的是第几页 1
        iPage1.getSize();//获取每页存放的数据有几条
        iPage1.getPages();//一共有多少页
        iPage1.getRecords();//获取查询出来的数据
        System.out.println(iPage1.getRecords());
    }
}
