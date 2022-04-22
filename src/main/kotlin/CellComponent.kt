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

    div {
        css {
            if (props.g.isDraft(cell)) {
                filter = contrast(50.pct)
                outline = Outline(1.px, LineStyle.dashed)
            }
            height = size.vw
            width = size.vw
        }

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