package com.wula.stumanage.service.combination;

import com.wula.stumanage.pojo.votest.StuAndSou;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */

public interface IStuAndSouService {
    /**
     * 根据学号获取学生成绩信息
     * @param no
     * @return
     */
    StuAndSou getByNo(Integer no);
}
