package com.bjpowernode.crm.commons.domain;

public class ReturnObject {
    //处理成功或者失败的标记 1--成功 0--失败
    private String code;
    //提示信息
    private String msg;
    //其他返回数据
    private Object retData;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getRetData() {
        return retData;
    }

    public void setRetData(Object retData) {
        this.retData = retData;
    }
}
