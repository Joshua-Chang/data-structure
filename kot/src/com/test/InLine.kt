package com.test

inline fun sum(a:Int,b :Int):Int=a+b


/**
 * 某些时候业务逻辑需要围绕某种类型创建包装器。然而，由于额外的堆内存分配问题，它会引入运行时的性能开销。
 * 此外，如果被包装的类型是原生类型，性能的损失是很糟糕的，因为原生类型通常在运行时就进行了大量优化，
 * 然而他们的包装器却没有得到任何特殊的处理。
 *
 * 为了解决这类问题，在类的前面定义一个 inline 修饰符，叫内联类
 * 类的数据被 “内联”到该类使用的地方（类似于内联函数中的代码被内联到该函数调用的地方）
 * 内联类必须含有唯一的一个属性在主构造函数中初始化
 * 内联类只能有一些简单的计算属性
 * 不能有init块、不能有get/set使用的field/value幕后字段
 */
inline class Name(val s: String) {
    val length: Int
        get() = s.length
}
/**
 * 使用高阶函数会带来一些运行时的效率损失：每一个函数都是一个对象，并且会捕获一个闭包。
 * 即那些在函数体内会访问到的变量。 内存分配（对于函数对象和类）和虚拟调用会引入运行时间开销。
 * 通过内联化 lambda 表达式可以消除这类的开销
 * inline: 从编译器角度将函数的函数体复制到调用处实现内联。
 * noinline: 声明 inline 函数的形参中，不希望内联的 lambda
 * crossinline: 表明 inline 函数的形参中的 lambda 不能有 return
 * 使用内联的情况下，只需要一个方法栈帧，降低了方法调用的成本。
 * 但内联对性能的影响微乎其微，内联超长的方法反而得不偿失
 * kotlin内联方法主要是消除高阶函数：函数做参数或返回值 带来的额外开销
 * 方法做参数或返回值时
 * 不访问外部作用域的成员，则被编译成Function0接口invoke方法
 * 访问外部作用域的成员，则会new一个持有外部对象的Function0实例并调invoke方法
 * 而使用内联方法，不会有Function0等开销
 */
inline fun runCatch(/*crossinline */block:()->Unit){
/*crossinline 做参数或返回值的函数，不能将内联到的整个方法都退出，只能return@label退出当前做参数/返回值的函数*/
    println("before")
    block()
    println("after")
}
fun run(){
    var message="xxx"
//    runCatch{ println("xxx")}/*不访问闭包成员*/
//    runCatch{ println(message)}/*访问*/

    //只有内联函数，才能在函数做参数/返回值里直接返回
    /*将runCatch里的代码复制到run里，return是整个外部函数run都退出*/
//    runCatch{return}/*只输出before。crossinline情况下此行编译失败*/
    runCatch{return@runCatch}/*只退出到runCatch*/
    println("end")
}

/**
 * 高阶函数一旦加上inline关键字，所有的做参数/返回值的函数都会内联
 * 假如参数block1函数 代码块巨长(或者其他原因)，不想将其内联。用noinline
 */
inline fun test(noinline block1: () -> Unit, block2: () -> Unit) {
    block1()
    println("xxx")
    block2()
}

inline fun sum(a: Int,b: Int,/*crossinline*/ add:(result:Int)->Unit):Int{
    val i = a + b
    add.invoke(i)
    return i
}

fun main(args: Array<String>) {

//    sum(1,2)
//    run()
    println("Start")
    sum(1,2){/*内联函数的作为参数/返回值的函数中有return语句，导致被内联复制到的目的地函数整个退出*/
        println("result=$it")
//        return/*导致main函数return*/ /*对做参数/返回值的函数加crossinline则不允许整个退出，只允许 return@label */
        return@sum/* 使用return@label 语法，在return时返回到对应的label而不是整个函数*/
    }
    println("End")
}
