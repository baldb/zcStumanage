package com.wula.stumanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wula.stumanage.mapper.IStudentMapper;
import com.wula.stumanage.pojo.Student;
import com.wula.stumanage.service.IStudentService;
import org.springframework.stereotype.Service;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Service
public class StudentServiceImpl extends ServiceImpl<IStudentMapper, Student> implements IStudentService {
}
