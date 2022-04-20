import csstype.*
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.div

external interface PlayerOProps : Props {
    var oSize: Int

}

val UserO = FC<PlayerOProps> { props ->

    val innerSize = (props.oSize - 4).px //


    val margin = (props.oSize * 0.05).px

    div {
        css {
            marginTop = margin
            width = innerSize
            height = innerSize
            //backgroundColor = NamedColor.darkgray
            borderRadius = props.oSize.pc
            display = Display.inlineBlock
            border = Border(2.px, LineStyle.solid, NamedColor.darkred)
        }
    }


//        css {
//            padding = 5.px
//            backgroundColor = rgb(8, 97, 22)
//            color = rgb(56, 246, 137)
//        }

}