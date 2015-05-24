package nl.mvdr.breakout.state

import nl.mvdr.game.state.GameState
import nl.mvdr.breakout.input.BreakoutInput
import com.typesafe.scalalogging.LazyLogging

/**
 * Container for the game state of a game of breakout.
 * 
 * @param ball the ball
 * @param paddle the paddle, controlled by the player
 * @param remaining bricks
 * @param lives remaining number of lives
 * 
 * @author Martijn van de Rijdt
 */
case class BreakoutState(ball: Ball, paddle: Paddle, bricks: List[Brick], lives: Int) extends GameState with LazyLogging {
  /** Default constructor. */
  def this() = this(new Ball, new Paddle, StartingBricks(), 2)
  
  override def isGameOver: Boolean = won || lost
  
  /** @return whether the player has won the game */
  def won: Boolean = bricks.isEmpty
  
  /** @return whether the player has lost the game */
  def lost: Boolean = lives == 0 && !(ball overlaps PlayingField)

  /**
   * Computes the next state.
   *
   * @param pressed which inputs have been pressed by the player
   * @return next game state
   */
  def next(pressed: BreakoutInput => Boolean): BreakoutState = {
    if (!(ball overlaps PlayingField)) {
      logger.info("Ball left the playing field: {}. Remaining lives: {}", ball, (lives - 1).toString)
      BreakoutState(new Ball, movePaddle(pressed), bricks, lives - 1)
    } else {
      var bounceHorizontally: Boolean = false
      var bounceVertically: Boolean = false

      if ((ball overlaps LeftWall) && ball.speed.x < 0) {
        logger.info("Ball bounces off left wall: {}.", ball)
        bounceHorizontally = true
      }
      if ((ball overlaps RightWall) && 0 < ball.speed.x) {
        logger.info("Ball bounces off right wall: {}.", ball)
        bounceHorizontally = true
      }
      if ((ball overlaps TopWall) && ball.speed.y < 0) {
        logger.info("Ball bounces off top wall: {}.", ball)
        bounceVertically = true
      }
      // TODO paddle
      // TODO bricks

      var newSpeed = ball.speed
      if (bounceHorizontally) newSpeed = newSpeed.copy(x = -newSpeed.x)
      if (bounceVertically) newSpeed = newSpeed.copy(y = -newSpeed.y)
      if (bounceHorizontally || bounceVertically) newSpeed = newSpeed // TODO add a bit of randomisation here

      val newBall = Ball(ball.location + newSpeed, newSpeed)
      val newPaddle = movePaddle(pressed)
      val newBricks = bricks // TODO adjust hit points / remove bricks
      val newLives = lives // TODO adjust

      BreakoutState(newBall, newPaddle, newBricks, newLives)
    }
  }
  
  private def movePaddle(pressed: BreakoutInput => Boolean): Paddle = 
    if (pressed(BreakoutInput.LEFT) && !pressed(BreakoutInput.RIGHT)) paddle.moveLeft
    else if (!pressed(BreakoutInput.LEFT) && pressed(BreakoutInput.RIGHT)) paddle.moveRight
    else paddle
  
  /** @return game state as ASCII art */
  override def toString: String = {
      val playingField = for {
        y <- 0 until PlayingField.height.toInt by 10
        x <- 0 until PlayingField.width.toInt by 10
      } yield {
        val prefix = if (x == 0) "\n" else ""
        
        object Dummy extends GameObject(Point(x, y), 10, 10) { override def character = ' ' }
        val objects = ball :: paddle :: bricks ::: List(LeftWall, RightWall, TopWall, PlayingField)
        val character = objects.filter(_ overlaps Dummy).head.character
        
        prefix + character
      }
      
      "BreakoutState" + (playingField mkString "")
    }
}

private object StartingBricks extends Function0[List[Brick]] {
  /** The gap between bricks, and the minimal gap between a brick and a wall. */
  val Gap = 10
  
  /** @return a list containing the initial bricks at the start of a game of Breakout */
  override def apply: List[Brick] = (for {
    x <- (LeftWall.x + LeftWall.width + Gap) to (RightWall.x - Gap - BrickSize.Width) by BrickSize.Width + Gap
    y <- (TopWall.x + TopWall.height + Gap) to (PlayingField.height / 2) by BrickSize.Height + Gap
  } yield {
    val location = Point(x, y)
    val hitPoints =
      if (y == TopWall.x + TopWall.height + Gap)
        3 // top row
      else if (y == TopWall.x + TopWall.height + Gap + BrickSize.Height + Gap)
        2 // second row
      else
        1 // everything else
    Brick(location , hitPoints)
  }).toList
}