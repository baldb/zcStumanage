package com.wula.stumanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wula.stumanage.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
public interface IUserService extends IService<User> {
    /**
     * select -----> get 查
     * delete -----> remove 删
     * update -----> update 改/更新
     * save -------> save  增
     */

    /**
     * 完成登陆判断
     *
     * @return
     */
    User login(String name, String password);

    /**
     * 判断用户名不存在或者密码错误
     */
    String judgeNP(String name, String password);

    /**
     * 根据用户名查询用户
     */
    User getUserByName(String username);
}
