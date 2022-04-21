import csstype.*
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.div

external interface PlayerXWallProps : Props {
    var oWallSize: Int
    var isConnected: Boolean
}

val PlayerXWall = FC<PlayerXWallProps> { props ->


    val innerSize = (props.oWallSize * 1.05).vw

    val margin = (props.oWallSize * 0.03).vw


    div {
        css {
            marginTop = margin
            width = innerSize
            height = innerSize
            backgroundColor = if (props.isConnected) COLOR_X else COLOR_X_INACTIVE
            borderRadius = 50.vw
            display = Display.inlineBlock
        }
    }
}