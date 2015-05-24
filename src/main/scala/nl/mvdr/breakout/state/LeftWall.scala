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
 * The left wall.
 *
 * @author Martijn van de Rijdt
 */
object LeftWall extends GameObject(PlayingField.location, PlayingField.WallDepth, PlayingField.height) {
  override def character = '|'
}