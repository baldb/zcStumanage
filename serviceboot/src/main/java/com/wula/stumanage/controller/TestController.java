package com.wula.stumanage.controller;

import com.wula.stumanage.mapper.IStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@RestController
public class TestController {


    @Autowired
    private IStudentMapper studentMapper;


    @RequestMapping("/test")
    public List getall(){
        return studentMapper.getAllByTeach(2121003);
    }

    @PutMapping("/test1")
    public void getall1(@RequestBody Map map){
        System.out.println(map.get("courseId"));
        System.out.println(map.get("stuId"));
        System.out.println(map.get("score"));
    }
}
