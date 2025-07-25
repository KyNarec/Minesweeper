package org.minesweeper.graphics;

import javax.swing.*;

public class MainMenu extends JMenuBar {

    public MainMenu(GUI gui){
        JMenu play = new JMenu("Play");
        JMenu options = new JMenu("Options");

        JMenuItem setSize = new JMenuItem("Set size of game");
        setSize.addActionListener(ev -> gui.setSizeScreen());
        options.add(setSize);

        JMenuItem setBombs = new JMenuItem("Set amount of bombs");
        setBombs.addActionListener(ev -> gui.setBombsScreen());
        options.add(setBombs);

        add(play);
        add(options);
    }
}
