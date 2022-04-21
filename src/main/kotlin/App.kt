import csstype.*
import game.Game
import game.Game2
import game.Game4
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

    var mayBeGame by useState<Game>()

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
                        margin=3.vw
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
                        margin=3.vw
                    }
                    onClick = { mayBeGame = Game4() }
                    +"2 vs 2"
                }
            }
        }


    } else {
        div {

            id = "header"
            css {
                fontSize = 5.vh


            }
            if (mayBeGame is Game2) {
                Game2Component {
                    game = mayBeGame as Game2
                }
            } else {
                Game4Component {
                    game = mayBeGame as Game4
                }
            }
        }
    }


    div {
        +"v2.00"
    }
}