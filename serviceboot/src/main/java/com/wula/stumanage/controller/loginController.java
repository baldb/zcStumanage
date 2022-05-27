package com.wula.stumanage.controller;

import com.wula.stumanage.pojo.User;
import com.wula.stumanage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
     *Response：服务器给浏览器发请求
     * 获取登陆密码进行验证
     */
    @RequestMapping("/login")
    public Map login(@RequestParam("name") String name,
                     @RequestParam("password") String password,
                     HttpServletRequest request){
        Map map = new HashMap();
        HttpSession session = request.getSession();
        String id = session.getId();
        map.put("sessionID",id);

        User user = userService.login(name, password);

        if(user!=null){
            map.put("user",user);
            map.put("msg","success");
        }else {
            map.put("msg","filed");
        }
        return map;
    }
}
