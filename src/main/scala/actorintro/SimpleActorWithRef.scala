package actorintro

import java.util.concurrent.atomic.AtomicLongFieldUpdater

import actorintro.SimpleActorWithRef.ToPongMessage
import akka.actor.{Actor, ActorRef, ActorSystem, Props}

import scala.concurrent.ExecutionContext.Implicits.global

object SimpleActorWithRef extends App {

    

  class Joshua extends Actor{
    override def receive: Receive = {
      case _ => println("Joshua")
    }
  }

  val system = ActorSystem("PingPongActorSystem")
  val pong = system.actorOf(Props[Pong], "pong")

  //val joshua = system.actorSelection("//PingPongActorSystem/user/pong/Joshua")
  //joshua ! "Test Joshua"
  val ping = system.actorOf(Props(new Ping(pong)), name = "ping")

  println(ping.path)
  println(pong.path)
  ping ! StartMessages
  //ping ! ForcedException
  //system.stop(pong)
  //system.stop(ping)
  system.terminate()

  case object StartMessages
  case object ToPongMessage
  case object ToPingMessage
  case object ForcedException

  //ping sends messages to pong
  class Ping (pong: ActorRef) extends  Actor{

    var count = 0
    def doIncrementCount(): Unit ={
      count +=1
      println(count)
    }

    override def receive: Receive = {
      case StartMessages =>
        doIncrementCount
        pong ! ToPongMessage
      case ToPingMessage =>
        doIncrementCount
        if(count >5){
          context.stop(self)
        }
        pong ! ToPongMessage
      case ForcedException => throw new Exception("Error happened!")
      case _ => println("Ping else ")


      }//end of receive
    }//end of class


  //just send message
  class Pong extends Actor{

    val joshua = system.actorOf(Props[Joshua],"Joshua")
    override def preStart(): Unit = {
      //super.preStart()
      println("Pong Actor :: preStart")
    }

    override def receive: Receive = {
      case message: String =>
        println("From Pong Actor")
      case ToPongMessage =>
        println("Case : ToPongMessage ")
        sender() ! ToPingMessage

      case _ => println("Pong else case")
    }
  }
}
