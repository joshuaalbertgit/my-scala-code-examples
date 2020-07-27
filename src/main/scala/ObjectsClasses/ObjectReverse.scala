package ObjectsClasses

object ObjectReverse extends App {
  println("Hello!")

  object Reverse {
    def apply(str: String): String = {
      println(str.reverse)
      str.reverse
    }
  }

  //no need to call the method apply
  Reverse("normal")



}
