package com.test

import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.declaredMembers

class Dog ()
fun testClass(javaclass: Class<*>, kclass: KClass<*>) =0
fun main(args: Array<String>) {
    val dog = Dog()
    //一、直接获取
    Dog::class//获得KClass类：Dog.class
    dog::class//获得KClass对象：dog.getClass()
    dog.javaClass//获得java的Class对象：dog.getClass()

    //KClass与java的Class反射语法不同，习惯使用java的
    Dog::class.declaredMembers//KClasses.getDeclaredMembers(Reflection.getOrCreateKotlinClass(Dog.class));
    dog.javaClass.declaredFields//dog.getClass().getDeclaredFields();

    //二、间接转换
    //从KClass获取javaClass
    Dog::class.java.declaredFields//Dog.class.getDeclaredFields();
    dog::class.java.declaredFields//dog.getClass().getDeclaredFields();
    //从javaClass获取KClass
    dog.javaClass.kotlin.declaredMembers//KClasses.getDeclaredMembers(JvmClassMappingKt.getKotlinClass(dog.getClass()));


    //、结论：毕竟在kotlin中，用类或实例先访问KClass再转成javaClass
    // 即依赖了Kotlin的环境，又附和java的反射习惯
    Dog::class.java
    dog::class.java

    testClass(dog::class.java,Dog::class)
    testClass(Dog::class.java,dog::class)
    testClass(dog.javaClass,dog.javaClass.kotlin)
}