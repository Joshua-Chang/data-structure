package com.test

import sun.net.NetworkClient
import sun.nio.ch.Net
import java.io.File
import java.nio.channels.NetworkChannel

/*
 * Lambda本质是匿名方法
 * 匿名方法没有方法名，但可以（像变量声明一样）拥有字面值
 * 具名方法：不可像变量
 * 匿名方法：可以像变量 拥有方法字面值
 * 匿名方法拥有方法字面值像变量一样，可以做方法的参数或返回值
 * 也可以直接调用或 方法名.invoke()调用
 * */
fun pr0() {
    println()
    addd1.invoke(2,3)
    addd1(1,2)
}

fun pr1() = println()/*具名方法：不可以做变量*/
var pr2 = fun() { println() }/*匿名无参*/
var pr3 = { println() }/*匿名无参，Lambda形式*/
fun add(i: Int, j: Int): Int = i + j /*具名方法：不可以做变量*/
var add = fun(i: Int, j: Int): Int = i + j /*匿名方法addd是和变量名一样的字面值*/
var addd1 = { i: Int, j: Int -> i + j }/*匿名方法，Lambda形式*/
var addd2: (Int, Int) -> Int = { i: Int, j: Int -> i + j }//定义了一个变量addd2，类型是....
var addd3: (Int, Int) -> Int = { i, j -> i + j }
/**
 * 有参匿名方法Lambda表达形式，
 * 可以先var+冒号，声明参数类型类型，再代码
 * 也可以var+等号，声明参数类型和代码一起写  更为常用
 */

/*匿名方法变量做参数*/
fun calculate1(i1: Int, i2: Int, f: (j: Int, k: Int) -> Int) = f(i1, i2)

/*匿名方法变量做返回值*/
var cal = { c: Int -> c * 2 }
fun calculate2(i: Int): (j: Int) -> Int {
/*多种写法*/
/*局部方法：是某个方法内的方法，可以访问闭包的变量。和匿名不是同一维度，互不干涉*/
//    return cal  //匿名方法cal，不是calculate2的局部方法，利用不到闭包内的变量
//    var cal2={c:Int->i+c}
//    return cal2 //匿名方法cal，是calculate2的局部方法，可以访问闭包内的变量
//    return fun (j:Int):Int=i+j/* i：局部方法可以访问外部方法（即闭包）的局部变量*/
    return { c: Int -> i + c } //干脆直接用
}

/*返回值是：以int j为参数1；以int k为参数，返回值为空的函数f0为参数2。返回值为空的函数f*/
fun calculate3(i: Int): (j: Int, (k: Int) -> Unit) -> Unit {
    return fun(j: Int, f0: (k: Int) -> Unit): Unit {
        return f0(i + j)
    }
}

fun main() {
    /*匿名方法变量做参数*/
    calculate1(5, 3, add)/*方法字面值当变量*/
    calculate1(5, 3) { i: Int, j: Int -> i - j }/*直接用方法*/
    /*匿名方法变量做返回值*/
    /*先调第一个函数，在调返回的函数*/
    calculate2(5)
    calculate2(5)(3)/*第二个参数，给做返回值的函数使用*/

    calculate3(5)(3, { k -> println(k) })
    calculate3(5)(3) {
        println(it)
    }
}

fun <T> T.deal(block: T.() -> Unit): T {
    block()
    return this
}

fun Car.deal1(block: Car.() -> Unit): Car {
    block()
    return this
}

// 只有一个抽象方法的接口称为函数式接口或 SAM（单一抽象方法）接口。
// 函数式接口可以有多个非抽象成员，但只能有一个抽象成员。
// 用fun 修饰符在 Kotlin 中声明一个函数式接口
fun interface IntPredicate {
    fun accept(i: Int): Boolean
    fun accept1() {}/*非抽象*/
}

val isEven = IntPredicate { it % 2 == 0 }
val isEven2 = IntPredicate { return@IntPredicate it % 2 == 0 }
val isEven3 = object : IntPredicate {
    /*太麻烦了*/
    override fun accept(i: Int): Boolean {
        return i % 2 == 0
    }
}






//可以为较长的泛型类型、函数类型、类嵌套时可用typealias起一个较短的别名
typealias NodeSet = Set<NetworkChannel>
typealias FileTable<K> = MutableMap<K, MutableList<File>>
typealias MyHandler = (Int, String, Any) -> Unit

class A {
    inner class Inner
} typealias AIn = A.Inner

class Out {
    var outA = 1

    inner class In {
        /*inner：内部类，持有外部类引用，不加访问不到外部类的成员 */
        fun foo() {
            println(outA)
        }
    }
}

/**
 * 密封类用sealed关键字，表示受限制的类继承解构
 * 用在一个值可以为有限的几种类型时
 * 相当于更高级的枚举，配合when可以枚举出所有情况
 */
sealed class Expr/*密封类是抽象的*/
data class Const(val number: Number) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

fun eval(expr: Expr): Double = when (expr) {
    is Const -> expr.number as Double
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
    /*不再需要else，因为已枚举了所有情况*/
}
