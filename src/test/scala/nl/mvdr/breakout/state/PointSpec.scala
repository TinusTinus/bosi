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

import collection.mutable.Stack
import org.scalatest._

/**
 * Test specification for {@link Point}.
 *
 * @author Martijn van de Rijdt
 */
class PointSpec extends FlatSpec with Matchers {

  "A Point's toString" should "be nice and readable" in {
    val point = Point(3, 5)
    
    point.toString should be ("(3.0, 5.0)")
  }

  "Addition of points" should "be correct for positive values" in {
    val left = Point(3, 5)
    val right = Point(7, 2)
    
    left + right should be (Point(10, 7))
  }
  
  it should "be correct for zero" in {
    val zero = Point(0, 0)
    
    zero + zero should be (zero)
  }
  
  it should "be correct for negative values" in {
    val left = Point(-3, -5)
    val right = Point(-7, -2)
    
    left + right should be (Point(-10, -7))
  }
  
  "Negation of a point" should "be correct for a positive value" in {
    val point = Point(2, 9)
    
    -point should be (Point(-2, -9))
  }
  
  it should "be correct for a negative value" in {
    val point = Point(-2, -9)
    
    -point should be (Point(2, 9))
  }
  
  it should "be correct for zero" in {
    val zero = Point(0, 0)
    
    -zero should be (zero)
  }
  
  it should "be correct for a mixture of positive and negative values" in {
    val point = Point(2, -9)
    
    -point should be (Point(-2, 9))
  }
  
  "Subtraction of points" should "be correct for positive values" in {
    val left = Point(3, 5)
    val right = Point(7, 2)
    
    left - right should be (Point(-4, 3))
  }
  
  it should "be correct for zero" in {
    val zero = Point(0, 0)
    
    zero - zero should be (zero)
  }
  
  it should "be correct for negative values" in {
    val left = Point(-3, -5)
    val right = Point(-7, -2)
    
    left - right should be (Point(4, -3))
  }
}