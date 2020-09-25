package FunctionLiteralsAndPlaceholders

//StockMarketData.csv => fields => Date, Open, High, Low, Close, Company
case class StockMarketData(date: String,
                          open: Float,
                          high: Float,
                          low: Float,
                          close: Float,
                          company: String)



/*case class StockRecord(date:String,
                       open:Float,
                       high:Float,
                       low:Float,
                       close:Float,
                       adj_close:Float,
                       volume:Double)
 */