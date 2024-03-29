// Part 2 about Alcohol-Consumption Worldwide
//============================================

object CW6b {

import io.Source
import scala.util._

val url_alcohol =
  "https://raw.githubusercontent.com/fivethirtyeight/data/master/alcohol-consumption/drinks.csv"

val file_population =
  "population.csv"


//(1) Complete the get_csv_page function below. It takes a URL-string
//    as argument and generates a list of strings corresponding to each
//    line in the downloaded csv-list. The URL url_alcohol above is one
//    possible argument.

def get_csv_page(url: String) : List[String] = {

    Source.fromURL(url).mkString.split("\n").toList

}


//    Complete the get_csv_file function below. It takes a file name
//    as argument and reads the content of the given file. Like above,
//    it should generate a list of strings corresponding to each
//    line in the csv-list. The filename file_population is one possible
//    argument.

def get_csv_file(file: String) : List[String] = {

    Source.fromFile(file).getLines.toList
}




//(2) Complete the functions that process the csv-lists. For
//    process_alcs extract the country name (as String) and the
//    pure alcohol consumption (as Double). For process_pops
//    generate a Map of Strings (country names) to Long numbers
//    (population sizes).

def process_alcs(lines: List[String]) : List[(String, Double)] = {

  val list = for {
      line <- lines
      lst = line.split(",").toList
  } yield { (lst(0), lst(4).toDouble) }
  list
}

def process_pops(lines: List[String]) : Map[String, Long] = {

  val list = for {
    line <- lines
    lst = line.split(",").toList
    } yield { (lst(0), lst(1).toLong) }
  list.toMap
}



//(3) Calculate for each country the overall alcohol_consumption using
//    the data from the alcohol list and the population sizes list. You
//    should only include countries on the alcohol list that are also
//    on the population sizes list with the exact same name. Note that
//    the spelling of some names in the alcohol list differs from the
//    population sizes list. You can ignore entries where the names differ.
//    Sort the resulting list according to the country with the highest alcohol
//    consumption to the country with the lowest alcohol consumption.

def sorted_country_consumption() : List[(String, Long)] = {
  val nameA = process_alcs(get_csv_page(url_alcohol).drop(1))
  val nameB = process_pops(get_csv_file(file_population).drop(1))
  val list = for(i <- nameA ; j <- nameB) yield {
    if (i._1 == j._1) {
      val total = i._2.toDouble * j._2.toDouble
      (i._1, total.toLong) }
    else
      ("a", 2L)
  }

  list.toList.filter(_ != ("a", 2L)).sortBy(_._2).reverse
}


//   Calculate the world consumption of pure alcohol of all countries, which
//   should be the first element in the tuple below. The second element is
//   the overall consumption of the first n countries in the sorted list
//   from above; and finally the double should be the percentage of the
//   first n countries drinking from the the world consumption of alcohol.

def percentage(n: Int) : (Long, Long, Double) = {

    val result = for (
        n <- sorted_country_consumption()
    ) yield (n.toString().split(","))(1).substring(0, n.toString().split(",")(1).length-1).toLong
    val triple = ((result.sum).toLong, (result.take(n).sum).toLong, (result.take(n).sum.toDouble * 100) / (result.sum.toDouble))
    triple

}

}
