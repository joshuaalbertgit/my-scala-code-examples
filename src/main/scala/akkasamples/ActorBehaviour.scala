package akkasamples

import akka.actor.{Actor, ActorSystem, Props}
import akkasamples.ActorBehaviour.mom.{Ask, Food}
import akkasamples.AkkaExamples.{SendMsg, sampleActor}


/*
 * Mom and Kid - actors - sending messages to each other
 */
object ActorBehaviour extends App {

  //mom need to send food (like chocolate or vegetable
  object mom {
    case class Food (food: String)
    case class Ask (msg: String) //asking like, do you want to play with mom?

    //define food items
    val VEGETABLE = "vegetable"
    val CHOCOLATE = "chocolate"

  }
  class Mom extends Actor{
    override def receive: Receive = {
      ???
    }
  }//end of mom class

  //kid is happy, when getting chocolate
  //is sad, when getting vegetable
  object kid{
    val HAPPY = "happy"
    val SAD = "sad"
    val GOOD_RESPONSE = "Am coming to play, mom!"
    val BAD_RESPONSE = "Am NOT coming to play, MOM!"

    case object KidAccept
    case object KidReject
  }

  class Kid extends Actor {

    import kid._
    import mom._

    var state = HAPPY


    override def receive: Receive = {
      //case message: String => println("hello")
      case Food(VEGETABLE) =>
        state = SAD
        println(s" Give Food is: ${VEGETABLE} and state: $state")

      case Food(CHOCOLATE) =>
        state = HAPPY
        println(s" Give Food is: ${CHOCOLATE} and state: $state")

      case Ask(_) => {
        if (state == HAPPY)
          sender() ! println(s"${kid.GOOD_RESPONSE}")
        else
          sender() ! println(s"${kid.BAD_RESPONSE}")
      }
    }

    }//end of class kid

    //setup the actor system
    var system = ActorSystem("ActorBehaviour")
    //var momActor = system.actorOf(Props[Mom],"momActor")
    var kidActor = system.actorOf(Props[Kid],"kidActor")



    //kidActor ! "mom"
    //kidActor ! mom.VEGETABLE not working

    //kidActor ! Food(mom.VEGETABLE) //or
    import mom._
    kidActor ! Food(VEGETABLE) //or
    kidActor ! Ask ("can i play?")

    kidActor ! Food(mom.CHOCOLATE)
    kidActor ! Ask ("can i play?")

}

