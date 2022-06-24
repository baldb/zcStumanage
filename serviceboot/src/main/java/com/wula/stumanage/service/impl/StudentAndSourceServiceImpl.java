package com.wula.stumanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wula.stumanage.mapper.ICourseMapper;
import com.wula.stumanage.mapper.ISourceMapper;
import com.wula.stumanage.mapper.combination.IStudentAndSourceMapper;
import com.wula.stumanage.pojo.TCourse;
import com.wula.stumanage.pojo.combination.StudentAndSource;
import com.wula.stumanage.pojo.TSource;
import com.wula.stumanage.service.IStudentAndSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Service
public class StudentAndSourceServiceImpl extends ServiceImpl<IStudentAndSourceMapper, StudentAndSource> implements IStudentAndSourceService {
    @Autowired
    private IStudentAndSourceMapper studentAndSourceMapper;

    @Autowired
    private ISourceMapper sourceMapper;

    @Autowired
    private ICourseMapper courseMapper;
    @Override
    public StudentAndSource getStuAndSouByNo(Integer no) {
        //根据学号查询信息，返回学生基本信息和成绩
        StudentAndSource studentAndSource = studentAndSourceMapper.selectById(no);

        //按照条件查新成绩表里该学号学生的各科成绩
        LambdaQueryWrapper<TSource> tSourceQueryWrapper = new LambdaQueryWrapper<>();
        tSourceQueryWrapper.like(no!=null,TSource::getStuNo,no);
        List<TSource> tSources = sourceMapper.selectList(tSourceQueryWrapper);

        for (TSource tSource :tSources) {
            Integer courseId = tSource.getCourseId();   //获取选课/课程id
            TCourse tCourse = courseMapper.selectById(courseId);    //根据课程ID查找课程信息
            tSource.setCourseName(tCourse.getCourseName()); //将课程名添加到数据中
        }

        studentAndSource.setTSource(tSources);  //添加到返回数据列表中
        return studentAndSource;
    }
}
