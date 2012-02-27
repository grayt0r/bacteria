object MultiArray {
  def main(args: Array[String]) {
    val dish = Array.ofDim[Boolean](5, 5)
    
    readInput(dish)
    printDish(dish)
    val nextGen = calculateNextGeneration(dish)
    printDish(nextGen)
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
    val numLiveNeighbours = Array.ofDim[Int](5, 5)
    
    for (y <- 0 until dish.length) {
      for (x <- 0 until dish(y).length) {
        
        val isLive = dish(y)(x)
        
        // Calculate the number of live neighbours
        val count = calculateNumberOfLiveNeighbours(dish, y, x)
        numLiveNeighbours(y)(x) = count
        
        // Rule 2
        if (isLive && (count == 2 || count ==3)) nextGen(y)(x) = true
        // Rule 4
        if (!isLive && count == 3) nextGen(y)(x) = true
      }
    }
    
    printDish(numLiveNeighbours)
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
  
  def printDish(dish: Array[Array[Boolean]]) {
    val width = dish(0).length + 2
    
    print("\n\n")
    
    // Print top row
    for (i <- 0 until width) print("* ")
    println("")
    
    // Print each cell in the grid
    for (row <- dish) {
      print("* ")
      for(el <- row) if(el) print("X ") else print("- ")
      println("* ")
    }
    
    // Print bottom row
    for (i <- 0 until width) print("* ")
    print("\n\n")
  }
  
  def printDish(dish: Array[Array[Int]]) {
    val width = dish(0).length + 2
    
    print("\n\n")
    
    // Print top row
    for (i <- 0 until width) print("* ")
    println("")
    
    // Print each cell in the grid
    for (row <- dish) {
      print("* ")
      for(el <- row) print(el + " ")
      println("* ")
    }
    
    // Print bottom row
    for (i <- 0 until width) print("* ")
    print("\n\n")
  }
}

//dish(0)(1) = true
//dish(1)(2) = true

//for (row <- dish)
//  for (el <- row)
//    println(el)

//for (row <- dish; el <- row) println(el)

//matrix(row)(column) = 42