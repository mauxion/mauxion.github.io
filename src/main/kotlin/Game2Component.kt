import csstype.Height
import csstype.px
import csstype.rgb
import game.Cell
import game.CellState
import game.Game
import game.Game2
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

external interface Game2Props : Props {
    var game: Game2
}


val Game2Component = FC<Game2Props> { props ->

    var game by useState(props.game)

    val updateFunction: (Game2) -> Unit = { newGame ->
        game = newGame.copy()
    }

    Header2 {
        g = game
        setGame = updateFunction
    }

    Field2 {
        g = game
        setGame = updateFunction
    }
}