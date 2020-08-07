package testing

import akka.actor.{Actor, ActorSystem, Props}

object AkkaTestingBasics extends App {


  class SimpleActor extends Actor {

    override def receive: Receive = {
      case message: String => {
        println(message)
        //sender() ! message
      }
    }
  }

  val system = ActorSystem("SimpleActor")
  val simpleActor = system.actorOf(Props[SimpleActor])

  var msg = simpleActor ! "Hi"
  println(s"received message is :: $msg")

  system.terminate()
  //calling methods recursively , with improved operations
  //Programming in Scala
  //

}
