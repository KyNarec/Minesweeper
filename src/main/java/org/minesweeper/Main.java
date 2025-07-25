package org.minesweeper;

import javax.swing.SwingUtilities;

import org.minesweeper.graphics.GUI;
import org.minesweeper.logic.Logic;

public class Main {
  public static void main(String[] args) {
    int sizeX = 10;
    int sizeY = 10;
    int bombs = 15;

    Logic logic = new Logic(sizeX, sizeY, bombs);
    SwingUtilities.invokeLater(() -> {
      GUI gui = new GUI(logic);
      logic.setGUI(gui);
      gui.setVisible(true);

      logic.setCells(gui.getCells());
    });

  }
}
