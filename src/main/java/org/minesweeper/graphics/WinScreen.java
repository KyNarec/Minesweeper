package org.minesweeper.graphics;

import javax.swing.*;

public class WinScreen extends JFrame {

  private JLabel winText;

  public WinScreen(){
    setTitle("You won!");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(300, 150);
    setResizable(false);
    setLocationRelativeTo(null);

    winText = new JLabel("You won, Congratulations!", SwingConstants.CENTER);
    
    add(winText);
    setVisible(true);
    repaint();
  }
}
