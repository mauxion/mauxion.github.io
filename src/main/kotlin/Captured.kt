import kotlinx.browser.document
import react.FC
import react.Props
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.div

external interface IconCompProps : Props {
    var iconSize: Int
    var iconName: String
}

val IconComp = FC<IconCompProps> { props ->
    val imgW = document.body!!.offsetWidth * (props.iconSize) / 100.0

    div {
        ReactHTML.img {
            width = imgW
            src = "img/${props.iconName}.png"
        }
    }
}