package com.wula.stumanage.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wula.stumanage.pojo.TSource;

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
    List<TSource> getAllByNo(Integer no);
}
