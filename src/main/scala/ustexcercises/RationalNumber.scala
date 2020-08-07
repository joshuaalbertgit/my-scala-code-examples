package ustexcercises

object RationalNumber extends App {

  class RationalNumber(n: Int, d: Int) {
    def numerator: Int = n
    def denominator: Int = d
    var actualValue: Int = 0

    def addNumbers(someNewNumber: RationalNumber): Int ={
      //println("N/D : Old:: " + numerator/denominator)
      //println("N/D : new:: " + someNewNumber.numerator/someNewNumber.denominator)
      (numerator/denominator) + (someNewNumber.numerator/someNewNumber.denominator)
    }

    override def toString: String = {
      actualValue = numerator/denominator
      "Actual Value:: " + (numerator/denominator).toString
    }
  }

  val x = new RationalNumber(10,2)//actual value should be 10/2=5
  println("x->N ::" + x.numerator + " D::" + x.denominator)
  println(x.toString)

  val y = new RationalNumber(50,2)//actual value should be 50/2=25
  println("y->N ::" + y.numerator + " D::" + y.denominator)
  println(y.toString)

  x.addNumbers(y)
  println(x.toString)//it should print 30 (actual values 5 + 25

}




