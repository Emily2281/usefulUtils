package com.obj;

public class ResultData {
    public String result;
    public String msg;
    public String code;

    public ResultData() {
    }

    public ResultData(String result, String msg, String code) {
        this.result = result;
        this.msg = msg;
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString(){
        return result+","+msg+","+code;
    }
}
