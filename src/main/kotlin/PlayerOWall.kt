import csstype.Display
import csstype.px
import csstype.vw
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.div

external interface PlayerOWallProps : Props {
    var xWallSize: Int
    var isConnected: Boolean

}

val PlayerOWall = FC<PlayerOWallProps> { props ->


    val innerSize = props.xWallSize.vw

    val margin = (props.xWallSize * 0.05).vw

    div {

        css {
            marginTop = margin
            width = innerSize
            height = innerSize
            backgroundColor = if (props.isConnected) COLOR_O else COLOR_O_INACTIVE
            borderRadius = 2.px
            display = Display.inlineBlock

        }
    }

}