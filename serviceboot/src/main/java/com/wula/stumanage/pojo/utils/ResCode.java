package com.wula.stumanage.pojo.utils;

import lombok.Data;

import java.sql.ResultSet;
import java.util.List;


/**
 * @author 林逸
 * cool boy
 * 1.0
 */
@Data
public class ResCode<T> {
    private Integer Status;
    private String msg;
    private T ResultSet;

    public  void CodeAll(Boolean res,T o){
        if(res){
            this.msg="success";
            this.Status=200;
            this.ResultSet= o ;
        }else {
            this.msg="failure";
            this.Status=403;
            this.ResultSet= o;
        }
    }

}
