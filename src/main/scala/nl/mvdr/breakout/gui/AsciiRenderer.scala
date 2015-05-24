package nl.mvdr.breakout.gui

import javafx.application.Platform
import javafx.scene.control.Label
import nl.mvdr.breakout.state.BreakoutState
import nl.mvdr.game.gui.GameRenderer
import javafx.scene.text.Font

/**
 * Renderer which renders the game state into a {@link Label} as ASCII art.
 *
 * @author Martijn van de Rijdt
 */
object AsciiRenderer extends GameRenderer[BreakoutState] {
  val label = new Label
  label.setFont(Font.font("Monospaced"))
  
  override def render(state: BreakoutState): Unit = {
    object runnable extends Runnable {
      def run(): Unit = label.setText(state.toString)
    }
    
    Platform.runLater(runnable)
  }
}