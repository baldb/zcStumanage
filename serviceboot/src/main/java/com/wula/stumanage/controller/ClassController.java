package com.wula.stumanage.controller;

/**
 * @author 林逸
 * cool boy
 * 1.0
 * <p>
 * 班级的增删改查
 */

/**
 * 班级的增删改查
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wula.stumanage.pojo.Teacher;
import com.wula.stumanage.pojo.ZcClass;
import com.wula.stumanage.pojo.combination.ClassAndTeach;
import com.wula.stumanage.pojo.utils.ClassSelection;
import com.wula.stumanage.pojo.utils.PageUtil;
import com.wula.stumanage.pojo.utils.ResCode;
import com.wula.stumanage.service.IStudentService;
import com.wula.stumanage.service.ITeacherService;
import com.wula.stumanage.service.ZcClassService;
import com.wula.stumanage.service.combination.IClassAndTeachService;
import com.wula.stumanage.service.utilservice.IResCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *  * /student
 *  * GET-获取用户
 *  * DELETE-删除用户
 *  * PUT-修改用户
 *  * POST-保存/添加用户
 */

@RestController
@RequestMapping("supers")
public class ClassController {
    @Autowired
    private IResCodeService resCodeService;

    @Autowired
    private ZcClassService zcClassService;

//    @Autowired
//    private IClassAndTeachService classAndTeachService;
//
//    @Autowired
//    private ITeacherService teacherService;


    @Autowired
    private IStudentService studentService;

    /**
     * 分页查询班级信息
     * @param pn
     * @param offset
     * @param name
     * @param response
     * @return
     */
    @GetMapping("/class")
    //ZcClass
    public ResCode<ClassAndTeach> getZcClass(@RequestParam(value = "pn", defaultValue = "0") int pn,
                                             @RequestParam(value = "offset", defaultValue = "0") int offset,
                                             @RequestParam(value = "name", required = false) String name,
                                             HttpServletResponse response) {
        return resCodeService.getClassPage(pn, offset, name, response);
    }


    /**
     * 添加
     * @param zcClass
     * @param response
     * @return
     */
    @PostMapping("/class")
    public ResCode addZcClass(@RequestBody ZcClass zcClass,
                              HttpServletResponse response) {
        return zcClassService.addOrUpClass(zcClass);
    }

    /**
     * 修改班级信息
     * @param zcClass
     * @param response
     * @return
     */
    @PutMapping("/class")
    public ResCode updateClass(@RequestBody ZcClass zcClass,
                               HttpServletResponse response) {
        return zcClassService.UpdateClass(zcClass);
    }

    /**
     * 根据ID删除班级
     * @param classId
     * @param response
     * @return
     */
    @DeleteMapping("/class")
    public ResCode delClass(@RequestParam("classId") Integer classId,
                            HttpServletResponse response) {
        return zcClassService.deleteClass(classId);
    }

    /**
     * 根据班级id查找该班的学生
     */
    @GetMapping("/getclassstu")
    ResCode getStuAllByClassId(@RequestParam("classId") Integer classId) {
        return studentService.selStudentByClassId(classId);
    }

    /**
     * 添加学生到该班级id为ID的班级中
     */
    @PostMapping("/addclassstu")
    public ResCode addStudentToClass(@RequestBody  ClassSelection<Integer> classSelection) {
        return studentService.addStudentForClass(classSelection);
    }

    /**
     * 根据学号移除该班学号为此学号的学生，将class_id设置为null
     */
    @GetMapping("/delclassstu")
    public ResCode delStudentToClass(@RequestParam("stuNo") Integer stuNo) {
        return studentService.delStudentForClass(stuNo);
    }

}
