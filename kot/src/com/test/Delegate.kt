package com.test

//委托模式已经证明是实现继承的一个很好的替代方式， 而 Kotlin 可以零样板代码地原生支持它
interface Base {
    val message: String
    fun print()
    fun printChar()
}

class BaseImpl(val x: Int) : Base {
    override val message = "BaseImpl: x = $x"
    override fun print() = print(x)
    override fun printChar() = println(x)
}

//Derived 类可以通过将其所有公有成员都委托给指定对象b,来实现一个接口 Base：
//而BaseImpl对象的实例b作为Derived构造方法的参数传入
//继承来的
class Derived(b: Base) : Base by b{
    override val message = "Message of Derived"
    override fun printChar() = println("xxx")
//    override fun print() = print()/*message 重写委托来的成员不能互相使用*/
}

fun main(args: Array<String>) {
    val b = BaseImpl(10)
    val derived = Derived(b)
    derived.print()
    derived.printChar()
}