package com.wula.stumanage.mapper.votest;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wula.stumanage.pojo.votest.StuAndSou;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */

@Repository
public interface IStuAndSouMapper extends BaseMapper<StuAndSou> {

    @Select("select stu.stu_no,stu.stu_name,stu.class_id,sou.course_id,sou.score from student stu,source sou where stu.stu_no=sou.stu_no and stu.stu_no = #{no}")
    StuAndSou getStuAndSouByNo(Integer no);
}
