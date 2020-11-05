package com.array;

import java.util.Arrays;
import java.util.List;

public class TestArrayBox {
    public static void main(String[] args){
//        ArrayBox arrayBox = new ArrayBox();
//        for (int i=1;i<=11;i++){
//            arrayBox.add(i*10);
//        }
//        System.out.println("get index value:"+arrayBox.size());
//        System.out.println("real length:"+arrayBox.elementData.length);

        List<String> strList = Arrays.asList("YangHang", "AnXiaoHei", "LiuPengFei");
        strList.forEach(System.out::println);
    }
}
