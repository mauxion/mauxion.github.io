import csstype.*
import game.*
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.div
import react.useState

external interface CellProps : Props {
    var cell: Cell

    var g: Game
    var size: Int


}

val CellComponent = FC<CellProps> { props ->

    var cell by useState(props.cell)

    val size = props.size
    val innerSize =  (props.size * 0.9).toInt()

    val connected = props.g.isConnected(cell)


    div {
        css {
            height = size.vw
            width = size.vw
        }
//        +"${icon}"

        when (cell.state) {
            CellState.NEUTRAL -> +""
            CellState.CAPTURED -> {
                if (cell.owner!!.icon == Icon.X) {
                    PlayerX{
                        xSize = innerSize
                    }
                } else if (cell.owner!!.icon == Icon.O) {
                    UserO{
                        oSize = innerSize
                    }
                }
            }
            CellState.WALL -> {
                if (cell.owner!!.icon == Icon.X) {
                    UserAWall{
                        oWallSize = innerSize
                        isConnected = connected
                    }
                } else if (cell.owner!!.icon == Icon.O) {
                    PlayerOWall{
                        xWallSize = innerSize
                        isConnected = connected
                    }
                }
            }
        }

    }
//        css {
//            padding = 5.px
//            backgroundColor = rgb(8, 97, 22)
//            color = rgb(56, 246, 137)
//        }

}