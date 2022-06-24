package com.wula.stumanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wula.stumanage.pojo.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Repository
public interface IStudentMapper extends BaseMapper<Student>{

    /**
     * 根据老师id返回学生信息
     * @param teachId
     * @return
     */
    List<Student> getAllByTeach(Integer teachId);

    /**
     * 根据班级ID查询该班学生信息
     * @param classId
     * @return
     */
    List<Student> getStuByClassId(@Param("classId")Integer classId);
}
