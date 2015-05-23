package nl.mvdr.breakout.state

import nl.mvdr.game.state.GameState

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
  
  def next: BreakoutState = ???
}