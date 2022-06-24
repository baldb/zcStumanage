package com.wula.stumanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wula.stumanage.pojo.Course;
import com.wula.stumanage.pojo.Student;
import com.wula.stumanage.pojo.utils.ClassSelection;
import com.wula.stumanage.pojo.utils.CourseSelection;
import com.wula.stumanage.pojo.utils.ResCode;

import java.util.List;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
public interface IStudentService extends IService<Student> {
    /**
     * 根据学生姓名返回信息
     * @param name
     * @return
     */
    List<Student> getStuByName(String name);

    /**
     * 学生的选课系统
     * @param courseCourseSelection：封装了学号，选课信息
     * @return
     */
    ResCode courseSelection(CourseSelection<Course> courseCourseSelection);

    /**
     * 根据班级id查找该班的学生
     */
    ResCode selStudentByClassId(Integer classId);
    /**
     * 添加学生到该班级id为ID的班级中
     */
    ResCode addStudentForClass(ClassSelection<Integer> classSelection);

    /**
     * 根据学号移除该班学号为此学号的学生，将class_id设置为null
     */
    ResCode delStudentForClass(Integer stuNo);
}
