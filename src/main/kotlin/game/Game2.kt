package game

import PLAYER_O
import PLAYER_X


class Game2(
    override val dimention: Int = 1,
    override val playerX: Player = PLAYER_X,
    override val playerO: Player = PLAYER_O
) : GameAbstr (){
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


    override fun copy(): Game2 {
        val copy = Game2(dimention, playerX, playerO)
        copy.field = field
        copy.current = current
        copy.actions = actions
        copy.draftMode = draftMode
        return copy
    }
}