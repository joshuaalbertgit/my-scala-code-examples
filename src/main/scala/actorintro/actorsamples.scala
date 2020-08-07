package actorintro

import akka.actor.{Actor, ActorSystem, Props}

object actorsamples extends  App {

  //only one actor system per application (normall)
  val actorSystem = ActorSystem("JoshuaApp1")
  println(actorSystem.name)

  class WordCounter extends Actor {
    //internal data
    var totalWords = 0

    override def receive: Receive = {
      case message: String =>
        totalWords += message.split(" ").length
        println(s"given message is : $message word count:: $totalWords")
    }
  }//end of class

  val wCounter = actorSystem.actorOf(Props[WordCounter],"wordCounter")
  wCounter ! "Joshua Jeyaseelan Albert"


  //additional things
  //adding a class->send message

  class Person (name: String) extends Actor{
    override def receive: Actor.Receive = {
      case message: String => println(s"given message in class Person is $message $name")
    }
  }//end of class
  val person = actorSystem.actorOf(Props(new Person("Tommy")))
  person ! "Hello"

  //create new object for Person
  object personUpdated {
    def props (name: String) = Props(new PersonUpdated(name))
  }
  class PersonUpdated (name: String) extends Actor{
    override def receive: Actor.Receive = {
      case "hi" =>
        println(s"Hello $name")
        sender() ! "yo yo"
      case _ =>
    }
  }//end of class
  val personUpdatedActor = actorSystem.actorOf(personUpdated.props("Angline Joshua"))
  //personUpdatedActor ! "hi"

  val myres = personUpdatedActor ! "hi"
  println("received message ::" + myres.toString)

  actorSystem.terminate()

}
