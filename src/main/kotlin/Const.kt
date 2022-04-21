import csstype.NamedColor
import game.Icon
import game.Player

val COLOR_O = NamedColor.darkred
val COLOR_O_INACTIVE = NamedColor.rosybrown

val COLOR_X = NamedColor.darkblue
val COLOR_X_INACTIVE = NamedColor.cornflowerblue

val PLAYER_X: Player = Player("X", Icon.X, COLOR_X)
val PLAYER_PLUS: Player = Player("+", Icon.PLUS, COLOR_X)

val PLAYER_O: Player = Player("O", Icon.O, COLOR_O)
val PLAYER_DOT: Player = Player(".", Icon.DOT, COLOR_O)