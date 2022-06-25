package com.wula.stumanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.wula.stumanage.mapper.IStudentMapper;
import com.wula.stumanage.pojo.Student;
import com.wula.stumanage.pojo.TSource;
import com.wula.stumanage.pojo.User;
import com.wula.stumanage.pojo.utils.ResCode;
import com.wula.stumanage.service.ISourceService;
import com.wula.stumanage.service.IStudentService;
import com.wula.stumanage.service.IUserService;
import com.wula.stumanage.service.utilservice.IResCodeService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/supers")
@Slf4j
public class SuperUserController {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IResCodeService resCodeService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ISourceService sourceService;

    /**
     * 根据页数返回学生信息
     * 诺没有返回页数和页面输出几条数据的数据  则返回学生全部信息
     * @param stuname：根据学生姓名查询学生信息
     * @param pn：输出的页数为第几页
     * @param offset ：每页输出条数
     * @param response
     * @return
     */
    @GetMapping("/super")
    public ResCode getStu(@RequestParam(value = "pn",defaultValue = "0") int pn ,
                          @RequestParam(value = "offset",defaultValue = "0") int offset ,
                          @RequestParam(value = "stuName",required = false) String name,
                          HttpServletResponse response){
            return resCodeService.getPageOnCode(pn,offset,name,response);

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
        System.out.println(student);
        return resCodeService.addStuAndUser(student,response);
    }

    /**
     * 删除学生信息
     */
    @DeleteMapping("/super")
    public ResCode delete(@RequestParam("stuNo") Integer stuNo){
        boolean b = studentService.removeById(stuNo);
        if(b){
            LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
            lqw.eq(b, User::getUserName, stuNo + "");
            boolean u = userService.remove(lqw);
        }
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

    /**
     * 查询功能，根据学生姓名来查询学生信息
     */
    @GetMapping("/name")
    public ResCode selByName(@RequestParam("stuName") String name){
        System.out.println("根据姓名查询");
        List<Student> stuByName = studentService.getStuByName(name);
        ResCode objectResCode = new ResCode();
        objectResCode.CodeAll(name!=null,stuByName);
        return objectResCode;
    }

    /**
     * 根据课程id查找该课程的学生信息
     */
    @GetMapping("/bycourseId")
    public ResCode getStudentByCourseId(@RequestParam("courseId") Integer courseId) {
        return studentService.getStuByCourseId(courseId);
    }

    /**
     * 根据课程id，学生ID，成绩，修改学生成绩
     */
    @PutMapping("/updatescore")
    public ResCode getall1(@RequestBody Map map){
        LambdaUpdateWrapper<TSource> tSourceLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        tSourceLambdaUpdateWrapper.eq(TSource::getCourseId,map.get("courseId"))
                .eq(TSource::getStuNo,map.get("stuId"))
                .set(TSource::getScore,map.get("score"));
        boolean update = sourceService.update(tSourceLambdaUpdateWrapper);
        ResCode<Object> resCode = new ResCode<>();
        if(update){
            resCode.setMsg("添加成功");
            resCode.setStatus(200);
        }else {
            resCode.setMsg("添加失败");
            resCode.setStatus(403);
        }
        return resCode;
    }
    /**
     * 查询没有班级的学生
     */
    @GetMapping("/noclass")
    public ResCode selNoClass(){
        ResCode<Object> resCode = new ResCode<>();
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.isNull(Student::getClassId);
        List<Student> list = studentService.list(studentLambdaQueryWrapper);

        resCode.setResultSet(list);
        return resCode;
    }
    /**
     * 查询同班同学，根据该同学的学生id
     */
    @Autowired
    private IStudentMapper studentMapper;
    @GetMapping("/sameclass")
    public ResCode selStuByStu(@RequestParam("stuId") Integer stuId,
                               HttpServletResponse response){
        ResCode<Object> resCode = new ResCode<>();
        List<Student> list = studentMapper.getStuByStuId(stuId);
        if(list.size()>0) {
            resCode.setResultSet(list);
            resCode.setMsg("success");
        }else{
            resCode.setResultSet(list);
            response.setStatus(403);
            resCode.setMsg("没有同班同学");
            resCode.setStatus(403);
        }
        return resCode;
    }
}
