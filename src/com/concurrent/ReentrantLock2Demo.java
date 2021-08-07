package com.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 打印机 打印多张纸的正反面
 */
public class ReentrantLock2Demo {
    // 公平锁，所有进入阻塞的线程均有公平的机执行
    // 默认非公平锁
    // 优点:允许线程插队，避免每一个线程都先进入阻塞，再唤醒.性能高。
    // 缺点:线程可以插队，导致队列中可能会存在线程饿死的情况，一直得不到锁，一直得不到执行。
    static class Printer{
        ReentrantLock lock=new ReentrantLock(false);
        void print(){
            String name = Thread.currentThread().getName();
            try {
                lock.lock();
                System.out.println("打印"+name+"的正面");
                Thread.sleep(10);
                lock.unlock();
                lock.lock();
                //公平锁下,当前释放锁,其他线程公平竞争锁.当前线程再去获取锁时,和其他阻塞的线程公平竞争,可能获取不到执行机会
                //非平锁下,当前线程刚释放锁,再去获取锁,比唤醒阻塞的其他线程性能开销小,所以当前线程优先获取锁去执行
                System.out.println("打印"+name+"的反面");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Printer printer = new Printer();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                printer.print();
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }
}