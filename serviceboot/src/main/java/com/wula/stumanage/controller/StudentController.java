package com.wula.stumanage.controller;

import com.wula.stumanage.pojo.Student;
import com.wula.stumanage.pojo.User;
import com.wula.stumanage.pojo.stuAndsou.StudentAndSource;
import com.wula.stumanage.pojo.utils.ResCode;
import com.wula.stumanage.service.IStudentAndSourceService;
import com.wula.stumanage.service.IStudentService;
import com.wula.stumanage.service.IUserService;
import com.wula.stumanage.service.utilservice.IResCodeService;
import com.wula.stumanage.utils.UploadPhotoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

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
@RequestMapping("/stus")
@Slf4j
public class StudentController {

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
    @GetMapping("/stu")
    public ResCode getStu(@RequestParam(value = "pn",defaultValue = "0") int pn ,
                      @RequestParam(value = "offset",defaultValue = "0") int offset ,
                      HttpServletResponse response){
        return resCodeService.getPageOnCode(pn,offset, response);
    }

//    /**
//     * 返回学生全部信息
//     */
//    @GetMapping("/getstu")
//    public Map getStu02(HttpServletResponse response) {
//        return resCodeService.getAllStudent(response);
//    }

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
    @PostMapping("/stu")
    public ResCode addStu(Student student,
                          HttpServletResponse response){
        return resCodeService.addStuAndUser(student,response);
    }

    /**
     * 删除学生信息
     */

    /**
     * 修改学生信息
     */
}
