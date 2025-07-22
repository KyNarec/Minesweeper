package org.minesweeper.graphics;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JMenuBar {

    public MainMenu(){
        add(new JMenu("Play"));
        add(new JMenu("Options"));
    }
}
