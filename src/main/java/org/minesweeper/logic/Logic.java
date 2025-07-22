package org.minesweeper.logic;
import java.util.Random

public class Logic {

    //has been opened
    private boolean[][] open;

    //field is a bomb
    private boolean[][] bomb;

    //score
    private int score;

    //amount of bombs
    private int nbomb;

    //size of the field
    private int sizeX;
    private int sizeY;


    public Logic(int nSizeX, int nSizeY, int bombs){

        //setting of variables
        open = new boolean[size][size];
        bomb = new boolean[size][size];
        nbomb = bombs;
        this.sizeX = nSizeX;
        this.sizeY = nSizeY;

        //setting field to false
        for(int i = 0;i<size;i++){
            for(int j = 0;j<size;j++){
                open[i][j] = false;
            }
        }


    }

    private void generation(int pressedX, int pressedY){
        //setting up local variabels
        int placedMines = 0;
        int fields = this.sizeX*this.sizeY;

        //Making it by a random position with java.util.Random
        while (placedMines <= nbomb){

            //What field to place a mine
            int x = randomNumbers.nextInt(this.sizeX);
            int y = randomNumbers.nextInt(this.sizeY);

            //Checking if there is already a bomb and if true placing it
            if (!this.bomb[x][y]){
                this.bomb[x][y] = true;
                placedMines++;
            }
        }
    }
}
