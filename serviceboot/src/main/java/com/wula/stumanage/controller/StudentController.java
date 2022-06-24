package com.wula.stumanage.controller;

import com.wula.stumanage.mapper.CourseMapper;
import com.wula.stumanage.pojo.Course;
import com.wula.stumanage.pojo.Student;
import com.wula.stumanage.pojo.utils.CourseSelection;
import com.wula.stumanage.pojo.utils.ResCode;
import com.wula.stumanage.service.CourseService;
import com.wula.stumanage.service.ISourceService;
import com.wula.stumanage.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */

/**
 * 学生端的增删改查（只针对自己的基本信息）
 */
@RestController
@RequestMapping("/stus")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ISourceService sourceService;
    /**
     * 根据学生ID查询基本信息,在loginController中
     */

    /**
     * 根据学号查询目前选课信息
     */
    @GetMapping("/stu/course")
    ResCode courseByNO(@RequestParam("stuId")Integer stuId){
        return courseService.getCourseByNo(stuId);
    }

    /**
     * 显示没有被选上的课
     */
    @GetMapping("/stu/courseNo")
    ResCode NotcourseByNO(@RequestParam("stuId")Integer stuId){
        return courseService.getNotCourseByNo(stuId);
    }

    /**
     * 选课信息
     */
    @PostMapping("/stu/course")
    public ResCode courseSelection(@RequestBody CourseSelection<Integer> courseSelection){
        return sourceService.addByStuAndCouid(courseSelection.getStuId(),courseSelection.getCourseList());
    }
}
