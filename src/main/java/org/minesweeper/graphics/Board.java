package org.minesweeper.graphics;

import org.minesweeper.logic.Logic;
import java.awt.*;

import javax.swing.*;

public class Board extends JPanel {

    private final Logic logic;
    private final Cell[][] cells;

    public Board(int rows, int cols, Logic logic) {
        this.logic = logic;
        this.cells = new Cell[rows][cols];
        setLayout(new GridLayout(rows, cols));
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                this.cells[i][j] = new Cell(i, j, logic);
//                add(cells[i][j]);
//            }
//        }

        Thread[] threads = new Thread[rows];

        // Create cells in parallel (but don't add to UI yet)
        for (int i = 0; i < rows; i++) {
            final int row = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < cols; j++) {
                    cells[row][j] = new Cell(row, j, logic);
                }
            });
            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Now update the UI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    add(cells[i][j]);
                }
            }
            revalidate();  // Optional: re-layout the container
            repaint();     // Optional: refresh the display
        });
    }

    public Cell[][] getCells(){
        return cells;
    }
}
