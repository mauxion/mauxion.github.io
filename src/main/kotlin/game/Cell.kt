package game

class Cell(val x: Int, val y: Int) {
    var owner: Player? = null
      var state: CellState = CellState.NEUTRAL

}