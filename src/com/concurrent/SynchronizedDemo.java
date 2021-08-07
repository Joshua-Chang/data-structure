package com.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * 卖票的并发场景--不设置并发安全措施，会出现多人买到同一张票
 * <p>
 * synchronized
 * 如果加在代码块上面，为获取到对象锁的线程，可以访问同步代码块之外的代码
 * 如果加在实例方法上，对该实例加锁，
 * 加在static方法上面，就相对是给Class对象加锁，由于在jvm中只会存在一份class对象。所以此时无论是不是同一个java对象，去访问同步访问，都只能排队
 */
//synchronized优点：不用自己释放锁，避免锁死 缺点：不灵活
public class SynchronizedDemo {
    static List<String> tickets = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println((-1 << 29) & ~((1 << 29) - 1));
        for (int i = 0; i < 10; i++) {
            tickets.add("票_" + (i + 1));
        }
        new SynchronizedDemo().sellTickets();
    }

    private void sellTickets() {
        final Buyer testDemo = new Buyer();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    testDemo.buy();//a.对实例方法加锁,对该实例对象有效
//                    new Buyer().buy();//a.对不同实例对象无效
//                    new Buyer().buy();//b.对静态方法加锁,对不同实例对象有效

//                    加在static 方法上面，就相对是给Class对象加锁，
//                    由于在jvm中只会存在一份class对象。所以此时无论是不是同一个java对象，去访问同步访问，都只能排队
                }
            }).start();
        }
    }

    /*static*/ class Buyer {
        /*synchronized*/ void buy() {//a.加在实例方法上，对该实例加锁;和其他实例无关
//        synchronized static void buy() {//b.加在静态方法上，对class加锁;其他实例也受影响
            String name = Thread.currentThread().getName();
            System.out.println("买票人：" + name + "准备好了");
            synchronized  (this){//c.对代码块加锁 代码块之外的部分其他线程可以访问
                try {
                    Thread.sleep(1000);
                    System.out.println("买票人：" + name + "正在买票");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("买票人：" + name + " 买到的票是..." + tickets.remove(0));
        }
    }
}
