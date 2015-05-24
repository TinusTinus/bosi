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

import org.scalatest._

/**
 * Test specification for {@link Brick}.
 *
 * @author Martijn van de Rijdt
 */
class BrickSpec extends FlatSpec with Matchers {
  "A brick" should "be destroyed when its hit points are reduced to zero" in {
    val brick = new Brick(Point(0, 0))
    
    brick.takeDamage should be (Option.empty)
  }
  
  it should "lose a hit point when it takes damage" in {
    val brick = Brick(Point(0, 0), 5)
    
    brick.takeDamage should be (Option(Brick(Point(0, 0), 4)))
  }
  
  it should "not be allowed to have negative hit points" in {
    intercept[IllegalArgumentException] {
      Brick(Point(0, 0), -10)
    }
  }
  
  it should "not be allowed to have zero hit points" in {
    intercept[IllegalArgumentException] {
      Brick(Point(0, 0), 0)
    }
  }

}