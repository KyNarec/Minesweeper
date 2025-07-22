package org.minesweeper.graphics;

import org.minesweeper.logic.Logic;

import java.awt.*;

import javax.swing.*;

public class GUI extends JFrame {

  private Logic logic;
  private final Board board;
  public GUI(Logic logic) {
    setTitle("Minesweeper");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(600, 600);
    setResizable(false);
    setLocationRelativeTo(null);

    setJMenuBar(new MainMenu());

    board = new Board(10, 10, logic);
    add(board);

  }

  public Cell[][] getCells() {
    return board.getCells();
  }
}
