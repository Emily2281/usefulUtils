package design;

public class Singleton {
    //饿汉 单例
    //private static Singleton singleton = new Singleton();

    //懒汉 单例
    private static Singleton singleton ;

    private Singleton(){

    }
//     饿汉 单例
//    public static Singleton getSingleTon(){
//        return singleton;
//    }

    //懒汉 单例
    public static Singleton getSingleTon(){
        if(singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }


}
