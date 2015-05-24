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
 * Test specification for {@link Paddle}.
 *
 * @author Martijn van de Rijdt
 */
class PaddleSpec extends FlatSpec with Matchers {
  "A paddle" should "move to the left" in {
    val paddle = new Paddle
    
    val newPaddle = paddle.moveLeft
    
    newPaddle.x should be < paddle.x
    newPaddle.y should be (paddle.y)
    newPaddle.width should be (paddle.width)
    newPaddle.height should be (paddle.height)
  }
  
  it should "move to the right" in {
    val paddle = new Paddle
    
    val newPaddle = paddle.moveRight
    
    newPaddle.x should be > paddle.x
    newPaddle.y should be (paddle.y)
    newPaddle.width should be (paddle.width)
    newPaddle.height should be (paddle.height)
  }
  
  it should "not move through the left wall" in {
    val paddle = Paddle(Point(LeftWall.x + LeftWall.width, 0))
    
    val newPaddle = paddle.moveLeft
    
    newPaddle should be (paddle)
  }
  
  it should "not move through the right wall" in {
    val paddle = Paddle(Point(RightWall.x - PaddleSize.Width, 0))
    
    val newPaddle = paddle.moveRight
    
    newPaddle should be (paddle)
  }
  
  it should "move left and right at the same speed" in {
    val paddle = new Paddle
    
    val newPaddle = paddle.moveLeft.moveRight
    
    newPaddle should be (paddle)
  }
}