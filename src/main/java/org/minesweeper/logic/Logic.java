package org.minesweeper.logic;

import org.minesweeper.graphics.Cell;
import org.minesweeper.graphics.State;

import java.util.Random;

public class Logic {

    /**
     * Field that holds information on whether a cell is open or not.
     */
    private final boolean[][] open;

    /**
     * Field that holds information on where the bombs are.
     */
    private final boolean[][] bomb;

    /**
     * Tracks the score.
     */
    private int score;

    /**
     * Tracks whether this was the first click or not.
     */
    private boolean firstClick;

    /**
     * Specifies the amount of bombs in the game.
     */
    private final int nBomb;

    /**
     * Specifies the size of the window horizontally.
     */
    private final int sizeX;

    /**
     * Specifies the size of the window vertically.
     */
    private final int sizeY;

    /**
     * Field that holds all the cells in the game and is able to reference them.
     */
    private Cell[][] cells;

    public Logic(int nSizeX, int nSizeY, int bombs) {

        //setting of variables
        open = new boolean[nSizeX][nSizeY];
        bomb = new boolean[nSizeX][nSizeY];
        nBomb = bombs;
        this.sizeX = nSizeX;
        this.sizeY = nSizeY;
        firstClick = true;


        //setting field to false
        for (int i = 0; i < nSizeX; i++) {
            for (int j = 0; j < nSizeY; j++) {
                open[i][j] = false;
            }
        }

    }

    /**
     * Sets the cells variable so that the logic can reference cells on its own.
     * @param newCells a list of cells
     */
    public void setCells(Cell[][] newCells) {
        this.cells = newCells;
    }


    public void leftClick(int x, int y) {
        if (!open[x][y]) {
            open[x][y] = true;
            if (firstClick) {
                firstClick = false;
                generation();
            } else {
                if (bomb[x][y]) {
                    lost(x, y);
                    System.out.println("You clicked on a bomb. GAME OVER!");
                } else {
                    int attachedBombs = countBombs(x, y);
                    if (attachedBombs == 0) {
                        recOpen(x, y);
                        System.out.println("Attached bombs are 0");
                    } else {
                        System.out.println("Switching attached Bombs. attachedBombs: " + attachedBombs);
                        switch (attachedBombs) {
                            case 1:
                                cells[x][y].setState(State.ONE);
                            case 2:
                                cells[x][y].setState(State.TWO);
                            case 3:
                                cells[x][y].setState(State.THREE);
                            case 4:
                                cells[x][y].setState(State.FOUR);
                            case 5:
                                cells[x][y].setState(State.FIVE);
                            case 6:
                                cells[x][y].setState(State.SIX);
                            case 7:
                                cells[x][y].setState(State.SEVEN);
                            case 8:
                                cells[x][y].setState(State.EIGHT);
                        }
                    }
                }
            }
        }
    }


    public void rightClick(int x, int y) {
        if (open[x][y]) {
        } else {
            if (cells[x][y].getState() == State.FLAGGED) {
                cells[x][y].setState(State.UNKNOWN);
            } else {
                cells[x][y].setState(State.FLAGGED);
            }
        }
    }


    public int countBombs(int x, int y) {
        int numBombs = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (x + i >= 0 && x + i < sizeX && y + j >= 0 && y + j < sizeY) {
                    numBombs++;
                    System.out.println("attachedBombs++. New attachedBombs count: " + numBombs);
                }
            }
        }
        return numBombs;
    }

    public void lost(int x, int y) {
        cells[x][y].setState(State.MINE);
        //cells[x][y].isMine();
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (bomb[i][j]) {
                    cells[i][j].setState(State.MINE);
                }
            }
        }
    }

    public void recOpen(int x, int y) {
        if (!open[x][y]) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (x + i >= 0 && x + i < sizeX && y + j >= 0 && y + j < sizeY) {
                        int attachedBombs = countBombs(i, j);
                        if (attachedBombs == 0) {
                            recOpen(i, j);
                            open[i][j] = true;
                            cells[i][j].setState(State.ZERO);
                            System.out.println("Cell x = " +
                                    i +
                                    " and y = " +
                                    j +
                                    " has now the state ZERO");
                        }
                    }
                }
            }
        }
    }

    private void generation() {
        //setting up local variables
        int placedMines = 0;
        Random r = new Random();

        //Making it by a random position with java.util.Random
        while (placedMines <= nBomb) {

            //What field to place a mine
            int x = r.nextInt(this.sizeX - 1);
            int y = r.nextInt(this.sizeY - 1);

            System.out.println("Generating a mine");

            //Checking if there is already a bomb and if true placing it
            if (!this.bomb[x][y] && !this.open[x][y]) {
                this.bomb[x][y] = true;
                placedMines++;
            }
        }
    }
}
