package view.userintefaces;

/**
 * Filename : DB.java
 * Programmer : D'Riski Maulana
 * Date : 10 June 2022
 * Email : driskimaulana@upi.edu
 * Website : driskimaulana.c120.me
 * Deskripsi : Game Screen object, that manage all the game
 */

import static view.utils.Constants.GameConstants.BOX_WIDTH;
import static view.utils.Constants.GameConstants.GAME_SCREEN_HEIGHT;
import static view.utils.Constants.GameConstants.GAME_SCREEN_WIDTH;
import static view.utils.Constants.GameConstants.PLAYER_WIDTH;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import presenter.ProsesTexperiences;
import view.entities.Background;
import view.entities.PlayerProfile;
import view.gameobject.LandBox;
import view.gameobject.Player;
import view.utils.Constants.GameState.STATE;
import view.utils.MusicResource;
import view.utils.Resource;

public class GameScreen extends JPanel implements Runnable, KeyListener {

    // class attribute
    Random randomGenerator;
    private Thread thread;
    Player player;
    List<LandBox> listBox;
    LandBox firstBox;
    int currentBoxHeight;
    LandBox leftBox, rightBox, currentBox;

    int idx;

    private GameMenu gm;

    // set state to playing
    public static STATE state = STATE.PLAYING;
    JFrame game;
    PlayerProfile currentPlayer;
    BufferedImage landImage;

    // game art
    private Background bg1, bg2, bg5, bg6, fg1, fg2, blueBackground;

    private MusicResource musicResource;

    public GameScreen(PlayerProfile currentPlayer)
    {
        // class constructors
        this.currentPlayer = currentPlayer;
        thread = new Thread(this);
        player = new Player(this.currentPlayer.getAdapt(), this.currentPlayer.getFall());
        listBox = new ArrayList<LandBox>();
        randomGenerator = new Random();
        
        // import image
        landImage = Resource.getResourceImage("data/ground.png");
        this.bg1 = new Background(0, Resource.getResourceImage("data/bg_1.png"));
        this.bg2 = new Background(-600, Resource.getResourceImage("data/bg_2.png"));
        this.bg5 = new Background(0, Resource.getResourceImage("data/bg_5.png"));
        this.bg6 = new Background(-600, Resource.getResourceImage("data/bg_6.png"));
        this.fg1 = new Background(0, Resource.getResourceImage("data/fg_1.png"));
        this.fg2 = new Background(-600, Resource.getResourceImage("data/fg_2.png"));
        this.blueBackground = new Background(0, Resource.getResourceImage("data/BlueBackground.png"));

        // import background music
        this.musicResource = new MusicResource("data/backsound.wav");

        this.musicResource.startLooop();
        
        // generate box with random height and add it to arraylist
        for(int i = 11; i >= 0; i--)
        {
            LandBox box = new LandBox();
            int randomWidth = randomGenerator.nextInt(300 - 0) + 0;
            box.setHeight(randomWidth);
            box.setPosX(i*50);
            listBox.add(box);
        }
        
        // get firstbox from the list
        firstBox = listBox.get(1);
        // set player x position
        player.setX(firstBox.getPosX() + 5);

        // create new frame
        game = new JFrame("The Survive Hop");

        game.setSize(GAME_SCREEN_HEIGHT, GAME_SCREEN_WIDTH);
        game.setLocation(400, 100);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setResizable(false);

        game.addKeyListener(this);
        game.setVisible(true);
        game.add(this);
        this.startGame();
    }
  
    public void startGame()
    {
        // start thread
        thread.start();
    }
    
    @Override
    public void run() {
        // game loop
            while (true) {
                try {
                    if(state == STATE.PLAYING)
                    {
                        // if state is playing
                        idx = 1;
        
                        for (LandBox landBox : listBox) {
                            // get the current box where the player is stand
                            if((player.getX() > landBox.posX - BOX_WIDTH && player.getX()+ PLAYER_WIDTH < landBox.posX))
                            {
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
                    }    
                    // update player
                    player.update(currentBox, leftBox, rightBox);
                    this.update();
                    repaint();
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }

    public void update()
    {
        // update method
        if(state == STATE.PLAYING)
        {
            // parallax effect
            // first layer background

            // move slowly
            bg5.x-=0.2;
            bg6.x-=0.2;
            
            if(bg5.x+600 < 0)
            {
                // move the image after each other, to create infinite image loop
                bg5.x = 600;
            }
            if(bg6.x+600 < 0)
            {
                // move the image after each other, to create infinite image loop
                bg6.x = 600;
            }

            // second layer of background

            // move a little bit slower from player
            bg1.x-=0.8;
            bg2.x-=0.8;
            // move image, create infite loop
            if(bg1.x+600 < 0)
            {
                bg1.x = 600;
            }
            if(bg2.x+600 < 0)
            {
                bg2.x = 600;
            }

            // front layer
            // move equally with player
            fg1.x-=1;
            fg2.x-=1;
            
            if(fg1.x+600 < 0)
            {
                fg1.x = 600;
            }
            if(fg2.x+600 < 0)
            {
                fg2.x = 600;
            }

            // move all boxes to the left
            for (int i = 0; i < listBox.size(); i++) {
                listBox.get(i).posX++;
            }
    
            // generate a new box if some box is out of screen
            LandBox box = listBox.get(0);
            if(box.posX - 50 > 500)
            {
                // if the first box from list is out of screen
                // create a new box and add it to the list
                LandBox newBox = new LandBox();
                int randomWidth = randomGenerator.nextInt(300 - 0) +0;
                newBox.posX = listBox.get(listBox.size() - 1).posX - 50;
                newBox.height = randomWidth;
                listBox.add(newBox);
            }
    
            if(box.posX - 50 > 600)
            {
                // the first box is out of screen and not used anymore
                // so, remove the box
                listBox.remove(0);
            }
        }else if(state == STATE.GAMEOVER)
        {
            // game over state
            musicResource.stopMusic();
            // save progress to database
            if(this.currentPlayer.getId() == -1)
            {
                // create a new username
                String query = "INSERT INTO texperiences (username, adapt, fall) VALUES ('" + this.currentPlayer.getUsername() + "', " + player.getAdapt() + ", " + this.player.getFall() + ")";
                ProsesTexperiences tex = new ProsesTexperiences();
                tex.insertData(query);
                game.setVisible(false);

                // load game menu frame
                GameMenu gm = new GameMenu();
                gm.setVisible(true);
                game.dispose();
                thread.stop();
            }else {
                // returned player
                // update adapt and fall skor
                String query = "UPDATE texperiences SET adapt=" + this.player.getAdapt() + ", fall=" + this.player.getFall() + " WHERE id=" + this.currentPlayer.getId();
                ProsesTexperiences tex = new ProsesTexperiences();
                tex.insertData(query);
                game.setVisible(false);

                // load game menu frame
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
        // create canvas
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());

        // draw blue background
        g.drawImage(blueBackground.getImage(), (int) blueBackground.getX(), 0, 600, 600, null);
        
        // draw background image
        g.drawImage(bg5.getImage(), (int) bg5.getX(), 200, 605, 400, null);
        g.drawImage(bg6.getImage(), (int) bg6.getX(), 200, 605, 400, null);
        g.drawImage(bg1.getImage(), (int) bg1.getX(), 300, 605, 300, null);
        g.drawImage(bg2.getImage(), (int) bg2.getX(), 300, 605, 300, null);
        
        // draw all the boxes with land sprite
        for (int i = 0; i < listBox.size(); i++) {
            listBox.get(i).posX++;
            g.setColor(Color.blue);
            g.drawImage(landImage, listBox.get(i).getPosX() -5, GAME_SCREEN_HEIGHT - listBox.get(i).getHeight() - 8, 50, listBox.get(i).height, null);
        }
        // draw the player
        player.draw(g);

        // draw the foreground of parallax effect
        g.drawImage(fg1.getImage(), (int) fg1.getX(), 500, 605, 100, null);
        g.drawImage(fg2.getImage(), (int) fg2.getX(), 500, 605, 100, null);
    }



    @Override
    public void keyPressed(KeyEvent event) {
        int keycode = event.getKeyCode();
        if(keycode == KeyEvent.VK_UP){
            // arrow of is jump
            if (leftBox != null) {
                player.jump(leftBox);
            }
        }

        if(keycode == KeyEvent.VK_LEFT){
            if(leftBox != null)
            {
                // arrow left, move to the left
                player.moveLeft(leftBox);
            }
        }

        if(keycode == KeyEvent.VK_RIGHT){
            if(rightBox != null)
            {
                // arrow right, move to the right
                player.moveRight(rightBox);
            }
        }

        if(keycode == KeyEvent.VK_SPACE){
            // space key, return to gamemeu
            this.state = STATE.GAMEOVER;
        }

        if (keycode == KeyEvent.VK_DOWN) {
            // arrow down, add box height
            if (idx >=0 && idx < 11) {
                listBox.get(idx).height+=20;
                 
            }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent event) {
        int keycode = event.getKeyCode();
        if (keycode == KeyEvent.VK_DOWN) {
            // arrow down, add box height
            if (idx >=0 && idx < 11) {
                // cost to point to add box height
                player.setAdapt(player.getAdapt() - 2);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        
    }


}
