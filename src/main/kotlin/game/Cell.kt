package game

class Cell(val x: Int, val y: Int) {
    var owner: User? = null
      var state: CellState = CellState.NEUTRAL

}