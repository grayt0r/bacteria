package com.grayt0r.bacteria

import scala.collection.mutable.ArrayBuffer

class PetriDish(
    val height: Int = 5,
    val width: Int = 5,
    val printGrid: Boolean = false,
    val getInput: () => String = readLine) {
  
  var grid = Array.ofDim[Boolean](height, width)
  var nextGen = Array.ofDim[Boolean](height, width)
  
  def simulate() {
    println("\nPlease enter details of the live cells...")
    readInput()
    calculateNextGeneration()
    printResult()
  }
  
  def readInput() {
    grid = Array.ofDim[Boolean](height, width)
  
    var readingInput = true
    val inputs = new ArrayBuffer[(Int, Int)]
    
    while (readingInput) {
      try {
        val coords = parseInput(getInput())
        if (coords._1 == -1 && coords._2 == -1) {
          readingInput = false
        } else {
          inputs += coords
        }
      } catch {
        case e: IllegalArgumentException => println(e.getMessage)
      }
    }
    
    setLiveCells(inputs.toArray)
  }
  
  def parseInput(line: String): (Int, Int) = {
    try {
      val tokens = line.split(",")
      val x = tokens(0).trim().toInt
      val y = tokens(1).trim().toInt
      return (x, y)
    } catch {
      case e => throw new IllegalArgumentException("Error parsing input. Please enter two integers in format: x,y")
    }
  }
  
  def setLiveCells(liveCells: Array[(Int, Int)]) {
    liveCells.foreach { lc => grid(lc._2)(lc._1) = true }
  }

  def calculateNextGeneration() {
    nextGen = Array.ofDim[Boolean](height, width)
  
    for (y <- 0 until height) {
      for (x <- 0 until width) {
        val isLive = grid(y)(x)
        val count = calculateNumberOfLiveNeighbours(x, y)
      
        // Rule 2
        if (isLive && (count == 2 || count ==3)) nextGen(y)(x) = true
        // Rule 4
        if (!isLive && count == 3) nextGen(y)(x) = true
      }
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
  
  def printResult() {
    println("\nLive cells in the next generation:")
    
    for (y <- 0 until height) {
      for (x <- 0 until width) {
        // Print the output
        if (nextGen(y)(x)) println(x + "," + y)
      }
    }
    
    // Print the terminator
    println("-1,-1\n")
    
    if (printGrid) printGrids(width)
    
    println("Would you like to continue? (y/n)")
    if (readLine() == "y") {
      grid = nextGen
      calculateNextGeneration()
      printResult()
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