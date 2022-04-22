import csstype.*
import game.Cell
import game.CellState
import game.GameAbstr
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.div
import react.useState

external interface CellProps : Props {
    var cell: Cell
    var g: GameAbstr
    var size: Int
}

val CellComponent = FC<CellProps> { props ->

    var cell by useState(props.cell)
    val game = props.g
    var isValid = game.hintMode && game.isNextMoveValid(cell)

    var size = props.size

    div {
        css {
            if (game.isDraft(cell)) {
                filter = contrast(50.pct)
                outline = Outline(1.px, LineStyle.dashed)
            }
            if (isValid) {
                backgroundColor = NamedColor.darkgray
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
                    g = game
                    chainLink = cell
                }
            }
        }
    }
}