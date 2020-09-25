package PartiallyAppliedFunction

import FunctionLiteralsAndPlaceholders.StockMarketData

object PartialFunctionDemos extends App {

  val readFinanceData = () => {
    val source = io.Source.fromFile("src/main/resources/stockMarketData.csv")

    for {
      line <- source.getLines().drop(1).toVector
      cols = line.split(",").map(_.trim)
    }yield StockMarketData(cols(0),
                           cols(1).toFloat,cols(2).toFloat, cols(3).toFloat, cols(4).toFloat,
                           cols(5))
  }

  val data = readFinanceData()

  println(s"data size :: ${data.size}")

  for (record <- data){
    println(s"each record :: $record")
  }

  //higher order functions
  //functions which accepts other functions as input parameters

  val extractOpenPrice = (records: Vector[StockMarketData]) => {
    for (record <- records)
      yield (record.company, record.open)
  }

  val listOpenPrice = extractOpenPrice(data)
  println(s"listOpenPrice ${listOpenPrice}")

  //close-open
  val extractDeltaPrice = (records: Vector[StockMarketData]) => {
    for (record <- records)
      yield (record.company,record.close - record.open)
  }

  val listDelta = extractDeltaPrice(data)
  println(s"listDelta ${listDelta}")

  //higher order function HOF
  val hof = (records: Vector[StockMarketData],
             someExtractFunction : Vector[StockMarketData] => Vector[(String, Float)]) =>
          {
            someExtractFunction(records)
        }

  val someFunction1 = (parameter1: Int) => {
    parameter1*parameter1
  }
  val callFunction = someFunction1(10)
  println(s"callFunction :: $callFunction")
  val someFunction2 = (p1: Int, p2: Int) => {
    println(s"executing someFunction2 called as parameter at someFunctionWithHOF2 p1:: $p1 p2:: $p2")
    p1*p2
  }
  //calling a function as parameter
  //calling function is hof: Int => Int
  //hof is the name of the function, carrying one parameter of type Int, which returns a Int
  //then call the same function hot with the first parameter
  //so the second parameter is a function, which uses a parameter, which uses the first prameter

  val someFunctionWithHOF = (parameter1: Int, hof: Int => Int) => {
    hof(parameter1)
  }
  val callHOF = someFunctionWithHOF(25,someFunction1)
  println(s"callHOF $callHOF")

  val someFunctionWithHOF2 = (parameter1: Int, hof: (Int, Int) => Int) => {
    println("---------------executing someFunctionWithHOF2")
    hof(parameter1, parameter1)
    //println("after calling hof(parameter1, parameter1)")
  }
  val HOF2 = someFunctionWithHOF2(30,someFunction2)
  println(s"------------------HOF2 $HOF2")

  val dummyFun1 = (a: Int) => a*a
  val dumVal = dummyFun1(4)//calling a function
  println(s"-----------calling a dummy function directly $dumVal")

  //val dummyHOF1 = (a: Int, b: Int, c:Int) => a+b*c //inputs an int and returns the same
  val dummyHOF1 = (a: Int, b: Int, innerHOF: Int => Int) => {
    innerHOF(a+b)
  }
  val dumVal2 = dummyHOF1(5,5, dummyFun1)
  println(s"-------calling a HOF $dumVal2")


}
