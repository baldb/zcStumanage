package com.book.controller;

import com.book.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Controller
public class TestController {

    @ResponseBody
    @RequestMapping("/test")
    public User test(String name,String password){
        User user = new User("linyi","123456");
        User user1 = new User(name,password);
        System.out.println(user1);
        return user;
    }
}
