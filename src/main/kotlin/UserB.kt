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

external interface UserBProps : Props {
    var oSize: Int

}

val UserB = FC<UserBProps> { props ->

    val innerSize = (props.oSize -4).px //


    val margin = (props.oSize*0.05).px

        div{
            css{
                marginTop = margin
                width = innerSize
                height = innerSize
                //backgroundColor = NamedColor.darkgray
                borderRadius = props.oSize.pc
                display = Display.inlineBlock
                border = Border(2.px, LineStyle.solid)
            }
        }


//        css {
//            padding = 5.px
//            backgroundColor = rgb(8, 97, 22)
//            color = rgb(56, 246, 137)
//        }

}