object MultiArray {
  private val HEIGHT = 5
  private val WIDTH = 5
  
  def main(args: Array[String]) {
    val dish = readInput()
    val nextGen = calculateNextGeneration(dish)
    printDishes(dish, nextGen)
  }
  
  def readInput() = {
    val dish = Array.ofDim[Boolean](HEIGHT, WIDTH)
    
    println("Please enter details of the live cells...")
    
    var readingInput = true
    while (readingInput) {
      val input = readLine().split(",")
      val x = input(0).toInt
      val y = input(1).toInt
      if (x == -1 && y == -1) {
        readingInput = false
      } else {
        dish(y)(x) = true
      }
    }
    
    dish
  }
  
  def calculateNextGeneration(dish: Array[Array[Boolean]]) = {
    val nextGen = Array.ofDim[Boolean](HEIGHT, WIDTH)
    
    for (y <- 0 until HEIGHT) {
      for (x <- 0 until WIDTH) {
        val isLive = dish(y)(x)
        val count = calculateNumberOfLiveNeighbours(dish, y, x)
        
        // Rule 2
        if (isLive && (count == 2 || count ==3)) nextGen(y)(x) = true
        // Rule 4
        if (!isLive && count == 3) nextGen(y)(x) = true
      }
    }
    
    nextGen
  }
  
  def calculateNumberOfLiveNeighbours(dish: Array[Array[Boolean]], y: Int, x: Int) = {
    val neighbours = List((y-1, x-1), (y-1, x), (y-1, x+1), (y, x-1), (y, x+1), (y+1, x-1), (y+1, x), (y+1, x+1))
    
    neighbours.foldLeft(0) { (total, n) =>
      if (!((n._1 < 0 || n._1 >= HEIGHT) || (n._2 < 0 || n._2 >= WIDTH)) && dish(n._1)(n._2)) {
        total + 1
      } else {
        total
      }
    }
  }
  
  def printDishes(orig: Array[Array[Boolean]], nextGen: Array[Array[Boolean]]) {    
    println("\nOriginal:        Next Gen:")
    
    // Print top row
    print("* ")
    for (i <- 0 until WIDTH) print("* ")
    print("*    * ")
    for (i <- 0 until WIDTH) print("* ")
    print("* \n")
    
    // Print each cell in the grid
    for (y <- 0 until HEIGHT) {
      print("* ")
      for(el <- orig(y)) if(el) print("X ") else print("- ")
      print("*    * ")
      for(el <- nextGen(y)) if(el) print("X ") else print("- ")
      print("* \n")
    }
    
    // Print bottom row
    print("* ")
    for (i <- 0 until WIDTH) print("* ")
    print("*    * ")
    for (i <- 0 until WIDTH) print("* ")
    print("* \n\n\n")
  }
  
}