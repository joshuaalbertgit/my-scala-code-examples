package designpatter

object Hybrid extends App {

  val add = (a: Int,b: Int) => a + b
  var answer1 = add (2,3)
  println(answer1)

  //one parameter is defauled to 2
  val addTwo = (2, _ : Int)
  var answer2 = addTwo (5)
  println(answer2)

  val numbers = List (1,2,3,4,5,6,7,8,9,10)
  println(numbers)

  var filtered = numbers.filter( (p: Int) =>  p >5)
  filtered.foreach(n=> println(n))

  filtered.foreach(n=> {
    val i = n * 2
    println(i)
  })

}
