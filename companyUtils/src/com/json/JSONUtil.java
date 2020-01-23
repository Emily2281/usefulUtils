package com.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.obj.ResultData;

import java.util.List;

/**
 * @author lh
 */
public class JSONUtil {

    /**
     * string类型的json转换成json对象
     * @param str
     * @return
     */
    public static JSONObject fromString(String str){
        return JSON.parseObject(str);
    }

    /**
     * string类型的json转换成指定对象
     * @param str
     * @param clazz
     * @return
     */
    public static <T> T fromString(String str,Class<T> clazz){
        return JSON.parseObject(str,clazz);
    }

    /**
     * 对象类型的json转换成string类型
     * @param obj
     * @return
     */
    public static String toString(Object obj){
        if(obj == null) {
            return null;
        }
        return JSON.toJSONString(obj);
    }

    /**
     * string数组转换指定类型的List数组
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> parseArray(String text, Class<T> clazz){
        return JSONArray.parseArray(text, clazz);
    }

    /**
     * string数组转换json数组
     * @param text
     * @return
     */
    public static JSONArray parseArray(String text){
        return JSONArray.parseArray(text);
    }

    public static void main(String[] args) {
        String str = "{\"result\":\"success\",\"msg\":\"成功!\",\"code\":\"200\"}";

        //string转换obj
        ResultData data = fromString(str,ResultData.class);
        System.out.println("string转换obj:"+data.toString());

        //是否JSONObject
        boolean isJsonObj = fromString(str) instanceof JSONObject;
        System.out.println("是否JSONObject:"+isJsonObj);

        //obj转换string
        String strJson = toString(data);
        System.out.println("obj转换string:"+strJson);

        //string数组转换指定类型的List数组
        String strList = "[{\"result\":\"success\",\"msg\":\"成功!\",\"code\":\"200\"}]";
        List<ResultData> listObj= parseArray(strList,ResultData.class);
        System.out.println("string转换指定类型的List数组:"+listObj);

        //string数组转换json数组
        JSONArray jsonArray = parseArray(strList);
        System.out.println("\n============== Lambda 表达式 遍历 JSONArray ============");
        jsonArray.forEach(result -> System.out.println("resultData info: " + result));
    }

}