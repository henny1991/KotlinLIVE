package com.yhe.kotlinlive.test

open class Base(override var level: Int) : Infoable{

    companion object{
        val CONST_1: Int = 1;
    }

    override fun info() {

    }  //kotlin的类，方法默认是final的； 如果想要被继承，则要使用open修饰符


}

val CONST_2 = 2;

class Seed constructor(var sss: Int) : Base(1){

    override fun info(){
        super.info()
        println("send info $sss，" + this);
    }
}

class DD{

    constructor(a: Int){

    }

    constructor(a: Int, s: String){

    }
}

class Counter constructor (initValue: Int, var seed: Seed){ //主构造器

    init {
        println("Counter init")
    }

    constructor(initValue: Int, seed: Seed, flag: String) : this(initValue, seed){ //次构造器必须代理主构造器， init函数在次构造器前执行
        println("constructor Counter 3 parameters")
    }

    var count: Int = initValue // var 可变变量
        get() = field //后端变量
        set(value) {
            if (value < 10) {       // 如果传入的值小于 10 返回该值
                field = value
            } else {
                field = -1         // 如果传入的值大于等于 10 返回 -1
            }
        }

    init {
        println("init")

        println(seed)
        ::seed.set(Seed(2))
        println(seed.info())
    }

    fun increase() :Int = ++count

    fun decrease():Int = --count



}

fun main(args: Array<String>){
    var text = "haha"
    println(text[2])

    val text2 = """
    |start     
    |多行字符串
    |多行字符串
    |end
    """          //三个冒号“”“括起来，支持多行字符串
    println(text2.trimMargin("|")) // 使用trimMargin方法可以删除每行的前置空格， 默认的边界前缀是”|“， 所以在此例trimMargin也可以使用空参方法

    var y = 1;
    println("${y is Int}");

    val v: Int;
    var counter: Counter? = Counter(5, Seed(3), "HEIHEI"); //类型后面加？号代表可为空变量
    //counter= null;
    counter?.count = 100; //调用set方法


    v = 1;
    //v = 2;   //can not be reassigned

    println(v);
    println("${"it is a dog".replace("dog", "cat")}, but so big") //字符串模板
    println("${counter?.decrease()} - HAHA") //如果调用可为空变量，Only safe (?.) or non-null asserted (!!.) calls are allowed
    println("${'$'}9.99");
    printVarArgs("1","2","3");
    println()
    var arrs: Array<String> = Array(3, { j -> ("str${j}") })
    printArray(arrs)
    println()

    for (i in 1..4){  // .. 区间，类似于 1<= i && i =< 4
        print(i)
    }
    println()

    if (2 in 1..4){
        println("In ininin")
    }

    for (i in 1..4 step 2){  //还可以指定步长， 输出13
        print(i)
    }
    println()

    for (i in 4..1){  //无法输出
        print(i)
    }
    println()

    for (i in 4 downTo 1){  //输出4321
        print(i)
    }
    println()

    for (i in 1 until 4){  //类似于 1 <= i < 4
        print(i)
    }
    println()

    for (i in 1 until 9 step 2){  //输出1357
        print(i)
    }
    println()

    var x = 5;
    when (x) {
        in 1..10 -> print("x is in the range")
        !in 10..20 -> print("x is outside the range")
        else -> print("none of the above")
    }

}

fun printArray(arr: Array<String>){
    for (str in arr){
        print("$str,");//template
    }
    println();

    for (index in arr.indices) { // 也可以使用索引遍历
        println("item at $index is ${arr[index]}")
    }

    for ((index, value) in arr.withIndex()) {  //相当于entrySet
        println("the element at $index is $value")
    }

}

//可变参数
fun printVarArgs(vararg v: String){
    for (str in v){
        print("$str,");//template
    }
}