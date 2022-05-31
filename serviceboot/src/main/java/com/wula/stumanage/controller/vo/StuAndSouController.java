package com.wula.stumanage.controller.vo;

import com.wula.stumanage.pojo.votest.StuAndSou;
import com.wula.stumanage.service.avo.IStuAndSouService;
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
public class StuAndSouController {

//    @Qualifier("IStuAndSouService")
    @Autowired
    private IStuAndSouService iStuAndSouService;

    @RequestMapping("/getno")
    public Map getSou(Integer no){
       Map map = new HashMap();
        StuAndSou byNo = iStuAndSouService.getByNo(no);
        map.put("stu",byNo);
        return map;
    }


}
