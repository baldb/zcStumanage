package com.wula.stumanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wula.stumanage.mapper.IUserMapper;
import com.wula.stumanage.pojo.User;
import com.wula.stumanage.service.IUserService;
import com.wula.stumanage.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<IUserMapper, User> implements IUserService {
   @Autowired
   private IUserMapper userMapper;

    /**
     * 实现登陆功能
     * @param name
     * @param password
     * @return
     */
    @Override
    public User login(String name, String password) {
        String miPwd= MD5Util.getMD5(password);
        //填写自己所要查询的条件
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.like(name!=null,User::getUserName,name);
        lqw.like(miPwd!=null,User::getPassword,miPwd);
        User user = userMapper.selectOne(lqw);
        if(user!=null){
            return user;
        }else {
            return null;
        }
    }

    @Override
    public String judgeNP(String name, String password) {
        String miPwd= MD5Util.getMD5(password);
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> like = lqw.like(name != null, User::getUserName, name);
        User users = userMapper.selectOne(lqw);
        if(users==null) {
            return "用户名不存在";
        }else {
            if(users.getPassword().equals(miPwd)){
                return "登陆成功";
            }else {
                return "密码错误";
            }
        }

    }

    @Override
    public User getUserByName(String username) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> like = lqw.eq(username != null, User::getUserName, username);
        User users = userMapper.selectOne(lqw);
        return users;
    }
}
