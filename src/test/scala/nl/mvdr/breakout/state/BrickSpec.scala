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