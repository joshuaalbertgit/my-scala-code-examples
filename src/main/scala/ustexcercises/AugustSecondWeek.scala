package ustexcercises

import ustexcercises.AugustSecondWeek.a

/*Exercise – write these programs in functional way.

1.	Write a function to sum all numbers between 2 numbers( a and b)
2.	Write a function to sum squares of numbers between 2 numbers.
3.	Write a function which add all digits of a number to single digit.(for example, 98 is reduced to 17 -> to 8)
4.	Write a function to find fib number for a given index(for example for index 5, fib number is 8)
5.	Write a function to get weather a number is prime or not
6. //currying higher order function annonymomus function
*/

object AugustSecondWeek extends App {

    //1. Write a function to sum all numbers between 2 numbers( a and b)
    var n = 0
    def sumBetweenNumber(a: Int, b: Int) : Int = {
      if (a > b) 0
      else {
        n = n + a
        sumBetweenNumber(a+1,b)
      }
      n
    }
  var a= 1
  var b= 3
  var sum = sumBetweenNumber(a,b)
  println(s"Sum of the numbers between $a and $b :: $sum")

  //2.	Write a function to sum squares of numbers between 2 numbers.

  def squareNumber(a: Int, b: Int) : Int = {
    if (a > b) 0
    else
      a*a + squareNumber(a+1,b)
  }
  a=1; b=4
  var squared = squareNumber(a,b)
  println(s"sum of squares between $a and $b is :: $squared")

  //3.	Write a function which add all digits of a number to single digit.
  // (for example, 98 is reduced to 17 -> to 8)

  def addToSingleDigit(a: Int) : Int = {
    if (a <0 )0
    else {
      var l = a.toString.toList;
      //l.foreach(a => {i = i+a; println(a,a)})
//      l match {
//        case firstNumber => println("firstNumber ::" + firstNumber)
//      }
      0
    }
  }
  var sumOfDigitis = addToSingleDigit(10)
  println(s"singleDigit is :: $sumOfDigitis")

  val fruits = List("appple", "orange", "Peaches")
  def listToString(list: List[String]): String = list match {
    case s :: rest => {
      println(s"rest ::  first element :: $s rest of the list:: $rest")
      s + " " + listToString(rest)
    }
    case Nil => ""
  }
  var str = listToString(fruits)
  println(str)

  //4.	Write a function to find fib number for a given index

  def getFibNumOf(num : Int) : Int = {
    if (num <= 1) 1
    else num * getFibNumOf(num-1)
  }
  a = 4
  var getFibNUmber = getFibNumOf(a)
  println(s"getfib $getFibNUmber")

  //5.	Write a function to get weather a number is prime or not
  var i: Int = 7
  var l =i/i
  println("Prime check:: " + l)
  println("Prime check:: " + (i%i == 0 ))

  def isEvenNumber(a: Int): String = {
    if (a%2 == 0) s"This given number :: $a is a Even Number\n"
    else s"This given number :: $a is Odd Number!\n"
  }

  var isEven= isEvenNumber(44)
  print(isEven)

  def isPrimeNumber(a: Int): String = {
    if (a%1 == 0 && a%a == 0) s"This given number :: $a is a Prime Number\n"
    else s"This given number :: $a is not a Prime Number!\n"
  }

  var isPrime= isPrimeNumber(8)
  print(isPrime)

}
