package com.wula.stumanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wula.stumanage.mapper.ITeacherMapper;
import com.wula.stumanage.pojo.Teacher;
import com.wula.stumanage.pojo.ZcClass;
import com.wula.stumanage.pojo.utils.ResCode;
import com.wula.stumanage.service.ZcClassService;
import com.wula.stumanage.mapper.ZcClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author linyi
* @description 针对表【zc_class】的数据库操作Service实现
* @createDate 2022-06-12 14:47:46
*/
@Service
public class ZcClassServiceImpl extends ServiceImpl<ZcClassMapper, ZcClass> implements ZcClassService{
    @Autowired
    private ZcClassMapper classMapper;

    @Autowired
    private ITeacherMapper teacherMapper;

    @Override
    public ResCode addOrUpClass(ZcClass zcClass) {
        ResCode<ZcClass> zcClassResCode = new ResCode<>();
        LambdaQueryWrapper<Teacher> teacherQueryWrapper = new LambdaQueryWrapper<>();
        teacherQueryWrapper.eq(Teacher::getTeachNo,zcClass.getTechNo());
        Integer integer = teacherMapper.selectCount(teacherQueryWrapper);
        if(integer>0) {
            int insert = classMapper.insert(zcClass);
            zcClassResCode.setStatus(200);
            zcClassResCode.setMsg("添加成功");
            zcClassResCode.setResultSet(null);
        }else {
            zcClassResCode.setStatus(403);
            zcClassResCode.setMsg("添加失败,没有这个老师");
            zcClassResCode.setResultSet(null);
        }
        return zcClassResCode;
    }

    @Override
    public ResCode UpdateClass(ZcClass zcClass) {
        ResCode<ZcClass> zcClassResCode = new ResCode<>();
        LambdaQueryWrapper<Teacher> teacherQueryWrapper = new LambdaQueryWrapper<>();
        teacherQueryWrapper.eq(Teacher::getTeachNo,zcClass.getTechNo());
        Integer integer = teacherMapper.selectCount(teacherQueryWrapper);
        if(integer>0) {
            int i = classMapper.updateById(zcClass);
            zcClassResCode.setStatus(200);
            zcClassResCode.setMsg("修改成功");
            zcClassResCode.setResultSet(null);
        }else {
            zcClassResCode.setStatus(403);
            zcClassResCode.setMsg("修改失败,没有这个老师");
            zcClassResCode.setResultSet(null);
        }
        return zcClassResCode;
    }

    @Override
    public ResCode deleteClass(Integer classId) {
        ResCode<Object> objectResCode = new ResCode<>();
        int i = classMapper.deleteById(classId);
        if(i>0){
            objectResCode.setMsg("删除成功！");
            objectResCode.setStatus(200);
        }else {
            objectResCode.setMsg("删除失败，重新删除");
            objectResCode.setStatus(403);
        }
        return objectResCode;
    }
}




