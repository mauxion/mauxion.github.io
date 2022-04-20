import csstype.*
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.div

external interface PlayerOWallProps : Props {
    var xWallSize: Int

}

val PlayerOWall = FC<PlayerOWallProps> { props ->


    val innerSize = props.xWallSize.px

    val margin = (props.xWallSize*0.05).px


        div{

            css{
                marginTop = margin
                width = innerSize
                height = innerSize
                backgroundColor = NamedColor.black
                borderRadius = 2.px
                display = Display.inlineBlock
            }
        }


//        css {
//            padding = 5.px
//            backgroundColor = rgb(8, 97, 22)
//            color = rgb(56, 246, 137)
//        }

}