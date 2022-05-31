package com.wula.stumanage.pojo.utils;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Data
public class PageUtil<T> {

    //一共有几页
    private Long Pages;

    //一共有多少条条数据
    private int total;

    //当前输出的是第几页
    private Long page;

    //默认每页输出10条数据
    private int offSet =10;


    //查询结果
    private List<T> Records;

}
