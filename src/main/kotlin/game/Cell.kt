package game

import csstype.NamedColor

data class Cell(val x: Int, val y: Int) {
    var owner: Player? = null
    var color: NamedColor? = null
    var state: CellState = CellState.NEUTRAL

    fun isWall() = state == CellState.WALL
    fun isWall(player: Player) = state == CellState.WALL && owner == player
    fun isWall(color: NamedColor) = state == CellState.WALL && this.color == color

    fun isNeutral() = state == CellState.NEUTRAL
    fun isCaptured() = state == CellState.CAPTURED

    fun isCaptured(color: NamedColor) = state == CellState.CAPTURED && this.color == color
    fun isCaptured(player: Player) = state == CellState.CAPTURED && owner == player

    fun neutralize() {
        state = CellState.NEUTRAL
        owner = null
    }
}