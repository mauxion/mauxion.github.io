import csstype.*
import game.*
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.div

external interface PlayerOWallProps : Props {
    var xWallSize: Int
    var chainLink: Cell
    var game: Game
}

val PlayerOWall = FC<PlayerOWallProps> { props ->


    val innerSize = props.xWallSize.vw

    val margin = (props.xWallSize * 0.05).vw
    val game = props.game
    val chainLink = props.chainLink
    val chain = game.getChain(chainLink)
    val connectors = game.connectors(chain)

    val bg = when (game) {
        is Game2 -> if (connectors.isEmpty()) COLOR_O_INACTIVE else COLOR_O
        else -> if (connectors.size == 2) COLOR_O else COLOR_O_INACTIVE
    }

    div {

        css {
            marginTop = margin
            width = innerSize
            height = innerSize
            backgroundColor = bg
            borderRadius = 2.px
            display = Display.inlineBlock
        }
        if (connectors.size == 1 && game is Game4) {
            when(connectors.first()){
                Icon.O -> {
                    div {
                        css {
                            marginTop = (props.xWallSize * 0.1).vw
                            width = (props.xWallSize/2).vw
                            height = (props.xWallSize/2).vw
                            borderRadius = 55.vw
                            display = Display.inlineBlock
                            border = Border(1.vw, LineStyle.solid, COLOR_O)
                        }
                    }
                }
                else ->{
                    div {

                        css {
                            marginTop = margin
                            width =  (props.xWallSize/4).vw
                            height =  (props.xWallSize/4).vw
                            backgroundColor =   COLOR_O
                            borderRadius = 50.vw
                            display = Display.inlineBlock

                        }
                    }

                }
            }

        }
    }

}