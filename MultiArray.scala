object MultiArray {
  def main(args: Array[String]) {
    val dish = Array.ofDim[Boolean](5, 5)
    
    readInput(dish)
    
    val nextGen = calculateNextGeneration(dish)
    
    printDishes(dish, nextGen)
  }
  
  def readInput(dish: Array[Array[Boolean]]) {
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
  }
  
  def calculateNextGeneration(dish: Array[Array[Boolean]]) = {
    val nextGen = Array.ofDim[Boolean](5, 5)
    
    for (y <- 0 until dish.length) {
      for (x <- 0 until dish(y).length) {
        
        val isLive = dish(y)(x)
        
        // Calculate the number of live neighbours
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
    var count = 0
    
    if(isNeighbourLive(dish, y-1, x-1)) count += 1
    if(isNeighbourLive(dish, y-1, x)) count += 1
    if(isNeighbourLive(dish, y-1, x+1)) count += 1
    if(isNeighbourLive(dish, y, x-1)) count += 1
    if(isNeighbourLive(dish, y, x+1)) count += 1
    if(isNeighbourLive(dish, y+1, x-1)) count += 1
    if(isNeighbourLive(dish, y+1, x)) count += 1
    if(isNeighbourLive(dish, y+1, x+1)) count += 1
    
    count
  }
  
  def isNeighbourLive(dish: Array[Array[Boolean]], y: Int, x: Int): Boolean = {
    if (y < 0 || y >= dish.length) return false
    if (x < 0 || x >= dish(0).length) return false
    
    dish(y)(x)
  }
  
  def printDishes(orig: Array[Array[Boolean]], nextGen: Array[Array[Boolean]]) {
    val width = orig(0).length + 2
    
    println()
    
    println("Original:        Next Gen:")
    
    // Print top row
    for (i <- 0 until width) print("* ")
    print("   ")
    for (i <- 0 until width) print("* ")
    println()
    
    // Print each cell in the grid
    for (y <- 0 until orig.length) {
      print("* ")
      for(el <- orig(y)) if(el) print("X ") else print("- ")
      print("*    * ")
      for(el <- nextGen(y)) if(el) print("X ") else print("- ")
      print("* ")
      println()
    }
    
    // Print bottom row
    for (i <- 0 until width) print("* ")
    print("   ")
    for (i <- 0 until width) print("* ")
    
    println()
    println()
  }
}