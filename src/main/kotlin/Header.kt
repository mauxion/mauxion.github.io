import csstype.*
import game.Cell
import game.CellState
import game.Game
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLSpanElement
import react.FC
import react.Props
import react.css.css
import react.dom.events.ChangeEventHandler
import react.dom.events.MouseEventHandler
import react.dom.html.InputType
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.br
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.span
import react.dom.html.ReactHTML.table
import react.dom.html.ReactHTML.tbody
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.th
import react.dom.html.ReactHTML.thead
import react.dom.html.ReactHTML.tr
import react.useState

external interface HeaderProps : Props {
    var g: Game
    var setGame: (Game) -> Unit
}


val Header = FC<HeaderProps> { props ->

    var game by useState(props.g)

    val handleDraftMode: MouseEventHandler<HTMLSpanElement> = {

        val draftMode = !game.draftMode

        game.draftMode = draftMode

        if (!draftMode && game.actions.size == 3) {
            game.finishActions()
        }
        props.setGame(game)
        game = game.copy()
    }




    div {
        id = "header"
        css {
            height = 15.vh
            padding = 1.vh
            fontSize = 4.vh
        }

        +"Player ${game.current.name} (${3-game.actions.size} left)"


        if (game.draftMode) {
            button {
                css {
                    height = 13.vh
                    fontSize = 5.vh
                    padding = 1.vh
                    marginLeft = 10.vw
                }
                disabled = game.actions.size < 3

                +"done"
                onClick = {
                    if (game.finishActions()) {
                        props.setGame(game)
                    }

                }
            }
        }

        ReactHTML.span{
            +"Draft Mode"
            onClick = handleDraftMode
            css {
                marginLeft = 5.vw
                border = Border(1.px, LineStyle.solid)
            }
            input {

                name = "draftMode"
                type = InputType.checkbox
//                value = draftMode.toString()
                defaultValue = game.draftMode.toString()
                defaultChecked = game.draftMode

            }
        }
    }
}