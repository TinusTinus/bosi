package nl.mvdr.breakout.engine

import nl.mvdr.breakout.input.BreakoutInput
import nl.mvdr.breakout.state.BreakoutState
import nl.mvdr.game.engine.GameEngine
import nl.mvdr.game.input.InputState

class BreakoutEngine extends GameEngine[BreakoutState, BreakoutInput] {
  override def initGameState: BreakoutState = new BreakoutState

  override def computeNextState(previousState: BreakoutState, inputStates: java.util.List[InputState[BreakoutInput]]): BreakoutState = {
    // This version of Breakout is a single player game.
    assert(inputStates.size == 1)
    nextState(previousState, inputStates get 0)
  }

  /**
   * Computes the next game state based on the previous one and the state of the controls.
   * 
   * @param previousState
   *            previous game state
   * @param inputState
   *            input state for the player
   * @return new game state
   */
  private def nextState(previousState: BreakoutState, inputState: InputState[BreakoutInput]) = previousState // TODO implement for reals
}