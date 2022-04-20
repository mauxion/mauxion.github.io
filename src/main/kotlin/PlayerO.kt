import csstype.*
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.div

external interface PlayerOProps : Props {
    var oSize: Int
}

val UserO = FC<PlayerOProps> { props ->

    val innerSize = (props.oSize*0.85).vh //


    val margin = (props.oSize * 0.05).vh

    div {
        css {
            marginTop = margin
            width = innerSize
            height = innerSize
            borderRadius = props.oSize.vh
            display = Display.inlineBlock
            border = Border(1.vh, LineStyle.solid, NamedColor.darkred)
        }
    }


}