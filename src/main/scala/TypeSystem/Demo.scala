package TypeSystem

object Demo extends App {

  class Amount (value: Int, currency: String)
  val fiveDollars = new Amount(5, "USD")
  println(fiveDollars)//it prints TypeSystem.Demo$Amount@1e4cb85


  //to print the values apply override toString
  class Amount1 (value: Int, currency: String){
    override def toString: String = s"Amount ${this.value}, ${this.currency}"
  }
  val oneDollars = new Amount1(1, "USD")
  println(oneDollars)//it prints Amount 1, USD
  //println(twoDollars.amountValue)//it will fail


  //to print the attributes value/currency of it
  class Amount2 (value: Double, currency: String){
    val amountValue = this.value
    val amountCurrency = this.currency

    override def toString: String = s"Amount ${this.value}, ${this.currency}"
  }
  val twoDollars = new Amount2(2, "USD")
  println(twoDollars)
  println(twoDollars.amountValue)
  println(twoDollars.amountCurrency)

  //using val in the constructor
  class Amount3 (val value: Double, val currency: String){
    override def toString: String = s"Amount ${this.value}, ${this.currency}"
  }
  val threeDollars = new Amount3(3, "USD")
  println(threeDollars)
  println(threeDollars.value)
  println(threeDollars.currency)

}
