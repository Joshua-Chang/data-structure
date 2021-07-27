package com.test

fun List<Int>.sum(callback: (Int) -> Unit): Int {
    var result = 0
    for (i in this) {
        result += i
        callback(i)
    }
    return result
}

fun List<String>.toIntSum(): (scale: Int) -> Float {
    println("level : 1")
    return fun (scale:Int):Float{
        var result = 0f
        for (i in this) {
            result += i.toInt()*scale
        }
        return result
    }
}
fun Car.deal(block: Car.() -> Unit): Car {
    block()
    return this
}

fun main(args: Array<String>) {
    Car().deal { speed=1 }
    val list = listOf(1, 2, 3, 4, 5)
    val sum = list.sum { println(it) }
    println(sum)

    val list1 = listOf<String>("1", "2", "3", "4", "5")
    val toIntSum = list1.toIntSum()(2)
    println(toIntSum)
}