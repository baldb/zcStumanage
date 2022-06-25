package com.wula.stumanage.service.combination;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wula.stumanage.pojo.Teacher;
import com.wula.stumanage.pojo.combination.ClassAndTeach;
import com.wula.stumanage.pojo.utils.ResCode;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
public interface IClassAndTeachService extends IService<ClassAndTeach> {

    /**
     * 班级的分页查询信息
     * @param pn
     * @param offset
     * @param name
     * @param response
     * @return
     */
//    ResCode getClassPage(int pn,
//                         int offset,
//                         String name,
//                         HttpServletResponse response);
}
