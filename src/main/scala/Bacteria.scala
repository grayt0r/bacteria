package com.grayt0r.bacteria

object Bacteria {
  
  type OptionMap = Map[Symbol, Any]
  
  def main(args: Array[String]) {
    val options = parseOptions(Map(), args.toList)
    
    val print = options.getOrElse('print, false).asInstanceOf[Boolean]
    val height = options.getOrElse('height, 5).asInstanceOf[Int]
    val width = options.getOrElse('width, 5).asInstanceOf[Int]
    
    val pd = new PetriDish(height, width)
    pd.readInput()
    pd.calculateNextGeneration(print)
  }
  
  def parseOptions(map: OptionMap, list: List[String]) : OptionMap = {    
    list match {
      case Nil => map
      case "--print" :: tail =>
        parseOptions(map ++ Map('print -> true), tail)
      case "--height" :: value :: tail =>
        parseOptions(map ++ Map('height -> value.toInt), tail)
      case "--width" :: value :: tail =>
        parseOptions(map ++ Map('width -> value.toInt), tail)
      case option :: tail =>
        println("Unknown option: " + option) 
        sys.exit(1) 
    }
  }
  
}