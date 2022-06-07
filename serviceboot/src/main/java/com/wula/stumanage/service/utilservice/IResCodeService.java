package com.wula.stumanage.service.utilservice;

import com.wula.stumanage.pojo.Student;
import com.wula.stumanage.pojo.Teacher;
import com.wula.stumanage.pojo.User;
import com.wula.stumanage.pojo.utils.ResCode;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
public interface IResCodeService {

    /**
     * 封装分页查询所有信息
     * @return
     */
    ResCode getPageOnCode(int pn,
                      int offset,
                      String stuName,
                      HttpServletResponse response);

    /**
     * 添加用户信息
     * @param name
     * @return
     */
    User addUser(String name,String ID,String password);

    /**
     * 返回学生的全部信息
     * @param response
     * @return
     */
    Map getAllStudent(HttpServletResponse response);

    /**
     * 图片上传服务
     * @param headerImg
     * @return
     */
    ResCode uploadPic(MultipartFile headerImg,HttpServletResponse response);

    /**
     * 添加学生时，自动添加User
     * @param student
     * @param response
     * @return
     */
    ResCode addStuAndUser(Student student,HttpServletResponse response);

    /**
     * **********************************************************************************
     */
    /**
     * 老师的分页信息查询
     */
    ResCode getPageForTeacher(int pn,
                          int offset,
                          String name,
                          HttpServletResponse response);
    /**
     * 添加老师信息
     */
    ResCode addForTeacher(Teacher teacher,HttpServletResponse response);

    /**
     * 删除老师信息
     */
    ResCode delTeacherById(Integer id);
}
