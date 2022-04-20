import csstype.*
import game.Cell
import game.CellState
import game.Game
import game.User
import kotlinx.browser.document
import react.FC
import react.Props
import react.css.css
import react.dom.html.InputType
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.table
import react.dom.html.ReactHTML.tbody
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.th
import react.dom.html.ReactHTML.thead
import react.dom.html.ReactHTML.tr
import react.useState

external interface UserAWallProps : Props {
    var oWallSize: Int

}

val UserAWall = FC<UserAWallProps> { props ->

//    val innerSize = (props.size * 0.9).px




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