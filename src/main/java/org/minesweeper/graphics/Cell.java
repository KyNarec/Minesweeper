package org.minesweeper.graphics;

import javax.swing.JButton;

public class Cell extends JButton {

    private boolean isMine;
    private boolean isRevealed;
    private boolean isFlagged;
    private int adjacentMines;
    
    public Cell() {
        setText("Mine");

    }


 
}
