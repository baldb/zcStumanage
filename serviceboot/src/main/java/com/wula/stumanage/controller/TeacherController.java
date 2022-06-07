package com.wula.stumanage.controller;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */

import com.wula.stumanage.pojo.Teacher;
import com.wula.stumanage.pojo.utils.ResCode;
import com.wula.stumanage.service.ITeacherService;
import com.wula.stumanage.service.utilservice.IResCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 *  * /student
 *  * GET-获取用户
 *  * DELETE-删除用户
 *  * PUT-修改用户
 *  * POST-保存/添加用户
 */
@RestController
@RequestMapping("/supers")
public class TeacherController {
    @Autowired
    private IResCodeService resCodeService;
    @Autowired
    private ITeacherService teacherService;
    /**
     * 获取老师的所有信息
     * 及其
     * 根据老师姓名查询老师信息
     * required = false:表示不一定要有参数，没有参数时默认为null
     */
    @GetMapping("/teacher")
    public ResCode getAllTeacher(@RequestParam(value = "pn",defaultValue = "0") int pn,
                                 @RequestParam(value = "offset",defaultValue = "0")int offset,
                                 @RequestParam(value = "name",required = false)String name,
                                 HttpServletResponse response){

        return resCodeService.getPageForTeacher(pn,offset,name,response);
    }

    /**
     * 添加老师功能
     */
    @PostMapping("/teacher")
    public ResCode addTeacher(@RequestBody Teacher teacher,
                              HttpServletResponse response){
        return resCodeService.addForTeacher(teacher,response);
    }

    /**
     * 修改老师信息功能
     */
    @PutMapping("/teacher")
    public ResCode updateTeacher(@RequestBody Teacher teacher,
                                 HttpServletResponse response){
        boolean b = teacherService.updateById(teacher);
        ResCode<Object> objectResCode = new ResCode<>();
        objectResCode.CodeAll(b,null);
        return objectResCode;
    }
    /**
     * 删除老师信息
     */
    @DeleteMapping("/teacher")
    public ResCode delTeacher(@RequestParam("id") Integer id,
                              HttpServletResponse response){
        return resCodeService.delTeacherById(id);
    }
}
