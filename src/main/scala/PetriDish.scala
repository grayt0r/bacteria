package com.grayt0r.bacteria

import scala.collection.mutable.ArrayBuffer

class PetriDish(val height: Int = 5, val width: Int = 5) {
  var grid = Array.ofDim[Boolean](height, width)
  var nextGen = Array.ofDim[Boolean](height, width)
  
  def this(liveCells: Array[(Int, Int)]) {
    this()
    setLiveCells(liveCells)
  }
  
  def readInput() {
    grid = Array.ofDim[Boolean](height, width)
    println("\nPlease enter details of the live cells...")
  
    var readingInput = true
    val inputs = new ArrayBuffer[(Int, Int)]
    
    while (readingInput) {
      try {
        val tokens = readLine().split(",")
        val x = tokens(0).trim().toInt
        val y = tokens(1).trim().toInt
        if (x == -1 && y == -1) {
          readingInput = false
        } else {
          val t = (x, y)
          inputs += t
        }
      } catch {
        case e => {
          println("Error parsing input. Expected format: x,y.")
        }
      }
    }
    
    setLiveCells(inputs.toArray)
  }
  
  def setLiveCells(liveCells: Array[(Int, Int)]) {
    liveCells.foreach { lc => grid(lc._1)(lc._2) = true }
  }

  def calculateNextGeneration(isPrint: Boolean) {
    nextGen = Array.ofDim[Boolean](height, width)
    
    println("\nLive cells in the next generation:")
  
    for (y <- 0 until height) {
      for (x <- 0 until width) {
        val isLive = grid(y)(x)
        val count = calculateNumberOfLiveNeighbours(x, y)
      
        // Rule 2
        if (isLive && (count == 2 || count ==3)) nextGen(y)(x) = true
        // Rule 4
        if (!isLive && count == 3) nextGen(y)(x) = true
        
        // Print the output
        if (nextGen(y)(x)) println(x + "," + y)
      }
    }
    
    // Print the terminator
    println("-1,-1\n")
    
    if (isPrint) printGrids(width)
    
    println("Would you like to continue? (y/n)")
    if (readLine() == "y") {
      grid = nextGen
      calculateNextGeneration(isPrint)
    }
  }

  def calculateNumberOfLiveNeighbours(x: Int, y: Int) = {
    val neighbours = List((x-1, y-1), (x, y-1), (x+1, y-1), (x-1, y), (x+1, y), (x-1, y+1), (x, y+1), (x+1, y+1))
    
    // Loop round each potential neighbour, keeping a count of the number of live ones
    neighbours.foldLeft(0) { (total, n) =>
      try {
        if (grid(n._2)(n._1)) total + 1 else total
      } catch {
        // Catch the case where the neighbour isn't a valid array index
        case _: ArrayIndexOutOfBoundsException => total
      }
    }
  }

  def printGrids(width: Int) {    
    print("Original:")
    for(i <- 1 to (width*2 -2)) print(" ")
    print("Next Gen:\n")
  
    // Print top row
    print("* ")
    for (i <- 0 until width) print("* ")
    print("*    * ")
    for (i <- 0 until width) print("* ")
    print("* \n")
  
    // Print each cell in the grid
    for (y <- 0 until height) {
      print("* ")
      for(el <- grid(y)) if(el) print("X ") else print("- ")
      print("*    * ")
      for(el <- nextGen(y)) if(el) print("X ") else print("- ")
      print("* \n")
    }
  
    // Print bottom row
    print("* ")
    for (i <- 0 until width) print("* ")
    print("*    * ")
    for (i <- 0 until width) print("* ")
    print("* \n\n")
  }

}