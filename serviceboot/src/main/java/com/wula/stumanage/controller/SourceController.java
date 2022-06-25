package com.wula.stumanage.controller;

import com.wula.stumanage.pojo.combination.StudentAndSource;
import com.wula.stumanage.pojo.utils.ResCode;
import com.wula.stumanage.service.IStudentAndSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */

/**
 * /student
 *  *  * GET-获取用户
 *  *  * DELETE-删除用户
 *  *  * PUT-修改用户
 *  *  * POST-保存/添加用户
 */
@RestController
@RequestMapping("/sources")
public class SourceController {

    @Autowired
    private IStudentAndSourceService studentAndSourceService;
    @GetMapping("/source")
    public ResCode test(@RequestParam("no") Integer no){
        StudentAndSource stuAndSouByNo = studentAndSourceService.getStuAndSouByNo(no);
        ResCode<StudentAndSource> studentAndSourceResCode = new ResCode<>();
        studentAndSourceResCode.CodeAll(stuAndSouByNo!=null,stuAndSouByNo);
        return studentAndSourceResCode;
    }

    /**
     *
     */
}
