package com.concurrent;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产消费
 */
public class ReentrantLockCondition {
    Condition condition1, condition2;
    volatile int workTask = -1;
    ReentrantLock lock = new ReentrantLock(true);

    public ReentrantLockCondition() {
        condition1=lock.newCondition();
        condition2=lock.newCondition();
    }

    void work1() {
        try {
            lock.lock();
            if (workTask ==-1 || workTask % 2 != 0) {
                System.out.println("work1 休息.........");
                condition1.await();
            }
            if (workTask!=-1) {
                System.out.println("work1 处理" + workTask + "");
                workTask =-1;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void work2() {
        try {
            lock.lock();
            if (workTask ==-1 || workTask % 2 == 0) {//不是自己要处理的工作
                System.out.println("work2 休息.........");
                condition2.await();
            }
            if (workTask!=-1) {
                System.out.println("work2 处理" + workTask);
                workTask =-1;//代表被处理了
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 主管给员工派发工作
     */
    void manager() {
        try {
            lock.lock();
            workTask = new Random().nextInt(100);
            if (workTask % 2 != 0) {
                System.out.println("有新任务" + workTask + ",派给work1");
                condition1.signal();
            } else {
                System.out.println("有新任务" + workTask + ",派给work2");
                condition2.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockCondition lockCondition = new ReentrantLockCondition();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    lockCondition.work1();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    lockCondition.work2();
            }
        }).start();

        for (int i = 0; i < 20; i++) {
            lockCondition.manager();
        }
    }
}
