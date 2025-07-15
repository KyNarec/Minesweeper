package org.minesweeper.graphics;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GUI extends JFrame {
    public GUI() {
        setTitle("Minesweeper");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        Board board = new Board(6, 6);
        add(board, BorderLayout.CENTER);
        pack();
    }
}