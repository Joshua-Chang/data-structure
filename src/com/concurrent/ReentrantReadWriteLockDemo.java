package com.concurrent;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo {
    static class Document{
        ReentrantReadWriteLock lock;
        ReentrantReadWriteLock.ReadLock readLock;
        ReentrantReadWriteLock.WriteLock writeLock;
        public Document() {
            lock=new ReentrantReadWriteLock();
            readLock=lock.readLock();
            writeLock=lock.writeLock();
        }

        void read(){
            String name = Thread.currentThread().getName();
            try {
                readLock.lock();
                System.out.println(name+"正在读取");
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
                System.out.println(name+"读取完成");
            }
        }
        void write(){
            String name = Thread.currentThread().getName();
            try {
                writeLock.lock();
                System.out.println(name+"正在写入");
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
                System.out.println(name+"释放写锁");
            }
        }
    }

    public static void main(String[] args) {
        Document document=new Document();
        for (int i = 0; i <3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    document.read();
                }
            }).start();
        }
        for (int i = 0; i <3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    document.write();
                }
            }).start();
        }
    }
}
