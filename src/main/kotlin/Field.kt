import csstype.*
import game.Cell
import game.Game
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.table
import react.dom.html.ReactHTML.tbody
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.tr
import react.useState

external interface FieldProps : Props {

    var g: Game
    var setGame: (Game) -> Unit
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
                                if (props.g.isDraft(cellItem)) {
                                    backgroundColor = NamedColor.darkgray
                                    border = Border(1.px, LineStyle.dashed)
                                } else {
                                    border = Border(1.px, LineStyle.solid)
                                }
                            }
                            CellComponent {
                                cell = cellItem
                                size = cellSize
                                g = game
                            }
                            onClick = { cellClick(cellItem) }
                            onContextMenu = {
                                val chain = game.getChain(cellItem)
                                val str = chain.map { "${it.x} ${it.y}" }.joinToString()
                                console.log(game.isConnected(chain), str)
                            }
                        }
                    }
                }
            }
        }
    }
}