package com.wula.stumanage.controller;

import com.wula.stumanage.mapper.IStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
