package com.wula.stumanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wula.stumanage.mapper.ICourseMapper;
import com.wula.stumanage.mapper.ITeacherMapper;
import com.wula.stumanage.pojo.Course;
import com.wula.stumanage.pojo.Teacher;
import com.wula.stumanage.pojo.utils.ResCode;
import com.wula.stumanage.service.CourseService;
import com.wula.stumanage.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author linyi
* @description 针对表【course】的数据库操作Service实现
* @createDate 2022-06-23 20:05:05
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ITeacherMapper teacherMapper;
    @Override
    public ResCode addCourseT(Course course) {
        ResCode<Course> courseResCode = new ResCode<>();
        LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        teacherLambdaQueryWrapper.eq(Teacher::getTeachNo,course.getTechNo());
        Integer integer = teacherMapper.selectCount(teacherLambdaQueryWrapper);
        if(integer>0) {
            int insert = courseMapper.insert(course);
            courseResCode.setMsg("添加成功！");
            courseResCode.setStatus(200);
        }else {
            courseResCode.setMsg("添加失败！老师不存在");
            courseResCode.setStatus(403);
        }
        return courseResCode;
    }

    @Override
    public ResCode updateCourseT(Course course) {
        ResCode<Course> courseResCode = new ResCode<>();
        LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        teacherLambdaQueryWrapper.eq(Teacher::getTeachNo,course.getTechNo());
        Integer integer = teacherMapper.selectCount(teacherLambdaQueryWrapper);
        if(integer>0) {
            int i = courseMapper.updateById(course);
            courseResCode.setMsg("修改成功！");
            courseResCode.setStatus(200);
        }else {
            courseResCode.setMsg("修改失败！老师不存在");
            courseResCode.setStatus(403);
        }
        return courseResCode;
    }

    @Override
    public ResCode delCourseT(Integer id) {
        ResCode<Course> courseResCode = new ResCode<>();
        int i = courseMapper.deleteById(id);
        if(i>0){
            courseResCode.setStatus(200);
            courseResCode.setMsg("删除成功");
        }else {
            courseResCode.setStatus(403);
            courseResCode.setMsg("删除失败");
        }
        return  courseResCode;
    }

    @Override
    public ResCode getCourseByNo(Integer stuid) {
        List<Course> byStuNo = courseMapper.getByStuNo(stuid);
        ResCode courseResCode = new ResCode<>();
        if(byStuNo.size()>0) {
            courseResCode.setResultSet(byStuNo);
            courseResCode.setStatus(200);
            courseResCode.setMsg("已经选课：" + byStuNo.size());
        }else {
            courseResCode.setResultSet(null);
            courseResCode.setStatus(200);
            courseResCode.setMsg("可添加五门课程");
        }
        return courseResCode;
    }

    @Override
    public ResCode getNotCourseByNo(Integer stuid) {
        List<Course> courseNotSelection = courseMapper.getCourseNotSelection(stuid);
        ResCode courseResCode = new ResCode<>();
        if(courseNotSelection.size()>0) {
            courseResCode.setResultSet(courseNotSelection);
            courseResCode.setStatus(200);
            courseResCode.setMsg("还可以选的课数：" + courseNotSelection.size());
        }else {
            courseResCode.setResultSet(null);
            courseResCode.setStatus(200);
            courseResCode.setMsg("课程较少，没得选");
        }
        return courseResCode;
    }
}




