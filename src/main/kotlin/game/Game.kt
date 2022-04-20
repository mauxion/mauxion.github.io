package game


class Game(val dimention: Int = 1, val playerX: Player = Player("X", Icon.X), val playerO: Player = Player("O", Icon.O)) {


    var current: Player = playerX
    val size = 6 * dimention + 2
    var field = ArrayList<ArrayList<Cell>>()

    var actions = ArrayList<Cell>()
    var draftMode = true

    init {
        for (i in 0 until size) {
            val row = ArrayList<Cell>()
            for (j in 0 until size) {
                row.add(Cell(i, j))
            }
            field.add(row)
        }
    }

    fun finishActions(): Boolean {
        return if (actions.size == 3) {
            current = if (current == playerX) {
                playerO
            } else {
                playerX
            }
            actions = ArrayList()
            true
        } else {
            false
        }

    }


    fun move(x: Int, y: Int) {
        if (actions.size < 3) {
            val cell = field[x][y]
            when (cell.state) {
                CellState.NEUTRAL -> {
                    cell.state = CellState.CAPTURED
                    cell.owner = current
                    actions.add(cell)
                }
                CellState.CAPTURED -> {
                    if (cell.owner != current) {
                        cell.owner = current
                        cell.state = CellState.WALL
                        actions.add(cell)
                    }
                }
            }

            if (actions.size == 3 && !draftMode) {
                finishActions()
            }
        }
    }

    fun copy(): Game {

        val copy = Game(dimention)
        copy.field = field
        copy.current = current
        copy.actions = actions
        copy.draftMode = draftMode
        return copy
    }


}