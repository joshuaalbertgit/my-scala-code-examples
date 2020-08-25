package ustexcercises

object ListSortExample extends App {

  var list1 = "1,2,3,4"
  var list2 = "5,6,7,8"

  val numberSort=List(9,7,8,5,6)

  def mergeSort(mainList:List[Int]): List[Int] = {

    val n=mainList.length/2
    if(n==0) mainList
    else
    {
      //merge function logic
      def merge(oneList : List[Int],secondList:List[Int]) : List[Int] = (oneList,secondList) match {
        case (Nil,secondList) => secondList
        case (oneList,Nil) => oneList
        case (x:: xs1,y::ys1) =>
          if(x < y) x :: merge(xs1,secondList)
          else  y :: merge(oneList,ys1)
      }
      val(firstList,anotherList)= mainList splitAt n
      merge (mergeSort(firstList),mergeSort(anotherList))
    }
  }
  val result=mergeSort(numberSort)
  println(result)

}
