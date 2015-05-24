package nl.mvdr.breakout.gui

import javafx.application.Application
import nl.mvdr.game.input.JInputController
import com.typesafe.scalalogging.LazyLogging
import nl.mvdr.breakout.input.BreakoutInput
import nl.mvdr.breakout.engine.BreakoutEngine
import nl.mvdr.game.input.InputController
import nl.mvdr.breakout.input.BreakoutControllerConfiguration
import nl.mvdr.game.engine.GameLoop
import nl.mvdr.breakout.logging.Logging
import scala.collection.JavaConversions
import javafx.stage.Stage
import nl.mvdr.breakout.state.BreakoutState
import javafx.scene.Scene
import javafx.scene.layout.FlowPane
import javafx.scene.paint.Color
import nl.mvdr.breakout.state.PlayingField

/**
 * Main entry point for launching the application.
 *
 * @author Martijn van de Rijdt
 */
class BreakoutApplication extends Application with LazyLogging {
  
  var gameLoop: Option[GameLoop[BreakoutState, BreakoutInput]] = Option.empty
  
  override def start(stage: Stage): Unit = {
    logger.info("Starting the application.")
    
    Logging.setUncaughtExceptionHandler()
    
    val controllerConfiguration = new BreakoutControllerConfiguration
    val inputController = new JInputController(classOf[BreakoutInput], controllerConfiguration)
    val inputControllers = List(inputController)
    
    gameLoop = Option(new GameLoop(JavaConversions.seqAsJavaList(inputControllers), BreakoutEngine, PlayingFieldRenderer))
    gameLoop.get.start()
    
    logger.info("Started the main game loop: {}", gameLoop.get)
    
    val scene = new Scene(PlayingFieldRenderer.group, Color.WHITE);
    
    stage.setTitle("Breakout")
    stage.setScene(scene)
    stage.setWidth(PlayingField.width.intValue * 2 + 15)
    stage.setHeight(PlayingField.height.intValue * 2 + 50)
    stage.show();
    logger.info("Stage shown.");
  }
  
  override def stop(): Unit = {
    logger.info("Stopping the application.")
    
    gameLoop.get.stop()
    
    super.stop()
  }
}