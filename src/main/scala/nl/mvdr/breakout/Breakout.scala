package nl.mvdr.breakout

import javafx.application.Application
import nl.mvdr.breakout.logging.Logging
import nl.mvdr.breakout.gui.BreakoutApplication

/**
 * Main class, which launches the game.
 * 
 * @author Martijn van de Rijdt
 */
object Breakout {
  /**
   * Main method.
   * 
   * @param args command line parameters; these are ignored
   */
  def main(args: Array[String]) {
    Logging.logVersionInfo()
    Logging.installSlf4jBridge()
    Application.launch(classOf[BreakoutApplication])
  }
}