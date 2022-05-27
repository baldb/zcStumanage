package com.wula.stumanage.service.impl;

import com.wula.stumanage.mapper.UserMapper;
import com.wula.stumanage.pojo.User;
import com.wula.stumanage.service.UserService;
import com.wula.stumanage.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(String name, String password) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName",name);//相当于 where name = "王小二"
        List<User> admins = userMapper.selectByExample(example);//查询name为王小二的所有数据
        if(admins.size()>0){
            User admin = admins.get(0);
            String miPwd= MD5Util.getMD5(password);
            if(miPwd.equals(admin.getPassword())){
                return admin;
            }
        }
        return null;
    }

    @Override
    public User selectById(int Id) {
        return userMapper.selectByPrimaryKey(Id);
    }
}
