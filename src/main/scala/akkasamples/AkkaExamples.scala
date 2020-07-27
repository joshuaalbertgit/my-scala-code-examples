package akkasamples

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object AkkaExamples extends  App {
  println("hello")


  class SimpleActor extends Actor{
    override def receive: Receive = {
      case message: String => println(s"Hello I am ${context.self.path} a String:: $message")
      case number: Int  => println(s"Hello I am a number :: $number")
      case number: Double  => println(s"Hello I am double/decimal :: $number")
      case SendMsg (msg) => println(s"I am from a class $msg")
      case MsgMe (msg) =>
        {
          //self ! msg
          //println(s"$msg")
          self ! {msg.toInt}
        }
    }
  }

  var system = ActorSystem("ActorDemo")
  var sampleActor = system.actorOf(Props[SimpleActor],"SampleActor4Demo")

  sampleActor ! "Joshua"
  //sampleActor ! 100
  //sampleActor ! 100.9876
  //! means TELL method by only this method we communicate

  case class SendMsg (msg : String)
  sampleActor ! SendMsg("am sending a message from SendMsg class")

  case class MsgMe(msg: String)
  sampleActor ! MsgMe ("2")

  val joshua = system.actorOf(Props[SimpleActor],"joshua")
  val albert = system.actorOf(Props[SimpleActor],"albert")

  case class SayHiTo(ref: ActorRef)
  joshua ! SayHiTo (albert)

  joshua ! "mememe"
}
