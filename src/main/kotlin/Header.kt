import csstype.px
import csstype.rgb
import csstype.vh
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
import react.dom.html.ReactHTML.br
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
    var setGame: (Game)-> Unit
}


val Header = FC<HeaderProps> { props ->

    val game   by useState(props.g)





    div {
        id = "header"
        css {
            height = 15.vh
            padding = 1.vh
        }


        button {
            css {
                height = 13.vh
                fontSize = 5.vh
                padding = 1.vh
                marginLeft = 20.vw
            }
            +"Player ${game.current.name}"
            br{}
                    +"finished"
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