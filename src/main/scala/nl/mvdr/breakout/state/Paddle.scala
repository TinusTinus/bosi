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
 * Representation of the player's paddle. 
 * 
 * @param location
 * 
 * @author Martijn van de Rijdt 
 */
case class Paddle(override val location: Point) extends GameObject(location, PaddleSize.Width, PaddleSize.Height) {
  
  /** Default constructor. */
  def this() = this(Point(PlayingField.width / 2 - PaddleSize.Width / 2, PlayingField.height - 10))
  
  /** The paddle's speed while moving, in horziontal distance per frame. Note: a frame is 1/60 of a second. */
  private val Speed = 1
  
  /**
   * Moves the paddle to the right, if it is not right up to the right wall. Otherwise leaves it in place.
   * 
   * @return a copy of this paddle, moved to the right if possible
   */
  def moveRight: Paddle = {
    val newPaddle = moveHorizontally(Speed)
    
    if (newPaddle overlaps RightWall) this
    else newPaddle
  }
  
  /**
   * Moves the paddle to the left, if it is not right up to the left wall. Otherwise leaves it in place.
   * 
   * @return a copy of this paddle, moved to the left if possible
   */
  def moveLeft : Paddle = {
    val newPaddle = moveHorizontally(-Speed)
    
    if (newPaddle overlaps LeftWall) this
    else newPaddle
  }

  private def moveHorizontally(dx: Double) = Paddle(Point(x + dx, y))
  
  override def character = '='
}

object PaddleSize {
  val Width = 40
  val Height = 5
}