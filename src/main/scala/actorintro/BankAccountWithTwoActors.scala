package actorintro

import actorintro.BankAccountWithTwoActors.Person.LiveTheLife
import akka.actor.{Actor, ActorRef, ActorSystem, Props}
object BankAccountWithTwoActors extends App {
  //DOMAIN
  object BankAccount {
    case class Deposit (amount: Int)
    case class Withdraw (amount: Int)
    case class NOT_ENOUGH_BALANCE(amountAsked: Int)
    case object PrintBankStatement
    case class TransactionFailure (message: String)
    case class TransactionSuccess (message: String)
  }
  class BankAccount extends Actor{
   import BankAccount._
    var amount = 0
    override def receive: Receive = {//message handler
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
        else {
          amount -= amt
          self ! TransactionSuccess(s"$amt been withdrawn successfully!")
        }
      case NOT_ENOUGH_BALANCE (asked) =>
        println(s"Sorry, no available balance exists! " +
              s"\n Asked amount:: $asked | Available balance:: $amount")
      case PrintBankStatement =>
        println(s"Current Balance :: $amount")
      case TransactionFailure(message) => println(message)
      case TransactionSuccess(message) => println(message)
    }
  }//end of actor class BankAccount

  object Person {
    case class LiveTheLife (bankAccount: ActorRef, int : Int)
  }
  class Person extends Actor {

    import BankAccount._
    import Person._

    override def receive: Receive = {
      case LiveTheLife(bankAccount, int) =>
        bankAccount ! Deposit (int*5)
        bankAccount ! Withdraw (int*2)
        bankAccount ! PrintBankStatement
    }

} //end of class actor Person

  val system = ActorSystem("BankAccount")

  val bankAccount = system.actorOf(Props[BankAccount],"bankUser")
  val person = system.actorOf(Props[Person],"person")

  //person using the bank account Actor
  person ! LiveTheLife(bankAccount, 100)
  system.terminate()
}
