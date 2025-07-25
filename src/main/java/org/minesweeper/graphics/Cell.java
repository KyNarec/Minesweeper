package org.minesweeper.graphics;

import org.minesweeper.logic.Logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

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
        // setIcon("src/main/resources/hidden.jpg");
        setState(State.UNKNOWN);
        setIconTextGap(0);
        setBorderPainted(false);
        setContentAreaFilled(false);
        // this.state = State.UNKNOWN;
        this.logic = logic;
        setupListeners();
    }

    /**
     * @return true, if the cell is a mine
     * @deprecated
     */
    @Deprecated
    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    /**
     * Returns the state of the cell.
     *
     * @return state of the cell.
     */
    public State getState() {
        return state;
    }

    /**
     * Sets the state of the Cell and displays that state.
     *
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
    
    /**
     * Filters left and right click on the cell.
     */
    public void setupListeners() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    logic.leftClick(x, y);
                    System.out.println("Cell x = " +
                            x +
                            " and y = " +
                            y +
                            " has been LEFT clicked");
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    logic.rightClick(x, y);
                    System.out.println("Cell x = " +
                            x +
                            " and y = " +
                            y +
                            " has been RIGHT clicked");
                }
            }
        });
    }

    /**
     * Tell the Cell to display a state.
     *
     * @param state state that you want to display
     */
    public void displayState(State state) {
        switch (state) {
            case UNKNOWN -> setIcon("/images/hidden.jpg");
            case ONE -> setIcon("/images/one.jpg");
            case TWO -> setIcon("/images/two.jpg");
            case THREE -> setIcon("/images/three.jpg");
            case FOUR -> setIcon("/images/four.jpg");
            case FIVE -> setIcon("/images/five.jpg");
            case SIX -> setIcon("/images/six.jpg");
            case SEVEN -> setIcon("/images/seven.jpg");
            case EIGHT -> setIcon("/images/eight.jpg");
            case MINE -> setIcon("/images/bomb.jpg");
            case FLAGGED -> setIcon("/images/flag.jpg");
            case ZERO -> setIcon("/images/revealed.jpg");

        }
    }

    /**
     * Sets an icon as the icon of the cell.
     *
     * @param iconPath path to the Icon
     */
    public void setIcon(String iconPath) {
        // ImageIcon(iconPath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        try {
            URL imageUrl = getClass().getResource(iconPath);
            if (imageUrl != null) {
                ImageIcon originalIcon = new ImageIcon(imageUrl);
                Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                setIcon(new ImageIcon(scaledImage));
            }
        } catch (Exception e) {
            System.err.println("Could not load image: " + iconPath);
        }
    }
}
