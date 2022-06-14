package view.userintefaces;

import static view.utils.Constants.GameConstants.BOX_WIDTH;
import static view.utils.Constants.GameConstants.GAME_SCREEN_HEIGHT;
import static view.utils.Constants.GameConstants.GAME_SCREEN_WIDTH;
import static view.utils.Constants.GameConstants.PLAYER_WIDTH;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import presenter.ProsesTexperiences;
import view.entities.PlayerProfile;
import view.gameobject.Land;
import view.gameobject.LandBox;
import view.gameobject.Player;
import view.utils.Constants.GameState.STATE;;

public class GameScreen extends JPanel implements Runnable, KeyListener {

    Random randomGenerator;

    private Thread thread;

    Land land;
    Player player;

    List<LandBox> listBox;

    LandBox firstBox;
    
    int currentBoxHeight;

    boolean isPlayerCanMove;

    LandBox nextBox;

    LandBox leftBox, rightBox, currentBox;

    int idx;

    private GameMenu gm;

    public static STATE state = STATE.PLAYING;

    JFrame game;

    PlayerProfile currentPlayer;

    public GameScreen(PlayerProfile currentPlayer)
    {
        this.currentPlayer = currentPlayer;
        thread = new Thread(this);
        player = new Player(this.currentPlayer.getAdapt(), this.currentPlayer.getFall());
        listBox = new ArrayList<LandBox>();
        randomGenerator = new Random();


        for(int i = 11; i >= 0; i--)
        {
            LandBox box = new LandBox();
            int randomWidth = randomGenerator.nextInt(300 - 0) + 0;
            box.setHeight(randomWidth);
            box.setPosX(i*50);

            System.out.println(box.getPosX());

            listBox.add(box);
        }
        
        firstBox = listBox.get(1);
        player.setX(firstBox.getPosX() + 5);

        game = new JFrame("The Survive Hop");

        game.setSize(GAME_SCREEN_HEIGHT, GAME_SCREEN_WIDTH);
        game.setLocation(400, 100);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setResizable(false);

        game.addKeyListener(this);
        game.setVisible(true);
        game.add(this);
        this.startGame();
        // randomGenerator = new Random();
    }
    
    public void init()
    {
        
    }
    
    public void startGame()
    {
        thread.start();
    }
    
    @Override
    public void run() {

        // if (state == STATE.PLAYING) {
            while (true) {
                // System.out.println(1);
                try {
                    if(state == STATE.PLAYING)
                    {

                        idx = 1;
        
                        for (LandBox landBox : listBox) {
                            
                            if(
                                
                                (player.getX() > landBox.posX - BOX_WIDTH && player.getX()+ PLAYER_WIDTH < landBox.posX)
        
                                
                                ){
                                    System.out.println("getout : " + idx);
                                    System.out.println("y : " + (player.getY()));
                                    System.out.println("land y : " + ((GAME_SCREEN_WIDTH - PLAYER_WIDTH) - landBox.height));
                                    System.out.println("player : "  + player.getX());
                                    System.out.println("landbox : "  + landBox.posX);
                                    System.out.println("idx: " + idx);
                                    currentBoxHeight = landBox.height;
                                    currentBox = listBox.get(idx);
                                    // listBox.get(idx).isStepped = true;
                                    if (idx < 11) {
                                        leftBox = listBox.get(idx+1);
                                    }else {
                                        
                                        leftBox = listBox.get(11);
                                    }
                                    if(idx <= 0){
                                        rightBox = listBox.get(0);
                                    }else{
                                        rightBox = listBox.get(idx-1);
            
                                    }
                                    break;
                                    
                            }
                           
                            idx++;
                            
                        }
        
                        player.update(currentBox, leftBox, rightBox);
                    }    
                    this.update();
                    repaint();
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        // }

        
    }

    public void update()
    {
        if(state == STATE.PLAYING)
        {

            for (LandBox imageLand : listBox) {
                imageLand.posX++;
            }
    
            LandBox box = listBox.get(0);
            if(box.posX - 50 > 500)
            {
                LandBox newBox = new LandBox();
                int randomWidth = randomGenerator.nextInt(300 - 0) +0;
                newBox.posX = listBox.get(listBox.size() - 1).posX - 50;
                newBox.height = randomWidth;
                listBox.add(newBox);
            }
    
            if(box.posX - 50 > 600)
            {
                listBox.remove(0);
            }
        }else if(state == STATE.GAMEOVER)
        {
            // save progress to database
            if(this.currentPlayer.getId() == -1)
            {
                String query = "INSERT INTO texperiences (username, adapt, fall) VALUES ('" + this.currentPlayer.getUsername() + "', " + player.getAdapt() + ", " + this.player.getFall() + ")";
                ProsesTexperiences tex = new ProsesTexperiences();
                tex.insertData(query);
                game.setVisible(false);
                GameMenu gm = new GameMenu();
                gm.setVisible(true);
                game.dispose();
                thread.stop();
                // System.exit(0);
            }else {
                String query = "UPDATE texperiences SET adapt=" + this.player.getAdapt() + ", fall=" + this.player.getFall() + " WHERE id=" + this.currentPlayer.getId();
                // System.out.println(query);
                ProsesTexperiences tex = new ProsesTexperiences();
                tex.insertData(query);
                game.setVisible(false);
                GameMenu gm = new GameMenu();
                gm.setVisible(true);
                game.dispose();
                thread.stop();
            }
        }
    }

    public void paint(Graphics g)
    {
        this.draw(g);
    }

    public void draw(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        for (LandBox box : listBox) {
            g.setColor(Color.blue);
            g.fillRect(box.getPosX(),GAME_SCREEN_HEIGHT - box.getHeight(), 50, box.getHeight());
        }
        player.draw(g);
    }



    @Override
    public void keyPressed(KeyEvent event) {
        int keycode = event.getKeyCode();
        if(keycode == KeyEvent.VK_UP){
            player.jump(leftBox);
        }

        if(keycode == KeyEvent.VK_LEFT){
            player.moveLeft(leftBox);
        }

        if(keycode == KeyEvent.VK_RIGHT){
            player.moveRight(rightBox);
        }

        if(keycode == KeyEvent.VK_SPACE){
            this.state = STATE.GAMEOVER;
        }

        if (keycode == KeyEvent.VK_DOWN) {
            listBox.get(idx).height+=10;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        
    }



    
}
