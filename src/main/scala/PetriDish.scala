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
      val tokens = readLine().split(",")
      val x = tokens(0).toInt
      val y = tokens(1).toInt
      if (x == -1 && y == -1) {
        readingInput = false
      } else {
        val t = (y, x)
        inputs += t
      }
    }
    
    setLiveCells(inputs.toArray)
  }
  
  def setLiveCells(liveCells: Array[(Int, Int)]) {
    liveCells.foreach { lc => grid(lc._1)(lc._2) = true }
  }

  def calculateNextGeneration() {
    nextGen = Array.ofDim[Boolean](height, width)
  
    for (y <- 0 until height) {
      for (x <- 0 until width) {
        val isLive = grid(y)(x)
        val count = calculateNumberOfLiveNeighbours(y, x)
      
        // Rule 2
        if (isLive && (count == 2 || count ==3)) nextGen(y)(x) = true
        // Rule 4
        if (!isLive && count == 3) nextGen(y)(x) = true
      }
    }
  }

  def calculateNumberOfLiveNeighbours(y: Int, x: Int) = {
    val neighbours = List((y-1, x-1), (y-1, x), (y-1, x+1), (y, x-1), (y, x+1), (y+1, x-1), (y+1, x), (y+1, x+1))
  
    neighbours.foldLeft(0) { (total, n) =>
      if (!((n._1 < 0 || n._1 >= height) || (n._2 < 0 || n._2 >= width)) && grid(n._1)(n._2)) {
        total + 1
      } else {
        total
      }
    }
  }

  def printGrids() {    
    println("\nOriginal:        Next Gen:")
  
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
    print("* \n\n\n")
  }

}