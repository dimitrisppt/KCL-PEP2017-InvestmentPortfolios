// Advanced Part 3 about a really dumb investment strategy
//==========================================================

object CW6c {


//two test portfolios

val blchip_portfolio = List("GOOG", "AAPL", "MSFT", "IBM", "FB", "AMZN", "BIDU", "FOOBAR")

val rstate_portfolio = List("PLD", "PSA", "AMT", "AIV", "AVB", "BXP", "CCI", "DLR", "EQIX", "EQR", "ESS", "EXR", "FRT", "GGP", "HCP") 

// (1.a) The function below takes a stock symbol and a year as arguments.
//       It should read the corresponding CSV-file and read the January
//       data from the given year. The data should be collected in a list of
//       strings for each line in the CSV-file.

import io.Source
import scala.util._

def get_january_data(symbol: String, year: Int) : List[String] = {
    val url = "http://ichart.yahoo.com/table.csv?s=" + symbol
    val lines = Source.fromURL(url).mkString.split("\n").toList
    for(singleLine <- lines.drop(1)
        if (singleLine.startsWith(year.toString))) yield singleLine
}


// (1.b) From the output of the get_january_data function, the next function
//       should extract the first line (if it exists) and the corresponding
//       first trading price in that year as Option[Double]. If no line is
//       generated by get_january_data then the result is None


def get_first_price(symbol: String, year: Int): Option[Double] = {
   Try{
     val data = get_january_data(symbol,year)
     (data.head.split(",")(1).toDouble)
   }
   .toOption
}


// (1.c) Complete the function below that obtains all first prices
//       for the stock symbols from a portfolio (list of strings) and
//       for the given range of years. The inner lists are for the
//       stock symbols and the outer list for the years.


def get_prices(portfolio: List[String], years: Range) : List[List[Option[Double]]] = {
    val listOfYears = years.toList
    listOfYears.map(singleYear => portfolio.map(get_first_price(_, singleYear)))
}



// (2) The first function below calculates the change factor (delta) between
//     a price in year n and a price in year n + 1. The second function calculates
//     all change factors for all prices (from a portfolio). The input to this
//     function are the nested lists created by get_prices above.

//def get_delta(price_old: Option[Double], price_new: Option[Double]) : Option[Double] = ...

//def get_deltas(data: List[List[Option[Double]]]) :  List[List[Option[Double]]] = ...



// (3) Write a function that given change factors, a starting balance and a year
//     calculates the yearly yield, i.e. new balance, according to our dump investment
//     strategy. Another function calculates given the same data calculates the
//     compound yield up to a given year. Finally a function combines all
//     calculations by taking a portfolio, a range of years and a start balance
//     as arguments.


//def yearly_yield(data: List[List[Option[Double]]], balance: Long, year: Int) : Long = ...

//def compound_yield(data: List[List[Option[Double]]], balance: Long, year: Int) : Long = ...

//def investment(portfolio: List[String], years: Range, start_balance: Long) : Long = ...



//test cases for the two portfolios given above

//investment(rstate_portfolio, 1978 to 2017, 100)
//investment(blchip_portfolio, 1978 to 2017, 100)

}
