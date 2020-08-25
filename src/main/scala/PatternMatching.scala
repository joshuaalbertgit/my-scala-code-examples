object PatternMatching  extends App{

  trait Expression {
    def evaluateNumbers:Int=this match{
      case Number(n)=>n
      case Sum(e1,e2)=>e1.evaluateNumbers+e2.evaluateNumbers
      case Product(e1,e2)=>e1.evaluateNumbers*e2.evaluateNumbers
    }
    //pattern matching show
    def show:String= this match{
      case Number(n)=>n.toString
      case Sum(e1,e2)=> e1.show + "+" + e2.show
      case Product(e1,e2)=>e1.show + "*" + e2.show
    }
  }
  //case class Number
  case class Number(x: Int) extends Expression
  //case class Sum
  case class Sum(x: Expression, y: Expression) extends Expression
  //case class Product
  case class Product(x: Expression, y: Expression) extends Expression
  //println(Sum(Number(1),Number(2)).eval)            //> 3
  var productValue = Product(Number(5),Number(7))
  println("productValue ::show :: " + productValue.show)
  println("productValue ::eval :: " + productValue.evaluateNumbers)
}