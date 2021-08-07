package com.concurrent;

public class ConcurrentTest {
    Object object = new Object();
//    Thread t1 = new Thread(new Runnable() {
//        @Override
//        public void run() {
//            System.out.println( "t1 run");
//            synchronized (object) {
//                object.notify();
//            }
//            System.out.println(  "t1 end");
//        }
//    });
//
//    class T2 extends Thread {
//        @Override
//        public void run() {
//            super.run();
//            System.out.println(  "T2 run");
//            synchronized (object) {
//                try {
//                    object.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(  "T2 end");
//            }
//        }
//    }

    private volatile boolean hasNotify = false;

    class R1 implements Runnable {
        @Override
        public void run() {
            System.out.println("R1 run");
            synchronized (object) {
                try {
                    if (!hasNotify) {
                        object.wait(2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("R1 end");
            }
        }
    }

    class R2 implements Runnable {
        @Override
        public void run() {
            System.out.println("R2 run");
            synchronized (object) {
                object.notify();
                hasNotify = true;
            }
            System.out.println("R2 end");
        }
    }

    public void test() {
        new Thread(new R1()).start();
        new Thread(new R2()).start();
    }

    public static void main(String[] args) {
        ConcurrentTest test = new ConcurrentTest();
        test.test();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2");
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" end");
    }
}
