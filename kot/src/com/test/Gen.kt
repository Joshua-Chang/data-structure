package com.test

interface Drinks<T> {
    fun taste(): T
    fun price(t: T)
}

abstract class Color<T>(var t: T/*泛型字段*/) {
    abstract fun printColor()
    fun test(t: T) {}
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

fun sumOfList(list: List<out Number>) {}/*extend*/
fun sumOfList2(dest: Array<in String>) {}/*super*/

open class Gen() {}
class GenC() : Gen() {}
class Normal<T, G : Gen>(var t: T?, var g: G?) {
    fun getGen(g: Gen) {}
    fun getT(t: T) {}
}

interface Normal2<out G> {
    /*out 只能在输出位置，生产者。如返回值*/
    fun nextGen(): G
}

interface Normal3<in G> {
    /*in 只能在输入位置，消费者。如参数*/
    fun getG(g: G)
}

class RecyclerView {
    inner class ViewHolder() {}
}


abstract class ItemViewHolder<DATA, VH : RecyclerView.ViewHolder>(data: DATA? = null) {
    open fun onViewAttachedToWindow(holder: VH) {}
}

fun main(args: Array<String>) {
    val gc = GenC()
    val normal = Normal(1, gc)
    normal.getGen(gc)
//    ItemViewHolder(gc)
}

