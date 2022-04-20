import csstype.*
import game.*
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.div
import react.useState

external interface CellProps : Props {
    var cell: Cell

    var size: Int


}

val CellComponent = FC<CellProps> { props ->

    var cell by useState(props.cell)

    val size = props.size.px
    val innerSize =  (props.size * 0.9).toInt()

//    val icon = when (cell.state) {
//        CellState.NEUTRAL -> "[]"
//        CellState.CAPTURED -> cell.owner!!.icon.text
//        CellState.WALL -> "W"
//    }



    div {
        css {
            height = size
            width = size

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
                    }
                } else if (cell.owner!!.icon == Icon.O) {
                    PlayerOWall{
                        xWallSize = innerSize
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