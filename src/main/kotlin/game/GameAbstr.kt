package game

import csstype.NamedColor


open abstract class GameAbstr(


) : Game {
    abstract val playerO: Player
    abstract val playerX: Player
    abstract val dimention: Int
    abstract var current:  Player
    abstract val size: Int
    abstract var field: ArrayList<ArrayList<Cell>>

    abstract var actions: ArrayList<Cell>
    abstract var draftMode:Boolean






    override fun finishActions(): Boolean {
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

    override fun isFirstMove(): Boolean {
        return null == field.flatten().find { it.owner == current || it.isWall() }
    }

    override fun isFirstMoveValid(x: Int, y: Int): Boolean {
        val last = size - 1
        return if (current == playerX) {
            (x == 0 || x == last) && (y == 0 || y == last)
        } else
            if (field[0][0].isCaptured()) {
                x == last && y == last
            } else if (field[0][last].isCaptured()) {
                x == last && y == 0
            } else if (field[last][0].isCaptured()) {
                x == 0 && y == last
            } else {
                x == 0 && y == 0
            }
    }


    override fun isFirstMoveNotValid(x: Int, y: Int): Boolean {
        return !isFirstMoveValid(x, y)
    }

    override fun checkNeighboring(x: Int, y: Int): Boolean {
        val cell = field[x][y]
        if (cell.isCaptured(current)) {
            return true
        }
        if (cell.isWall(current)) {
            return isConnected(cell)
        }
        return false
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

    override fun revertLast(last: Cell) {
        if (last.isCaptured()) {
            last.neutralize()
        } else if (last.isWall()) {
            if (last.owner == playerX) {
                last.owner = playerO
            } else {
                last.owner = playerX
            }
            last.state = CellState.CAPTURED
        }
        field[last.x][last.y] = last
        actions.removeLast()
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
        val owner = collector.first()!!.owner!!
        if (c.isWall(owner) && !collector.contains(c)) {
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


}