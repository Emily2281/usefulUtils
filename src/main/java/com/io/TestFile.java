package com.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestFile {
    public static void main(String[] args) {
        try {
//            FileInputStream fis = new FileInputStream("test.txt");
//            int i = fis.read();
//            while (i!=-1){
//                System.out.print((char)i);
//                i=fis.read();
//            }
            FileOutputStream fos = new FileOutputStream("test.txt",true);
            byte[] b= new byte[]{'a','b'};
            fos.write(b);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
//            fis.close();
        }
    }
}
