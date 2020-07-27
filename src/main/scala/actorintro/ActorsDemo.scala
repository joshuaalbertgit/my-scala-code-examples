package actorintro

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object ActorsDemo extends App {

  class demoActor extends Actor{

    def printValues (fromActor: ActorRef, toActor: ActorRef): Unit ={
      println(s"From :: ${fromActor.path} To :: ${toActor.path}")
    }
    override def receive: Receive = {
      case message: String =>
        println(s"message :: $message | From actor :: ${self.path} ")
      case Msg2AnotherActor (ref) =>
        ref ! s"Sender Actor :: ${self.path} | Receiving Actor :: ${ref.path}"
      case Msg2AnotherActorWithMessage (ref, message) =>
        printValues(self, ref)
        ref ! message
    }
  }

  val system = ActorSystem("actorsdemo")
  val actor1 = system.actorOf(Props[demoActor],"actor1")
  val actor2 = system.actorOf(Props[demoActor],"actor2")

  actor1 ! "Hello1"
  actor2 ! "Hello2"

  case class Msg2AnotherActor (ref: ActorRef)

  actor1 ! Msg2AnotherActor (actor2)
  actor2 ! Msg2AnotherActor (actor1)

  case class Msg2AnotherActorWithMessage (ref: ActorRef, message: String)
  actor1 ! Msg2AnotherActorWithMessage (actor2,"actor1->actor2")
  actor2 ! Msg2AnotherActorWithMessage (actor1,"actor2->actor1")

  system.terminate()
}
