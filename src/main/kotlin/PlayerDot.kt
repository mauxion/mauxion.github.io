import csstype.*
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.div

external interface PlayerDotProps : Props {
    var dotSize: Int
}

val PlayerDot = FC<PlayerDotProps> { props ->

    val innerSize = props.dotSize.vw

    val margin = (props.dotSize * 0.05).vw

    div {

        css {
            marginTop = margin
            width = innerSize
            height = innerSize
            backgroundColor =   COLOR_O
            borderRadius = 50.vw
            display = Display.inlineBlock

        }
    }

}