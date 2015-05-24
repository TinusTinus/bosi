package nl.mvdr.breakout.state

/**
 * A brick. The object of the game is to destroy all of these.
 *
 * @param location location of the brick
 * @param hitPoints the number of hit points; must be positive
 * 
 * @author Martijn van de Rijdt
 */
case class Brick(override val location: Point, val hitPoints: Int) extends GameObject(location, BrickSize.Width, BrickSize.Height) {
  
  require(0 < hitPoints)
  
  /**
   * Constructor. Creates a brick with one hit point.
   * 
   * @param location location of the brick
   */
  def this(location: Point) = this(location, 1)
  
  /**
   * Processes a hit by subtracting a hit point.
   * 
   * @return empty if hit points were reduced to zero, a copy of this brick with one less HP otherwise
   */
  def takeDamage: Option[Brick] = 
    if (hitPoints == 1) Option.empty
    else Option(copy(hitPoints = this.hitPoints - 1))
    
  override def character = hitPoints.toString.last
}

object BrickSize {
  val Width = 30
  val Height = 10
}