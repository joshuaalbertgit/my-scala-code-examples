package ObjectsClasses

object CompanionObjects extends App {
  println("Hello!")

  object Hello {
    private val defaultMsg: String = "Default Good Morning!"
  }

  class Hello (message: String = Hello.defaultMsg){
    println(message  + " How are you doing today?")
  }

  new Hello("")
  new Hello("Joshua")

  val me = new Hello("JJ")

}
