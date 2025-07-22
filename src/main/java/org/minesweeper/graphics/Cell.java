package org.minesweeper.graphics;

import javax.swing.JButton;

public class Cell extends JButton {


    private enum State {
       UNKNOWN,
       FLAGGED,
       ONE,
       TWO,
       THREE,
       FOUR
    }
    private boolean isRevealed;
    private boolean isMine;
    private State state;

    public Cell() {
        setText("Mine");
        this.state = State.UNKNOWN;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }


    public State getState() {
        return state;
    }
 
    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public void setState(State state) {
        this.state = state;
    }
}
