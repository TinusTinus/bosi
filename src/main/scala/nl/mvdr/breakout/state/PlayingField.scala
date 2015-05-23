package nl.mvdr.breakout.state

/**
 * The main playing field.
 *
 * @author Martijn van de Rijdt
 */
object PlayingField extends GameObject(Point(0, 0), 200, 400) {
  /** Width (for vertical walls) or height (for the horizontal wall) of the walls at the edges of the playing field. */
  val WallDepth = 10
}