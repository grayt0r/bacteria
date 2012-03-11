import org.specs2.mutable._
import org.specs2.specification.Scope

import com.grayt0r.bacteria.PetriDish

class PetriDishSpec extends Specification {

  "A PetriDish" should {
    "have a default grid of 5x5" in new dish {
      pd.grid.length must beEqualTo(5)
      pd.grid(0).length must beEqualTo(5)
    }
    "not print the grids by default" in new dish {
      pd.printGrid must beEqualTo(false)
    }
    "be initialised with false values" in new dish {
      for(row <- pd.grid; el <- row) el must beEqualTo(false)
    }
    "have the live cells set via a method call" in new dish {
      val liveCells = Array((1, 2), (2, 2), (3, 2))
      pd.setLiveCells(liveCells)
      
      pd.grid(2)(1) must beEqualTo(true)
      pd.grid(2)(2) must beEqualTo(true)
      pd.grid(2)(3) must beEqualTo(true)
    }
    "parse command line arguments to be used as the live cells" in new dish {
      pd.readInput()
      
      pd.grid(2)(1) must beEqualTo(true)
      pd.grid(2)(2) must beEqualTo(true)
      pd.grid(2)(3) must beEqualTo(true)
    }
    "be able to calculate the next generation of live cells" in new dish {
      pd.readInput()
      pd.calculateNextGeneration()
      
      pd.nextGen(1)(2) must beEqualTo(true)
      pd.nextGen(2)(2) must beEqualTo(true)
      pd.nextGen(3)(2) must beEqualTo(true)
      
      pd.nextGen(2)(1) must beEqualTo(false)
      pd.nextGen(2)(3) must beEqualTo(false)
    }
  }
  
  "The parseInput method" should {
    "parse simple coordinates and return a tuple" in new dish {
      var t = pd.parseInput("1,2")
      t._1 must beEqualTo(1)
      t._2 must beEqualTo(2)
    }
    "handle extra whitespace" in new dish {
      var t = pd.parseInput(" 1 , 2 ")
      t._1 must beEqualTo(1)
      t._2 must beEqualTo(2)
    }
    "throw an IllegalArgumentException if the input is invalid" in new dish {
      pd.parseInput("abc, abc") must throwA[IllegalArgumentException]
    }
  }
  
  //"The parseInput method" should {  
  //}
  
  trait dish extends Scope {
    var dummyInputs = List("1,2", "2,2", "3,2", "-1,-1")
    
    def getDummyInput() = {
      val head = dummyInputs.head
      dummyInputs = dummyInputs.tail
      head
    }
    
    val pd = new PetriDish(getInput = getDummyInput)
  }
  
}