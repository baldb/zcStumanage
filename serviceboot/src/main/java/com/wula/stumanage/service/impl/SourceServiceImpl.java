package com.wula.stumanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wula.stumanage.mapper.ISourceMapper;
import com.wula.stumanage.pojo.TSource;
import com.wula.stumanage.pojo.utils.ResCode;
import com.wula.stumanage.service.ISourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author linyi
* @description 针对表【source】的数据库操作Service实现
* @createDate 2022-06-24 11:18:31
*/
@Service
public class SourceServiceImpl extends ServiceImpl<ISourceMapper, TSource>
    implements ISourceService {

    @Autowired
    private ISourceMapper sourceMapper;

    @Autowired
    private ISourceService sourceService;


    @Override
    public ResCode addByStuAndCouid(Integer stuId, List<Integer> scList) {
        List<TSource> list = new ArrayList<>();
        for (Integer integer :scList) {
            TSource tSource = new TSource();
            tSource.setCourseId(integer);
            tSource.setStuNo(stuId);
            list.add(tSource);
        }
        boolean b = sourceService.saveBatch(list);
        ResCode<Object> resCode = new ResCode<>();
        if(b){
            resCode.setMsg("添加成功");
            resCode.setStatus(200);
        }else {
            resCode.setMsg("添加失败");
            resCode.setStatus(403);
        }
        return resCode;
    }
}




