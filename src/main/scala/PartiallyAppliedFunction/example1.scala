package PartiallyAppliedFunction

object example1 extends App {

  val googleStockPrices = List(1200, 1209.32,1367.36, 1360.66, 1394.20, 1393.33, 1404.31, 1419.82, 1429.73)

  def checkPriceGreaterThan1400(price: Double):Boolean = price > 1400

  val googleStockPricesGreaterThan1400 =
    googleStockPrices.filter(price => checkPriceGreaterThan1400(price))

  println(googleStockPricesGreaterThan1400)

  val filList = googleStockPrices.filter(_ > 1419.00)
  println(filList)

  println(checkPriceGreaterThan1400(100))

  //takes each entries from the lists and checks this condition without a variable, _ takes care of it
  println(googleStockPrices.filter(_ > 1400.00))

  //takes each entries from the lists and checks this condition
  // with a variable, price takes care of it
  println(googleStockPrices.filter(price => price > 1400.00))

  //#1 - WAY 1
  //now filter by a Function/method checkPriceGreaterThan1400
  var filList1400Above1 = googleStockPrices.filter(price => checkPriceGreaterThan1400(price))
  println(s"filList1400Above1 $filList1400Above1")

  //#2 - WAY 2
  var filList1400Above2 = googleStockPrices.filter(checkPriceGreaterThan1400 _)
  println(s"filList1400Above2 $filList1400Above2")

  //#3 - WAY 3
  var filList1400Above3 = googleStockPrices.filter(checkPriceGreaterThan1400)
  println(s"filList1400Above3 $filList1400Above3")

  //WAY-4
  val checkPricesGreaterThan1400Function1 = price => checkPriceGreaterThan1400 (price)
  val googleStockPricesGreaterThan1400_1_1 = googleStockPrices.filter(price =>checkPricesGreaterThan1400Function1(price))
  val googleStockPricesGreaterThan1400_1_2 = googleStockPrices.filter(checkPricesGreaterThan1400Function1)
  println(googleStockPricesGreaterThan1400_1_1)
  println(googleStockPricesGreaterThan1400_1_2)

  //way-5
  val checkPricesGreaterThan1400Function2 = checkPriceGreaterThan1400 _
  val googleStockPricesGreaterThan1400_2 = googleStockPrices.filter(checkPricesGreaterThan1400Function2)
  println(googleStockPricesGreaterThan1400_2)

  //method way
  def checkPriceInRange(price: Double, lower: Double, upper: Double) : Boolean = price >= lower && price <= upper
  //function way
  val checkPriceInRangeFunction = (price: Double, lower: Double, upper: Double) => price >= lower && price <= upper

  var filterRangeList = googleStockPrices.filter((price) => checkPriceInRangeFunction(price,1300,1410))//way 1 passing each value in the list
  println(s"filterRangeList-1 $filterRangeList")
  filterRangeList = googleStockPrices.filter(price => checkPriceInRangeFunction(price,1295,1410))//way 2
  println(s"filterRangeList-2 $filterRangeList")
  filterRangeList = googleStockPrices.filter(checkPriceInRangeFunction(_,1395,1410))//way 3 passing full set with _
  println(s"filterRangeList-3 $filterRangeList")


  //partially applied functions
  //calling this method checkPriceInRange in a function (partial - example setting upper limit)
  val funcForMethod = checkPriceInRange(_: Double, _: Double, 1500)
  //when you call this function, it calls the method underneath, where no need to pass the 3rd parameter

  //defining partially applied functions with only one param (upper)
  var partialList = googleStockPrices.filter(funcForMethod(_,1200))//pass the full set (each values in the list) with lower boundary
  println(s"partialList-1 $partialList")

  partialList = googleStockPrices.filter(funcForMethod(_,1300))
  println(s"partialList-2 $partialList")

  partialList = googleStockPrices.filter(funcForMethod(_,1400))
  println(s"partialList-3 $partialList")

  val funcForMethod2 = checkPriceInRange(_: Double, 1200, 1500)//defining partially applied functions with two params (upper and lower)
  var filList3= googleStockPrices.filter(funcForMethod2)
  println(s"filList3 $filList3")

}
