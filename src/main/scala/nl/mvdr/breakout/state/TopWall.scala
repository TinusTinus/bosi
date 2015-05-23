package nl.mvdr.breakout.state

/**
 * The top wall.
 *
 * @author Martijn van de Rijdt
 */
object TopWall extends GameObject(PlayingField.location, PlayingField.width, PlayingField.WallDepth) {
  override def character = '_'
}