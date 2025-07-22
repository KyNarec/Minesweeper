package org.minesweeper.logic;

import org.minesweeper.graphics.Cell;
import org.minesweeper.graphics.State;

import java.util.Random;

public class Logic {

    //has been opened
    private final boolean[][] open;

    //field is a bomb
    private final boolean[][] bomb;

    //score
    private int score;

    //start game
    private boolean firstClick;

    //amount of bombs
    private final int nBomb;

    //size of the field
    private final int sizeX;
    private final int sizeY;


    //reference to cells for use in logic
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

    public void setCells(Cell[][] ncells) {
        this.cells = ncells;
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
                } else {
                    int attachedBombs = countBombs(x, y);
                    if (attachedBombs == 0) {
                        recOpen(x, y);
                    } else {
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
                }
            }
        }
        return numBombs;
    }

    public void lost(int x, int y) {
        cells[x][y].setState(State.MINE);
        cells[x][y].isMine();
        for (int i = 0; i <= sizeX; i++) {
            for (int j = 0; j <= sizeY; j++) {
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
                        int attachedbombs = countBombs(i, j);
                        if (attachedbombs == 0) {
                            recOpen(i, j);
                            open[i][j] = true;
                            cells[i][j].setState(State.ZERO);
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

            System.out.println("this is generation");

            //Checking if there is already a bomb and if true placing it
            if (!this.bomb[x][y] && !this.open[x][y]) {
                this.bomb[x][y] = true;
                placedMines++;
            }
        }
    }

}
