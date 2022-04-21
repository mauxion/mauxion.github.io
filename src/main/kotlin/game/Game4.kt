package game

import PLAYER_DOT
import PLAYER_O
import PLAYER_PLUS
import PLAYER_X


class Game4(
    override val dimention: Int = 1,
    override val playerX: Player = PLAYER_X,
    val playerPlus: Player = PLAYER_PLUS,
    override val playerO: Player = PLAYER_O,
    val playerDot: Player = PLAYER_DOT,
) : GameAbstr() {

    override var current: Player = playerX


    override val size = 6 * dimention + 2
    override var field = ArrayList<ArrayList<Cell>>()

    override var actions = ArrayList<Cell>()
    override var draftMode = false

    init {
        for (i in 0 until size) {
            val row = ArrayList<Cell>()
            for (j in 0 until size) {
                row.add(Cell(i, j))
            }
            field.add(row)
        }
    }


    override fun copy(): Game4 {
        val copy = Game4(dimention, playerX, playerPlus, playerO, playerDot)
        copy.field = field
        copy.current = current
        copy.actions = actions
        copy.draftMode = draftMode
        return copy
    }
}