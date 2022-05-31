package com.wula.stumanage.service.avo.impl;

import com.wula.stumanage.mapper.votest.IStuAndSouMapper;
import com.wula.stumanage.pojo.votest.StuAndSou;
import com.wula.stumanage.service.avo.IStuAndSouService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */

@Service
public class IStuAndSouServiceImpl implements IStuAndSouService {
    @Autowired
    private IStuAndSouMapper stuAndSouMapper;
    @Override
    public StuAndSou getByNo(Integer no) {
        return stuAndSouMapper.getStuAndSouByNo(no);
    }
}
