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