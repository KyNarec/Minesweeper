package org.minesweeper.graphics;

import org.minesweeper.logic.Logic;

import javax.swing.JButton;

public class Cell extends JButton {

    public enum State {
       UNKNOWN,
       FLAGGED,
        MINE,
        ZERO,
       ONE,
       TWO,
       THREE,
       FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT
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
        setupListeners();
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

    public void setupListeners() {
        this.addActionListener(e -> setState(State.UNKNOWN));
  }
}
