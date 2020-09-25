package FunctionLiteralsAndPlaceholders

object FunctionLiteralsAndPlaceholders extends App {

  val readFinanceData = () =>{

    //read the file
    val source = io.Source.fromFile("src/main/resources/GOOG.csv")
    //write expression
    //read trim and fill the StockRecord (case class)
    for {
      line <- source.getLines().drop(1).toVector
      cols = line.split(",").map(_.trim)
    }yield StockRecord (cols(0),
                        cols(1).toFloat,
                        cols(2).toFloat,
                        cols(3).toFloat,
                        cols(4).toFloat,
                        cols(5).toFloat,
                        cols(6).toDouble)
    //return or yield
  }

  val printData = (data: Vector[StockRecord]) => {
    println(data.size)
    data.foreach(a => println(a))
  }

  val getCloseData = (dataType: String, data: Vector[StockRecord]) => {
    var specificData = data.filter(stockRecord => stockRecord.close > 1508.00)
    println(s"close > 1508.00 " + specificData.size)
    specificData.foreach(a => println(a))
  }

  val getCloseValueOnDate = (date: String) => {
    var specificDateStock = data.filter(_.date == date)
    specificDateStock.map(_.close).head //first record
  }

  var data = readFinanceData()
  //var results = printData(data)
  //var closeData = getCloseData("Close", data)

  //the below code is the function literal definitions
  val getTotalNumberOfRows = () => data.size
  println(s"Dataset size: ${getTotalNumberOfRows()}")

  val getAvgCloseValue = () => data.map(_.close).sum / getTotalNumberOfRows()
  println(s"Get average close:: ${getAvgCloseValue()}")


  val getMinCloseValue = () => data.map(_.close).min
  println(s"Get min close:: ${getMinCloseValue()}")

  val getMaxCloseValue = () => data.map(_.close).max
  println(s"Get max close:: ${getMaxCloseValue()}")

  var getDate = "2020-01-17"
  var getStockByDate = getCloseValueOnDate(getDate)
  println(s"Stock on this data $getDate is :: ${getStockByDate}")

  getDate = "2020-01-03"
  getStockByDate = getCloseValueOnDate(getDate)
  println(s"Stock on this data $getDate is :: ${getStockByDate}")

  val getStockRecordByDate = (date: String)  =>{
    data.filter(_.date == date)
  }
  println(s"getStockRecordByDate ${getStockRecordByDate(getDate)}")

  val record = data.filter(_.date == "2020-01-03")
  println(s"record :: $record")

  val priceDelta = (_: Double) - (_:Double)
  val getDailyDelta = (openPrice:Double, closePrice:Double,
                       delta:(Double, Double) => Double) => delta(openPrice, closePrice)

  println(s"priceDelta :: $priceDelta")
  println(s"open :: ${record.head.open}")
  println(s"close :: ${record.head.close}")
  var opencloseval =record.head.open-record.head.close
  println(s"open-close :: $opencloseval")
  print(getDailyDelta(record(0).open, record(0).close, priceDelta))
}
