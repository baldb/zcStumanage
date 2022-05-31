package com.wula.stumanage.controller;

import com.wula.stumanage.pojo.stuAndsou.StudentAndSource;
import com.wula.stumanage.service.IStudentAndSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */

@RestController
public class SourceController {

    @Autowired
    private IStudentAndSourceService studentService;
    @RequestMapping("/byno")
    public Map test(Integer no){
        Map map = new HashMap();
        StudentAndSource stuAndSouByNo = studentService.getStuAndSouByNo(no);
        map.put("stu",stuAndSouByNo);
        return map;
    }
}
