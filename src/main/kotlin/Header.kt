import csstype.*
import game.Game4
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
    var hintMode by useState(game.hintMode)
    var switchToModeName = if (draftMode) "strict" else "draft"
    val turnHintMode = if (hintMode) "off" else "on"

    val handleDraftMode: MouseEventHandler<HTMLButtonElement> = {
        val newDraftMode = !draftMode
        game.draftMode = newDraftMode
        if (!newDraftMode && game.actions.size == 3) {
            game.finishActions()
        }
        draftMode = newDraftMode
        props.setGame(game)
    }

    val handleHintMode: MouseEventHandler<HTMLButtonElement> = {
        val newHintMode = !hintMode
        game.hintMode = newHintMode
        hintMode = newHintMode
        props.setGame(game)
    }

    val headerFontSize = 3.vw
    val playerIconSize = 4
    val headerElemWidth = 23.vw

    div {
        id = "header"
        css {
            textAlign = TextAlign.center
            color = game.current.color.namedColor
            display = Display.inlineBlock
        }

        div {
            css {
                float = Float.left
                minWidth = headerElemWidth
                fontSize = headerFontSize
            }
            PlayerIcon {
                iconSize = playerIconSize
                iconName = game.current.icon.name
            }
            if (game is Game4) {
                +" → "
                PlayerIcon {
                    iconSize = playerIconSize
                    iconName = game.nextPlayer().icon.name
                }
            }
            br {}
            +"(${3 - game.actions.size} left)"
        }

        div {
            css {
                float = Float.left
                minWidth = headerElemWidth
                minHeight = 1.vh
            }
            if (game.draftMode) {
                button {
                    css {
                        fontSize = headerFontSize
                        padding = 1.vh
                        if (game.actions.size == 3) {
                            filter = dropShadow(0.2.vw, 0.4.vw, 0.6.vw)
                        }
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
                float = Float.left
                minWidth = headerElemWidth
            }
            button {
                css {
                    fontSize = headerFontSize
                    display = Display.block
                    filter = dropShadow(0.2.vw, 0.4.vw, 0.6.vw)
                }
                +"turn $turnHintMode"
                br {}
                +"hints"
                onClick = handleHintMode
            }
        }

        div {
            css {
                float = Float.right
                minWidth = headerElemWidth
            }
            button {
                css {
                    fontSize = headerFontSize
                    display = Display.block
                    filter = dropShadow(0.2.vw, 0.4.vw, 0.6.vw)
                }
                +"switch to"
                br {}
                +"$switchToModeName mode"
                onClick = handleDraftMode
            }
        }
    }
}