package com.test

import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * 扩展方法:仅仅是通过静态方法扩展原来的对象
 * 接收类型.方法名 表示对什么类型做扩展
 * 方法体内用this表示接收的对象
 */
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    var temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

/**
public static final void swap(@NotNull List ll, int index1, int index2) {
int temp = ((Number)ll.get(index1)).intValue();
ll.set(index1, ll.get(index2));
ll.set(index2, temp);
}
 */
fun <T> MutableList<T>.swap1(index1: Int, index2: Int) {
    var temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

/*拓展属性：只是多了一种访问属性的方式，不能用来修改现有实例的属性*/
val String.lastChar: Char get() = this.get(this.length - 1)
val <T> List<T>.last: T get() = get(size - 1)

/*为伴生对象的Companion内部类添加扩展*/
class Drive {
    companion object {}
}

fun Drive.Companion.speed(int: Int) {
    println(int)
}

fun main() {
    Drive.speed(100)
}

/*—————————————————————————————————————————————————————————常见自带扩展—————————————————————————————————————————————————————————————————————————————————————————————————————*/
//fun <T, R> T.let(block: (T) -> R):R=block(this)
fun testLet(str: String?) {
    str.let {
        var str2 = "let 拓展"/*限制str2作用域*/
        println(it + str2)
    }
    str?.let { println(it.length) }/*省去判空*/
}

//fun <T, R> T.run(block: T.() -> R):R=block()
data class Room(val address: String, val price: Float, val size: Float)

fun testRun(room: Room) {
    room.run {
        println("$address")
    }
}
//fun <T> T.apply(block: T.() -> Unit): T { block();return this}
fun testApply() {
    ArrayList<String>().apply {
        add("testApply")
        add("testApply")
        add("testApply")
        println("$this")
    }.let { println(it) }
}

fun emptyStr(){
    "".isNullOrEmpty()
    "".isNullOrBlank()/*空格*/
}


