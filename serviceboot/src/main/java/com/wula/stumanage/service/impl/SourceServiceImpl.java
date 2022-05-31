package com.wula.stumanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wula.stumanage.mapper.ISourceMapper;
import com.wula.stumanage.pojo.TSource;
import com.wula.stumanage.service.ISourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Service
public class SourceServiceImpl extends ServiceImpl<ISourceMapper, TSource> implements ISourceService {

    @Autowired
    private  ISourceMapper iSourceMapper;
    @Override
    public List<TSource> getAllByNo(Integer no) {
        LambdaQueryWrapper<TSource> tSourceQueryWrapper = new LambdaQueryWrapper<>();
        tSourceQueryWrapper.like(no!=null,TSource::getStuNo,no);
        List<TSource> tSources = iSourceMapper.selectList(tSourceQueryWrapper);
        return tSources;
    }
}
