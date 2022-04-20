import csstype.*
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.div

external interface PlayerXWallProps : Props {
    var oWallSize: Int

}

val UserAWall = FC<PlayerXWallProps> { props ->






    val innerSize = props.oWallSize.px

    val margin = (props.oWallSize*0.05).px

    div{
        css{


            marginTop= margin
            width = innerSize
            height = innerSize
            backgroundColor = NamedColor.black
            borderRadius = 50.pc
            display = Display.inlineBlock
        }
    }




//        css {
//            padding = 5.px
//            backgroundColor = rgb(8, 97, 22)
//            color = rgb(56, 246, 137)
//        }

}