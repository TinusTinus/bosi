package nl.mvdr.breakout.input

import nl.mvdr.game.input.JInputControllerConfiguration
import nl.mvdr.game.input.InputMapping
import net.java.games.input.Controller
import scala.collection.JavaConversions
import net.java.games.input.ControllerEnvironment
import net.java.games.input.Controller.Type
import net.java.games.input.Component.Identifier.Key

/**
 * Controller configuration for a game of Breakout.
 *
 * @constructor Constructor to create a new configuration.
 * @param mapping mapping from inputs to the corresponding buttons (or other input devices); all {@link BreakoutInput} values should occur as a key
 * @param controllers all controllers used by the buttons in mapping
 * 
 * @author Martijn van de Rijdt
 */
class BreakoutControllerConfiguration(mapping: Map[BreakoutInput, Set[InputMapping]], controllers: Set[Controller])
  extends JInputControllerConfiguration[BreakoutInput](ConversionHelper.convertMapOfSets(mapping), ConversionHelper.convertSet(controllers)) {

  /** Default constructor. Creates a configurations that uses any keyboard's arrow keys. */
  def this() = this(DefaultControllerMapping.defaultMapping, DefaultControllerMapping.keyboardControllers)
}

/** Helper class to convert Scala types to Java types as required by {@link JInputControllerConfiguration}. */
private object ConversionHelper {
  /**
   * Converts a set.
   *
   * @param set set to be converted
   * @return Java Set
   */
  def convertSet[T](set: Set[T]): java.util.Set[T] = JavaConversions.setAsJavaSet(set)

  /**
   * Converts a map, where the values themselves are sets.
   *
   * @param input map to be converted
   * @return Java map, where the values are Java sets
   */
  def convertMapOfSets[K, V](input: Map[K, Set[V]]): java.util.Map[K, java.util.Set[V]] = {
    // first convert values to java.util.Sets
    val temp = input map (entry => (entry._1, convertSet(entry._2)))
    // then convert the map itself
    JavaConversions.mapAsJavaMap(temp)
  }
}

/** Helper class to construct a default controller mapping. */
private object DefaultControllerMapping {
  lazy private val environmentControllers = ControllerEnvironment.getDefaultEnvironment.getControllers.toSet
  lazy val keyboardControllers = environmentControllers filter (_.getType == Type.KEYBOARD)

  private def inputMapping(key: Key) = {
    keyboardControllers map (controller => new InputMapping(controller getComponent key, 1))
  }
  
  def defaultMapping = Map((BreakoutInput.LEFT, inputMapping(Key.LEFT)), (BreakoutInput.RIGHT, inputMapping(Key.RIGHT)))
}