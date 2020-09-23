package funcmethods

object FunctionsAndMethods extends App {

  //method
  def addNumbers (a: Int, b: Int) : Int = {
    a+b
  }
  var newNum = addNumbers(4,5)
  println(s"calling addNumbers method $newNum")

  //Functions are objects
  val AddNumbers = (a: Int, b: Int)  => {
    a+b
  }: Int

  newNum = AddNumbers(14,6)
  println(s"calling addNumbers FUNCTION $newNum")

  //functions class implements traits
  //it can be called with apply

  val data = Array("hi", "how", "are", "you?")

  //this method accepts an Arrary and return it's length
  def getListLengthMethod(a: Array[String]) : Int = a.length

  var getListLengthFunction1 = () => data.length
  var getListLengthFunction2 = (a: Array[String]) =>{
    a.length
  }
  var a = getListLengthMethod(data)
  var b = getListLengthFunction2(data)
  println(s"calling a METHOD $a")
  println(s"calling a FUNCTION $b")

  println(getListLengthMethod(data).getClass)
  
  println(getListLengthFunction1().getClass)
  println(getListLengthFunction2(data).getClass)

  println(getListLengthFunction1.apply())
  println(getListLengthFunction2.apply(data))

  //method
  def stringCheckMethod (str: String) : Boolean =   data.contains(str)
  //function
  val stringCheckFunc = (str: String) => data.contains(str)

  println("boolean method "+ stringCheckMethod("hi"))
  println("boolean function "+ stringCheckFunc("how"))

  //calling a method and assigning the return value
  val strExists = stringCheckMethod _
  println("is 'hi' exists in the data array " +  strExists("hi"))

  //Function
  // can have varriance based on the number of input parameters
  //its and instance of a class
  //cab contains methods as well
  //apply method of a function, invokes the function when called

  //1. parameter of a function - also can be a function
  //2. function can be stored in a variable

  //anonymous function - no name
  var result = (x: Int) => x *100
  a = result(3)
  b= result(5)

  println(s"anonymous function calling with 3*100 = $a")
  println(s"anonymous function calling with 5*100 = $b")

  //functions can be reassigned
  result = (x: Int) => x * 123

  var c = (x: Int) => x * 123
  var d = c(5)
  println(s"calling anonymous function $d")

  var twoOps = (x: Int, y: String) => (x * 123).toString + y
  var newString = twoOps(10,"Ten")
  println(s"calling  anonymous function with 2 parameters::$newString")

  //function literal
  (x: Int) => x * 100


  //this need to be assigned, before to be invoked

  var x = (x: Int) => x * 45
  println(x(3))

  x = (x: Int) => x * 100
  var newVar = x(14+1)
  println(s"function changed :: $newVar")

  var sumIt = (x: Int, y: Int) => {
    var sum = x+y
    var sub = x-y
    (sum,sub)
  }
  var sums = sumIt(3,4)
  println(s"sum function return value $sums")

  val stockList = List(1,2.3, 3, 4.4, 5, 6,6.8)

  (stock: Double) => println(stock) //this is function literal
  //but need to be called
  stockList.foreach((stock: Double) => println(stock))

  stockList.foreach(stock  => println(stock))

  val filteredStock = stockList.filter((stock: Double) => stock >5)//greater than 5
  println("filtered stock greater than 5" + filteredStock)

  var greaterSix = stockList.filter(stock => stock >6)
  println(greaterSix)

  //placeholder called _
  var lessThan3 = stockList.filter(stock => stock < 3)
  println(lessThan3)

  var placeholder = stockList.filter(_ < 2) // _ one or more parameter
  println(placeholder)

  println("----")
  stockList.foreach(println(_))

  val nameList = List("Joshua","Nathaniel", "Angeline", "Gratia")
  println(nameList)
  val nameTrans = nameList.map(_.toUpperCase)
  println(nameTrans)

  var addTwoNums = (x: Int, y: Int) => x + y
  var addThreeNums = (addTwoNums : Int, z: Int) => addTwoNums + z

  var num2 = addTwoNums(5,5)
  var nums3 = addThreeNums(num2,5)

  println("3 numbers added " + nums3)

  //calling with apply

  println(addTwoNums.apply(7,7))
  println(addThreeNums.apply(num2,56))
}
