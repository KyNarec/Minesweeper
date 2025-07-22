package org.minesweeper.graphics;

import org.minesweeper.logic.Logic;

import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {

    private Logic logic;
    private boolean isMine;
    private State state;
    private int x;
    private int y;

    public Cell(Logic logic) {
        setSize(new Dimension(30, 30));
        setIcon(new ImageIcon(new ImageIcon("src/main/resources/hidden.jpg").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        setIconTextGap(0);
        setBorderPainted(false);
        setContentAreaFilled(false);
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
        //this.addActionListener(e -> logic.leftClick(x, y));
    }
}
