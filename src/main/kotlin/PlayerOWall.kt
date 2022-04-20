import csstype.Display
import csstype.NamedColor
import csstype.px
import csstype.vh
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.div

external interface PlayerOWallProps : Props {
    var xWallSize: Int
    var isConnected: Boolean

}

val PlayerOWall = FC<PlayerOWallProps> { props ->


    val innerSize = props.xWallSize.vh

    val color = if (props.isConnected) {
        NamedColor.darkred
    } else {
        NamedColor.rosybrown
    }

    val margin = (props.xWallSize * 0.1).vh

    div {

        css {
            marginTop = margin
            width = innerSize
            height = innerSize
            backgroundColor = color
            borderRadius = 2.px
            display = Display.inlineBlock

        }
    }

}