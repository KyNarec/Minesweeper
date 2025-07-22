package org.minesweeper.logic;

import org.minesweeper.graphics.Board;
import org.minesweeper.graphics.Cell;

import java.util.Random;

public class Logic {

    //has been opened
    private boolean[][] open;

    //field is a bomb
    private boolean[][] bomb;

    //score
    private int score;

    //start game
    private boolean firstclick;

    //amount of bombs
    private int nbomb;

    //size of the field
    private int sizeX;
    private int sizeY;


    //reference to cells for use in logic
    private Cell[][] cells;

    public Logic(int nSizeX, int nSizeY, int bombs , Cell[][] ncells){

        //setting of variables
        open = new boolean[nSizeX][nSizeY];
        bomb = new boolean[nSizeX][nSizeY];
        nbomb = bombs;
        this.sizeX = nSizeX;
        this.sizeY = nSizeY;
        firstclick = true;
        cells = ncells;


        //setting field to false
        for (int i = 0; i < nSizeX; i++) {
            for (int j = 0; j < nSizeY; j++) {
                open[i][j] = false;
            }
        }

    }



    public void leftClick(int x , int y) {
        if (open[x][y]) {
        } else{
            open[x][y] = true;
            if (firstclick) {
                firstclick = true;
                generation(x , y);
            } else {
                if (bomb[x][y]) {
                    lost(x , y);
                } else {
                    int attachedBombs = countBombs(x, y);
                    if(attachedBombs == 0){
                        recOpen(x , y);
                    }else{
                        if(attachedBombs == 1){
                            cells[x][y].setState(Cell.State.ONE);
                        }
                        if(attachedBombs == 2){
                            cells[x][y].setState(Cell.State.TWO);
                        }
                        if(attachedBombs == 3){
                            cells[x][y].setState(Cell.State.THREE);
                        }
                        if(attachedBombs == 4){
                            cells[x][y].setState(Cell.State.FOUR);
                        }
                        if(attachedBombs == 5){
                            cells[x][y].setState(Cell.State.FIVE);
                        }
                        if(attachedBombs == 6){
                            cells[x][y].setState(Cell.State.SIX);
                        }
                        if(attachedBombs == 7){
                            cells[x][y].setState(Cell.State.SEVEN);
                        }
                        if(attachedBombs == 8){
                            cells[x][y].setState(Cell.State.EIGHT);
                        }
                    }


                }
            }


        }
    }


    public void rightClick(int x, int y){
        if(open[x][y]){
        }else{
            if(cells[x][y].getState() == Cell.State.FLAGGED){
                cells[x][y].setState(Cell.State.UNKNOWN);
            }else{
                cells[x][y].setState(Cell.State.FLAGGED);
            }
        }
    }


    public int countBombs(int x, int y){
        int numBombs = 0;
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(x+i >= 0 && x+i < sizeX && y+j >= 0 && y+j < sizeY){
                    numBombs++;
                }
            }
        }
        return numBombs;
    }

    public void lost(int x ,int y){
        cells[x][y].setState(Cell.State.MINE);
        cells[x][y].isMine();
        for(int i = 0; i<= sizeX; i++){
            for(int j = 0; j<= sizeY; j++){
                if(bomb[i][j] == true){
                    cells[i][j].setState(Cell.State.MINE);
                }
            }
        }
    }

    public void recOpen(int x , int y){
        if(open[x][y]== false) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (x + i >= 0 && x + i < sizeX && y + j >= 0 && y + j < sizeY) {
                        int attachedbombs = countBombs(i, j);
                        if (attachedbombs == 0) {
                            recOpen(i, j);
                            open[i][j] = true;
                            cells[i][j].setState(Cell.State.ZERO);
                        }
                    }
                }
            }
        }
    }

    private void generation(int pressedX, int pressedY){
        //setting up local variabels
        int placedMines = 0;
        Random r = new Random();

        //Making it by a random position with java.util.Random
        while (placedMines <= nbomb){

            //What field to place a mine
            int x = r.nextInt(this.sizeX);
            int y = r.nextInt(this.sizeY);

            //Checking if there is already a bomb and if true placing it
            if (!this.bomb[x][y]&&this.open[x][y]){
                this.bomb[x][y] = true;
                placedMines++;
            }
        }
    }
}
