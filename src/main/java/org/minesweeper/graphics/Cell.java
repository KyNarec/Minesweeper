package org.minesweeper.graphics;

import org.minesweeper.logic.Logic;

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

    private Logic logic;
    private boolean isMine;
    private State state;
    private int x;
    private int y;

    public Cell(Logic logic) {
        setText("Mine");
        this.state = State.UNKNOWN;
        this.logic = logic;
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
 
    public void setState(State state) {
        this.state = state;
    }
}
