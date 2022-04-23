import csstype.dropShadow
import csstype.vw
import emotion.react.css
import game.Game2
import game.GameAbstr
import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.span
import react.useState

external interface GameProps : Props {
    var game: GameAbstr
    var restart: () -> Unit
}

val GameComponent = FC<GameProps> { props ->

    var game by useState(props.game)

    val updateFunction: (GameAbstr) -> Unit = { newGame ->
        game = newGame.copy()
    }

    if (game.isLost()) {
        val next = game.nextPlayer()
        if (game is Game2) {
            +"Player "
            PlayerIcon {
                iconSize = 4
                iconName = next.icon.name
            }
            +" won!"
        } else {
            +"Team "
            span {
                css {
                    color = next.color.namedColor
                }
                +"${next.color.name}"
            }
            +" won!"
        }

        button {
            css {
                fontSize = 4.vw
                marginLeft = 3.vw
                marginBottom = 3.vw
                filter = dropShadow(0.2.vw, 0.4.vw, 0.6.vw)
            }
            onClick = { props.restart() }
            +"New Game"

        }
    } else {
        Header {
            g = game
            setGame = updateFunction
        }
    }

    Field {
        g = game
        setGame = updateFunction
    }
}