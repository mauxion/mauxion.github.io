import csstype.*
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.div

external interface PlayerPlusProps : Props {
    var plusSize: Int

}

val PlayerPlus = FC<PlayerPlusProps> { props ->
//    val margin = (props.plusSize * -0.86).vw

    div {
        css {
            fontSize = (props.plusSize*0.8).vw
            color = NamedColor.darkblue

        }
        +"+"
    }
}