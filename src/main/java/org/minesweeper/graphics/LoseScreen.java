package org.minesweeper.graphics;

import org.minesweeper.Main;

import javax.swing.*;
import java.awt.*;

public class LoseScreen extends JFrame {

    public LoseScreen() {
        setTitle("You lost!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 150);
        setResizable(false);
        setLocationRelativeTo(null);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel loseText = new JLabel("You lost! What a disappointment!");
        loseText.setAlignmentX(Component.CENTER_ALIGNMENT);
        loseText.setBorder(BorderFactory.createEmptyBorder(50, 0, 30, 0));

        JButton retry = getRetry();

        add(loseText);
        add(Box.createVerticalGlue()); // Pushes content to center
        add(retry);
        add(Box.createVerticalGlue()); // Adds space below button
        setVisible(true);
        repaint();
    }

    private JButton getRetry() {
        JButton retry = new JButton("Retry");
        retry.setAlignmentX(Component.CENTER_ALIGNMENT);
        retry.setMaximumSize(new Dimension(200, 60));
        retry.setPreferredSize(new Dimension(200, 60));

        retry.addActionListener(e -> {
            dispose(); // Close current window

            // Close all other windows
            Window[] windows = Window.getWindows();
            for (Window window : windows) {
                window.dispose();
            }

            // Call main method directly
            Main.main(new String[]{});
        });
        return retry;
    }
}
