package com.wula.stumanage.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wula.stumanage.pojo.TSource;
import com.wula.stumanage.pojo.utils.ResCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
public interface ISourceService extends IService<TSource> {

    /**
     * 根据学号获取全部成绩
     * @param no
     * @return
     */
//    List<TSource> getAllByNo(Integer no);


    /**
     * 根据学号，课程号添加数据
     * @param stuId
     * @param scList
     * @return
     */
    ResCode addByStuAndCouid(Integer stuId, List<Integer> scList);

}
