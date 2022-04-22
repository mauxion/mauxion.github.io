import csstype.Float
import csstype.TextAlign
import csstype.vh
import csstype.vw
import game.GameAbstr
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
    var g: GameAbstr
    var setGame: (GameAbstr) -> Unit
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

    div {
        id = "header"
        css {
            fontSize = 4.vh
            textAlign = TextAlign.center
            color = game.current.color.namedColor
        }

        div {
            css {
                float = Float.left
                width = 30.vw
            }
            +"Player ${game.current.name}"
            br {}
            +"(${3 - game.actions.size} left)"
        }

        div {
            css {
                float = Float.left
                width = 30.vw
            }
            if (game.draftMode) {
                button {
                    css {
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
                fontSize = 4.vh
                width = 30.vw
            }
            button {
                css {
                    fontSize = 4.vh
                }
                +"switch to"
                br {}
                +"$switchToModeName mode"
                onClick = handleDraftMode
            }
        }
    }
}