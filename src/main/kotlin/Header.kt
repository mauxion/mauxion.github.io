import csstype.px
import csstype.rgb
import csstype.vw
import game.Cell
import game.CellState
import game.Game
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

external interface HeaderProps : Props {
    var g: Game
    var height: Int
    var width: Int
    var setGame: (Game)-> Unit
}


val Header = FC<HeaderProps> { props ->

    val game   by useState(props.g)





    div {
        id = "header"
        css {
            padding = 5.px

        }
        button {
            +"New game"
            css {  marginRight = 1.vw}
            onClick = {
                props.setGame(Game())
            }
        }

        button {
            +"Player ${game.current.name} finished"
            onClick = {
                if (game.current == game.userA) {
                    game.current = game.userB
                } else {
                    game.current = game.userA
                }
                props.setGame(game)
            }
        }
    }


}