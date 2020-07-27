package akkasamples

import akka.actor.{Actor, ActorSystem, Props}

object CounterActor extends App {
  println("hi")

  class CounterDemo extends Actor {

    override def receive: Receive = {
      case withdraw: Int => println(s"decrementing $withdraw")
      //case deposit: Int => println(s"incrementing $deposit")
    }
  }

    var system = ActorSystem("CounterActorDemo")
    var actor1 = system.actorOf(Props[CounterDemo],"withdrawActorDemo")

    actor1 ! 5



}
