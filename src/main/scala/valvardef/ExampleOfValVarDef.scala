package valvardef

object ExampleOfValVarDef extends App {

  val i =100 //immutable
  println(s"Value of the constant i $i")

  var j =10
  j = j+10
  println(s"Value of the changed value of j is  $j")

  def volume = 10 //this is a method which returns a value 10
  println(s"Value of the definition of volume is  $volume")

  def area = {
    3.13 * volume + 3.9
  } //this method uses another method to rerun a value
  area
  println(s"Value of the definition of area is  $area")

  //lazy val wont have the value, when it was assigned
  //it will have the value, when it is called

  //val

  val data = {
    var i=10
    var j=12
    println(i + j)
    Array("hi", "how", "are you?")
  }
  data.foreach(println)//once all initialization, it will print the i,j (value 22 printed once)
  data.foreach(println)//after the initialization only the return will be taken

  //we can not reassign things afresh
  /*
  data = {
    Array("hi", "how", "are you?")
  }
   */
  //but reassign/modify it data is possible
  data(0) = "new"
  data.foreach(println)

  def dataDef = {
    var i=10//only set once during the initial call, next time onwards only the return value is dealt
    var j=12
    println(i + j)
    Array("hi", "how", "are you?")
  }
  dataDef.foreach(println)
  dataDef.foreach(println)

  lazy val aNum = {
    println("lazy val initialized")
    //it will print only once for the first call, after it, it will deal with the return value
    //meaning it wont be re initialized
    100
  }
  println(s"lazy value $aNum")

  var newNumb = aNum * aNum
  println(s"combined values $newNumb")


}
