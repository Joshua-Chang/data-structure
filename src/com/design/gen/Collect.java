package com.design.gen;

import java.util.ArrayList;
import java.util.List;

public class Collect {
//Lev 1

    class Food {
    }

//Lev 2

    class Fruit extends Food {
    }

    class Meat extends Food {
    }

//Lev 3

    class Apple extends Fruit {
    }

    class Banana extends Fruit {
    }

    class Pork extends Meat {
    }

    class Beef extends Meat {
    }

//Lev 4

    class RedApple extends Apple {
    }

    class GreenApple extends Apple {
    }


    public static void main(String[] args) {
    }
    //泛型只存在于源码中，编译时被抹成原始类型object,运行时再通过强转转回来
    //泛型擦除的根本原因：出现泛型这一新的语法糖时，兼容之前没有该语法糖的代码。至于新的语法糖必然是代表进步的，就像声明式UI
    //虽然泛型被抹掉，但声明侧泛型仍以Signature的形式 保留在Class文件的Constant pool中。仍可通过getGenericXXXType/Class获得方法/类的泛型类型，
    //如获取方法上的泛型：getGenericReturnType获取带泛型信息的返回类型 、 getGenericParameterTypes获取带泛型信息的参数类型。
    //类上的泛型getGenericSuperclass
    //retrofit接口方法、 就是通过getGenericXXX获取方法/类上的泛型

    /**
     * 声明侧泛型会以Signature的形式存在Constant pool中，仍可通过getGenericxxx获取
     * 1.泛型类，或泛型接口的声明 2.带有泛型参数的方法 3.带有泛型参数的成员变量
     * retrofit接口方法，getGenericReturn/ParameterType获取接口方法上的泛型
     * 使用侧泛型：
     * 也就是方法的局部变量,方法调用时传入的变量。
     * 因此Gson序列化方法，要额外传入对象的类，再通过类去getGenericSuperclass
     */

    public void test() {
        //PECS的规范，是为了实现集合的多态
        ArrayList<? extends Fruit> fruits2 = new ArrayList<Apple>();//协变
//        fruits2.add(new Apple());/*并不知道要加入的是什么类型，Apple、Orange、Pear都可以*/
        fruits2.get(0);/*不影响取*/

        ArrayList<? super Apple> apples2 = new ArrayList<Fruit>();//逆变
        apples2.add(new Apple());
        apples2.add(new RedApple());//多态为redApple：向下类型转换
        Object object = apples2.get(0);//编译器不知道上限是什么，干脆到顶Object
//        Apple apple2 = apples2.get(0);//编译失败：返回的是object，不能用其子类apple的引用apple2来接收
        Apple apple3 = (Apple) apples2.get(0);//不能直接取；非取不可就强转
    }

    // 在List<? extends Fruit>的泛型集合中。允许读取，禁止写入
    // 集合里存的是Fruit或其子类，却不知道具体是哪个子类。
    // 假如贸然往集合里存Apple，但这个集合本应该存Pear，就会冲突，所以干脆禁止写入。
    public void testCoVariance(ArrayList<? extends Apple> apples) {
        Apple b = new Apple();
        RedApple c = new RedApple();
//        apples.add(b); // does not compile
//        apples.add(c); // does not compile
        Fruit a = apples.get(0);
        Apple d = apples.get(0);
    }

    // 在List<? super Apple>的泛型集合中，允许写入，禁止读取
    // 集合里存的是Apple或其父类，但无法知道是哪个具体的父类。
    // 假如贸然读取成了Apple，但本应该读取为Fruit，产生冲突。所以干脆禁止读取。
    // （编译时泛型类型擦除为object，也可以读取成object再强转成其他类型。）
    public void testContraVariance(ArrayList<? super Apple> apples) {
        Apple b = new Apple();
        RedApple c = new RedApple();
        apples.add(b);
        apples.add(c);
//        Fruit a = apples.get(0); // does not compile
        Object object = apples.get(0);
    }


    public void demo() {
//        Fruit fruit = new Apple();/*多态：苹果 IS-A 水果*/
//        ArrayList<Fruit> fruits = new ArrayList<Apple>();/*编译失败：苹果的集合 NOT-IS-A 水果的集合*/
        ArrayList<?extends Fruit> fruits = new ArrayList<Apple>();/*多余的<?extends Fruit>*/


//        ArrayList<Apple> apples=  new  ArrayList<Fruit> ();
        ArrayList<? super Apple> apples=  new  ArrayList<Fruit> ();
//        apples.add(new Apple());
//        ArrayList<Apple> apples=fruits;

    }

    interface Comparable<T> {
        int compareTo(T other);
    }

    public void demo2(Comparable<Fruit> fruitComparable) {
        fruitComparable.compareTo(new Apple());
        Comparable<? super Apple> appleComparable = fruitComparable;
    }
    public class Collections {
        public <T> void copy(List<? super T> dest, List<? extends T> src)   {
            for (int i=0; i<src.size(); i++) {
                dest.set(i, src.get(i));
//从src中获取(生产)一个泛型类型的值；将此泛型类型的值存入dest中消费调。
            }
        }
    }

}