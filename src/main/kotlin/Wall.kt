import game.Cell
import game.Game
import game.Game4
import kotlinx.browser.document
import react.FC
import react.Props
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.div

external interface WallProps : Props {
    var iconSize: Int
    var chainLink: Cell
    var game: Game
}

val INACTIVE = "-inactive"

val Wall = FC<WallProps> { props ->
    val imgW = document.body!!.offsetWidth * (props.iconSize) / 100.0

    val game = props.game
    val chainLink = props.chainLink
    val connectors = game.connectors(game.getChain(chainLink))

    var iconName = "Wall-${chainLink.color!!.name}"

    if (connectors.isEmpty()) {
        iconName += INACTIVE
    } else if (game is Game4 && connectors.size == 1) {
        iconName += "-${connectors.first().text}"
    }

    div {
        onContextMenu = {
            console.log("Connectors", connectors.joinToString { "${it.name}" })
        }
        ReactHTML.img {
            width = imgW
            src = "img/${iconName}.gif"
        }
    }
}