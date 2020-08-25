package ustexcercises

object RationalNumber extends App {

  class RationalNumber(n: Int, d: Int) {
    def numerator: Int = n
    def denominator: Int = d
    var actualValue: Int = 0

    def + (a: Int) = 10

    def addNumbers(someNewNumber: RationalNumber): Int ={
      //println("N/D : Old:: " + numerator/denominator)
      //println("N/D : new:: " + someNewNumber.numerator/someNewNumber.denominator)
      (numerator/denominator) + (someNewNumber.numerator/someNewNumber.denominator)
    }

    override def toString: String = {
      actualValue = numerator/denominator
      "Actual Value:: " + actualValue
    }
  }

  var x = new RationalNumber(10,1)//actual value should be 10/2=5
  //println("x->N ::" + x.numerator + " D::" + x.denominator)
  println(x.toString)

  var y = new RationalNumber(50,1)//actual value should be 50/2=25
  //println("y->N ::" + y.numerator + " D::" + y.denominator)
  println(y.toString)

  var sumOfNumbers = x.addNumbers(y)
  println("sumOfNumbers ::" + sumOfNumbers)

  println("1,1 and 4,2 ##### \n")

  x = new RationalNumber(1,1)//actual value should be 10/2=5
  println(x.toString)
  y = new RationalNumber(4,2)//actual value should be 10/2=5
  println(y.toString)
  sumOfNumbers = x.addNumbers(y)
  println("sumOfNumbers ::" + sumOfNumbers)

}




