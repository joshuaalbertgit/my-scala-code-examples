package actorintro


import akka.actor.{Actor, ActorSystem, Props}

object CounterActorDomainExercise extends App {

  //DOMAIN
  object Counter {
    case object Increment
    case object Decrement
    case object Multiply
    case class Increment (amount: Int)
    case class Decrement (amount: Int)
    case class PrintBalance()
  }

  class Counter extends Actor{

   import  Counter._
    var count = 0

    def getBalance (): Unit ={
      println(s"current Balance is  :: $count")
    }

    override def receive: Receive = {
      case Increment =>
        count += 5
        getBalance()
      case Increment(amount: Int) =>
        count += amount
        getBalance()
      case Decrement(amount: Int) =>
        count -= amount
        getBalance()
      case Decrement =>
        count -= 2
        getBalance()
      case Multiply =>
        count *= 3
        getBalance()
      case PrintBalance =>getBalance()
    }
  }

  val system = ActorSystem("Counter")
  val counter = system.actorOf(Props[Counter],"actor1")
  import Counter._
  counter ! Increment (10)
  (1 to 5).foreach(_ => counter ! Increment)
  counter ! PrintBalance
  (6 to 10).foreach(_ => counter ! Decrement)
  (16 to 20).foreach(_ => counter ! Multiply)



  system.terminate()
}
