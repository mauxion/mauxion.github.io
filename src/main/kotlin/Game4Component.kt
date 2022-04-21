import csstype.Height
import csstype.px
import csstype.rgb
import game.Cell
import game.CellState
import game.Game
import game.Game4
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

external interface Game4ComponentProps : Props {
    var game: Game4
}


val Game4Component = FC<Game4ComponentProps> { props ->

    var game by useState(props.game)

    val updateFunction: (Game4) -> Unit = { newGame ->
        game = newGame.copy()
    }

    Header4 {
        g = game
        setGame = updateFunction
    }

    Field4 {
        g = game
        setGame = updateFunction
    }
}