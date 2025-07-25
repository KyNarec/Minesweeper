package org.minesweeper.graphics;

import org.minesweeper.Main;
import org.minesweeper.logic.Logic;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private final Logic logic;
    private final Board board;

    public GUI(int sizeX, int sizeY, Logic logic) {
        setTitle("Minesweeper");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(60 * sizeX, 60 * sizeY);
        setResizable(false);
        setLocationRelativeTo(null);

        setJMenuBar(new MainMenu(this));
        this.logic = logic;
        this.board = new Board(sizeX, sizeY, logic);
        add(board);

    }

    public Cell[][] getCells() {
        return board.getCells();
    }

    public void displayWinScreen() {
        add(new WinScreen());
    }

    public void displayLoseScreen() {
        add(new LoseScreen());
    }

    public void setSizeScreen() {
        JFrame setSizeScreen = new JFrame();
        setSizeScreen.setTitle("Set size of Game");
        setSizeScreen.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSizeScreen.setSize(300, 150);
        setSizeScreen.setResizable(false);
        setSizeScreen.setLocationRelativeTo(null);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        String s = JOptionPane.showInputDialog(
                setSizeScreen,
                "Set the size of the game",
                "Set size",
                JOptionPane.PLAIN_MESSAGE);

        if ((s != null) && (s.length() > 0) && isNumeric(s)) {
            System.out.println("Setting size to: " + s);
            Main.sizeX = Integer.parseInt(s);
            Main.sizeY = Integer.parseInt(s);
            newMainClass();
        } else setSizeScreen();
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void newMainClass(){
        dispose(); // Close current window

        // Close all other windows
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            window.dispose();
        }

        // Call main method directly
        Main.main(new String[]{});
    }

    public void setBombsScreen() {
        JFrame setSizeScreen = new JFrame();
        setSizeScreen.setTitle("Set amount of bombs");
        setSizeScreen.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSizeScreen.setSize(300, 150);
        setSizeScreen.setResizable(false);
        setSizeScreen.setLocationRelativeTo(null);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        String s = JOptionPane.showInputDialog(
                setSizeScreen,
                "Set the desired amount of bombs",
                "Set amount of bombs",
                JOptionPane.PLAIN_MESSAGE);

        if ((s != null) && (s.length() > 0) && isNumeric(s)) {
            System.out.println("Setting amount of bombs to: " + s);
            Main.bombs = Integer.parseInt(s);
            newMainClass();
        } else setBombsScreen();
    }
}
