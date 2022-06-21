package view.userintefaces;

/**
 * Filename : GameWindow.java
 * Programmer : D'Riski Maulana
 * Date : 10 June 2022
 * Email : driskimaulana@upi.edu
 * Website : driskimaulana.c120.me
 * Deskripsi : frame for game menu
 */

import java.awt.Color;

 import javax.swing.JFrame;

 public class GameWindow extends JFrame{

    // attribute
    public GameMenu gameMenu;

    public GameWindow()
    {
        super("The Survive Hop");
        setSize(400, 320);
        setLocation(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBackground(Color.white);
    }

}