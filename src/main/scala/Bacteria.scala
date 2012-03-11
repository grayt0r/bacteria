package com.grayt0r.bacteria

object Bacteria {
  
  type OptionMap = Map[Symbol, Any]
  
  def main(args: Array[String]) {
    try {
      val options = parseOptions(Map(), args.toList)
      
      val printArg = options.getOrElse('print, false).asInstanceOf[Boolean]
      val heightArg = options.getOrElse('height, 5).asInstanceOf[Int]
      val widthArg = options.getOrElse('width, 5).asInstanceOf[Int]

      val pd = new PetriDish(heightArg, widthArg, printArg)
      pd.simulate()
      
    } catch {
      case e: IllegalArgumentException =>
        println(e.getMessage)
        sys.exit(1)
    }
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
        throw new IllegalArgumentException("Unknown argument: " + option)
    }
  }
  
}