package Futures

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random

object SampleFuture extends App {

  val currentTime = System.currentTimeMillis()

  //Future a sure retun on a fucntion or method


  val getCurrentTime = Future {
    currentTime
  }



  val result = Await.result(getCurrentTime, 1 second)
  println(result)

  wait(2000)
}
