package game


open abstract class GameAbstr() : Game {
    abstract val playerO: Player
    abstract val playerX: Player
    abstract val dimention: Int
    abstract var current: Player
    abstract val size: Int
    abstract var field: ArrayList<ArrayList<Cell>>
    abstract var actions: ArrayList<Cell>
    abstract var draftMode: Boolean
    abstract var hintMode: Boolean

    override fun isFirstMove(): Boolean {
        val resp = null == field.flatten().find { it.owner == current || it.isWall() }
        return resp
    }


    override fun isFirstMoveNotValid(x: Int, y: Int): Boolean {
        return !isFirstMoveValid(x, y)
    }

    override fun checkNeighboring(x: Int, y: Int): Boolean {
        val cell = field[x][y]
        if (cell.isCaptured(current)) {
            return true
        }
        if (cell.isWall(current.color)) {
            val chain = getChain(cell)
            return connectors(chain).contains(current.icon)
        }
        return false
    }


    override fun isNextMoveValid(cell: Cell): Boolean {
        if (actions.size == 3) return false
        if (isFirstMove()) {
            return isFirstMoveValid(cell.x, cell.y)
        }
        if (cell.color == current.color) return false
        if (cell.isWall()) return false

        return isMoveValid(cell.x, cell.y)
    }

    override fun isMoveValid(x: Int, y: Int): Boolean {

        //north
        if (x - 1 >= 0 && checkNeighboring(x - 1, y)) return true
        //north-west
        if (x - 1 >= 0 && y - 1 >= 0 && checkNeighboring(x - 1, y - 1)) return true
        //west
        if (y - 1 >= 0 && checkNeighboring(x, y - 1)) return true
        //south-west
        if (x + 1 < size && y - 1 >= 0 && checkNeighboring(x + 1, y - 1)) return true
        //south
        if (x + 1 < size && checkNeighboring(x + 1, y)) return true
        //south-east
        if (x + 1 < size && y + 1 < size && checkNeighboring(x + 1, y + 1)) return true
        //east
        if (y + 1 < size && checkNeighboring(x, y + 1)) return true
        //north-east
        if (x - 1 >= 0 && y + 1 < size && checkNeighboring(x - 1, y + 1)) return true
        return isFirstMove()
    }

    override fun isDraft(cell: Cell): Boolean {
        return actions.find { it.x == cell.x && it.y == cell.y } != null
    }

    override fun move(x: Int, y: Int) {
        if (actions.size == 3) {
            return
        }
        if (isFirstMove() && isFirstMoveNotValid(x, y)) {
            return
        }
        if (!isMoveValid(x, y)) {
            return
        }
        val cell = field[x][y]
        when (cell.state) {
            CellState.NEUTRAL -> {
                cell.state = CellState.CAPTURED
                cell.owner = current
                cell.color = current.color
                actions.add(cell)
            }
            CellState.CAPTURED -> {
                if (cell.color != current.color) {
                    cell.state = CellState.WALL
                    cell.color = current.color
                    actions.add(cell)
                }
            }
        }
        if (actions.size == 3 && !draftMode) {
            finishActions()
        }
    }


    override fun handleClick(x: Int, y: Int) {
        val last = actions.lastOrNull()
        if (draftMode && last?.x == x && last.y == y) {
            revertLast(last)
        } else {
            move(x, y)
        }
    }


    override fun fill(x: Int, y: Int, collector: MutableSet<Cell>) {
        val c = field[x][y]
        val color = collector.first()!!.color!!

        if (c.isWall(color) && !collector.contains(c)) {
            fillChain(c, collector)
        }
    }

    override fun fillChain(cell: Cell, collector: MutableSet<Cell>) {
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


    override fun isConnection(x: Int, y: Int, owner: Player): Boolean {
        val cell = field[x][y]
        return cell.isCaptured(owner)
    }


    protected fun fill(x: Int, y: Int, color: Color, collector: MutableSet<Icon>) {
        val c = field[x][y]
        if (c.owner != null) {
            val icon = c.owner!!.icon
            if (c.isCaptured(color) && !collector.contains(icon)) {
                collector.add(icon)
            }
        }
    }

    override fun revertLast(last: Cell) {
        if (last.isCaptured()) {
            last.neutralize()
        } else if (last.isWall()) {
            if (last.color == playerX.color) {
                last.color = playerO.color
            } else {
                last.color = playerX.color
            }
            last.state = CellState.CAPTURED
        }
        field[last.x][last.y] = last
        actions.removeLast()
    }

    protected fun addConnectors(chainLink: Cell, collector: MutableSet<Icon>) {
        val x = chainLink.x
        val y = chainLink.y
        val size = field.size
        val color = chainLink.color!!

        //north
        if (x - 1 >= 0) fill(x - 1, y, color, collector)
        //north-west
        if (x - 1 >= 0 && y - 1 >= 0) fill(x - 1, y - 1, color, collector)
        //west
        if (y - 1 >= 0) fill(x, y - 1, color, collector)
        //south-west
        if (x + 1 < size && y - 1 >= 0) fill(x + 1, y - 1, color, collector)
        //south
        if (x + 1 < size) fill(x + 1, y, color, collector)
        //south-east
        if (x + 1 < size && y + 1 < size) fill(x + 1, y + 1, color, collector)
        //east
        if (y + 1 < size) fill(x, y + 1, color, collector)
        //north-east
        if (x - 1 >= 0 && y + 1 < size) fill(x - 1, y + 1, color, collector)
    }

    override fun connectors(chain: Set<Cell>): Set<Icon> {
        val set = mutableSetOf<Icon>()
        chain.forEach { cell ->
            if (set.size == 0 || (this is Game4 && set.size == 1)) {
                addConnectors(cell, set)
            }
        }
        return set
    }

    override fun isCellConnected(cell: Cell): Boolean {
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

    override fun finishActions(): Boolean {
        return if (actions.size == 3) {
            current = nextPlayer()
            actions = ArrayList()
            true
        } else {
            false
        }
    }

    override fun isLost(): Boolean {
        if (actions.size == 3) return false
        return field.flatten().none { isNextMoveValid(it) }
    }

    abstract fun copy(): GameAbstr
}