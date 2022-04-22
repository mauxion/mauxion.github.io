import game.GameAbstr
import react.FC
import react.Props
import react.useState

external interface GameProps : Props {
    var game: GameAbstr
}

val GameComponent = FC<GameProps> { props ->

    var game by useState(props.game)

    val updateFunction: (GameAbstr) -> Unit = { newGame ->
        game = newGame.copy()
    }

    Header {
        g = game
        setGame = updateFunction
    }

    Field {
        g = game
        setGame = updateFunction
    }
}