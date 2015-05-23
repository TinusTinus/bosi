package nl.mvdr.breakout.state

/**
 * The right wall.
 *
 * @author Martijn van de Rijdt
 */
object RightWall extends GameObject(new Point(PlayingField.x + PlayingField.width - PlayingField.WallDepth,
    PlayingField.y), PlayingField.WallDepth, PlayingField.height) {
  override def character = '|'
}