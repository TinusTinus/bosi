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
 * Test specification for {@link GameObject}.
 *
 * @author Martijn van de Rijdt
 */
class GameObjectSpec extends FlatSpec with Matchers {
  /** Dummy subclass of {@link GameObject}. */
  private case class DummyGameObject(override val location: Point, override val width: Double, override val height: Double) 
      extends GameObject(location, width, height) {
    /**
     * Convenience constructor.
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @param width width
     * @param height height
     */
    def this(x: Double, y: Double, width: Double, height: Double) = this(Point(x, y), width, height)
    
    override def character = ' '
  }
  
  "A game object" should "overlap with itself" in {
    val gameObject = new DummyGameObject(1, 3, 5, 2)
    
    gameObject overlapsHorizontally gameObject should be (true)
    gameObject overlapsVertically gameObject should be (true)
    gameObject overlaps gameObject should be (true)
  }
  
  it should "not overlap with an object to the top left" in {
    val object0 = new DummyGameObject(0, 0, 2, 2)
    val object1 = new DummyGameObject(3, 3, 2, 2)
    
    object0 overlapsHorizontally object1 should be (false)
    object1 overlapsHorizontally object0 should be (false)
    object0 overlapsVertically object1 should be (false)
    object1 overlapsVertically object0 should be (false)
    object0 overlaps object1 should be (false)
    object1 overlaps object0 should be (false)
  }
  
  it should "not overlap with an object to the bottom left" in {
    val object0 = new DummyGameObject(0, 3, 2, 2)
    val object1 = new DummyGameObject(3, 0, 2, 2)
    
    object0 overlapsHorizontally object1 should be (false)
    object1 overlapsHorizontally object0 should be (false)
    object0 overlapsVertically object1 should be (false)
    object1 overlapsVertically object0 should be (false)
    object0 overlaps object1 should be (false)
    object1 overlaps object0 should be (false)
  }
  
  it should "not overlap with an object to the left" in {
    val object0 = new DummyGameObject(3, 0, 2, 2)
    val object1 = new DummyGameObject(0, 0, 2, 2)
    
    object0 overlapsHorizontally object1 should be (false)
    object1 overlapsHorizontally object0 should be (false)
    object0 overlapsVertically object1 should be (true)
    object1 overlapsVertically object0 should be (true)
    object0 overlaps object1 should be (false)
    object1 overlaps object0 should be (false)
  }
  
  it should "not overlap with an object below" in {
    val object0 = new DummyGameObject(0, 3, 2, 2)
    val object1 = new DummyGameObject(0, 0, 2, 2)
    
    object0 overlapsHorizontally object1 should be (true)
    object1 overlapsHorizontally object0 should be (true)
    object0 overlapsVertically object1 should be (false)
    object1 overlapsVertically object0 should be (false)
    object0 overlaps object1 should be (false)
    object1 overlaps object0 should be (false)
  }
  
  it should "overlap with an object contained within" in {
    val object0 = new DummyGameObject(0, 0, 10, 10)
    val object1 = new DummyGameObject(3, 2, 5, 5)
    
    object0 overlapsHorizontally object1 should be (true)
    object1 overlapsHorizontally object0 should be (true)
    object0 overlapsVertically object1 should be (true)
    object1 overlapsVertically object0 should be (true)
    object0 overlaps object1 should be (true)
    object1 overlaps object0 should be (true)
  }
  
  it should "overlap diagonally" in {
    val object0 = new DummyGameObject(0, 0, 2, 2)
    val object1 = new DummyGameObject(1, 1, 2, 2)
    
    object0 overlapsHorizontally object1 should be (true)
    object1 overlapsHorizontally object0 should be (true)
    object0 overlapsVertically object1 should be (true)
    object1 overlapsVertically object0 should be (true)
    object0 overlaps object1 should be (true)
    object1 overlaps object0 should be (true)
  }
  
  it should "not overlap with an object adjacent to the left" in {
    val object0 = new DummyGameObject(0, 0, 2, 2)
    val object1 = new DummyGameObject(2, 0, 2, 2)
    
    object0 overlapsHorizontally object1 should be (false)
    object1 overlapsHorizontally object0 should be (false)
    object0 overlapsVertically object1 should be (true)
    object1 overlapsVertically object0 should be (true)
    object0 overlaps object1 should be (false)
    object1 overlaps object0 should be (false)
  }
  
  it should "not overlap with an object adjacent below" in {
    val object0 = new DummyGameObject(0, 0, 2, 2)
    val object1 = new DummyGameObject(0, 2, 2, 2)
    
    object0 overlapsHorizontally object1 should be (true)
    object1 overlapsHorizontally object0 should be (true)
    object0 overlapsVertically object1 should be (false)
    object1 overlapsVertically object0 should be (false)
    object0 overlaps object1 should be (false)
    object1 overlaps object0 should be (false)
  }
  
  it should "overlap with an object along the top edge" in {
    val object0 = new DummyGameObject(1, 1, 4, 4)
    val object1 = new DummyGameObject(2, 0, 2, 2)
    
    object0 overlapsHorizontally object1 should be (true)
    object1 overlapsHorizontally object0 should be (true)
    object0 overlapsVertically object1 should be (true)
    object1 overlapsVertically object0 should be (true)
    object0 overlaps object1 should be (true)
    object1 overlaps object0 should be (true)
  }
  
  it should "overlap with an object along the left edge" in {
    val object0 = new DummyGameObject(1, 1, 4, 4)
    val object1 = new DummyGameObject(0, 2, 2, 2)
    
    object0 overlapsHorizontally object1 should be (true)
    object1 overlapsHorizontally object0 should be (true)
    object0 overlapsVertically object1 should be (true)
    object1 overlapsVertically object0 should be (true)
    object0 overlaps object1 should be (true)
    object1 overlaps object0 should be (true)
  }
  
  it should "overlap with an object along the bottom edge" in {
    val object0 = new DummyGameObject(1, 1, 4, 4)
    val object1 = new DummyGameObject(2, 4, 2, 2)
    
    object0 overlapsHorizontally object1 should be (true)
    object1 overlapsHorizontally object0 should be (true)
    object0 overlapsVertically object1 should be (true)
    object1 overlapsVertically object0 should be (true)
    object0 overlaps object1 should be (true)
    object1 overlaps object0 should be (true)
  }
  
  it should "overlap with an object along the right edge" in {
    val object0 = new DummyGameObject(1, 1, 4, 4)
    val object1 = new DummyGameObject(4, 2, 2, 2)
    
    object0 overlapsHorizontally object1 should be (true)
    object1 overlapsHorizontally object0 should be (true)
    object0 overlapsVertically object1 should be (true)
    object1 overlapsVertically object0 should be (true)
    object0 overlaps object1 should be (true)
    object1 overlaps object0 should be (true)
  }
  
  it should "overlap" in {
    val object0 = new DummyGameObject(0, 0, 10, 100)
    val object1 = new DummyGameObject(9.99, 0, 80, 5)
    
    object0 overlapsHorizontally object1 should be (true)
    object1 overlapsHorizontally object0 should be (true)
    object0 overlapsVertically object1 should be (true)
    object1 overlapsVertically object0 should be (true)
    object0 overlaps object1 should be (true)
    object1 overlaps object0 should be (true)
  }
}