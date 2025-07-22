package org.minesweeper.graphics;

import org.minesweeper.logic.Logic;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GUI extends JFrame {

  private Logic logic;
  public GUI(Logic logic) {
    setTitle("Minesweeper");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(600, 600);
    setResizable(false);
    Board board = new Board(6, 6, logic);
    add(board, BorderLayout.CENTER);
    pack();
  }
}
