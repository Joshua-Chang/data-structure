package com.test

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class Other {
    var str: String = ""
    operator fun getValue(example: Example, property: KProperty<*>): String {
        return "$example, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(example: Example, property: KProperty<*>, s: String) {
        println("$s has been assigned to '${property.name}' in $example.")

    }
}

var outField: String = ""

class Example {
    /*委托属性：属性声明 by 委托方，将属性的get/set方法委托给对方的get/setValue方法*/
    var p: String by Other()

    /*kotlin标准库的几种委托工厂方法*/

    /*lazy 属性的求值是同步锁的（synchronized）*/
    val lazyValue: String by lazy {/*延迟属性：接收lambda返回，返回的实例可以作为实现延迟属性的委托：
     第一次调用 get() 会执行已传递给 lazy() 的 lambda 表达式并记录结果， 后续调用 get() 只是返回记录的结果。*/
        println("xxx")
        "Hello"
    }

    /*可观察属性 Observable*/
    //Delegates.observable接受两个参数：初始值与修改时处理程序（handler）:
    //每当我们给属性赋值时会调用该处理程序（在赋值后执行）。它有三个参数：被赋值的属性、旧值与新值
    //如果你想截获赋值并“否决”它们,使用 vetoable() 取代 observable()。 在属性被赋新值生效之前会调用传递给 vetoable 的处理程序。
    var name: String by Delegates.observable("oldChar") { property, oldValue, newValue ->
        println("$oldValue->$newValue")
    }

//    var field: String by this::name/*类内其他属性*/
//    var field2: String by ::outField/*外部属性*/
//    var field3: String by Other()::str/*其他类的属性*/
    /*在版本更新后，属性更名也能向后兼容*/
}

/**
 * 在json解析等场景中
 * 用map存储属性的值时
 * 可以用map做属性的委托方
 */
class User(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
}

fun main(args: Array<String>) {
    val example = Example()
    example.p = "Hello"/*set*/
    println(example.p)/*get*/
    println(example.lazyValue)
    println(example.lazyValue)
    example.name = "Tom"
    example.name = "Jim"
    val user = User(mapOf("name" to "Tom", "age" to 18))
    println("${user.name}:${user.age}")
}
