package com.wula.stumanage.controller;

import com.wula.stumanage.pojo.Student;
import com.wula.stumanage.pojo.utils.ResCode;
import com.wula.stumanage.service.IStudentService;
import com.wula.stumanage.service.IUserService;
import com.wula.stumanage.service.utilservice.IResCodeService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */

/**
 *  * /student
 *  * GET-获取用户
 *  * DELETE-删除用户
 *  * PUT-修改用户
 *  * POST-保存/添加用户
 */
@SuppressWarnings({"all"})
@RestController
@RequestMapping("/supers")
@Slf4j
public class SuperUserController {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IResCodeService resCodeService;

    @Autowired
    private IUserService userService;

    /**
     * 根据页数返回学生信息
     * 诺没有返回页数和页面输出几条数据的数据  则返回学生全部信息
     * @param pn
     * @param response
     * @return
     */
    @GetMapping("/super")
    public ResCode getStu(@RequestParam(value = "pn",defaultValue = "0") int pn ,
                          @RequestParam(value = "offset",defaultValue = "0") int offset ,
                          HttpServletResponse response){
            return resCodeService.getPageOnCode(pn,offset, response);

    }
    /**
     * 上传图片。返回图片名
     */
    @PostMapping("/upload")
    public ResCode upPic(@RequestPart("headerImg") MultipartFile headerImg,
                         HttpServletResponse response){
            log.info("headerImg={}",headerImg.getSize(),headerImg.getName());

            return resCodeService.uploadPic(headerImg,response);
    }
    /**
     * 添加学生信息
     * 同时添加把学生添加到User表中
     */
//    前端使用 x-www-form-urlencoded 传数据
//    @PostMapping("/super")
//    @ResponseBody
//    public ResCode addStu(Student student,
//                          HttpServletResponse response){
//        System.out.println(student);
//            return resCodeService.addStuAndUser(student,response);
//    }
//    前端使用json传数据
    @PostMapping("/super")
    @ResponseBody
    public ResCode addStutes(@RequestBody Student student,
                          HttpServletResponse response){
        return resCodeService.addStuAndUser(student,response);
    }

    /**
     * 删除学生信息
     */
    @DeleteMapping("/super")
    public ResCode delete(@RequestParam("stuNo") Integer stuNo){
        boolean b = studentService.removeById(stuNo);
        ResCode resCode = new ResCode();
        resCode.CodeAll(b,null);
        return resCode;
    }
    /**
     * 修改学生信息
     */
    @PutMapping("/super")
    @ResponseBody
    public ResCode putStu(@RequestBody Student student){
        boolean b = studentService.updateById(student);
        ResCode<Student> studentResCode = new ResCode<>();
        Student byId = studentService.getById(student.getStuNo());
        studentResCode.CodeAll(b,byId);
        return studentResCode;
    }
}
