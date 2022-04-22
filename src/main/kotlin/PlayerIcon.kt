import kotlinx.browser.document
import react.FC
import react.Props
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.div

external interface PlayerIconProps : Props {
    var iconSize: Int
    var iconName: String
}

val PlayerIcon = FC<PlayerIconProps> { props ->
    val imgW = document.body!!.offsetWidth * (props.iconSize) / 100.0
        ReactHTML.img {
            width = imgW
            src = "img/${props.iconName}.gif"
        }
}