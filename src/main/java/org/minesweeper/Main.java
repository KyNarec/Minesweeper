package org.minesweeper;

import javax.swing.SwingUtilities;

import org.minesweeper.graphics.GUI;
import org.minesweeper.logic.Logic;

public class Main {
  public static int sizeX = 10;
  public static int sizeY = 10;
  public static int bombs = 5;
  
  public static void main(String[] args) {

    Logic logic = new Logic(sizeX, sizeY, bombs);
    SwingUtilities.invokeLater(() -> {
      GUI gui = new GUI(sizeX, sizeY, logic);
      logic.setGUI(gui);
      gui.setVisible(true);

      logic.setCells(gui.getCells());
    });

  }
}
