package com.wula.stumanage;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wula.stumanage.mapper.IUserMapper;
import com.wula.stumanage.pojo.User;
import com.wula.stumanage.service.IUserService;
import com.wula.stumanage.utils.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@SpringBootTest
public class MapperTest {

    @Autowired
    private IUserMapper userMapper;

    @Test
    void test(){
        String name="linyi";
        String password="123456";
        String miPwd= MD5Util.getMD5(password);
        //填写自己所要查询的条件
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.like(name!=null,User::getUserName,name);
        lqw.like(miPwd!=null,User::getPassword,miPwd);
        User user = userMapper.selectOne(lqw);
        System.out.println(user);
    }
}
