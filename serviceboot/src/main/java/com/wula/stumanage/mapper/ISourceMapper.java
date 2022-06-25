package com.wula.stumanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wula.stumanage.pojo.TSource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Repository
public interface ISourceMapper extends BaseMapper<TSource> {

    /**
     * 根据学号，课程号添加数据
     * @param stuId
     * @param scList
     * @return
     */
    Integer addByStuAndCouid(@Param("stuId") Integer stuId, List scList);

}
