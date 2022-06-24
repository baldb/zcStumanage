package com.wula.stumanage.service;

import com.wula.stumanage.pojo.ZcClass;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wula.stumanage.pojo.utils.ResCode;
import org.springframework.stereotype.Service;

/**
* @author linyi
* @description 针对表【zc_class】的数据库操作Service
* @createDate 2022-06-12 14:47:46
*/

public interface ZcClassService extends IService<ZcClass> {

    /**
     * 添加班级信息
     * @param zcClass
     * @return
     */
    ResCode addOrUpClass(ZcClass zcClass);

    /**
     * 修改班级信息
     * @param zcClass
     * @return
     */
    ResCode UpdateClass(ZcClass zcClass);


    ResCode deleteClass(Integer classId);

}
