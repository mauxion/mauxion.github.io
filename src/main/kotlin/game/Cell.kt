package game

data class Cell(val x: Int, val y: Int) {
    var owner: Player? = null
    var state: CellState = CellState.NEUTRAL

    fun isWall() = state == CellState.WALL
    fun isWall(player: Player) = state == CellState.WALL && owner == player
    fun isNeutral() = state == CellState.NEUTRAL
    fun isCaptured() = state == CellState.CAPTURED
    fun isCaptured(player: Player) = state == CellState.CAPTURED && owner == player

}