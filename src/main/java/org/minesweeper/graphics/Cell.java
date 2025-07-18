package org.minesweeper.graphics;

import javax.swing.JButton;

public class Cell extends JButton {

    private boolean isMine;
    private boolean isRevealed;
    private boolean isFlagged;

    public Cell() {
        setText("Mine");
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

}
