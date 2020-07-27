package actorintro

object HelloPrints extends App {

  def prints(): Unit ={
    println("Hello | World!")
  }
  prints()


  def echo (message: String) : String = message
  val msg =  echo("my name is Joshua")
  println(msg)

  def calc (num: Int) : Int = {num * num  * num + 10}
  val num = calc(15)
  println(num)

  def fullName (fn: String ="Mr", ln : String = "") : String = fn + ln
  println(fullName("", "Albert"))


  def addValues (num1 : Int = 11, num2: Int = 22) = num1 * num1 + num2
  println(addValues(4,0))

  object Hello {
    def echo (message: String ) = message
    def echoMessage (message: String ) = {
      println(message)
    }
  }

  import Hello._
  val hello = Hello.echo("hello")
  println(hello)
  Hello.echoMessage("hi")



}
