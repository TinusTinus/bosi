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