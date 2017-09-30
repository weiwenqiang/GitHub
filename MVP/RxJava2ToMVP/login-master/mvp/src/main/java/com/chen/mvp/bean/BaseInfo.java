package com.chen.mvp.bean;

/**
 * Created by long on 17-3-22.
 */

public class BaseInfo {


    /**
     * code : -1or0
     * msg : xxx
     * data : {"user":"phone","pass":"value","id":0}
     */

    private int code;
    private String msg;
    private UserInfo data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserInfo getData() {
        return data;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "result={" +
                "\"code\":" + code +
                ", \"msg\":'" + msg + '\'' +
                ", \"data\":{" + data.toString() +
                '}';
    }

}
