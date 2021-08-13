package com.concurrent;

public class TLTest {
    public static void main(String[] args) {
        String hello = "hello";
        int i = 1;
        ThreadLocal<String> tl1=new ThreadLocal<String>(){
            @Override
            protected String initialValue() {
                return "world";
            }
        };
        ThreadLocal<Integer> tl2=new ThreadLocal<>();

        tl1.set(hello);
        tl2.set(i);
        System.out.println(tl1.get());
        System.out.println(tl2.get());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(tl1.get());
                tl1.set("child");
                tl1.remove();
                System.out.println(tl1.get());

                System.out.println(tl2.get());
            }
        };
        new Thread(runnable).start();
    }
}
