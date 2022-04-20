package game


class Game(val dimention: Int = 1, val userA: User = User("A", Icon.X), val userB: User= User("B", Icon.O)) {


    var current: User = userA
    val size = 6 * dimention +2
    var field =  ArrayList<ArrayList<Cell>>()
    init {
        for (i in 0 until size) {
            val row = ArrayList<Cell>()
            for (j in 0 until size) {
                row.add(Cell(i, j))
            }
            field.add(row)
        }
    }

    val move: (Int, Int) -> Unit = { x, y ->
        val cell = field[x][y]
        when (cell.state) {
            CellState.NEUTRAL -> {
                cell.state = CellState.CAPTURED
                cell.owner = current

            }
            CellState.CAPTURED -> {
                if (cell.owner != current) {
                    cell.owner = current
                    cell.state = CellState.WALL
                }
            }
        }
    }

    fun copy(): Game{

        val copy = Game(dimention)
        copy.field = field
        copy.current = current
        return copy
    }


}