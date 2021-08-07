package com.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    static class Task {
        AtomicInteger atomicInteger = new AtomicInteger();
        volatile int volatileCount = 0;//volatile只能保证原子操作的安全

        void increment1() {
            atomicInteger.getAndIncrement();
        }

        void increment2() {
            volatileCount++;
        }

    }

    public static void main(String[] args) {
        Task task = new Task();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    task.increment1();
                    task.increment2();
                }
            }
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();


        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //使用join
        System.out.println(task.atomicInteger+" "+task.volatileCount);
    }
}
