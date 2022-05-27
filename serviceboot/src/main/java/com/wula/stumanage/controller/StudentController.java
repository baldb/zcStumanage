package com.wula.stumanage.controller;

import com.wula.stumanage.pojo.Student;
import com.wula.stumanage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
/**
 * /student
 * GET-获取用户
 * DELETE-删除用户
 * PUT-修改用户
 * POST-保存/添加用户
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 查询所有学生数据
     * @return
     */
    @GetMapping("/stu")
    public List<Student> getAll(){
        return studentService.getAll();
    }

    /**
     * 添加学生信息
     * @param student
     * @return
     */
    @PostMapping("/stu")
    public String addForStu(@RequestBody Student student){
        int i = studentService.addStu(student);
        String msg="";
        msg=i>0?"添加成功":"添加失败";
        return msg;
    }
    @DeleteMapping("/stu/{id}")
    public String delStu(@PathVariable("id")Integer id){
        int i = studentService.deleteStu(id);
        String msg="";
        msg=i>0?"删除成功":"删除失败";
        return msg;
    }
}
