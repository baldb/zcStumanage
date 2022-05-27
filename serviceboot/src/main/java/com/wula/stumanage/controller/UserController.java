package com.wula.stumanage.controller;

import com.wula.stumanage.pojo.User;
import com.wula.stumanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */

/**
 *
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/login")

    public Map login(@RequestParam("name") String name,
                     @RequestParam("password") String password,
                     HttpServletRequest request){//, HttpServletRequest request
        User user = userService.login(name, password);
        HttpSession session = request.getSession();
        String id = session.getId();
        Map map = new HashMap();
        map.put("user",user);
        map.put("sessionID",id);
        return map;
    }


}
