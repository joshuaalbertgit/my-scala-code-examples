package Collections

object CollectionsExamples extends App {

  var aList = 1 to 10
  aList.foreach(a => println(s"List entries $a"))

  aList.map(a => println(s"Map entries $a"))

  var intList : List[Int] = List(1,10)
  intList.foreach(a => println(s"List[Int] entries $a"))


  aList = 20 to 30
  var aMap = aList.map(a=>a+1)//on each entry, add one
  println(s"Modified list :: $aMap")

  var intArray = Array(1,2,3,4,5,32,2,4,15)
  intArray.foreach(a=> println("intArray :: " + a))
  intArray.map(a=> println("intArray :: " + a))

  var intSet = Set (3,4,5,7)
  intSet.foreach(a=> println("intSet :: " + a))
  intSet = intSet + 444
  intSet.foreach(a=> println("after add intSet :: " + a))

  //check the given value is present in a set
  println("Is 444 is present in intSet :: " + intSet(444))

  //Option
  var strOption = Option("Joshua")
  println("strOption get :: " + strOption.get)
  println("strOption getOrElse :: " + strOption.getOrElse("asdf"))

  //in a class variable, where anything is an option, but want to keep a default value
  //we can use this Option

  case class Customer (first: String, middle: Option[String] = None, last: String)
  var cust = Customer("Joshua", last = "Albert")
  println(cust.toString)
  //Customer(Joshua,None,Albert)

  //Tuples - can have 22 values

  val tuple1 = (1,"One", 2, "two", 3, "three")
  println(tuple1)

  //2nd value of tuple
  println(tuple1._2)
  println(tuple1._6)

  //deconstruct the tuple
  val tuple2 = (1,"One")
  val (first, second) = tuple2

  println(tuple2)
  println(tuple2._1)
  println(tuple2._2)

  //maps & zip
  val intMap = 1 to 5
  val strMap = 'a' to 'e'

  val mapZipped = intMap.zip(strMap)
  //Vector((1,a), (2,b), (3,c), (4,d), (5,e))
  println(s"map zipped ::  $mapZipped")

  var getMapOf3 = mapZipped(3)
  println(s"getMapOf3 ::  $getMapOf3")


  val vector2Map = mapZipped.toMap
  //Map(5 -> e, 1 -> a, 2 -> b, 3 -> c, 4 -> d)
  println(s"zipped map to Map ::  $vector2Map")

  var getVectorOf3 = vector2Map(3)
  println(s"getVector ::  $getVectorOf3")

  var getMap3 = vector2Map.get(3)
  println(s"getMap3 by get::  $getMap3")

  println("get out of boundary of a map :: " + vector2Map.get(123))//None
  println("out of boundary of a map (getOrElse):: " + vector2Map.getOrElse(4,"z"))
  //get default (z) if not matching , if 4 matching, getting d [4 -> d]


  //higher order function
  //map
  var oneTo5 = 1 to 5
  var addedMap = oneTo5.map(a=>a+1)

  //same - but as range Range 1 to 5
  println("map added values :: " + oneTo5.toString())

  //added by 1 as Vector Vector(2, 3, 4, 5, 6)
  println("map added values :: " + addedMap.toString())


  //FLAT MAP (singles)
  var strList = List ("Joshua", "Albert")
  strList = strList.map(name => name + "Mr")

  //List(JoshuaMr, AlbertMr)
  println("strList :: " + strList.toString())

  var flatMap = strList.flatMap(name => name + "#")
  //List(J, o, s, h, u, a, M, r, #, A, l, b, e, r, t, M, r, #)
  println("flatMap :: " + flatMap.toString())

  //filter (using contains)
  strList = List ("Joshua", "Albert")//filters by "os" and gets List(Joshua)
  var filteredStringArray = strList.filter(name => name.contains("os"))
  println(" filteredStringArray :: " + filteredStringArray.toString())

  //foreach
  strList = List ("Joshua", "albert")
  strList.map(println)

  strList.foreach(println)//

  //forall
  var Boolean  = strList.forall(name => name.contains("a"))
  //a is present in both Joshua and Albert
  println(Boolean)

  Boolean  = strList.forall(name => name.contains("s"))
  println(Boolean)

  //important one in Data Science (MapReduce world) to transform data
  //REDUCE

  var map1To5 = 1 to 5
  var mapTotal = map1To5.reduce(( accumulate,current) => accumulate + current )
  var mapSum = map1To5.sum
  println("mapTotal :: " + mapTotal)
  println("mapSum :: " + mapSum)

  //foldLeft Right

  //product
  var mapProduct = map1To5.product
  println("mapProduct :: " + mapProduct)

  //exists
  var map1To10 = 1 to 10
  Boolean = map1To10.exists(n => n == 3)
  println("Is n == 3 in 1 to 10 :: " + Boolean)

  Boolean = map1To10.exists(n => ((n-1) + 1 > n))
  println("Is n == 3 in 1 to 10 :: " + Boolean)

  //find
  var intOption: Option[Int] = map1To10.find(n =>  n * n == n)
  println("Is n * n == n :: " + intOption.toString)

  //groupBy
  map1To10 = 1 to 10
  var mapGroup = map1To10.groupBy(n => n % 3)
  println("map group by :: " + mapGroup.toString())

  //take while & drop while
  var takeWhileMap = 1 to 10
  var takeWhile = takeWhileMap.takeWhile(n => n < 3)//Range 1 to 2
  println("takeWhile <3 :: " + takeWhile.toString())

  var dropWhileMap = 1 to 10
  var dropWhile = dropWhileMap.dropWhile(n => n <= 3)//Range 4 to 10
  println("dropWhile <=3 :: " + dropWhile.toString())
}
