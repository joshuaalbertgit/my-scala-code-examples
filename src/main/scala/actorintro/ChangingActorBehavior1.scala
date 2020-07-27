package actorintro

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object ChangingActorBehavior1 extends App {

  //kid will receive food (veg/chocolate) from mom
  object Kid {
    //based on food define kid's mood happy/sad
    val HAPPY = "Happy"
    val SAD = "SAD"
    case object PrintState
  }
  class Kid extends Actor {
    import Mom._, Kid._
    var state: String = SAD
    var givenFood: String = ""
    override def receive: Receive = {
      case FOOD(VEGI) =>
        state = SAD
        givenFood = VEGI
      case FOOD(CHOCOS) =>
        state = HAPPY
        givenFood = CHOCOS
      case PrintState => println(s"Give food::$givenFood | Kid's feels $state")
    }
  }

  //to deal with sending messages have an object
  object Mom {
    case class MommySendingActorRef(kidRef: ActorRef)
    case class MommySendingActorRefFood(kidRef: ActorRef, food: String)
    //define good and types
    case class FOOD(food: String)
    val VEGI = "VEGETABLES"
    val CHOCOS = "CHOCOLATES"
  }
  //will send food (veg/chocolate) to kid
  class Mom extends Actor {
    import Mom._, Kid._
    //to send food to kid, need to have kid's reference
    override def receive: Receive = {
      case MommySendingActorRef(kidRef) =>
        kidRef ! FOOD(VEGI)
        kidRef ! PrintState
      case MommySendingActorRefFood(kidRef,food) =>
        kidRef ! FOOD(food)
        kidRef ! PrintState
    }
  }
  import Mom._, Kid._
    val system = ActorSystem("ChangingActorBehavior1")
    val mom = system.actorOf(Props[Mom], "mom")
    val kid = system.actorOf(Props[Kid], "kid")
    mom ! MommySendingActorRef(kid)
    mom ! MommySendingActorRefFood(kid, CHOCOS)
    mom ! MommySendingActorRefFood(kid, VEGI)
    mom ! MommySendingActorRefFood(kid, CHOCOS)
    system.terminate()


}