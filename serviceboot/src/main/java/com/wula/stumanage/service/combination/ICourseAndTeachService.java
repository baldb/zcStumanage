package com.wula.stumanage.service.combination;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wula.stumanage.pojo.combination.CourseAndTeach;
import com.wula.stumanage.pojo.utils.ResCode;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
public interface ICourseAndTeachService extends IService<CourseAndTeach> {

    /**
     * 获取课程的分页信息
     * @param pn
     * @param offset
     * @param name
     * @param response
     * @return
     */
    ResCode getPageCT(@RequestParam(value = "pn",defaultValue = "0") int pn,
                      @RequestParam(value = "offset",defaultValue = "0")int offset,
                      @RequestParam(value = "name",required = false)String name,
                      HttpServletResponse response);
}
