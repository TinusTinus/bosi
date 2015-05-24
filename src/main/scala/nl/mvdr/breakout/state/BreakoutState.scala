package nl.mvdr.breakout.state

import nl.mvdr.game.state.GameState
import nl.mvdr.breakout.input.BreakoutInput
import com.typesafe.scalalogging.LazyLogging
import scala.util.Random

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
      // Determine how the ball should bounce.
      var bounceHorizontally: Boolean = false
      var bounceVertically: Boolean = false

      // walls
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
      
      // paddle
      if (ball overlaps paddle) {
        if (ball.x < paddle.x && 0 < ball.speed.x) {
          logger.info("Ball bounces off the paddle to the left: {}, {}", ball, paddle)
          bounceHorizontally = true
        }
        if (paddle.x < ball.x && ball.speed.x < 0) {
          logger.info("Ball bounces off the paddle to the right: {}, {}", ball, paddle)
          bounceHorizontally = true
        }
        if (ball.y < paddle.y && 0 < ball.speed.y) {
          logger.info("Ball bounces upwards off the paddle: {}, {}", ball, paddle)
          bounceVertically = true
        }
        if (paddle.y < ball.y && ball.speed.y < 0) {
          logger.info("Ball bounces downwards off the paddle: {}, {}", ball, paddle)
          bounceVertically = true
        }
      }

      // bricks
      var newBricks = bricks
      bricks filter (_ overlaps ball) match {
        case List() => // ball didn't collide with any bricks, do nothing
        case brick :: _ => {
          // update the brick
          newBricks = newBricks filter (_ != brick)
          brick.takeDamage.map(b => newBricks = b :: newBricks)

          // check how the ball should bounce
          if (ball.x < brick.x && 0 < ball.speed.x) {
            logger.info("Ball bounces off a brick to the left: {}, {}", ball, brick)
            bounceHorizontally = true
          }
          if (brick.x < ball.x && ball.speed.x < 0) {
            logger.info("Ball bounces off a brick to the right: {}, {}", ball, brick)
            bounceHorizontally = true
          }
          if (ball.y < brick.y && 0 < ball.speed.y) {
            logger.info("Ball bounces upwards off a brick: {}, {}", ball, brick)
            bounceVertically = true
          }
          if (brick.y < ball.y && ball.speed.y < 0) {
            logger.info("Ball bounces downwards off a brick: {}, {}", ball, brick)
            bounceVertically = true
          }
        }
      }

      var newSpeed = ball.speed
      if (bounceHorizontally) newSpeed = newSpeed.copy(x = -newSpeed.x)
      if (bounceVertically) newSpeed = newSpeed.copy(y = -newSpeed.y)
      // add a bit of randomisation if the ball bounced
      if (bounceHorizontally || bounceVertically) newSpeed = randomisePoint(newSpeed)

      val newBall = Ball(ball.location + newSpeed, newSpeed)
      val newPaddle = movePaddle(pressed)

      BreakoutState(newBall, newPaddle, newBricks, lives)
    }
  }
  
  private def movePaddle(pressed: BreakoutInput => Boolean): Paddle = 
    if (pressed(BreakoutInput.LEFT) && !pressed(BreakoutInput.RIGHT)) paddle.moveLeft
    else if (!pressed(BreakoutInput.LEFT) && pressed(BreakoutInput.RIGHT)) paddle.moveRight
    else paddle
    
  private def randomisePoint(point: Point): Point =
    Point(randomise(point.x), randomise(point.y))
  
  private def randomise(double: Double): Double =
    double + Random.nextDouble / 100 - .05
  
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
      
      val suffix =
        if (won) "\nYou win!"
        else if (lost) "\nGame over"
        else ""
      
      "BreakoutState" + (playingField mkString "") + "\nLives: " + lives + suffix
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