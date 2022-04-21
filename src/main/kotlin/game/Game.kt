package game


interface Game {


    fun finishActions(): Boolean

    fun isFirstMove(): Boolean

    fun isFirstMoveValid(x: Int, y: Int): Boolean

    fun isFirstMoveNotValid(x: Int, y: Int): Boolean

    fun checkNeighboring(x: Int, y: Int): Boolean
    fun isMoveValid(x: Int, y: Int): Boolean

    fun isDraft(cell: Cell): Boolean

    fun move(x: Int, y: Int)

    fun revertLast(last: Cell)

    fun handleClick(x: Int, y: Int)


    fun fill(x: Int, y: Int, collector: MutableSet<Cell>)
    fun fillChain(cell: Cell, collector: MutableSet<Cell>)


    fun isConnection(x: Int, y: Int, owner: Player): Boolean
    fun isCellConnected(cell: Cell): Boolean
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

    fun isConnected(wallPart: Cell): Boolean {
        return if (wallPart.isWall()) {
            val chain = getChain(wallPart)
            return isConnected(chain)
        } else {
            false
        }
    }

    fun copy(): Game
}