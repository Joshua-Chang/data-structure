package com.data.linkedlist;

/**
 * 1. 编译器给内部类和外部类的每个私有属性，都生成了相应的`access$0 access$1..access$5` 等静态方法，以供对方**访问**。
 * 2. 内部类构造的时候，会将外部类的引用`this$0`传递进来，并且作为内部类的一个属性。**内部类会持有一个其外部类的引用**，因此内部类不必通过实例外部类，就可以直接访问外部类的属性。但访问其私有属性是因为编译生成的`access`方法。
 *    外部类可以通过实例化内部类，来访问其内部属性。可以访问内部类的私有属性，同样是编译时对方暴露了访问方法`access`。**匿名内部类的私有成员就不可被外部类访问**。
 */
public class Outer {
    private char outChar;
    public void fun1(){
        Inner inner = new Inner();
        inner.inChar='1';
    }
    public class Inner {
        private char inChar;
        public void fun2(){
            outChar='2';
        }
    }
}
