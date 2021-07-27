package com.test

import java.lang.StringBuilder


class Car {
    var speed: Int? = null
    var price: Int? = null
}
class Bus(val speed:Int,val price:Int)
fun b(){
    val bus = Bus(123, 34000)
//    var (speed,price)=bus
}

object Utils {
    fun getScore(value: Int): Int {
        return 2 * value
    }

//    operator fun invoke() {
//        TODO("Not yet implemented")
//    }
}

class Utils2 private constructor() {
    companion object {
        fun getScore(value: Int): Int {
            return 2 * value
        }
    }
}

fun main() {
//    arr()
//    collection()
    spread()
}

fun is_as() {
    var bmw = Car()
    var car1 = bmw as Car
    if (bmw is Car) {
        var car2 = bmw
    }
}

fun collection() {
    val cars = listOf<Car>(Car().apply { speed = 1 }, Car().apply { speed = 2 })
    mapOf<Car, Int>(Car() to 198, Car() to 189)

    cars.forEach {
        println(it.speed)
    }
    cars.filter { it.speed!! > 1 }.forEach {
        println(it.speed)
    }
    //cars.add() 不可变集合
    val cars2 = mutableListOf<Car>(Car().apply {
        speed = 100
        price = 18000
    })
    cars2.add(Car().also {
        it.price = 17000
        it.speed = 120
    })
    /*集合排序*/
    cars2.shuffle()/*洗牌*/
    cars2.sortWith(Comparator { o1, o2 -> o1.speed!! - o2.speed!! })
    cars2.sortWith(compareBy { it.speed })
    cars2.sortWith(compareBy({ it.speed }, { it.price }))
    cars2.sortBy { it.price }
    /*集合相加 相减*/
    cars2 += cars
    cars2 -= cars

    val map = mapOf(1 to "ad", 2 to "bc")
    if (map.containsKey(1)) {
        println(map[1])
        println(map.get(1))
    }
    if (1 in map) println(map[1])
    if (map.containsValue("bc")) println("exist")
    println("${map.values}||${map.keys}")
}

fun utils() {
//    Utils()
    Utils.getScore(1)
    Utils2.getScore(2)
}

fun arr() {
    /*类型*/
    byteArrayOf(1)
    shortArrayOf(1)
    intArrayOf(1)
    longArrayOf(1)
    floatArrayOf(1f)
    doubleArrayOf(1.0)
    /*无个数限制*/
    val arr1 = arrayOf(1, 2, 3)
    val arr2 = arrayOfNulls<Int>(1)
    /*有个数限制*/
    val arr3 = Array(5) {}
    val arr4 = Array(5) { i -> (i * i) }
    /*[]是Array的运算符重载方法get/set*/
    arr4[1]

    for (item in arr4) println(item)
    for (index in arr4.indices) println(index)
    for (index in arr4.indices) println(arr4[index])
    for ((item, index) in arr4.withIndex()) println("$index:$item")
    arr3.forEach { println(it.toString()) }
    arr4.forEachIndexed { index, item -> println("$index:$item") }
}

fun spread() {
    fun append(vararg str: Char): String {
        val sb = StringBuffer()
        for (s in str) sb.append(s)
        return sb.toString()
    }

    val hi = charArrayOf('h', 'i', '!')
    val hel = charArrayOf(*hi, 'h', 'e', 'l')
    val result = append(*hel, 'l', 'o')
    println(result)
}
