import csstype.*
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.div

external interface PlayerXWallProps : Props {
    var oWallSize: Int
    var isConnected: Boolean
}

val UserAWall = FC<PlayerXWallProps> { props ->


    val innerSize = (props.oWallSize* 1.1).vw

    val margin = (props.oWallSize * 0.05).vw

    val color = if (props.isConnected) {
        NamedColor.darkblue
    } else {
        NamedColor.lightsteelblue
    }

    div {
        css {
            marginTop = margin
            width = innerSize
            height = innerSize
            backgroundColor = color
            borderRadius = 50.vw
            display = Display.inlineBlock
        }
    }
}