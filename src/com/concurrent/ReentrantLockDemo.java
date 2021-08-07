package com.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;


public class ReentrantLockDemo {
    static List<String> tickets = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println((-1 << 29) & ~((1 << 29) - 1));
        for (int i = 0; i < 100; i++) {
            tickets.add("票_" + (i + 1));
        }
        new ReentrantLockDemo().sellTickets();
    }

    private void sellTickets() {
        final Buyer testDemo = new Buyer();
        for (int i = 0; i < /*10*/100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    testDemo.buy();
                    testDemo.buy();
                }
            }).start();
        }
    }

    class Buyer {
        ReentrantLock reentrantLock = new ReentrantLock();

        void buy() {
            String name = Thread.currentThread().getName();
            try {
                reentrantLock.lock();
                System.out.println("买票人：" + name + "准备好了");
                Thread.sleep(100);
                System.out.println("买票人：" + name + "正在买票");

                /*再入锁:已有锁,再加锁*/
//                reentrantLock.lock();
//                System.out.println("买票人：" + name + "再次准备好了");
//                Thread.sleep(1000);
//                System.out.println("买票人：" + name + "再次正在买票");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
                System.out.println("买票人：" + name + " 买到的票是..." + tickets.remove(0));

//                reentrantLock.unlock();/*解锁时再解锁*/
//                System.out.println("买票人：" + name + " 再次买到的票是..." + tickets.remove(0));
            }
        }
    }
}
