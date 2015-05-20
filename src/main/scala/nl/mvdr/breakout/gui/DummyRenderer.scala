package nl.mvdr.breakout.gui

import nl.mvdr.game.gui.GameRenderer
import nl.mvdr.breakout.state.BreakoutState

/**
 * Dummy implementation of {@link GameRenderer}.
 *
 * @author Martijn van de Rijdt
 */
class DummyRenderer extends GameRenderer[BreakoutState] {
  override def render(state: BreakoutState): Unit = {
    // do nothing
  }
}