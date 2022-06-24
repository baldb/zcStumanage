package com.wula.stumanage.pojo.utils;

import lombok.Data;

import java.util.List;

/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Data
public class CourseSelection<T> {
    private Integer stuId;
//    private Integer[] courseArr;
    private List<T> courseList;
}
