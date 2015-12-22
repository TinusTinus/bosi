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
package nl.mvdr.breakout

import javafx.application.Application
import nl.mvdr.breakout.gui.BreakoutApplication
import nl.mvdr.breakout.logging.BreakoutLogging

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
    BreakoutLogging.logVersionInfo()
    BreakoutLogging.installSlf4jBridge()
    Application.launch(classOf[BreakoutApplication])
  }
}