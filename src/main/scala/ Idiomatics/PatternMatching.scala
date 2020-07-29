package  Idiomatics

import scala.concurrent.Future
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import java.util.concurrent.ForkJoinPool

import scala.util.{Failure, Random, Success, Try}

object PatternMatching extends  App {

  //5.1. For expressions
  val myNums = 1 to 3

  for {
    i <- myNums
    j = i + 1
  } yield println(j)

  println("yield demo ")
  for {
    i <- myNums
    j <- 1 to i
  } yield println(i * j)

  println("yield demo ")
  var forYield = for {
    i <- myNums
    j <- 1 to i
  } yield (i * j)
  println("forYield :: " + forYield)

  forYield = for {
    i <- myNums if i % 2 ==1
    j <- 1 to i
  } yield (i * j)
  println("forYield :: " + forYield)

  for (n <- 1 to 3 ) println("for without yield" + n)

  (1 to 3).foreach(n => println("with foreach" + n))


  //5.2. Pattern Matching

  def isCustomer (message: String) : Boolean = {
        message match {
          case "Hi" => true
          case _ => false
        }
  }
  var aCustomer = isCustomer("Hi")
  println("isCustomer check:: " + aCustomer)

  aCustomer = isCustomer("Hello")
  println("isCustomer check:: " + aCustomer)

  case class Customer (fname: String)
  def isCustomer1 (message: Any) : Boolean = {
    message match {
      case "Hi" => true
      case customer: Customer => true
      case _ => false
    }
  }
  var bCustomer = new Customer("Joshua")
  var custCustomer = isCustomer1(bCustomer)
  println("isCustomer[custCustomer1] check:: " + custCustomer)

  custCustomer = isCustomer1("Hi")
  println("isCustomer[custCustomer2] check:: " + custCustomer)

  custCustomer = isCustomer1("Hello")
  println("isCustomer[custCustomer3] check:: " + custCustomer)

  custCustomer = isCustomer1(87)
  println("isCustomer[custCustomer4] check:: " + custCustomer)

  custCustomer = isCustomer1(Customer("Jj"))
  println("isCustomer[custCustomer5] check:: " + custCustomer)

  //5.3. Handling Options

  def getMiddleName (name: Any) : String = {
    name match {
      case "Hi" => "Hi"
      case _ => "No Hi"
    }
  }
  var getMName = getMiddleName("Hi")
  println("getMName :: " + getMName)

  getMName = getMiddleName("Hello")
  println("getMName :: " + getMName)

  case class CustomerDetail (fname: String = "", mname: Option[String] = None, lname: String = "")


  def getMiddleNameOnly (value: Option[String]) : String = {
    value match {
      case Some (mName)   => mName
      case _ => "No middle name is present in the given object"
    }
  }
  var custDetail = CustomerDetail("joshua", Option("jeyaseelan"), "albert")
  var getMiddleName = getMiddleNameOnly(custDetail.mname)
  println("getMiddleName all 3 names:: " + getMiddleName)

  custDetail = CustomerDetail("joshua", lname = "albert")
  getMiddleName = getMiddleNameOnly(custDetail.mname)
  println("getMiddleName with first name and last name, no middle name:: " + getMiddleName)


  custDetail = CustomerDetail(fname = "joshua")
  getMiddleName = getMiddleNameOnly(custDetail.mname)
  println("getMiddleName with just first name only:: " + getMiddleName)

  var someOptionalName: Option[String] = Option("Joshua")
  var ressultOfMapOption = someOptionalName.map(name => println("Yay of map, "+ name))

  //an option is returned, it is not a good idea to map over an Option
  //it returns Option[Unit] = Some(())
  //so it is better to use foreach, which returns it the String
  println("ressultOfMapOption ::" + ressultOfMapOption)//its type is Some(())

  var resultOfOptionForEach = someOptionalName.foreach(name => println("Yay for each, "+ name))
  println("resultOfOptionForEach ::" + resultOfOptionForEach.toString)//returns ()

  val martin = Option("Martin")
  println(martin)//Some(Martin)
  val jane = Option("Jane")
  println(jane)//Some(Jane)

  var forOptionValue = for {
    m  <- martin
    j  <- jane
  }yield (m + ", he is a friend of " + jane)

  println(forOptionValue)//Some(Martinis a friend of Some(Jane))
  println(forOptionValue.get)//gets the string inside of it



  //5.4. Handling Failures (catching an Exception)

  def toInt (someNumberAsString : String) : Int = {
    try {
      someNumberAsString.toInt
    } catch {
      case _: NumberFormatException => {
        println("Exception :: NumberFormatException, so returning ZERO (0)" )
        0
      }
    }
  }//end of method

  var aNumber = toInt("2")
  println("aNumber :: string 1 "+ aNumber)

  aNumber = toInt("a")
  println("aNumber :: string a " + aNumber)

  var trySome = Try ("100".toInt) //Import Try, Success, Failure
  println(trySome)//Success(100)

  trySome = Try ("Joshua".toInt)
  println(trySome)//Failure(java.lang.NumberFormatException: For input string: "Joshua")

  //user pattern matching to handle this situation

  def toMakeInt (str : String) : Int = Try(str.toInt) match {
    case Success(n) => n
    case Failure(_) => 0
  }//end of method

  var testNum = toMakeInt("232")
  println("testNum :: " + testNum)

  testNum = toMakeInt("abc")
  println("testNum :: " + testNum)

  def getScala: Try[String] = Success("Joshua Joshua")
  val scala = getScala
  println(scala)//Success(Joshua)

  val reverse = scala.map(s=>s.reverse)
  println("reverse :: " + reverse)

  def getOuch: Try [String] = Failure(new Exception("Ouch"))
  val ouch = getOuch
  println(ouch)
  ouch.map(s=>s.reverse)//it does nothing

  val java = Success("Java")
  val rocks = Success("Rocks")

  println(java)
  println(rocks)

  var forSuccess = for {
    language <- Success("Java")
    behaviour <- Success("Rocks")
  }yield s"$language $behaviour"

  println(forSuccess)//Success(Java Rocks)


  //5.5. Handling Futures

  //to use Future asynchronously - define work that may happen later time, by another thread
  //it returns a try of Success/Failure after completion

  implicit  val ec: ExecutionContext = ExecutionContext.fromExecutor(new ForkJoinPool())
  implicit val timeout = 1 second

  val f: Future [Int] = Future{
    //inventoryService.getCurrentInventory(74726272)//in realtime something like this
    19//return some dummy number for now
  }

  val ff: Future[Int] = Future {
    //Thread.sleep(2000)//Future(<not completed>)
    1 + 2 //output : someFunction::onComplete::Success 3
    //Random.nextInt(4) //Future(<not completed>)

  }
  println(ff)
  ff.onComplete{
    case Success(i) => println(s"someFunction::onComplete::Success $i")
    case Failure(exp) => println(s"someFunction::onComplete::Failure ${exp.printStackTrace()}")
  }

  ff.map(n=>println("mapping result ::" + n))

  val usdQuote  = Future {
    //Random.nextInt(3)
    73
  }

  val indiaQuote  = Future {
    //Random.nextInt(3)
    72
  }

  val purchase = for {
    usd <- usdQuote
    ind <- indiaQuote
    //} yield ( if(usd > ind ) usd)
  }yield (s"USD $usd INDIA $ind")

  println("purchase USA IND :: " + purchase)
}
