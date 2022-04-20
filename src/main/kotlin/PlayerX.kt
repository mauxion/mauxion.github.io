import csstype.*
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.div

external interface PlayerXProps : Props {
    var xSize: Int
}

val PlayerX = FC<PlayerXProps> { props ->

    div {
        css {
            fontSize = props.xSize.px
            color = NamedColor.darkblue
        }
        +"X"
    }
}