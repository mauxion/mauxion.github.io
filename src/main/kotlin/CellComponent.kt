import csstype.*
import game.Cell
import game.CellState
import game.Game
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.div
import react.useState

external interface CellProps : Props {
    var cell: Cell
    var g: Game
    var size: Int
}

val CellComponent = FC<CellProps> { props ->

    var cell by useState(props.cell)
    var size = props.size

    val handleContext: (Cell) -> Unit = { cell ->
        val chain = props.g.getChain(cell)

        val con = props.g.isConnected(chain)
        val connectors = props.g.connectors(chain).joinToString()
        console.log(con, chain.joinToString { "${it.x} ${it.y}" }, connectors)
    }

    div {
        css {
            if (props.g.isDraft(cell)) {
                filter = contrast(50.pct)
                outline = Outline(1.px, LineStyle.dashed)
            }
            height = size.vw
            width = size.vw
        }
        onContextMenu = { handleContext(cell) }


        when (cell.state) {
            CellState.NEUTRAL -> +""
            CellState.CAPTURED -> {
                IconComp {
                    iconSize = size
                    iconName = cell.owner!!.icon.name
                }
            }
            CellState.WALL -> {
                Wall {
                    iconSize = size
                    game = props.g
                    chainLink = cell
                }
            }
        }
    }
}