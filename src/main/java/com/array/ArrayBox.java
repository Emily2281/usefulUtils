package com.array;

public class ArrayBox implements Box{

    //存储数组默认长度
    private static final int DEFAULT_CAPACITY = 10;
    public int[] elementData = new int[10];
    //private int[] elementData;
    //设计一个自己的属性，记录数组中的有效元素的个数
    private int size = 0;

    public ArrayBox(){
        elementData = new int[DEFAULT_CAPACITY];
    }

    public ArrayBox(int capacity){
        elementData = new int[capacity];
    }
    //确保数组的容量
    private void ensureCapacityInternal(int minCapacity){
        //判断需要的最小容量比原数组的长度要大
        if(minCapacity - elementData.length > 0){
            //需要扩容
            this.grow(minCapacity);
        }
    }

    private void grow(int minCapacity){
        int oldCapacity = elementData.length;
        //在原数组上扩大1.5倍
        int newCapacity = oldCapacity+(oldCapacity >>1);
        System.out.println("add capacity======ing===="+newCapacity);
        if(newCapacity - oldCapacity < 0){
            newCapacity = minCapacity;
        }
        //创建新数组，将旧数组中的元素全部移入新数组中
        elementData = this.copyOf(elementData,newCapacity);
    }

    //创建新数组，将旧数组中的元素全部移入新数组中
    private int[] copyOf(int[] oldArray,int newCapacity){
        int[] newArray = new int[newCapacity];
        for (int i=0;i <oldArray.length; i++){
            newArray[i] = oldArray[i];
        }
        return newArray;
    }

    //检测给定的位置是否合法
    private boolean rangeCheck(int index){
        if (index < 0 || index >= size){
            throw new BoxIndexOutOfBoundException("index:"+index+",size:"+size);
        }
        return true;
    }

    //存放element的数据
    public boolean add(int element){
        this.ensureCapacityInternal(size+1);
        elementData[size++] = element;
        return true;
    }

    //获取给定位置的元素
    public int get(int index){
        //检测给定的位置是否合法
        this.rangeCheck(index);
        return elementData[index];
    }

    //删除给定位置的元素
    public int remove(int index){
        this.rangeCheck(index);
        //找到index的元素保留起来
        int oldValue = elementData[index];
        //从index位置开始至最后，后面元素依次往前移覆盖
        for (int i=index; i < size-1; i++){
            elementData[i] = elementData[i+1];
        }
        elementData[--size] = 0;
        return oldValue;
    }

    //获取元素的长度
    public int size(){
        return size;
    }
}
