package com.test

interface Drinks<T> {
    fun taste(): T
    fun price(t: T)
}

abstract class Color<T>(var t: T/*泛型字段*/) {
    abstract fun printColor()
}

class Blue(val color: String = "blue")
class BlueColor(t: Blue) : Color<Blue>(t) {
    /*必须直接或间接的继承父类的构造函数*/
    override fun printColor() {
        println("${t.color}")
    }
}

fun <T> fromJson(json: String, tClass: Class<T>): T? {
    val t: T? = tClass.newInstance()
    return t
}

fun <T : Comparator<T>?> sort(list: List<T>?) {}/*类型上界约束*/
fun <T> sortChar(list: List<T>)
        where T : CharSequence,/*多类型上界约束*/
              T : Comparator<T> {
}

fun sumOfList(list: List<out Number>){}/*extend*/
fun sumOfList2(dest:Array<in String>){}/*super*/

