package com.array;

import java.util.Random;

public class TestRandomArray {
    public static void main(String[] args){
//        Random random = new Random();
//        System.out.println("随机生成数："+random.nextInt(5));
        int[] array = {10,20,30,40,50,60,70,80,90};
        int[] newArray =  testRandom(array);
        for (int i = 0; i < newArray.length; i++){
            System.out.print(newArray[i]+"  ");
        }
    }

    //数组乱序 洗牌
    public static int[] testRandom(int[] array){
        //创建新数组
        int[] newArray = new int[array.length];
        //记录原数组长度
        int count = array.length;
        //索引
        int index = 0;
        //位置
        int position = 0;
        //记录新数组索引
        int k = 0;
        do{
            //每次从已知数组随机一个数
            Random random = new Random();
            //将长度-1
            int r = count - index;
            //随机生成要交换的位置
            position = random.nextInt(r);
            //将随机到的位置的值放入新数组
            newArray[k++] = array[position];
            //将原数组最后一个值放入随机到的位置
            array[position] = array[r - 1];
            //处理下一个随机数
            index++;
        }while (index < count);

        return newArray;
    }
}
