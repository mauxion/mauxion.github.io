package game


class Game(
    val dimention: Int = 1,
    val playerX: Player = Player("X", Icon.X),
    val playerO: Player = Player("O", Icon.O)
) {


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



    private fun fill(x: Int, y: Int, collector: MutableSet<Cell>) {
        val c = field[x][y]
        val owner = collector.first()!!.owner!!
        if (c.isWall(owner) && !collector.contains(c)) {
            fillChain(c, collector)
        }
    }

    private fun fillChain(cell: Cell, collector: MutableSet<Cell>) {
        val x = cell.x
        val y = cell.y
        val size = field.size
        collector.add(cell)

        //north
        if (x - 1 >= 0) fill(x - 1, y, collector)
        //north-west
        if (x - 1 >= 0 && y - 1 >= 0) fill(x - 1, y - 1, collector)
        //west
        if (y - 1 >= 0) fill(x, y - 1, collector)
        //south-west
        if (x + 1 < size && y - 1 >= 0) fill(x + 1, y - 1, collector)
        //south
        if (x + 1 < size) fill(x + 1, y, collector)
        //south-east
        if (x + 1 < size && y + 1 < size) fill(x + 1, y + 1, collector)
        //east
        if (y + 1 < size) fill(x, y + 1, collector)
        //north-east
        if (x - 1 >= 0 && y + 1 < size) fill(x - 1, y + 1, collector)
    }


    private fun isConnection(x: Int, y: Int, owner: Player): Boolean {
        val cell = field[x][y]
        return cell.isCaptured(owner)
    }

    private fun isCellConnected(cell: Cell): Boolean {

        val x = cell.x
        val y = cell.y
        val size = field.size
        val owner = cell.owner!!

        //north
        if (x - 1 >= 0 && isConnection(x - 1, y, owner)) return true
        //north-west
        if (x - 1 >= 0 && y - 1 >= 0 && isConnection(x - 1, y - 1, owner)) return true
        //west
        if (y - 1 >= 0 && isConnection(x, y - 1, owner)) return true
        //south-west
        if (x + 1 < size && y - 1 >= 0 && isConnection(x + 1, y - 1, owner)) return true
        //south
        if (x + 1 < size && isConnection(x + 1, y, owner)) return true
        //south-east
        if (x + 1 < size && y + 1 < size && isConnection(x + 1, y + 1, owner)) return true
        //east
        if (y + 1 < size && isConnection(x, y + 1, owner)) return true
        //north-east
        if (x - 1 >= 0 && y + 1 < size && isConnection(x - 1, y + 1, owner)) return true

        return false
    }

      fun isConnected(chain: Set<Cell>): Boolean {

        for (cell in chain) {
            if (isCellConnected(cell)) {
                return true
            }
        }

        return false
    }

    fun getChain(cell: Cell): Set<Cell> {
        return if (cell.isWall()) {
            val chain = mutableSetOf<Cell>()
            fillChain(cell, chain)
            chain
        } else {
            emptySet()
        }
    }

    fun isConnected(cell: Cell): Boolean {
        return if (cell.isWall()) {
            val chain = getChain(cell)
            return isConnected(chain)
        } else {
            false
        }

    }

}