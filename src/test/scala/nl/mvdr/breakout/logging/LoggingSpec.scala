package nl.mvdr.breakout.state

import org.scalatest._
import nl.mvdr.breakout.logging.Logging

/**
 * Test specification for {@link Logging}.
 *
 * @author Martijn van de Rijdt
 */
class LoggingSpec extends FlatSpec with Matchers {
  "The logVersionInfo method" should "log some useful version info without crashing" in {
    Logging.logVersionInfo()
  }
}