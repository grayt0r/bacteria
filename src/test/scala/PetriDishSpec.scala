import org.specs2.mutable._
import org.specs2.specification.Scope

import com.grayt0r.bacteria.PetriDish

class PetriDishSpec extends Specification {

  "A PetriDish" should {
    "have a default grid of 5x5" in new dish {
      pd.grid.length must beEqualTo(5)
      pd.grid(0).length must beEqualTo(5)
    }
    "be initialised with false values" in new dish {
      for(row <- pd.grid; el <- row) el must beEqualTo(false)
    }
    "be able to have the live cells set via the constructor" in new liveDish {
      pd.grid(2)(1) must beEqualTo(true)
      pd.grid(2)(2) must beEqualTo(true)
      pd.grid(2)(3) must beEqualTo(true)
    }
    "be able to have the live cells set via a method call" in new dish {
      val liveCells = Array((2, 1), (2, 2), (2, 3))
      pd.setLiveCells(liveCells)
      
      pd.grid(2)(1) must beEqualTo(true)
      pd.grid(2)(2) must beEqualTo(true)
      pd.grid(2)(3) must beEqualTo(true)
    }
    "be able to calculate the next generation of live cells" in new liveDish {
      pd.calculateNextGeneration()
      
      pd.nextGen(1)(2) must beEqualTo(true)
      pd.nextGen(2)(2) must beEqualTo(true)
      pd.nextGen(3)(2) must beEqualTo(true)
      
      pd.nextGen(2)(1) must beEqualTo(false)
      pd.nextGen(2)(3) must beEqualTo(false)
    }
    "handle malformed input" in new dish {
      val liveCells = Array(("a", "b"))
      pd.setLiveCells(liveCells)
    }
  }
  
  trait dish extends Scope {
    val pd = new PetriDish()
  }
  
  trait liveDish extends Scope {
    val pd = new PetriDish(Array((2, 1), (2, 2), (2, 3)))
  }
  
}