package akkasamples

import akka.actor.{Actor, ActorRef, ActorSystem, Props}


/*
 * Mom and Kid - actors - sending messages to each other
 */
object ChangingActorBehavior extends App {

  //kid is happy, when getting chocolate
  //is sad, when getting vegetable
  object Kid{
    val HAPPY = "happy"
    val SAD = "sad"
    val GOOD_RESPONSE = "Am coming to play, mom!"
    val BAD_RESPONSE = "Am NOT coming to play, MOM!"

    case object KidAccept
    case object KidReject
  }

  class Kid extends Actor {
    import Kid._
    import Mom._

    var state = HAPPY

    override def receive: Receive = {
      case Food(VEGETABLE) =>
        state = SAD
      case Food(CHOCOLATE) =>
        state = HAPPY
      case Ask(_) => {
        if (state == HAPPY)
          sender() ! println(s"Given food: $CHOCOLATE; Kid's state:: $state; So, the kid says: ${Kid.GOOD_RESPONSE}")
        else
          sender() ! println(s"Given food: ${VEGETABLE}; Kid's state:: $state; So, the kid says: ${Kid.BAD_RESPONSE}")
      }
    }

    }//end of class kid

  //mom need to send food (like chocolate or vegetable
  object Mom {
    case class Food (food: String)
    case class Ask (msg: String) //asking like, do you want to play with mom?
    case class sendMessage (kidRef : ActorRef)

    //define food items
    val VEGETABLE = "vegetable"
    val CHOCOLATE = "chocolate"
  }
  class Mom extends Actor{
    import Mom._
    import Kid._

    /**
     * When individual actor sending messgae, we going to do it via Mom's receive method
     * kidActor ! Food(VEGETABLE)
     * kidActor ! Ask ("can i play?")
     *
     * @return
     */
    override def receive: Receive = {
      case sendMessage (kidRef) => {
        kidRef ! Food(VEGETABLE)
        kidRef ! Ask("Do you want to play?")
        kidRef ! Food(CHOCOLATE)
        kidRef ! Ask("Do you want to play?")
      }
    }
  }//end of mom class
  import Mom._
  //setup the actor system
  var system = ActorSystem("ActorBehaviour")
  val log = system.log
  log.info("hi log!")
  var momActor = system.actorOf(Props[Mom],"momActor")
  var kidActor = system.actorOf(Props[Kid],"kidActor")

  momActor ! sendMessage (kidActor)

  system.terminate()


}
