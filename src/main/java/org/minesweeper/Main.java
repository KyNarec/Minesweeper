package org.minesweeper;

import javax.swing.SwingUtilities;

import org.minesweeper.graphics.GUI;
import org.minesweeper.logic.Logic;

public class Main {
  public static void main(String[] args) {
    Logic logic = new Logic(10, 3);
    SwingUtilities.invokeLater(() -> {
      GUI gui = new GUI(logic);
      gui.setVisible(true);
    });
  }
}
