package com.test

/**
 * 抽象：方法没有方法体；变量没有赋值语句。
 * 必须要复写才有具体的意义，复写必须加override前缀
 */

/**
 * 抽象类中的方法和变量前加abstract关键字就变成抽象的
 */
abstract class A1{
    abstract fun f1()
    abstract var v1:Int
    fun f2(){}
    var v2:Int=0
}
/**
 * 接口中方法和变量都必须是抽象的，不用加abstract关键字就是。
 * 但方法可以加方法体不再抽象，类似于java9中的默认函数
 */
interface I1{
    fun f1()
    fun f2(){}
    var v1:Int
    var c: Car
}