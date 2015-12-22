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
package nl.mvdr.breakout.logging

import com.typesafe.scalalogging.LazyLogging
import java.lang.Thread.UncaughtExceptionHandler
import org.slf4j.bridge.SLF4JBridgeHandler
import nl.mvdr.game.logging.Logging

/**
 * Utility class with functionality related to logging.
 *
 * @author Martijn van de Rijdt
 */
object BreakoutLogging extends Logging with LazyLogging {
  /** Installs a bridge for java.util.logging to slf4j. */
  def installSlf4jBridge(): Unit = {
    logger.info("Installing java.util.logging to slf4j bridge.");

    // remove existing handlers attached to java.util.logging root logger
    SLF4JBridgeHandler.removeHandlersForRootLogger();

    // add SLF4JBridgeHandler to java.util.logging's root logger
    SLF4JBridgeHandler.install();
  }
}

private object Handler extends UncaughtExceptionHandler with LazyLogging {
  override def uncaughtException(thread: Thread, error: Throwable) = logger.error("Uncaught runtime exception", error)
}