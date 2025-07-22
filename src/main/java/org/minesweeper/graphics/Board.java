package org.minesweeper.graphics;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Board extends JPanel {
    private Cell[][] cells;
    private static final int cellSize = 100;

    public Board(int rows, int cols) {
        setMinimumSize(new Dimension(rows * cellSize, cols * cellSize));
        setMaximumSize(new Dimension(rows * cellSize, cols * cellSize));
        setPreferredSize(new Dimension(rows * cellSize, cols * cellSize));
        setLayout(null);
        cells = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new Cell();
                cells[i][j].setBounds(i * cellSize, j * cellSize, cellSize, cellSize);
                add(cells[i][j]);
            }
        }
    }

    public Cell[][] getCells(){
        return cells;
    }
}
