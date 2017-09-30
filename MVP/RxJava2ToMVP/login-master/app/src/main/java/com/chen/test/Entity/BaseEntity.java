package com.chen.test.Entity;

/**
 * Created by long on 17-3-22.
 */

public class BaseEntity {


    /**
     * code : BeJson
     * msg : http://www.bejson.com
     * data : {"user":"d","pass":"value","id":1}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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
