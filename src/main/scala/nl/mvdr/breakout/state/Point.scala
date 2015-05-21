package nl.mvdr.breakout.state

/**
 * A point in the two-dimensional game space.
 *
 * @param x horizontal coordinate; 0.0 is the left border of the playing field
 * @param y vertical coordinate; 0.0 is the top border of the playing field
 * 
 * @author Martijn van de Rijdt
 */
case class Point(val x: Double, y: Double) {
  /**
   * Addition operator.
   * 
   * @param that the Point to be added to this one
   * @return new Point representing the sum of this and that
   */
  def +(that: Point): Point = Point(this.x + that.x, this.y + that.y)
  
  /** @return negation of this point */
  def unary_- = Point(-x, -y)
  
  /**
   * Subtraction operator.
   * 
   * @param that the Point to be subtracted from this one
   * @return new Point representing this minus that
   */
  def -(that: Point): Point = this + -that
  
  override def toString = "(" + x + ", " + y + ")"
}