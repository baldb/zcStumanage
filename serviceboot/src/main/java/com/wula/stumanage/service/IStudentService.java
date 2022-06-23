package com.wula.stumanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wula.stumanage.pojo.Student;

import java.util.List;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
public interface IStudentService extends IService<Student> {
    /**
     * 根据学生姓名返回信息
     * @param name
     * @return
     */
    List<Student> getStuByName(String name);
}
