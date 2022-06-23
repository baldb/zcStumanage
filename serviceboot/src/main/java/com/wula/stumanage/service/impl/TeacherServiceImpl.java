package com.wula.stumanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wula.stumanage.mapper.IStudentMapper;
import com.wula.stumanage.mapper.ITeacherMapper;
import com.wula.stumanage.pojo.Student;
import com.wula.stumanage.pojo.Teacher;
import com.wula.stumanage.service.IStudentService;
import com.wula.stumanage.service.ITeacherService;
import org.springframework.stereotype.Service;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<ITeacherMapper, Teacher> implements ITeacherService {
}
