import org.specs2.mutable._
import org.specs2.specification.Scope

import com.grayt0r.bacteria.Bacteria

class BacteriaSpec extends Specification {

  "Bacteria" should {
    "use a --print parameter to flag if the grids should be printed" in {
      val options = Bacteria.parseOptions(Map(), List("--print"))
      options.getOrElse('print, false).asInstanceOf[Boolean] must beEqualTo(true)
    }
    "use a --height parameter to specify the grid height" in {
      val options = Bacteria.parseOptions(Map(), List("--height", "25"))
      options.getOrElse('height, 5).asInstanceOf[Int] must beEqualTo(25)
    }
    "use a --width parameter to specify the grid width" in {
      val options = Bacteria.parseOptions(Map(), List("--width", "15"))
      options.getOrElse('width, 5).asInstanceOf[Int] must beEqualTo(15)
    }
    "throw an IllegalArgumentException if an unknown argument is encountered" in {
      Bacteria.parseOptions(Map(), List("--testy")) must throwA[IllegalArgumentException]
    }
  }
  
}