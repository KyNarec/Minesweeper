package org.minesweeper.graphics;

import javax.swing.*;

public class LoseScreen extends JFrame{

  private JLabel loseText;

  public LoseScreen(){
    setTitle("You lost!");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(300, 150);
    setResizable(false);
    setLocationRelativeTo(null);

    loseText = new JLabel("You lost! What a dissapointment!", SwingConstants.CENTER);

    add(loseText);
    setVisible(true);
    repaint();
  }
  
}
