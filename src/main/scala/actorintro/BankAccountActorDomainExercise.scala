package actorintro

import akka.actor.{Actor, ActorSystem, Props}

object BankAccountActorDomainExercise extends App {

  //DOMAIN
  object Bank {
    case class Deposit (amount: Int)
    case class Withdraw (amount: Int)
    case class NOT_ENOUGH_BALANCE(amountAsked: Int)
    case object PrintBankStatement
    case class TransactionFailure (message: String)
    case class TransactionSuccess (message: String)
  }

  class Counter extends Actor{

   import Bank._
    var amount = 0

    override def receive: Receive = {
      case Deposit (amt)=>
        if(amt <= 0)
          self ! TransactionFailure (s"Given amount [$amt]is not valid")
        else {
          amount += amt
          self ! TransactionSuccess(s"$amt been deposited successfully!")
        }
      case Withdraw (amt) =>
        if(amt > amount)
          self ! NOT_ENOUGH_BALANCE (amt)
        else
          amount -= amt
      case NOT_ENOUGH_BALANCE (asked) =>
        println(s"Sorry, no available balance exists! " +
              s"\n Asked amount:: $asked | Available balance:: $amount")
      case PrintBankStatement =>
        println(s"Current Balance :: $amount")
      case TransactionFailure(message) => println(message)
      case TransactionSuccess(message) => println(message)
    }

  }//end of actor class

  val system = ActorSystem("BankAccount")
  val bankUser = system.actorOf(Props[Counter],"bankUser")
  import Bank._

  bankUser ! Deposit(0)

  (1 to 5).foreach(_ => bankUser ! Deposit (100))
  bankUser ! PrintBankStatement
  (1 to 1).foreach(_ => bankUser ! Withdraw (300))
  bankUser ! PrintBankStatement

  bankUser ! Withdraw (201)


  system.terminate()
}
