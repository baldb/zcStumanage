package com.wula.stumanage.service.combination.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wula.stumanage.mapper.combination.ICourseAndTeachMapper;
import com.wula.stumanage.pojo.Teacher;
import com.wula.stumanage.pojo.combination.ClassAndTeach;
import com.wula.stumanage.pojo.combination.CourseAndTeach;
import com.wula.stumanage.pojo.utils.PageUtil;
import com.wula.stumanage.pojo.utils.ResCode;
import com.wula.stumanage.service.CourseService;
import com.wula.stumanage.service.ITeacherService;
import com.wula.stumanage.service.combination.ICourseAndTeachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */

@Service
public class ICourseAndTeachServiceImpl extends ServiceImpl<ICourseAndTeachMapper, CourseAndTeach> implements ICourseAndTeachService {

    @Autowired
    private CourseService courseService;
    @Autowired
    private ICourseAndTeachService courseAndTeachService;
    @Autowired
    private ITeacherService teacherService;

    @Override
    public ResCode getPageCT(int pn, int offset, String name, HttpServletResponse response) {
        ResCode<CourseAndTeach> resCode = new ResCode<>();
        PageUtil<CourseAndTeach> courseAndTeachPageUtil = new PageUtil<>();
        QueryWrapper<CourseAndTeach> qwl = new QueryWrapper<>();
        qwl.orderByDesc("create_time");
        if(pn!=0 || offset!=0 || name!=null){
            if(name==null){ //课程名为空
                if(offset!=0)
                    courseAndTeachPageUtil.setOffSet(offset);
                Page<CourseAndTeach> courseAndTeachPage = new Page<>(pn, courseAndTeachPageUtil.getOffSet());
                Page<CourseAndTeach> page = courseAndTeachService.page(courseAndTeachPage, qwl);
                if(page.getRecords().size()>0){
                    for (CourseAndTeach courseAndTeach :page.getRecords()) {
                        LambdaQueryWrapper<Teacher> teacherQueryWrapper = new LambdaQueryWrapper<>();
                        Teacher teacher = teacherService.getOne(teacherQueryWrapper.select(Teacher::getTeachNo, Teacher::getTeachName)
                                .eq(Teacher::getTeachNo, courseAndTeach.getTechNo()));
                        courseAndTeach.setTeachMessage(teacher);
                    }
                    resCode.setMsg("success");
                    resCode.setPage(page);
                    resCode.setStatus(200);
                }else{
                    resCode.setMsg("没有数据，请先添加数据");
                    response.setStatus(403);
                    resCode.setStatus(response.getStatus());
                    resCode.setResultSet(null);
                }
            }else { //课程名不为空
                if(offset!=0)
                    courseAndTeachPageUtil.setOffSet(offset);
                Page<CourseAndTeach> page = new Page<>(pn,courseAndTeachPageUtil.getOffSet());
                LambdaQueryWrapper<CourseAndTeach> classAndTeachLambdaQueryWrapper = new LambdaQueryWrapper<>();
                classAndTeachLambdaQueryWrapper.eq(CourseAndTeach::getCourseName,name);
                CourseAndTeach one = courseAndTeachService.getOne(classAndTeachLambdaQueryWrapper);
                if(one!=null) {
                    LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    teacherLambdaQueryWrapper.eq(Teacher::getTeachNo, one.getTechNo());
                    one.setTeachMessage(teacherService.getOne(teacherLambdaQueryWrapper));
                    resCode.setMsg("success");
                    resCode.setResultSet(one);
                    resCode.setStatus(200);
                }else{
                    resCode.setMsg("没有此班级");
                    resCode.setResultSet(null);
                    resCode.setStatus(403);
                }
            }
        }
        return resCode;
    }
}
