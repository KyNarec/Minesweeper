package org.minesweeper.graphics;

import org.minesweeper.logic.Logic;

import java.awt.*;

import javax.swing.JPanel;

public class Board extends JPanel {

    private Logic logic;
    private Cell[][] cells;
    private static final int cellSize = 100;

    public Board(int rows, int cols, Logic logic) {
        this.logic = logic;
        this.cells = new Cell[rows][cols];
        setMinimumSize(new Dimension(rows * cellSize, cols * cellSize));
        setMaximumSize(new Dimension(rows * cellSize, cols * cellSize));
        setPreferredSize(new Dimension(rows * cellSize, cols * cellSize));
        setLayout(new GridLayout(rows, cols));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.cells[i][j] = new Cell(logic);
                add(cells[i][j]);
            }
        }
    }
}
