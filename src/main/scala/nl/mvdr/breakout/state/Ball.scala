package nl.mvdr.breakout.state

/**
 * The ball.
 * 
 * @param location location of the ball
 * @param speed, expressed as the (2D) distance the ball is supposed to move every frame (= 1/60th of a second)
 * 
 * @author Martijn van de Rijdt
 */
case class Ball(override val location: Point, val speed: Point) extends GameObject(location, BallSize.Diameter, BallSize.Diameter) {
  /** Default constructor. */
  def this() = this(Point(PlayingField.width / 2 - BallSize.Diameter / 2, PlayingField.height - 20), new Point(1, -1))
  
  override def character = 'o'
}

private object BallSize {
  val Diameter = 10
}