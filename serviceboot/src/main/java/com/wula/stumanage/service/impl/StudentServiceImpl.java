package com.wula.stumanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wula.stumanage.mapper.IStudentMapper;
import com.wula.stumanage.pojo.Student;
import com.wula.stumanage.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Service
public class StudentServiceImpl extends ServiceImpl<IStudentMapper, Student> implements IStudentService {
    @Autowired
    private IStudentMapper studentMapper;
    @Override
    public List<Student> getStuByName(String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("stu_name",name);
       return studentMapper.selectByMap(map);
    }
}
