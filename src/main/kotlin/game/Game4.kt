package game

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

    override fun finishActions(): Boolean {
        return if (actions.size == 3) {
            current = when (current) {
                playerX -> playerO
                playerO -> playerPlus
                playerPlus -> playerDot
                else -> playerX
            }
            actions = ArrayList()
            true
        } else {
            false
        }
    }

    override fun isFirstMoveValid(x: Int, y: Int): Boolean {
        val last = size - 1
        val valid = when (current) {
            playerX -> {
                (x == 0 || x == last) && (y == 0 || y == last)
            }
            else -> {
                if (field[x][y].isNeutral()) {
                    (x == 0 && y == 0)
                            || (x == last && y == 0)
                            || (x == 0 && y == last)
                            || (x == last && y == last)
                } else {
                    false
                }
            }
        }
        return valid
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