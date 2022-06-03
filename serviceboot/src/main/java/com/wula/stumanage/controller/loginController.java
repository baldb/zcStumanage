package com.wula.stumanage.controller;

import com.wula.stumanage.pojo.User;
import com.wula.stumanage.pojo.utils.ResCode;
import com.wula.stumanage.service.IUserService;
import com.wula.stumanage.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@SuppressWarnings({"all"})
@RestController
@RequestMapping("/logins")
public class loginController {

    @Autowired
    private IUserService userService;

    /**
     * Request：浏览器给服务器发请求
     * Response：服务器给浏览器发请求
     * 获取登陆密码进行验证
     */
    @RequestMapping("/login")
    public ResCode login(@RequestParam("name") String name,
                     @RequestParam("password") String password,
                     HttpServletResponse response) {
        User user = userService.login(name, password);
        ResCode<User> userResCode = new ResCode<>();
        if (user != null) {
            userResCode.setStatus(response.getStatus());
            userResCode.setResultSet(user);
            userResCode.setMsg(userService.judgeNP(name, password));
        } else {
            response.setStatus(403);
            userResCode.setStatus(response.getStatus());
            userResCode.setResultSet(null);
            userResCode.setMsg(userService.judgeNP(name, password));
        }
        return userResCode;
    }
}
