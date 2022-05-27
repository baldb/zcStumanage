package com.wula.stumanage.service;

import com.wula.stumanage.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Service
public interface UserService {
    /**
     * 完成登陆判断
     *
     * @return
     */
    User login(String name, String password);

    /**
     * 根据id查询，测试
     */
    User selectById(int Id);
}
