package com.wula.stumanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wula.stumanage.pojo.Student;
import com.wula.stumanage.pojo.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Repository
public interface ITeacherMapper extends BaseMapper<Teacher> {

//    /**
//     * 根据老师id获取学生选课情况
//     */
//    List<Student> getAllByTeach(Integer teachId);
}
