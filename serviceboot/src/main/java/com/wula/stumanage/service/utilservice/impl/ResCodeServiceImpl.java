package com.wula.stumanage.service.utilservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wula.stumanage.pojo.Student;
import com.wula.stumanage.pojo.Teacher;
import com.wula.stumanage.pojo.User;
import com.wula.stumanage.pojo.ZcClass;
import com.wula.stumanage.pojo.combination.ClassAndTeach;
import com.wula.stumanage.pojo.utils.PageUtil;
import com.wula.stumanage.pojo.utils.ResCode;
import com.wula.stumanage.pojo.utils.StudentList;
import com.wula.stumanage.service.*;
import com.wula.stumanage.service.combination.IClassAndTeachService;
import com.wula.stumanage.service.utilservice.IResCodeService;
import com.wula.stumanage.utils.MD5Util;
import com.wula.stumanage.utils.UploadPhotoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@Service
public class ResCodeServiceImpl implements IResCodeService {
//    @Autowired
//    private IStudentAndSourceService iStudentAndSourceService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IStudentService studentService;

    @Autowired
    public ITeacherService teacherService;

    @Autowired
    private ZcClassService zcClassService;

    @Autowired
    private IClassAndTeachService classAndTeachService;

    @Override
    public ResCode getClassPage(int pn, int offset, String name, HttpServletResponse response) {
        ResCode<ClassAndTeach> classAndTeachResCode = new ResCode<>();
        QueryWrapper<ClassAndTeach> qwr = new QueryWrapper<>();
        qwr.orderByDesc("create_time");
        PageUtil<ClassAndTeach> pageUtil = new PageUtil<>();

        if(pn!=0 || offset!=0 || name!=null){
            if(name==null){ //名字为空
                if(offset!=0)
                    pageUtil.setOffSet(offset);
                Page<ClassAndTeach> page = new Page<>(pn,pageUtil.getOffSet());
                Page<ClassAndTeach> page1 = classAndTeachService.page(page, qwr);
                List<ClassAndTeach> records = page1.getRecords();
                if(records.size()>0){
                    for (ClassAndTeach teacherClassAndTeach :records) {
                        LambdaQueryWrapper<Teacher> teacherQueryWrapper = new LambdaQueryWrapper<>();
                        Teacher teacher = teacherService.getOne(teacherQueryWrapper.select(Teacher::getTeachNo, Teacher::getTeachName)
                                .eq(Teacher::getTeachNo, teacherClassAndTeach.getTechNo()));
                        teacherClassAndTeach.setTeachMessage(teacher);
                    }
                    classAndTeachResCode.setMsg("success");
                    classAndTeachResCode.setPage(page1);
                    classAndTeachResCode.setStatus(200);
                }else {
                    classAndTeachResCode.setMsg("没有数据，请先添加数据");
                    response.setStatus(403);
                    classAndTeachResCode.setStatus(response.getStatus());
                    classAndTeachResCode.setResultSet(null);
                }
            }else { //名字不为空
                if(offset!=0)
                    pageUtil.setOffSet(offset);
                Page<ClassAndTeach> page = new Page<>(pn,pageUtil.getOffSet());
                LambdaQueryWrapper<ClassAndTeach> classAndTeachLambdaQueryWrapper = new LambdaQueryWrapper<>();
                classAndTeachLambdaQueryWrapper.eq(ClassAndTeach::getClassName,name);
                ClassAndTeach one = classAndTeachService.getOne(classAndTeachLambdaQueryWrapper);
                if(one!=null) {
                    LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    teacherLambdaQueryWrapper.eq(Teacher::getTeachNo, one.getTechNo());
                    one.setTeachMessage(teacherService.getOne(teacherLambdaQueryWrapper));
                    classAndTeachResCode.setMsg("success");
                    classAndTeachResCode.setResultSet(one);
                    classAndTeachResCode.setStatus(200);
                }else{
                    classAndTeachResCode.setMsg("没有此班级");
                    classAndTeachResCode.setResultSet(null);
                    classAndTeachResCode.setStatus(403);
                }
            }
        }
        //resCodeService.selZcClass(pn,offset,name,response)
        return classAndTeachResCode;
    }

    /**
     * 学生的分页查询
     *
     *
     * @param pn
     * @param offset
     * @param stuName
     * @param response
     * @return
     */
    @Override
    public ResCode getPageOnCode(int pn,
                                 int offset,
                                 String stuName,
                                 HttpServletResponse response) {
        ResCode<Object> pageUtilResCode = new ResCode<>();
        PageUtil<Student> studentPageUtil = new PageUtil<>();   //分页信息类

        if (pn != 0 || offset != 0 || !stuName.isEmpty()) {
            if (stuName == null) {
//                List<Student> list = studentService.list(null);
                if (offset != 0)
                    studentPageUtil.setOffSet(offset);
                IPage<Student> pageUtil = new Page<>(pn, studentPageUtil.getOffSet());
                IPage<Student> iPage1 = studentService.page(pageUtil); //分页后查找的数据

//                studentPageUtil.setPage(iPage1.getCurrent());//获取当前输出的是第几页
//                studentPageUtil.setPages(iPage1.getPages()); //一共有几页
//                studentPageUtil.setRecords(iPage1.getRecords()); //获取查询出来的数据
//                studentPageUtil.setTotal(list.size());

                if (iPage1.getRecords().size() > 0) {
                    pageUtilResCode.setMsg("success");
                    pageUtilResCode.setStatus(response.getStatus());
                    pageUtilResCode.setResultSet(iPage1);//studentPageUtil
                } else {
                    pageUtilResCode.setMsg("没有数据，请先添加数据");
                    response.setStatus(403);
                    pageUtilResCode.setStatus(response.getStatus());
                    pageUtilResCode.setResultSet(null);
                }
            } else {
                /**
                 * 实现根据学生姓名查询学生信息分页
                 */
                if (offset != 0)
                    studentPageUtil.setOffSet(offset);
                IPage<Student> pageUtil = new Page<>(pn, studentPageUtil.getOffSet());
                LambdaQueryWrapper<Student> lqw = new LambdaQueryWrapper<>();
                lqw.eq(stuName != null, Student::getStuName, stuName);
                IPage<Student> page = studentService.page(pageUtil, lqw);//根据条件获得分页查询数据
                pageUtilResCode.setMsg("success");
                pageUtilResCode.setStatus(response.getStatus());
                pageUtilResCode.setResultSet(page);
            }

        } else {
            LambdaQueryWrapper<Student> lqw = new LambdaQueryWrapper<>();
            lqw.last("order by create_time asc");
            List<Student> listAll = studentService.list(lqw);
            studentPageUtil.setPage((long) pn);//获取当前输出的是第几页
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

    /**
     * 添加学生或者老师的同时添加User用户，添加时得调用该方法
     *
     * @param name
     * @param ID
     * @return
     */
    @Override
    public User addUser(String name, String ID,String password) {
        String pass = MD5Util.getMD5(password);
        User user = new User();
        user.setUserName(name);
        user.setPassword(pass);
        user.setIdentity(ID);
//        user.setCreateTime(new Date());
//        user.setUpdateTime(new Date());
        return user;
    }

    /**
     * 获取所有学生的信息
     *
     * @param response
     * @return
     */
    @Override
    public Map getAllStudent(HttpServletResponse response) {
        List<Student> list = studentService.list(null);
        StudentList<Student> studentStudentList = new StudentList<>();
        studentStudentList.setList(list);
        ResCode<StudentList<Student>> pageUtilResCode = new ResCode<>();
        if (list.size() > 0) {
            pageUtilResCode.setMsg("success");
            pageUtilResCode.setStatus(response.getStatus());
            pageUtilResCode.setResultSet(studentStudentList);
        } else {
            pageUtilResCode.setMsg("没有数据，请先添加数据");
            response.setStatus(403);
            pageUtilResCode.setStatus(response.getStatus());
            pageUtilResCode.setResultSet(null);
        }
        Map map = new HashMap();
        map.put("res", pageUtilResCode);
        return map;
    }

    /**
     * 上传下载图片
     *
     * @param headerImg
     * @param response
     * @return
     */
    @Override
    public ResCode uploadPic(MultipartFile headerImg, HttpServletResponse response) {
        ResCode<Object> resCode = new ResCode<>();
        UploadPhotoUtil uploadPhotoUtil = new UploadPhotoUtil();
        uploadPhotoUtil.uploadPhoto(headerImg);
        if (headerImg.getSize() > 0) {
            resCode.setStatus(response.getStatus());
            resCode.setMsg("添加图片成功");
            resCode.setResultSet(uploadPhotoUtil.getPicName());
        } else {
            response.setStatus(403);
            resCode.setStatus(response.getStatus());
            resCode.setMsg("添加图片失败");
            resCode.setResultSet(null);
        }
        return resCode;

    }

    /**
     * 添加学生功能
     *
     * @param student
     * @param response
     * @return
     */
    @Override
    public ResCode addStuAndUser(Student student, HttpServletResponse response) {

//        student.setCreateTime(new Date());
//
//        student.setUpdateTime(new Date());

        String ID = "2";
        String password="123456";
        ResCode<Student> studentResCode = new ResCode<>();

        if (studentService.save(student)) {       //添加成功
            studentResCode.setStatus(response.getStatus());
            studentResCode.setMsg("添加成功");
            studentResCode.setResultSet(null);
            //添加用户时自动添加User信息，密码默认是123456
            User user = this.addUser(student.getStuNo() + "", ID,password);
            userService.save(user);
        } else {
            response.setStatus(403);
            studentResCode.setStatus(response.getStatus());
            studentResCode.setMsg("添加失败");
            studentResCode.setResultSet(null);
        }
        return studentResCode;
    }


    /**
     * 老师的分页查询
     * @param pn
     * @param offset
     * @param name
     * @param response
     * @return
     */
    @Override
    public ResCode getPageForTeacher(int pn, int offset, String name, HttpServletResponse response) {
        ResCode<Object> pageUtilResCode = new ResCode<>();
        int OFFSET=10;
        if (pn != 0 || offset != 0 || !name.isEmpty()) {
            if (name == null) {
                if (offset == 0)
                    offset=OFFSET;
                Page<Teacher> teacherPage = new Page<>(pn,offset);
                QueryWrapper<Teacher> objectQueryWrapper = new QueryWrapper<>();
                objectQueryWrapper.orderByDesc("create_time");
                IPage<Teacher> iPage1 = teacherService.page(teacherPage,objectQueryWrapper); //分页后查找的数据
                if (iPage1.getRecords().size() > 0) {
                    pageUtilResCode.setMsg("success");
                    pageUtilResCode.setStatus(response.getStatus());
                    pageUtilResCode.setResultSet(iPage1);//studentPageUtil
                } else {
                    pageUtilResCode.setMsg("没有数据，请先添加数据");
                    response.setStatus(403);
                    pageUtilResCode.setStatus(response.getStatus());
                    pageUtilResCode.setResultSet(null);
                }
            } else {
                if (offset == 0)
                    offset=OFFSET;
                IPage<Teacher> pageUtil = new Page(pn,offset);
                LambdaQueryWrapper<Teacher> lqw = new LambdaQueryWrapper<>();
                lqw.eq(name != null, Teacher::getTeachName, name);
                IPage<Teacher> page = teacherService.page(pageUtil, lqw);//根据条件获得分页查询数据
                pageUtilResCode.setMsg("success");
                pageUtilResCode.setStatus(response.getStatus());
                pageUtilResCode.setResultSet(page);
            }

        } else {
            LambdaQueryWrapper<Teacher> lqw = new LambdaQueryWrapper<>();
            lqw.last("order by create_time asc");
            List<Teacher> listAll = teacherService.list(lqw);
            pageUtilResCode.setMsg("查询所有数据");
            pageUtilResCode.setStatus(response.getStatus());
            pageUtilResCode.setResultSet(listAll);
        }

        return pageUtilResCode;
    }

    /**
     * 添加老师信息
     * @param teacher
     * @param response
     * @return
     */
    @Override
    public ResCode addForTeacher(Teacher teacher, HttpServletResponse response) {
        String ID = "1";
        String PASSWORD="654321";
        ResCode<Teacher> resCode = new ResCode<>();
        if (teacherService.saveOrUpdate(teacher)) {       //添加成功
            resCode.setStatus(response.getStatus());
            resCode.setMsg("添加成功");
            resCode.setResultSet(null);
            //添加用户时自动添加User信息，密码默认是654321
//            PinYInUtil pinYInUtil = new PinYInUtil();
//            User user = this.addUser(pinYInUtil.getPingYin(teacher.getTeachName()), ID,PASSWORD);
            User user = this.addUser(teacher.getTeachNo()+"", ID,PASSWORD);
            userService.save(user);
        } else {
            response.setStatus(403);
            resCode.setStatus(response.getStatus());
            resCode.setMsg("添加失败");
            resCode.setResultSet(null);
        }
        return resCode;
    }

    /**
     * 根据id删除老师信息
     * @param id
     * @return
     */
    @Override
    public ResCode delTeacherById(Integer id) {
        ResCode<Object> objectResCode = new ResCode<>();
        boolean b = teacherService.removeById(id);
        if(b){
            LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
            lqw.eq(b, User::getUserName, id + "");
            boolean u = userService.remove(lqw);
        }
        objectResCode.CodeAll(b,"删除老师成功");
        return objectResCode;
    }



    /**
     * 分页查询班级信息
     * @param pn
     * @param offset
     * @param name
     * @param response
     * @return
     */
    @Override
    public ResCode selZcClass(int pn, int offset, String name, HttpServletResponse response) {
        ResCode<Object> pageUtilResCode = new ResCode<>();
        PageUtil<ZcClass> pageUtil1 = new PageUtil<>();   //分页信息类
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_time");
        if (pn != 0 || offset != 0 || !name.isEmpty()) {
            if (name == null) {
                if (offset != 0)
                    pageUtil1.setOffSet(offset);
                IPage<ZcClass> pageUtil = new Page<>(pn, pageUtil1.getOffSet());
                IPage<ZcClass> iPage1 = zcClassService.page(pageUtil,queryWrapper); //分页后查找的数据
                if (iPage1.getRecords().size() > 0) {
                    pageUtilResCode.setMsg("success");
                    pageUtilResCode.setStatus(response.getStatus());
                    pageUtilResCode.setResultSet(iPage1);//studentPageUtil
                } else {
                    pageUtilResCode.setMsg("没有数据，请先添加数据");
                    response.setStatus(403);
                    pageUtilResCode.setStatus(response.getStatus());
                    pageUtilResCode.setResultSet(null);
                }
            } else {
                /**
                 * 实现根据学生姓名查询学生信息分页
                 */
                if (offset != 0)
                    pageUtil1.setOffSet(offset);
                IPage<ZcClass> pageUtil = new Page<>(pn, pageUtil1.getOffSet());
                LambdaQueryWrapper<ZcClass> lqw = new LambdaQueryWrapper<>();
                lqw.eq(name != null, ZcClass::getClassName, name);
                IPage<ZcClass> page = zcClassService.page(pageUtil, lqw);//根据条件获得分页查询数据
                pageUtilResCode.setMsg("success");
                pageUtilResCode.setStatus(response.getStatus());
                pageUtilResCode.setResultSet(page);
            }

        } else {
            LambdaQueryWrapper<ZcClass> lqw = new LambdaQueryWrapper<>();
            lqw.last("order by create_time asc");
            List<ZcClass> listAll = zcClassService.list(lqw);
            pageUtil1.setPage((long) pn);//获取当前输出的是第几页
            pageUtil1.setPages(0l); //一共有几页
            pageUtil1.setRecords(listAll); //获取查询出来的数据
            pageUtil1.setTotal(listAll.size());
            pageUtil1.setOffSet(listAll.size());
            pageUtilResCode.setMsg("查询所有数据");
            pageUtilResCode.setStatus(response.getStatus());
            pageUtilResCode.setResultSet( pageUtil1);
        }

        return pageUtilResCode;
    }
}
