package actorintro

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object CounterActorExercise extends App {

  class Counter extends Actor{

    var count: Int = 0

    def getBalance (): Unit ={
      println(s"current Balance is  :: $count")
    }

    override def receive: Receive = {
      case Increment(amount: Int) =>
        count += amount
        //getBalance()
      case Decrement(amount: Int) =>
        count -= amount
        //getBalance()
      case _ => getBalance()
    }
  }

  val system = ActorSystem("Counter")
  val actor1 = system.actorOf(Props[Counter],"actor1")

  case class Increment (amount: Int)
  actor1 ! Increment(1)
  actor1 ! Increment(10)

  case class Decrement (amount: Int)
  actor1 ! Decrement (10)
  actor1 ! ""

  system.terminate()
}
