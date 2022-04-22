package game


data class Cell(val x: Int, val y: Int) {
    var owner: Player? = null
    var color: Color? = null
    var state: CellState = CellState.NEUTRAL

    fun isWall() = state == CellState.WALL
    fun isWall(player: Player) = state == CellState.WALL && owner == player
    fun isWall(color: Color) = state == CellState.WALL && this.color == color

    fun isNeutral() = state == CellState.NEUTRAL
    fun isCaptured() = state == CellState.CAPTURED

    fun isCaptured(color: Color) = state == CellState.CAPTURED && this.color == color
    fun isCaptured(player: Player) = state == CellState.CAPTURED && owner == player

    fun neutralize() {
        state = CellState.NEUTRAL
        color = null
        owner = null
    }
}