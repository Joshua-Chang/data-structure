package com.test

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class HttpMethod(val value: String)
interface Api {
    val name: String
    val version: String
        get() = "1.0"
}
@HttpMethod("Get")
class ApiGetArticles:Api{
    override val name: String
        get() = "/api.articles"
}
fun fire(api: Api){
    val annotations = api.javaClass.annotations
    val method = annotations.find { it is HttpMethod } as HttpMethod
    println("${method.value}")
}