package com.wula.stumanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wula.stumanage.pojo.stuAndsou.StudentAndSource;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
public interface IStudentAndSourceService extends IService<StudentAndSource> {

    /**
     * 根据学号获取该学生所有成绩
     * @param no
     * @return
     */
    StudentAndSource getStuAndSouByNo(Integer no);
}
