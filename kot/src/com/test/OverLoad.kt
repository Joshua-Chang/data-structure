package com.test

/**
 * 重载有默认值或显式为空的变量
 */
class OverLoad @JvmOverloads constructor(
        name: String="",
        age: Int?=null,
        level: Int=1
)
fun test(){
    OverLoad()
    OverLoad("tom")
    OverLoad("tom",2)
    OverLoad("tom",2,3)
}