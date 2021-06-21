package com.yhe.kotlinlive.test

interface Infoable {

    var level: Int

    fun info()

    fun tag() : String{
        //CONST_2
        return this::class.simpleName?:"NULL";
    }


}

