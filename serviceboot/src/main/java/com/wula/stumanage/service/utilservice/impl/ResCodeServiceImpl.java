package com.wula.stumanage.service.utilservice.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wula.stumanage.pojo.Student;
import com.wula.stumanage.pojo.User;
import com.wula.stumanage.pojo.stuAndsou.StudentAndSource;
import com.wula.stumanage.pojo.utils.PageUtil;
import com.wula.stumanage.pojo.utils.ResCode;
import com.wula.stumanage.pojo.utils.StudentList;
import com.wula.stumanage.service.IStudentAndSourceService;
import com.wula.stumanage.service.IStudentService;
import com.wula.stumanage.service.IUserService;
import com.wula.stumanage.service.utilservice.IResCodeService;
import com.wula.stumanage.utils.MD5Util;
import com.wula.stumanage.utils.UploadPhotoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Service
public class ResCodeServiceImpl implements IResCodeService {
    @Autowired
    private IStudentAndSourceService iStudentAndSourceService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IStudentService studentService;


    @Override
    public ResCode getPageOnCode(int pn,
                             int offset,
                             HttpServletResponse response) {
        ResCode<PageUtil> pageUtilResCode = new ResCode<>();
        PageUtil<Student> studentPageUtil = new PageUtil<>();   //分页信息类

        if(pn!=0&&offset!=0){
            List<Student> list = studentService.list(null);
            if(offset!=0){
                studentPageUtil.setOffSet(offset);
            }
            IPage<Student> pageUtil = new Page<>(pn,studentPageUtil.getOffSet());
            IPage<Student> iPage1 = studentService.page(pageUtil); //分页后查找的数据

            studentPageUtil.setPage(iPage1.getCurrent());//获取当前输出的是第几页
            studentPageUtil.setPages(iPage1.getPages()); //一共有几页
            studentPageUtil.setRecords(iPage1.getRecords()); //获取查询出来的数据
            studentPageUtil.setTotal(list.size());

            if(iPage1.getRecords().size() >0){
                pageUtilResCode.setMsg("success");
                pageUtilResCode.setStatus(response.getStatus());
                pageUtilResCode.setResultSet(studentPageUtil);
            }else {
                pageUtilResCode.setMsg("没有数据，请先添加数据");
                response.setStatus(403);
                pageUtilResCode.setStatus(response.getStatus());
                pageUtilResCode.setResultSet(null);
            }

        }else {
            List<Student> listAll = studentService.list(null);
            studentPageUtil.setPage(0l);//获取当前输出的是第几页
            studentPageUtil.setPages(0l); //一共有几页
            studentPageUtil.setRecords(listAll); //获取查询出来的数据
            studentPageUtil.setTotal(listAll.size());
            studentPageUtil.setOffSet(listAll.size());
            pageUtilResCode.setMsg("查询所有数据");
            pageUtilResCode.setStatus(response.getStatus());
            pageUtilResCode.setResultSet(studentPageUtil);
        }

        return pageUtilResCode;
    }

    @Override
    public User addUser(String name, String ID) {
        String pass= MD5Util.getMD5("123456");
        User user = new User();
        user.setUserName(name);
        user.setPassword(pass);
        user.setIdentity(ID);
//        user.setCreateTime(new Date());
//        user.setUpdateTime(new Date());
        return user;
    }

    @Override
    public Map getAllStudent(HttpServletResponse response) {
        List<Student> list = studentService.list(null);
        StudentList<Student> studentStudentList = new StudentList<>();
        studentStudentList.setList(list);
        ResCode<StudentList<Student>> pageUtilResCode = new ResCode<>();
        if(list.size() >0){
            pageUtilResCode.setMsg("success");
            pageUtilResCode.setStatus(response.getStatus());
            pageUtilResCode.setResultSet(studentStudentList);
        }else {
            pageUtilResCode.setMsg("没有数据，请先添加数据");
            response.setStatus(403);
            pageUtilResCode.setStatus(response.getStatus());
            pageUtilResCode.setResultSet(null);
        }
        Map map = new HashMap();
        map.put("res",pageUtilResCode);
        return map;
    }

    @Override
    public ResCode uploadPic(MultipartFile headerImg,HttpServletResponse response) {
        ResCode<Object> resCode = new ResCode<>();
        UploadPhotoUtil uploadPhotoUtil = new UploadPhotoUtil();
        uploadPhotoUtil.uploadPhoto(headerImg);
        if(headerImg.getSize()>0){
            resCode.setStatus(response.getStatus());
            resCode.setMsg("添加图片成功");
            resCode.setResultSet(uploadPhotoUtil.getPicName());
        }else {
            response.setStatus(403);
            resCode.setStatus(response.getStatus());
            resCode.setMsg("添加图片失败");
            resCode.setResultSet(null);
        }
        return resCode;

    }

    @Override
    public ResCode addStuAndUser(Student student, HttpServletResponse response) {

//        student.setCreateTime(new Date());
//
//        student.setUpdateTime(new Date());

        String ID ="2";
        ResCode<Student> studentResCode = new ResCode<>();

        if(studentService.save(student)){       //添加成功
            studentResCode.setStatus(response.getStatus());
            studentResCode.setMsg("添加成功");
            studentResCode.setResultSet(null);
            //添加用户时自动添加User信息，密码默认是123456
            User user = this.addUser(student.getStuNo() + "", ID);
            userService.save(user);
        }else {
            response.setStatus(403);
            studentResCode.setStatus(response.getStatus());
            studentResCode.setMsg("添加失败");
            studentResCode.setResultSet(null);
        }
        return studentResCode;
    }
}
