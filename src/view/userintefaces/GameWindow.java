package view.userintefaces;

import java.awt.Color;

/**
 * GameWindow
 */

 import javax.swing.JFrame;

 public class GameWindow extends JFrame{

    public static final int SCREEN_WIDTH = 600;

    public GameScreen gameScreen;

    public GameWindow()
    {
        super("FutureRun");

        setSize(SCREEN_WIDTH, SCREEN_WIDTH);
        setLocation(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        gameScreen = new GameScreen();
        this.addKeyListener(gameScreen);
        add(gameScreen);

        setBackground(Color.red);
    }

    public void startGame()
    {
        gameScreen.startGame();
    }
    
    public static void main(String[] args) {
        GameWindow gw = new GameWindow();
        gw.setVisible(true);
        gw.startGame();
    }

}