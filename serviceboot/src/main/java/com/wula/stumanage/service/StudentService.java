package com.wula.stumanage.service;

import com.wula.stumanage.pojo.Student;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Service
public interface StudentService {
    /**
     * 获取学生所有数据
     * @return
     */
    List<Student> getAll();

    /**
     * 添加学生数据
     * @param student
     * @return
     */
    int addStu(Student student);

    int deleteStu(int id);
}
