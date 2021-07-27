package com.test

data class D1(var name: String, val age: Int)/*只有数据类，内部生成了Component方法，才能被解构声明*/

/*在主构造方法内声明属性*/
open class C2(var name: String) {/*主构造方法，没有任何注解或可见性修饰符可省略constructor关键字*/
    /**
     * 主构造方法不包含任何代码，init代码块是主构造方法的一部分，用来写初始化代码初始化代码
     * 因此init代码块执行在次级构造方法之前
     * init代码块和属性初始化，按出现在类体中的顺序执行
     * */
    constructor(name: String, age: Int) : this(name) {/*在类体内用constructor声明次构造方法*/
        println("second constructor")
    }//若同时存在主次构造方法，次构造方法必须直接或间接通过this委托给主构造方法

    //::表示创建成员引用或类引用，把println引用过来做also的参数
    val prop1 = "first:$name".also(::println)

    init {
        println("first init block print: $name")
    }

    val prop2 = "second:$name".also(::println)

    init {
        println("second init block print: ${name.length}")
    }

    lateinit var car: Car/*可以对非基本类型的属性延迟初始化，在初始化前访问该属性会异常，*/
    open fun isValid(): Boolean {
        if (::car.isInitialized)/*引用变量car的isInitialized方法检查是否完成初始化*/
            if (score == 1) return true
        return false
    }

    var subject: String = ""/*变量默认带get/set方法。
    如果要在get/set()内做额外处理，则在变量初始化语句后，定义get/set方法*/
    var score: Int = 0
        //变量的初始化 var 名称：类型=初始化 get() set()
        get() = if (field < 60) 0 else 1
        set(value) {/*get/set()方法中field代指该变量；value代指新的值*/
            field = value
            println(value)
        }
}

class C3 {
    var nums = mutableListOf<Int>();

    constructor(i: Int) { //在类体内用constructor声明次构造方法
        nums.add(i)
    }
}

class C4(i: Int) {
    var nums = mutableListOf<Int>(i);

    //若同时存在主次构造方法，次构造方法必须直接或间接通过this委托给主构造方法
    constructor(i: Int, index: Int) : this(i) {
        nums.add(index, i)
    }
}

fun main() {
    val c2 = C2("Tom", 20)
    c2.subject = "math"
    c2.score = 70
    println("${c2.subject}:${c2.score}")
    val d1 = D1("Hurry", 19)
    var (name, age) = d1

}
/*对象表达式就是匿名对象：像java中的匿名类*/
/*对象表达式*/
val v1 = object : C2("Jim") {
    override fun isValid(): Boolean {
        return super.isValid()
    }
}
/*未声明类型的对象表达式*/
val v2 = object {
    var x: Int = 1
    var y: Int = 5
}
/*匿名对象做公有方法返回值或变量赋值时，实际类型是匿名对象的超类；未声明类型的匿名对象超类是Any*/
private fun foo() = object {val x="X"}/*返回类型是匿名对象类型，x可被访问*/
fun foo2() = object {val x="X"} /*返回类型是Any，x不可被访问*/