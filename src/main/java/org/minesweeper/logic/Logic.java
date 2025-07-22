package org.minesweeper.logic;

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

    }
}
