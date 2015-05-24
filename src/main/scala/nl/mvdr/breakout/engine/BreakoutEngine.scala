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
package nl.mvdr.breakout.engine

import nl.mvdr.breakout.input.BreakoutInput
import nl.mvdr.breakout.state.BreakoutState
import nl.mvdr.game.engine.GameEngine
import nl.mvdr.game.input.InputState

/**
 * Game engine implementation for a game of Breakout.
 * 
 * @constructor Creates a new engine.
 * 
 * @author Martijn van de Rijdt
 */
object BreakoutEngine extends GameEngine[BreakoutState, BreakoutInput] {
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
  private def nextState(previousState: BreakoutState, inputState: InputState[BreakoutInput]) =
    previousState.next(inputState.isPressed)
}