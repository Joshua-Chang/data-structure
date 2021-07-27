package com.test
//带有接收者的函数字面值
//带有接收者的函数类型:A.(B) -> C 表示可以在 A 的接收者对象上以一个 B 类型参数来调用并返回一个 C 类型值的函数。


//在这样的函数字面值内部，传给调用的接收者对象成为隐式的this，
//以便访问接收者对象的成员而无需任何额外的限定符（当然使用this也能访问）
//和扩展函数允许在函数体内部访问接收者对象的成员类似
val sum: Int.(Int) -> Int = { i -> plus(i) }
val sum1 = fun Int.(i: Int) = this + i/*意思一样*/
//当接收者类型可以从上下文推断时，lambda 表达式可以用作带接收者的函数字面值。
class HTML{
    fun head(){}
    fun body(){}
}
fun html(init:HTML.()->Unit):HTML{/*接收一个名为init的函数，该函数类型为HTML.()->Unit*/
    /*即需要传一个HTML类型的实例做接受者，并且可以在函数内部调用该实例的成员*/
    val html = HTML()//接受者
    html.init()//将接受者传给lambda
    return html
}
fun main(args: Array<String>) {
    html {
        body()//调用接受者的方法
    }
}