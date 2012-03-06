import com.grayt0r.bacteria.PetriDish

object Bacteria {
  def main(args: Array[String]) {
    
    val pd = new PetriDish()
    pd.readInput()
    pd.calculateNextGeneration()
    pd.printGrids()
    
    /*
    val pd = new PetriDish(Array((2, 1), (2, 2), (2, 3)))
    pd.calculateNextGeneration()
    pd.printGrids()
    */
  }
}