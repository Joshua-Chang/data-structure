package com.test

/**
 * 伴生对象：构建静态单例内部类Companion
 * 若使用@JvmStatic注解，伴生对象的成员成为真正的静态
 */
class U1 private constructor(){
    companion object{
        fun getScore(value: Int): Int {
            return 2 * value
        }
    }
}

/**
 * 对象声明：单例
 */
object U2{
    fun getScore(value: Int): Int {
        return 2 * value
    }
}

/**
 * 私有构造方法+伴生对象（静态单例内部类）
 * object类静态变量
 */
fun x(){
    U1.getScore(1)
    U2.getScore(1)
}