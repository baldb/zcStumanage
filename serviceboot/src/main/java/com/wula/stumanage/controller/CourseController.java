package com.wula.stumanage.controller;

import com.wula.stumanage.pojo.Course;
import com.wula.stumanage.pojo.utils.ResCode;
import com.wula.stumanage.service.CourseService;
import com.wula.stumanage.service.combination.ICourseAndTeachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */

/**
 * 课程表的正删改查
 *  *  * GET-获取用户
 *  *  * DELETE-删除用户
 *  *  * PUT-修改用户
 *  *  * POST-保存/添加用户
 */
@RestController
@RequestMapping("/supers")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private ICourseAndTeachService courseAndTeachService;

    /**
     * 查询课程的分页信息
     * @param pn
     * @param offset
     * @param name
     * @param response
     * @return
     */
    @GetMapping("/course")
    public ResCode getCourseAndTeach(@RequestParam(value = "pn",defaultValue = "0") int pn,
                                     @RequestParam(value = "offset",defaultValue = "0")int offset,
                                     @RequestParam(value = "name",required = false)String name,
                                     HttpServletResponse response){
        return courseAndTeachService.getPageCT(pn, offset, name, response);
    }

    /**
     * 添加课程信息
     * @param course
     * @return
     */
    @PostMapping("/course")
    ResCode addCourse(@RequestBody Course course){

        return courseService.addCourseT(course);
    }


    @PutMapping("/course")
    ResCode updateCourse(@RequestBody Course course){
        return courseService.updateCourseT(course);
    }

    @DeleteMapping("/course")
    ResCode deleteCourse(@RequestParam("courseId") Integer courseId){
        return courseService.delCourseT(courseId);
    }
}
