import csstype.*
import game.Cell
import game.CellState
import game.Game
import game.User
import kotlinx.browser.document
import kotlinx.browser.window
import react.FC
import react.Props
import react.css.css
import react.dom.html.InputType
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.table
import react.dom.html.ReactHTML.tbody
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.th
import react.dom.html.ReactHTML.thead
import react.dom.html.ReactHTML.tr
import react.useState

external interface FieldProps : Props {

    var cellSize: Int
    var g: Game
    var setGame: (Game) -> Unit
}


val Field = FC<FieldProps> { props ->

    val game by useState(props.g)

    val cellSize = props.cellSize

    val cellClick: (Cell) -> Unit = { cell ->
        game.move(cell.x, cell.y)
        props.setGame(game)
    }



    table {
        css{

            borderCollapse = BorderCollapse.collapse
            border = Border(1.px, LineStyle.solid)
        }
        tbody {
            for (row in game.field) {
                tr {

                    css {
                        textAlign = TextAlign.center
                        verticalAlign = VerticalAlign.middle
                    }
                    for (cellItem in row) {
                        td {
                            css{
                                border = Border(1.px, LineStyle.solid)
                            }
                            CellComponent {
                                cell = cellItem
                                size = cellSize
                            }
                            onClick = { cellClick(cellItem) }
                        }
                    }
                }
            }
        }
    }
}