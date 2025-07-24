package org.minesweeper.graphics;

import org.minesweeper.Main;

import javax.swing.*;
import java.awt.*;

public class WinScreen extends JFrame {

    public WinScreen() {
        setTitle("You won!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 150);
        setResizable(false);
        setLocationRelativeTo(null);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel winText = new JLabel("You won, Congratulations!");
        winText.setAlignmentX(Component.CENTER_ALIGNMENT);
        winText.setBorder(BorderFactory.createEmptyBorder(50, 0, 30, 0));

        JButton playAgain = getPlayAgain();

        add(winText);
        add(Box.createVerticalGlue()); // Pushes content to center
        add(playAgain);
        add(Box.createVerticalGlue()); // Adds space below button
        setVisible(true);
        repaint();
    }

    private JButton getPlayAgain() {
        JButton playAgain = new JButton("Play again");
        playAgain.setAlignmentX(Component.CENTER_ALIGNMENT);
        playAgain.setMaximumSize(new Dimension(200, 60));
        playAgain.setPreferredSize(new Dimension(200, 60));

        playAgain.addActionListener(e -> {
            dispose(); // Close current window

            // Close all other windows
            Window[] windows = Window.getWindows();
            for (Window window : windows) {
                window.dispose();
            }

            // Call main method directly
            Main.main(new String[]{});
        });
        return playAgain;
    }
}
