package org.minesweeper.graphics;

import org.minesweeper.logic.Logic;

import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {

    private final Logic logic;
    private boolean isMine;
    private State state;
    private final int x;
    private final int y;

    public Cell(int x, int y, Logic logic) {
        this.x = x;
        this.y = y;
        setSize(new Dimension(30, 30));
//        setIcon("src/main/resources/hidden.jpg");
        setState(State.UNKNOWN);
        setIconTextGap(0);
        setBorderPainted(false);
        setContentAreaFilled(false);
        //this.state = State.UNKNOWN;
        this.logic = logic;
        setupListeners();
    }

    /**
     * @deprecated
     * @return true, if the cell is a mine
     */
    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    /**
     * Returns the state of the cell.
     * @return state of the cell.
     */
    public State getState() {
        return state;
    }

    /**
     * Sets the state of the Cell and displays that state.
     * @param state state that will be set and displayed
     */
    public void setState(State state) {
        System.out.println("Cell x = " +
                x +
                " and y = " +
                y +
                " has called to set its state to " +
                state.name());
        this.state = state;
        displayState(state);
    }

    public void setupListeners() {
        this.addActionListener(e -> {
            logic.leftClick(x, y);
            System.out.println("Cell x = " +
                    x +
                    " and y = " +
                    y +
                    " has been clicked");
        });
    }

    /**
     * Tell the Cell to display a state.
     * @param state state that you want to display
     */
    public void displayState(State state) {
        switch (state) {
            case UNKNOWN -> setIcon("src/main/resources/hidden.jpg");
            case ONE -> setIcon("src/main/resources/one.jpg");
            case TWO -> setIcon("src/main/resources/two.jpg");
            case THREE -> setIcon("src/main/resources/three.jpg");
            case FOUR -> setIcon("src/main/resources/four.jpg");
            case FIVE -> setIcon("src/main/resources/five.jpg");
            case SIX -> setIcon("src/main/resources/six.jpg");
            case SEVEN -> setIcon("src/main/resources/seven.jpg");
            case EIGHT -> setIcon("src/main/resources/eight.jpg");
            case MINE -> setIcon("src/main/resources/bomb.jpg");
            case FLAGGED -> setIcon("src/main/resources/flag.jpg");
            // TODO: make icon for zero and insert here
            case ZERO -> setIcon("src/main/resources/flag.png");
        }
    }

    /**
     * Sets an icon as the icon of the cell.
     * @param iconPath path to the Icon
     */
    public void setIcon(String iconPath) {
        setIcon(new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
    }
}
