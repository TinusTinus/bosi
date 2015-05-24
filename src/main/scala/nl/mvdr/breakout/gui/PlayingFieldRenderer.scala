package nl.mvdr.breakout.gui

import nl.mvdr.game.gui.GameRenderer
import nl.mvdr.breakout.state.BreakoutState
import javafx.application.Platform
import javafx.scene.Group
import nl.mvdr.breakout.state.PlayingField
import nl.mvdr.breakout.state.LeftWall
import javafx.scene.shape.Rectangle
import javafx.scene.paint.Color
import javafx.scene.paint.Stop
import javafx.scene.paint.LinearGradient
import javafx.scene.paint.CycleMethod
import scala.collection.JavaConversions
import nl.mvdr.breakout.state.RightWall
import nl.mvdr.breakout.state.GameObject
import nl.mvdr.breakout.state.TopWall
import nl.mvdr.breakout.state.Brick
import javafx.scene.paint.RadialGradient
import nl.mvdr.breakout.state.Paddle
import javafx.scene.shape.Circle
import nl.mvdr.breakout.state.Ball

/**
 * Renders the playing field as a {@link Group}.
 *
 * @author Martijn van de Rijdt
 */
object PlayingFieldRenderer extends GameRenderer[BreakoutState] {
  private val ArcSize = c(5)
  
  val group = new Group

  override def render(state: BreakoutState): Unit = {
    object runnable extends Runnable {
      def run(): Unit = {
        group.getChildren.clear()

        group.getChildren.addAll(renderPlayingField, renderTopWall, renderLeftWall, renderRightWall)
        group.getChildren.addAll(JavaConversions.seqAsJavaList(state.bricks map renderBrick))
        
        group.getChildren.addAll(renderPaddle(state.paddle), renderBall(state.ball))
      }
    }

    Platform.runLater(runnable)
  }

  private def renderPlayingField: Rectangle = {
    val result = renderGameObject(PlayingField)
    result.setFill(Color.GRAY)
    result
  }
  
  private def renderLeftWall: Rectangle = renderWall(LeftWall, 0, 0, 1, 0)

  private def renderRightWall: Rectangle = renderWall(RightWall, 1, 0, 0, 0)

  private def renderTopWall: Rectangle = renderWall(TopWall, 0, 0, 0, 1)

  private def renderWall(wall: GameObject, startX: Int, startY: Int, endX: Int, endY: Int): Rectangle = {
    val result = renderGameObject(wall)

    val stops = List(new Stop(0, Color.WHITE), new Stop(1, Color.BLACK))
    val gradient = new LinearGradient(startX, startY, endX, endY, true, CycleMethod.NO_CYCLE, JavaConversions.seqAsJavaList(stops));
    result.setFill(gradient)

    result
  }

  private def renderBrick(brick: Brick): Rectangle = {
    val color = brick.hitPoints match {
      case 1 => Color.YELLOW
      case 2 => Color.ORANGE
      case _ => Color.RED
    }
    
    renderBlock(brick, color)
  }
  
  private def renderPaddle(paddle: Paddle): Rectangle = renderBlock(paddle, Color.BLACK)
  
  private def renderBlock(gameObject: GameObject, color: Color): Rectangle = {
    val result = renderGameObject(gameObject)

    val fill = new RadialGradient(0,
      1,
      result.getX + result.getWidth / 4,
      result.getY + result.getHeight / 4,
      20,
      false,
      CycleMethod.NO_CYCLE,
      new Stop(0, color.brighter),
      new Stop(1, color.darker));
    result.setFill(fill)

    result.setArcWidth(ArcSize)
    result.setArcHeight(ArcSize)
    
    result    
  }
  
  private def renderGameObject(gameObject: GameObject): Rectangle =
    new Rectangle(c(gameObject.x), c(gameObject.y), c(gameObject.width), c(gameObject.height))
  
  private def renderBall(ball: Ball): Circle = {
    val result = new Circle(c(ball.x + ball.width / 2), c(ball.y) + c(ball.height / 2), c(ball.width / 2))
    
    val fill = new RadialGradient(0,
      1,
      result.getCenterX,
      result.getCenterY,
      20,
      false,
      CycleMethod.NO_CYCLE,
      new Stop(0, Color.GRAY),
      new Stop(1, Color.WHITE));
    result.setFill(fill)
    
    result
  }
  
  /**
   * Converts a coordinate in the model (playing field) to a coordinate in pixels.
   * 
   * @param coordinate
   * @return coordinate in pixels
   */
  private def c(x: Double): Int = (x * 2).intValue
}
