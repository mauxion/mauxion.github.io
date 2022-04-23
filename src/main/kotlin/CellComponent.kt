import csstype.brightness
import csstype.dropShadow
import csstype.vw
import emotion.react.css
import game.Cell
import game.CellState
import game.GameAbstr
import react.FC
import react.Props
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
                if (game.draftMode) {
                    backdropFilter = brightness(1.2)
                }
            } else {
                filter = dropShadow(0.2.vw, 0.4.vw, 0.6.vw)
            }
            if (isValid) {
                backdropFilter = brightness(1.2)
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