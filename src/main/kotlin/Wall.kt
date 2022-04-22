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
    var g: Game
}

val INACTIVE = "-inactive"

val Wall = FC<WallProps> { props ->
    val imgW = document.body!!.offsetWidth * (props.iconSize) / 100.0

    val game = props.g
    val chainLink = props.chainLink

    val chain = game.getChain(chainLink)

    val connectors = game.connectors(chain)

    var iconName = "Wall-${chainLink.color!!.name}"

    if (connectors.isEmpty()) {
        iconName += INACTIVE
    } else if (game is Game4 && connectors.size == 1) {
        iconName += "-${connectors.first().text}"
    }

    div {
        ReactHTML.img {
            width = imgW
            src = "img/${iconName}.png"
        }
    }
}