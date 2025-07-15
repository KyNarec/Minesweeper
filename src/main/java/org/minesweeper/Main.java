package org.minesweeper;

import javax.swing.SwingUtilities;

import org.minesweeper.graphics.GUI;

public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      GUI gui = new GUI();
      gui.setVisible(true);
    });
  }
}
