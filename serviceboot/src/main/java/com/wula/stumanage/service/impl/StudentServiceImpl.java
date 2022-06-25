package com.wula.stumanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wula.stumanage.mapper.IStudentMapper;
import com.wula.stumanage.pojo.Course;
import com.wula.stumanage.pojo.Student;
import com.wula.stumanage.pojo.TSource;
import com.wula.stumanage.pojo.utils.ClassSelection;
import com.wula.stumanage.pojo.utils.CourseSelection;
import com.wula.stumanage.pojo.utils.ResCode;
import com.wula.stumanage.service.CourseService;
import com.wula.stumanage.service.ISourceService;
import com.wula.stumanage.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Service
public class StudentServiceImpl extends ServiceImpl<IStudentMapper, Student> implements IStudentService {
    @Autowired
    private IStudentMapper studentMapper;
    @Override
    public List<Student> getStuByName(String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("stu_name",name);
       return studentMapper.selectByMap(map);
    }


    @Autowired
    private CourseService courseService;
    @Autowired
    private ISourceService sourceService;
    @Override
    public ResCode courseSelection(CourseSelection<Course> courseCourseSelection) {
        ResCode<Object> resCode = new ResCode<>();
        LambdaQueryWrapper<TSource> lambdaQueryWrapper01 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper01.eq(TSource::getStuNo,courseCourseSelection.getStuId());
        int count = sourceService.count(lambdaQueryWrapper01);
        if(count>5){
            resCode.setStatus(200);
            resCode.setMsg("选课超过5门，不能选择");
            resCode.setResultSet(null);
        }else{

        }
        return resCode;
    }

    @Override
    public ResCode selStudentByClassId(Integer classId) {
        ResCode<Object> resCode = new ResCode<>();
        List<Student> stuByClassId = studentMapper.getStuByClassId(classId);
        if(stuByClassId.size()>0){
            resCode.setStatus(200);
            resCode.setMsg("查询成功！");
            resCode.setResultSet(stuByClassId);
        }else {
            resCode.setStatus(200);
            resCode.setMsg("查询失败！，没有此班级或该班未有学生");
            resCode.setResultSet(stuByClassId);
        }
        return resCode;
    }

    @Override
    public ResCode addStudentForClass(ClassSelection<Integer> classSelection) {
        for (Integer integer : classSelection.getStuNoList()) {
            UpdateWrapper<Student> studentUpdateWrapper = new UpdateWrapper<>();
            studentUpdateWrapper.like("stu_no",integer);
            studentUpdateWrapper.set("class_id",classSelection.getClassId());
            studentMapper.update(null,studentUpdateWrapper);
        }
        ResCode<Object> resCode = new ResCode<>();
        resCode.setStatus(200);
        resCode.setMsg("班级添加学生成功！");
        resCode.setResultSet(null);
        return resCode;
    }

    @Override
    public ResCode delStudentForClass(Integer stuNo) {
        UpdateWrapper<Student> studentUpdateWrapper = new UpdateWrapper<>();
        studentUpdateWrapper.like("stu_no",stuNo);
        studentUpdateWrapper.set("class_id",null);
        studentMapper.update(null,studentUpdateWrapper);
        ResCode<Object> resCode = new ResCode<>();
        resCode.setStatus(200);
        resCode.setMsg("移除学生成功！");
        resCode.setResultSet(null);
        return resCode;
    }

    @Override
    public ResCode getStuByCourseId(Integer courseId) {
        ResCode<Object> resCode = new ResCode<>();
        List<Student> stuByCourseId = studentMapper.getStuByCourseId(courseId);
        if(stuByCourseId.size()>0){
            resCode.setStatus(200);
            resCode.setMsg("success");
            resCode.setResultSet(stuByCourseId);
        }else {
            resCode.setStatus(200);
            resCode.setMsg("该课程还未有学生选择");
            resCode.setResultSet(stuByCourseId);
        }
        return resCode;
    }
}
