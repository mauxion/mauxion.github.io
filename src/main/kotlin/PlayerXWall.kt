import csstype.*
import game.Cell
import game.Game
import game.Game2
import game.Game4
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.div

external interface PlayerXWallProps : Props {
    var oWallSize: Int
    var chainLink: Cell
    var game: Game
}

val PlayerXWall = FC<PlayerXWallProps> { props ->


    val innerSize = (props.oWallSize * 1.05).vw

    val margin = (props.oWallSize * 0.03).vw

    val game = props.game
    val chainLink = props.chainLink
    val chain = game.getChain(chainLink)
    val connectors = game.connectors(chain)

    val bg = when (game) {
        is Game2 -> if (connectors.isEmpty()) COLOR_X_INACTIVE else COLOR_X
        else -> if (connectors.size == 2) COLOR_X else COLOR_X_INACTIVE
    }

    div {
        css {
            marginTop = margin
            width = innerSize
            height = innerSize
            backgroundColor = bg
            borderRadius = 50.vw
            display = Display.inlineBlock
        }
        if (connectors.size == 1 && game is Game4) {
            +"${connectors.first().text}"
        }
    }
}