package nl.mvdr.breakout.logging

import com.typesafe.scalalogging.LazyLogging
import java.lang.Thread.UncaughtExceptionHandler
import org.slf4j.bridge.SLF4JBridgeHandler

/**
 * Utility class with functionality related to logging.
 *
 * @author Martijn van de Rijdt
 */
object Logging extends LazyLogging {
  /** Logs some version info. */
  def logVersionInfo(): Unit = {
    logger.info("Application version: {}", retrieveVersion)
    logger.info("Classpath: {}", System.getProperty("java.class.path"))
    logger.info("Library path: {}", System.getProperty("java.library.path"))
    logger.info("Java vendor: {}", System.getProperty("java.vendor"))
    logger.info("Java version: {}", System.getProperty("java.version"))
    logger.info("OS name: {}", System.getProperty("os.name"))
    logger.info("OS version: {}", System.getProperty("os.version"))
    logger.info("OS architecture: {}", System.getProperty("os.arch"))
  }

  /**
   * Returns the version number from the jar manifest file.
   *
   * @return version number if available
   */
  private def retrieveVersion(): String = getClass.getPackage.getImplementationVersion

  /** Sets an uncaught exception handler, which logs all exceptions, on the current thread. */
  def setUncaughtExceptionHandler(): Unit = {
    logger.info("Installing uncaught exception handler.");
    Thread.currentThread().setUncaughtExceptionHandler(Handler);
  }

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