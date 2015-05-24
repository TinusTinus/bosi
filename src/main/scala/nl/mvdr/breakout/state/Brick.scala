/*
 * Copyright 2015 Martijn van de Rijdt 
 * 
 * This file is part of BOSI.
 * 
 * BOSI is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * BOSI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with BOSI. If not, see <http://www.gnu.org/licenses/>.
 */
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