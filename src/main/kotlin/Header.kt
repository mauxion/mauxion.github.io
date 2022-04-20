import csstype.*
import game.Game
import org.w3c.dom.HTMLButtonElement
import react.FC
import react.Props
import react.css.css
import react.dom.events.MouseEventHandler
import react.dom.html.ReactHTML.br
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.useState

external interface HeaderProps : Props {
    var g: Game
    var setGame: (Game) -> Unit
}


val Header = FC<HeaderProps> { props ->

    var game by useState(props.g)
    var draftMode by useState(game.draftMode)

    var switchToModeName = if (draftMode) "strict" else "draft"

    val handleDraftMode: MouseEventHandler<HTMLButtonElement> = {


        val newDraftMode = !draftMode

        game.draftMode = newDraftMode

        if (!newDraftMode && game.actions.size == 3) {
            game.finishActions()
        }

        draftMode = newDraftMode
        props.setGame(game)
    }
    val commonHeight = 15.vh


    div {
        id = "header"
        css {
            fontSize = 4.vh
            textAlign = TextAlign.center
            color = game.current.color
        }

        div {
            css {
                float = Float.left
                width = 30.vw
                height = commonHeight
            }
            +"Player ${game.current.name}"
            br{}
            +"(${3 - game.actions.size} left)"
        }

        div {
            css {
                float = Float.left
                width = 30.vw
                height = commonHeight
            }
            if (game.draftMode) {
                button {
                    css {
                        height = 13.vh
                        fontSize = 5.vh
                        padding = 1.vh
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
        }


        div {
            css {
                float = Float.right
                fontSize=4.vh
                width = 30.vw
            }

            button {
                css {
                    fontSize=  4.vh
                }
                +"switch to"
                br {}
                +"$switchToModeName mode"
                onClick = handleDraftMode
            }
        }
    }
}