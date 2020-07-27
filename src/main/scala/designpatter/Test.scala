package designpatter

object Test extends App {

  def func1(s: String) = s + " func1"
  def func2(s: String) = s + " func2"

  val composedFunc = func1 _ compose func2 _

  val result = composedFunc("John")
  println(result)

  val func3: PartialFunction[String, String] = {case "John" => "John func1"}
  val func4: PartialFunction[String, String] = {case s: String => s + " func2"}

  val chain = func3 andThen func4
  val result1 = chain("John")
  println(result1)

}
