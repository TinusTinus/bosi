package nl.mvdr.breakout.state

/**
 * The ball.
 * 
 * @author Martijn van de Rijdt
 */
case class Ball(override val location: Point) extends GameObject(location, BallSize.Diameter, BallSize.Diameter) {
  /** Default constructor. */
  def this() = this(Point(PlayingField.width / 2 - BallSize.Diameter / 2, PlayingField.height - 20))
  
  override def character = 'o'
  
  // TODO ball movement
}

private object BallSize {
  val Diameter = 10
}