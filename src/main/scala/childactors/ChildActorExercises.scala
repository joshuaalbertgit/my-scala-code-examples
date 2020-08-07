package childactors

import akka.actor.{Actor, ActorSystem, Props}

object ChildActorExercises extends App {

  //create two actors (master and worker)

  //1. master to create n(10) worker instances
  //2. send a set of words to count to one of it's worker instance
  // (so worker needs to have this case class to receive this request)

  //3. worker instance, does count it
  //4. worker returns the count to the master

  //5. master receives the count (sender)

  //domain object for master
  // to define
  // 1. master to create n(10) worker instances
  //2. send a set of words to count to one of it's worker instance
  //5. master receives the count (sender)
  object WordCounterMaster {
    case class Initialize (nChildren: Int)
    case class InitializeChildren (nChildren: Int)
    case class WordCountTask (sentence: String)
    case class WordCountReply (noOfWords: Int)

  }
  //create two actors (master and worker)
  class WordCounterMaster extends Actor {


    import WordCounterMaster._

    override def receive: Receive = {
      case Initialize (nChildren) =>{
        println(s"Initialized $nChildren instances...")
        workerActor ! WordCountTask("How are you by brother today ?")
      }
      case InitializeChildren (nChildren) => {
        val childRefs = for (i <- 1 to nChildren) yield context.actorOf(Props[WordCounterWorker], s"worker_$i")
      }
      case WordCountReply (n) =>
        println(s"Have received the response as $n")

    }
  }

  //domain object for worker
  //to define
  // 3. worker instance, does count it
  object WordCounterWorker {

  }
  //create two actors (master and worker)
  class WordCounterWorker extends Actor {
    import WordCounterMaster._
    override def receive: Receive = {
      case WordCountTask (sentence)=>{
        var wordCout: Int = sentence.split(" ").length
        println(s"Word count is $wordCout")
        sender() ! WordCountReply (wordCout)
      }


    }
  }

  import WordCounterMaster._
  import WordCounterWorker._
  val system = ActorSystem("ChildActorExercisesDemo")
  val masterActor = system.actorOf(Props[WordCounterMaster],"master")
  masterActor ! Initialize(3)
  masterActor ! InitializeChildren(3)

  val workerActor = system.actorOf(Props[WordCounterWorker],"worker")

  system.terminate()

}
