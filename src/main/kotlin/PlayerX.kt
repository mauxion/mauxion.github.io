import csstype.*
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.div

external interface PlayerXProps : Props {
    var xSize: Int

}

val PlayerX = FC<PlayerXProps> { props ->
    val margin = (props.xSize * -0.65).vw



    div {
        css {
            fontSize = (props.xSize).vw
            color = NamedColor.darkblue
            marginTop = margin
        }
        +"✕"
    }
}