package com.dong.sitserver.common;

import com.dong.sitserver.common.annotation.util.JacksonUtil;

/**
 * Created by rxia on 2015/9/2.
 */
public class BackJsonBean {
    private Integer successCode = 0;
    private String msg;

    public BackJsonBean(String msg) {
        successCode = 1;
        this.msg = msg;
    }

    public BackJsonBean() {

    }


    public BackJsonBean(int successCode, String msg) {
        this.successCode = successCode;
        this.msg = msg;
    }

    public String toJson() throws Exception {
        return JacksonUtil.objToJson(this);
    }


    public Integer getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(Integer successCode) {
        this.successCode = successCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
