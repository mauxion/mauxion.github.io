import csstype.vw
import game.Cell
import game.CellState
import game.Game
import game.Icon
import org.w3c.dom.HTMLDivElement
import react.FC
import react.Props
import react.css.css
import react.dom.events.MouseEventHandler
import react.dom.html.ReactHTML.div
import react.useState

external interface CellProps : Props {
    var cell: Cell

    var g: Game
    var size: Int
}

val CellComponent = FC<CellProps> { props ->

    var cell by useState(props.cell)

    val size = props.size
    val innerSize = (props.size * 0.95).toInt()

    val connected = props.g.isConnected(cell)

//
//    val handleContext:(MouseEventHandler<HTMLDivElement>) -> Unit = {
//
//}

    val handleContext: (Cell) -> Unit = { cell ->
        val chain = props.g.getChain(cell)

        val con = props.g.isConnected(chain)
        val connectors = props.g.connectors(chain).joinToString()
        console.log(con, chain.joinToString { "${it.x} ${it.y}" }, connectors)
    }

    div {
        css {
            height = size.vw
            width = size.vw
        }
//        +"${icon}"
        onContextMenu = {handleContext(cell)}

        val icon = cell.owner?.icon

        when (cell.state) {
            CellState.NEUTRAL -> +""
            CellState.CAPTURED -> {
                when (icon) {
                    Icon.X -> PlayerX {
                        xSize = (innerSize * 1.4).toInt()
                    }
                    Icon.O -> PlayerO {
                        oSize = innerSize
                    }
                    Icon.PLUS -> PlayerPlus {
                        plusSize = (innerSize * 1.4).toInt()
                    }
                    else -> PlayerDot {
                        dotSize = (innerSize * 0.4).toInt()
                    }
                }
            }
            CellState.WALL -> {


                if (icon == Icon.X || icon == Icon.PLUS) {
                    PlayerXWall {
                        oWallSize = innerSize
                        game = props.g
                        chainLink = cell
                    }
                } else if (icon == Icon.O || icon == Icon.DOT) {
                    PlayerOWall {
                        xWallSize = innerSize
                        game = props.g
                        chainLink = cell
                    }
                }
            }
        }

    }
//        css {
//            padding = 5.px
//            backgroundColor = rgb(8, 97, 22)
//            color = rgb(56, 246, 137)
//        }

}