package com.string;

import com.obj.ResultData;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.apache.commons.beanutils.PropertyUtils.getPropertyDescriptors;

/**
 * 字符串工具类
 */
public class StringUtil {

    public static final String EMPTY = StringUtils.EMPTY;
    private static final char CAMEL_SEPARATOR = '_';

    public static boolean isEmpty(String str){
        return StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str){
        return StringUtils.isNotEmpty(str);
    }
    public static boolean isNotEmpty(Object str){
        if(str == null)return false;
        return StringUtils.isNotEmpty(str.toString());
    }
    
    public static boolean isEmptySerializable(Serializable s) {
    	return s == null || "".equals(s);
    }

    /**
     * camel2underline(驼峰格式转化成下划线格式)
     * @param s
     * @return
     */
    public static String camel2underline(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i >= 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    if (i > 0)
                        sb.append(CAMEL_SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }


    /**
     * underline2camel(下划线格式转化成驼峰格式，首字母小写)
     * TODO(这里描述这个方法的注意事项 – 可选).
     * @param s
     * @return
     */
    public static String underline2camel(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == CAMEL_SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }


    /**
     * toCapitalizeCamelCase(下划线格式转化成驼峰格式，首字母大写)
     * @param s
     * @return
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = underline2camel(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
    public static boolean isEmpty(Object val) {
        if(val == null)return true;
        if(val instanceof String)return isEmpty((String)val);
        return false;
    }

    /**
     * 生成主键
     * 全大写，压缩无连接符
     * @return
     */
    public static String generatePrimaryKey() {
        return UUID.randomUUID().toString().toUpperCase().replaceAll("-", StringUtils.EMPTY);
    }
    
    
    /**
     * 将二进制转换成16进制
     * @param buf
     * @return
     */
    public static String byte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] hexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
    
    
    /**
      * trim(去前后空格)
      * @param str
      * @return  null时返回""
      */
    public static String trim(String str) {
    	if(str == null) return "";
    	return str.trim();
    }

    /**
     * 方法一
     * JavaBean对象转成Map<String,Object> String 对象属性名，Object 对象属性值
     * @param target
     * @return
     */
    public static Map<String,Object> objectToMap(Object target) {
        Map<String, Object> map = new HashMap<>();
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        for (PropertyDescriptor targetPd : targetPds) {
            Method readMethod = targetPd.getReadMethod();
            if (readMethod!=null) {
                try {
                    if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                        readMethod.setAccessible(true);
                    }
                    Object value = readMethod.invoke(target);
                    if (value != null&&!StringUtils.contains(value.toString(), "class")) {
                        map.put(targetPd.getName(), value);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

    /**
     * 方法二
     * JavaBean转Map
     * @param obj
     * @return
     */
    public static Map<String, Object> beanToMap(Object obj) {
        Map<String, Object> params = new HashMap<>(0);
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
            for (PropertyDescriptor targetPd : descriptors) {
                String name = targetPd.getName();
                if (!StringUtils.equals(name, "class")) {
                    params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
                }
            }
        } catch (Exception e) {
        }
        return params;
    }

    //将Map<String,Object>转bean
    private static void mapToBean(Map<String, Object> map, Object obj) throws Exception {
        //拿到 user 的对应的class对象
        Class<?> clazz = obj.getClass();
        //通过反射拿到所有的属性拿到所有的属性值
        Field[] fields = clazz.getDeclaredFields();

        //遍历属性
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            //获取属性的名称
            String key = f.getName();
            //跳过属性的权限检查
            f.setAccessible(true);
            //都是字符串 操作  进行判断
            //如果属性的类型是int 判断int.class   如果是Ingeter 判断为 Integer.class
            if (f.getType() == int.class){
                f.set(obj,Integer.valueOf((String) map.get(key)));
            }else if (f.getType() == double.class){
                f.set(obj,Double.valueOf((String)map.get(key)));
            }else{
                f.set(obj,map.get(key));
            }
        }
    }

    public static void main(String[] args) {
        //驼峰格式转化成下划线格式
        String underline = camel2underline("userName");
        System.out.println("userName转下划线："+underline);

        //下划线格式转驼峰，首字母大写
        String camel = toCapitalizeCamelCase("user_name");
        System.out.println("下划线转驼峰："+camel);

        //方法一：将Object对象转成Map<String,Object>
        ResultData obj = new ResultData("success","成功","200");
        Map<String,Object> map = objectToMap(obj);
        map.forEach((k,v) -> System.out.println("方法一将Object对象转成Map：key:value=="+k+":"+v));

        //方法二：将Object对象转成Map<String,Object>
        Map<String,Object> beanMap = beanToMap(obj);
        beanMap.forEach((k,v) -> System.out.println("方法二将Object对象转成Map：key:value=="+k+":"+v));

        //将Map<String,Object>转bean
        Map<String,Object> mapbean = new HashMap<>();
        mapbean.put("result","failed");
        mapbean.put("msg","失败");
        mapbean.put("code","400");
        try {
            ResultData resultData = new ResultData();
            mapToBean(mapbean,resultData);
            System.out.println("将Map<String,Object>转成bean:"+resultData.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}