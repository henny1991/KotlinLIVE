package com.yhe.kotlinlive.test


class Counter2(var age: Int){


}

var c = Counter2(3);

fun main(args: Array<String>){

    println((-1).inv());
    println(::test.invoke())

}

fun test() : Int{
    println("HAHA")
    return 1;
}