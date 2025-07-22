package org.minesweeper.graphics;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Board extends JPanel {
    private Cell[][] cells;
    private static final int cellSize = 100;

    public Board(int rows, int cols, Cell[][] cells) {
        this.cells = cells;
        setMinimumSize(new Dimension(rows * cellSize, cols * cellSize));
        setMaximumSize(new Dimension(rows * cellSize, cols * cellSize));
        setPreferredSize(new Dimension(rows * cellSize, cols * cellSize));
        setLayout(null);
    }
}
