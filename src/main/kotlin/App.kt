import csstype.*
import game.Game2
import game.Game4
import game.GameAbstr
import org.w3c.dom.HTMLButtonElement
import react.FC
import react.Props
import react.css.css
import react.dom.events.MouseEvent
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.useState

external interface AppProps : Props {

}


val App = FC<AppProps> { props ->

    var mayBeGame by useState<GameAbstr>()

    val set2Game: (MouseEvent<HTMLButtonElement, *>) -> Unit = {
        mayBeGame = Game2()
    }
    if (mayBeGame == null) {

        div {
            css {
                textAlign = TextAlign.center
                display = Display.inlineBlock
            }
            div {
                css {
                    float = Float.left

                }
                button {
                    css {
                        width = 40.vw
                        fontSize = 12.vw
                        margin = 3.vw
                        filter = dropShadow(0.2.vw, 0.4.vw, 0.6.vw)
                    }
                    onClick = set2Game
                    +"1 vs 1"
                }
            }
            div {
                css {
                    float = Float.right
                }
                button {
                    css {
                        width = 40.vw
                        fontSize = 12.vw
                        margin = 3.vw
                        filter = dropShadow(0.2.vw, 0.4.vw, 0.6.vw)
                    }
                    onClick = { mayBeGame = Game4() }
                    +"2 vs 2"
                }
            }
        }
    } else {
        div {
            css {
                fontSize = 5.vh
            }
            GameComponent {
                game = mayBeGame!!
            }
        }
    }
    div {
        +"v3.00"
    }
}