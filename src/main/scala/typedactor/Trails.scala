package typedactor

import akka.actor.{Actor, ActorSystem, Props}
import typedactor.Trails.Greeter1.WhoToGreet

//https://github.com/akka/akka/blob/v2.6.8/akka-actor-typed-tests/src/test/scala/docs/akka/typed/StashDocSpec.scala#L13-L99


//Classic Actor
//https://doc.akka.io/docs/akka/current/actors.html#defining-an-actor-class

object Trails extends App {

  object Greeter1 {
    case object Greet
    final case class WhoToGreet(who: String)
  }
  class ClassGreeter extends Actor {
    import Greeter1._
    private var greeting = "hello"

    override def receive = {
      case WhoToGreet(who) =>
        greeting = s"hello, $who"
        println(greeting)
      case Greet =>
        println(greeting)
      case message: String =>
        println(s"give message is :: $greeting")

      }
    }

    val system = ActorSystem("me")
    val classActor = system.actorOf(Props[ClassGreeter],"greeter1")
    classActor ! "Joshua"
    var getMsg = classActor ! WhoToGreet("Raheem")
    println(getMsg)
    system.terminate()
}

