package nl.mvdr.breakout

import nl.mvdr.breakout.input.BreakoutControllerConfiguration
import nl.mvdr.breakout.engine.BreakoutEngine
import nl.mvdr.breakout.gui.DummyRenderer
import nl.mvdr.game.input.JInputController
import nl.mvdr.breakout.input.BreakoutInput
import nl.mvdr.game.engine.GameLoop
import scala.collection.JavaConversions
import nl.mvdr.game.input.InputController
import com.typesafe.scalalogging.LazyLogging

/**
 * Main class, which launches the game.
 * 
 * @author Martijn van de Rijdt
 */
object Breakout extends LazyLogging {
  
  /**
   * Main method.
   * 
   * @param args command line parameters; these are ignored
   */
  def main(args: Array[String]) {
    val controllerConfiguration = new BreakoutControllerConfiguration
    val inputController = new JInputController[BreakoutInput](classOf[BreakoutInput], controllerConfiguration)
    val inputControllers = List[InputController[BreakoutInput]](inputController)
    
    val loop = new GameLoop(JavaConversions.seqAsJavaList(inputControllers), BreakoutEngine, DummyRenderer)
    loop.start()
    
    logger.info("Started the main game loop: {}", loop)
  }
}