package com.wula.stumanage.service.combination.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wula.stumanage.mapper.combination.IClassAndTeachMapper;
import com.wula.stumanage.pojo.Teacher;
import com.wula.stumanage.pojo.combination.ClassAndTeach;
import com.wula.stumanage.pojo.utils.PageUtil;
import com.wula.stumanage.pojo.utils.ResCode;
import com.wula.stumanage.service.ITeacherService;
import com.wula.stumanage.service.combination.IClassAndTeachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.function.Function;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Service
public class IClassAndTeachServiceImpl extends ServiceImpl<IClassAndTeachMapper,ClassAndTeach> implements IClassAndTeachService {
//    @Autowired
//    private IClassAndTeachService classAndTeachService;
//
//    @Autowired
//    private ITeacherService teacherService;

//    @Override
//    public ResCode getClassPage(int pn, int offset, String name, HttpServletResponse response) {
//        ResCode<ClassAndTeach> classAndTeachResCode = new ResCode<>();
//        QueryWrapper<ClassAndTeach> qwr = new QueryWrapper<>();
//        qwr.orderByDesc("create_time");
//        PageUtil<ClassAndTeach> pageUtil = new PageUtil<>();
//
//        if(pn!=0 || offset!=0 || name!=null){
//            if(name==null){ //名字为空
//                if(offset!=0)
//                    pageUtil.setOffSet(offset);
//                Page<ClassAndTeach> page = new Page<>(pn,pageUtil.getOffSet());
//                Page<ClassAndTeach> page1 = classAndTeachService.page(page, qwr);
//                List<ClassAndTeach> records = page1.getRecords();
//                if(records.size()>0){
//                    for (ClassAndTeach teacherClassAndTeach :records) {
//                        LambdaQueryWrapper<Teacher> teacherQueryWrapper = new LambdaQueryWrapper<>();
//                        Teacher teacher = teacherService.getOne(teacherQueryWrapper.select(Teacher::getTeachNo, Teacher::getTeachName)
//                                .eq(Teacher::getTeachNo, teacherClassAndTeach.getTechNo()));
//                        teacherClassAndTeach.setTeachMessage(teacher);
//                    }
//                    classAndTeachResCode.setMsg("success");
//                    classAndTeachResCode.setPage(page1);
//                    classAndTeachResCode.setStatus(200);
//                }else {
//                    classAndTeachResCode.setMsg("没有数据，请先添加数据");
//                    response.setStatus(403);
//                    classAndTeachResCode.setStatus(response.getStatus());
//                    classAndTeachResCode.setResultSet(null);
//                }
//            }else { //名字不为空
//                if(offset!=0)
//                    pageUtil.setOffSet(offset);
//                Page<ClassAndTeach> page = new Page<>(pn,pageUtil.getOffSet());
//                LambdaQueryWrapper<ClassAndTeach> classAndTeachLambdaQueryWrapper = new LambdaQueryWrapper<>();
//                classAndTeachLambdaQueryWrapper.eq(ClassAndTeach::getClassName,name);
//                ClassAndTeach one = classAndTeachService.getOne(classAndTeachLambdaQueryWrapper);
//                if(one!=null) {
//                    LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
//                    teacherLambdaQueryWrapper.eq(Teacher::getTeachNo, one.getTechNo());
//                    one.setTeachMessage(teacherService.getOne(teacherLambdaQueryWrapper));
//                    classAndTeachResCode.setMsg("success");
//                    classAndTeachResCode.setResultSet(one);
//                    classAndTeachResCode.setStatus(200);
//                }else{
//                    classAndTeachResCode.setMsg("没有此班级");
//                    classAndTeachResCode.setResultSet(null);
//                    classAndTeachResCode.setStatus(403);
//                }
//            }
//        }
//        //resCodeService.selZcClass(pn,offset,name,response)
//        return classAndTeachResCode;
//    }
}
