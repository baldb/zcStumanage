package com.wula.stumanage.service.impl;

import com.wula.stumanage.mapper.StudentMapper;
import com.wula.stumanage.pojo.Student;
import com.wula.stumanage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public List<Student> getAll() {
        return studentMapper.selectAll();
    }

    @Override
    public int addStu(Student student) {
        return studentMapper.insert(student);
    }

    @Override
    public int deleteStu(int id) {
        return studentMapper.deleteByPrimaryKey(id);
    }
}
