package actorintro

import akka.actor.{Actor, ActorSystem, Props}

object SimpleActor extends App {

  //this class can receive String, Int types
  //if you want to send your own types (Class), refer the below extensions
  class SimpleActor extends Actor {
    override def receive: Receive = {
      case message: String => println(s"Message from SimpleActor $message")
    }
  }//end of class

  def printSomething(message : String ) = {
    val myReturn = message.length
    println(s"Response from a method ::[word count]  $myReturn")
    myReturn
  }
  val system = ActorSystem("actorSystem")
  val simpleActor = system.actorOf(Props[SimpleActor],"simpleActor")
  simpleActor ! "Hello Actor"

  //if you want to send your own types (Class), refer the below extensions
  case class OwnMessageType (message: String)
  case class OwnMessageIntType (message: Int)
  class BigActor extends Actor {
    override def receive: Receive = {
      case message: String => {
        println(s"Message from BigActor $message")
        val getResponse = printSomething("abcd")
        sender() ! getResponse

      }
      //case OwnMessageType (message: String) => println(s"special message type: $message")
      case OwnMessageType (msg) => println(s"special message type: $msg")
      //case OwnMessageIntType (message: Int) => println(s"special message type: $message")
      case OwnMessageIntType (msg) => println(s"special message type: $msg")
    }
  }//end of class


  val bigActor = system.actorOf(Props[BigActor],"BigActor")
  bigActor ! "Hello Big Actor"
  bigActor ! OwnMessageType("am 45 years old!")
  bigActor ! OwnMessageIntType(42)

  system.terminate()

}
