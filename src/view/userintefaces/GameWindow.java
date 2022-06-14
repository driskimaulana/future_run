package view.userintefaces;

import java.awt.Color;

/**
 * GameWindow
 */

 import javax.swing.JFrame;

import presenter.MouseInput;

 public class GameWindow extends JFrame{

    public GameScreen gameScreen;

    public GameMenu gameMenu;

    public GameWindow()
    {
        super("The Survive Hop");

        setSize(400, 320);
        setLocation(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        this.addMouseListener(new MouseInput());

        setBackground(Color.white);
    }

    public void startGame()
    {
        // gameScreen.startGame();
    }
    
    public static void main(String[] args) {
        // GameWindow gw = new GameWindow();
        // gw.setVisible(true);
        // gw.pack();

        GameMenu gm = new GameMenu();


        // gw.startGame();
    }

}