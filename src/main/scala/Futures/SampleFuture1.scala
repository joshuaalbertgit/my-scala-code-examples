package Futures

import Futures.SampleFuture.wait


import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Random, Success}

object SampleFuture1 extends App {

   val f = Future {
    Random.nextInt(500)
    //42
    }

  println("before onComplete")
  f.onComplete {
    case Success(value) => println(s"Got the callback, meaning = $value")
    case Failure(e) => e.printStackTrace
  }

  def getRandomNumber (i : Int) : Future [Int]=  Future {
    Random.nextInt(500)
  }

  getRandomNumber(10).onComplete{
    case Success(result) => {
      println(s"Success Random number got generated within 10 numbers is :: $result")
    }
    case Failure(e) => { e.printStackTrace()}
  }

  //wait(2000)
}
