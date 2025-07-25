package org.minesweeper.logic;

import org.minesweeper.graphics.Cell;
import org.minesweeper.graphics.State;
import org.minesweeper.graphics.GUI;

import java.util.Random;
import java.util.Arrays;

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

  /**
   * Specifies the gui, so that it can be called to display the win or lose
   * screen.
   */
  private GUI gui;

  public Logic(int nSizeX, int nSizeY, int bombs) {

    // setting of variables
    open = new boolean[nSizeX][nSizeY];
    bomb = new boolean[nSizeX][nSizeY];
    nBomb = bombs;
    this.sizeX = nSizeX;
    this.sizeY = nSizeY;
    firstClick = true;

    // setting field to false
    for (int i = 0; i < nSizeX; i++) {
      for (int j = 0; j < nSizeY; j++) {
        open[i][j] = false;
      }
    }
  }

  /**
   * Sets the cells variable so that the logic can reference cells on its own.
   * 
   * @param newCells a list of cells
   */
  public void setCells(Cell[][] newCells) {
    this.cells = newCells;
  }

  /**
   * Sets the gui value, so that it can be used to call the win / lose screen.
   * 
   * @param gui the gui
   */
  public void setGUI(GUI gui) {
    //System.out.println("Set gui");
    this.gui = gui;
  }

  public void leftClick(int x, int y) {
    if (!open[x][y] && cells[x][y].getState() != State.FLAGGED) {
      if (firstClick) {
        firstClick = false;
        generation();
      }
      if (bomb[x][y]) {
        lost(x, y);
        System.out.println("You clicked on a bomb. GAME OVER!");
      } else {
        int attachedBombs = countBombs(x, y);
        if (attachedBombs == 0) {
          cells[x][y].setState(State.ZERO);
          recOpen(x, y);
          System.out.println("Attached bombs are 0");
        } else {
          System.out.println("Switching attached Bombs. attachedBombs: " + attachedBombs);
          changeStateByAttachedBombs(x, y, attachedBombs);
        }
      }
      open[x][y] = true;
    } else
      System.out.println("Cell is already open");
    if (arraysAreFlipped(bomb, open)) win();
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
        if (x + i >= 0 && x + i < sizeX && y + j >= 0 && y + j < sizeY && checkBomb(x + i, y + j)) {
          numBombs++;
          System.out.println("attachedBombs++. New attachedBombs count: " + numBombs);
        }
      }
    }
    return numBombs;
  }

  public boolean checkBomb(int x, int y) {
    try {
      if (bomb[x][y])
        return true;
    } catch (Exception e) {
      return false;
    }
    return false;
  }

  public void lost(int x, int y) {
    cells[x][y].setState(State.MINE);
    // cells[x][y].isMine();
    for (int i = 0; i < sizeX; i++) {
      for (int j = 0; j < sizeY; j++) {
        if (bomb[i][j]) {
          cells[i][j].setState(State.MINE);
          // leftClick(i, j);
        }
      }
    }
    gui.displayLoseScreen();
  }

  public void recOpen(int x, int y) {
    open[x][y] = true;
    cells[x][y].setState(State.ZERO);

    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        int nX = x + i;
        int nY = y + j;
        if (nX >= 0 && nX < sizeX && nY >= 0 && nY < sizeY && !open[nX][nY]) {
          int attachedBombs = countBombs(nX, nY);
          if (attachedBombs == 0) {
            recOpen(nX, nY);
          } else {
            open[nX][nY] = true;
            changeStateByAttachedBombs(nX, nY, attachedBombs);
          }
        }
      }
    }
  }

  public void changeStateByAttachedBombs(int x, int y, int attachedBombs) {
    switch (attachedBombs) {
      case 0:
        cells[x][y].setState(State.ZERO);
        break;
      case 1:
        cells[x][y].setState(State.ONE);
        break;
      case 2:
        cells[x][y].setState(State.TWO);
        break;
      case 3:
        cells[x][y].setState(State.THREE);
        break;
      case 4:
        cells[x][y].setState(State.FOUR);
        break;
      case 5:
        cells[x][y].setState(State.FIVE);
        break;
      case 6:
        cells[x][y].setState(State.SIX);
        break;
      case 7:
        cells[x][y].setState(State.SEVEN);
        break;
      case 8:
        cells[x][y].setState(State.EIGHT);
        break;
    }
  }

  private void generation() {
    // setting up local variables
    int placedMines = 0;
    Random r = new Random();

    // Making it by a random position with java.util.Random
    while (placedMines < nBomb) {

      // What field to place a mine
      int x = r.nextInt(this.sizeX);
      int y = r.nextInt(this.sizeY);

      System.out.println("Generating a mine");

      // Checking if there is already a bomb and if true placing it
      if (!this.bomb[x][y] && !this.open[x][y]) {
        this.bomb[x][y] = true;
        placedMines++;
      }
    }
  }

  public void win() {
    try {
      gui.displayWinScreen();
    } catch (Exception e) {
      System.err.println(e.toString());
    }
  }

  public boolean arraysAreFlipped(boolean[][] a, boolean[][] b) {
    if (a.length != b.length)
      return false;

    for (int i = 0; i < a.length; i++) {
      if (a[i].length != b[i].length)
        return false;

      for (int j = 0; j < a[i].length; j++) {
        if (a[i][j] == b[i][j])
          return false; // same value means not flipped
      }
    }

    return true;
  }
}
