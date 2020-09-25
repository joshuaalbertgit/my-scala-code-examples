package FunctionLiteralsAndPlaceholders

//GOOG.csv => fields => Date,Open,High,Low,Close,Adj Close,Volume

case class StockRecord(date:String,
                       open:Float,
                       high:Float,
                       low:Float,
                       close:Float,
                       adj_close:Float,
                       volume:Double)
