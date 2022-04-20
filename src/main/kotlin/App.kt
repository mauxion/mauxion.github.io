import csstype.Height
import csstype.px
import csstype.rgb
import game.Cell
import game.CellState
import game.Game
import kotlinx.browser.document
import kotlinx.browser.window
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

external interface AppProps : Props {

}


val App = FC<AppProps> { props ->

    var game by useState(Game())


    val windowHeight = window.innerHeight

    val headerHeight = (windowHeight * 0.05).toInt()


    val maxHeight = (windowHeight*0.85).toInt()
    val windowWidth = (window.innerWidth*0.93).toInt()
    val maxSize = if (maxHeight < windowWidth) maxHeight else windowWidth

    val updateFunction: (Game) -> Unit = { newGame ->
        game = newGame.copy()
    }


    Header {
        g = game
        setGame =updateFunction
    }



    Field {
        cellSize = maxSize / game.size
        g = game
        setGame = updateFunction
     }
}