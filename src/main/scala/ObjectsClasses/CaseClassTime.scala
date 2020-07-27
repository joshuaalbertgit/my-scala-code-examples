package ObjectsClasses

object CaseClassTime extends App {
  println("Hello!")

  case class Time(hours: Int = 0, minutes: Int = 0)

  def printCurrentTime() = {
      println(s"Hrs:: ${time1.hours} Mts:: ${time1.minutes}")
  }

  var time1 = Time(1, 30)
  printCurrentTime()

  time1 = time1.copy(hours = 2, minutes = 23)
  printCurrentTime()

  time1 = time1.copy(minutes = 4, hours = 21)
  printCurrentTime()


}
