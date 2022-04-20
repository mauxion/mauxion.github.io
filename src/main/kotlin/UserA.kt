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

external interface UserAProps : Props {
    var xSize: Int
}

val UserA = FC<UserAProps> { props ->

        div{
            css{
                fontSize = props.xSize.px
            }
           +"X"
        }
}