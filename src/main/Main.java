package main;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args){

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Itochi");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); //Causes the window to be the preferred size and layouts of its subcomponents = GamePanel



        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
