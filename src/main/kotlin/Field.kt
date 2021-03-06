import csstype.*
import emotion.react.css
import game.Cell
import game.GameAbstr
import react.FC
import react.Props
import react.dom.html.ReactHTML.table
import react.dom.html.ReactHTML.tbody
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.tr
import react.useState

external interface FieldProps : Props {
    var g: GameAbstr
    var setGame: (GameAbstr) -> Unit
}


val Field = FC<FieldProps> { props ->

    val game by useState(props.g)

    val cellSize = (100 / (game.field.size * 1.1)).toInt()

    val cellClick: (Cell) -> Unit = { cell ->
        game.handleClick(cell.x, cell.y)
        props.setGame(game)
    }



    table {
        css {

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
                            css {

                                border = Border(1.px, LineStyle.solid)

                            }
                            CellComponent {
                                cell = cellItem
                                size = cellSize
                                g = game
                            }
                            onClick = { cellClick(cellItem) }
                        }
                    }
                }
            }
        }
    }
}