package nl.mvdr.breakout.state

import nl.mvdr.game.state.GameState
import nl.mvdr.breakout.input.BreakoutInput

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
case class BreakoutState(ball: Ball, paddle: Paddle, bricks: List[Brick], lives: Int) extends GameState {
  /** Default constructor. */
  // TODO add bricks
  def this() = this(new Ball, new Paddle, List(), 2)
  
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
    val newBall = ball // TODO process movement
    val newPaddle = movePaddle(pressed)
    val newBricks = bricks // TODO adjust hit points / remove bricks
    val newLives = lives // TODO adjust
    
    BreakoutState(newBall, newPaddle, newBricks, newLives)
  }
  
  private def movePaddle(pressed: BreakoutInput => Boolean): Paddle = 
    if (pressed(BreakoutInput.LEFT) && !pressed(BreakoutInput.RIGHT)) paddle.moveLeft
    else if (!pressed(BreakoutInput.LEFT) && pressed(BreakoutInput.RIGHT)) paddle.moveRight
    else paddle
    
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