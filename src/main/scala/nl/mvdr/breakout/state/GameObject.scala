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
 * An object in the game world.
 * 
 * @param location of (the upper left corner of) the game object
 * @param width width
 * @param height height
 *
 * @author Martijn van de Rijdt
 */
abstract class GameObject(val location: Point, val width: Double, val height: Double) {
  /** @return x coordinate */
  def x = location.x
  /** @return y coordinate */
  def y = location.y
  
  /**
   * Indicates whether the two game objects overlap.
   * 
   * @param that the other game object
   * @return whether this and that overlap
   */
  def overlaps(that: GameObject): Boolean = (this overlapsHorizontally that) && (this overlapsVertically that)
  
  /**
   * Indicates whether the two game objects overlap on the x axis.
   * 
   * @param that the other game object
   * @return whether this and that overlap
   */
  def overlapsHorizontally(that: GameObject): Boolean = overlap(this.x, this.width, that.x, that.width)
  
  /**
   * Indicates whether the two game objects overlap on the y axis.
   * 
   * @param that the other game object
   * @return whether this and that overlap
   */
  def overlapsVertically(that: GameObject): Boolean = overlap(this.y, this.height, that.y, that.height)
  
  /**
   * Indicates whether the given two-dimensional lines overlap.
   * 
   * @param line1Start start point of the first line
   * @param line1Length length of the first line
   * @param line2Start start point of the second line
   * @param line2Length start point of the second line
   * @return whether the two lines overlap
   */
  private def overlap(line1Start: Double, line1Length: Double, line2Start: Double, line2Length: Double): Boolean = {
    val line1End = line1Start + line1Length
    val line2End = line2Start + line2Length
    
    line2Start < line1End && line1Start < line2End
  }
  
  /** @return the character representing this game object in the game state toString */
  def character: Char
}