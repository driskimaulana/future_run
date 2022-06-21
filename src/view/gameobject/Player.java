package view.gameobject;

/**
 * Filename : Player.java
 * Programmer : D'Riski Maulana
 * Date : 10 June 2022
 * Email : driskimaulana@upi.edu
 * Website : driskimaulana.c120.me
 * Deskripsi : Player object, used to create and manage player object
 */

import static view.utils.Constants.GameConstants.GAME_SCREEN_HEIGHT;
import static view.utils.Constants.GameConstants.GRAVITY;
import static view.utils.Constants.GameConstants.PLAYER_HEIGHT;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import view.userintefaces.GameScreen;
import view.utils.Constants.GameState.STATE;
import view.utils.Animation;
import view.utils.Resource;

public class Player {
    // class attribute
    private float x;
    private float y;
    private float speedY;
    private float speedX;
    public float landBoxY;
    private int adapt;
    private int fall;

    // player character sprite
    private BufferedImage imageSprite;

    // animation
    private Animation walkAnimation;

    public Player(int adapt, int fall)
    {
        // class constructors, take adapt and fall from inserted username
        this.x = 300 +25;
        this.y = 0;
        this.adapt = adapt;
        this.fall = fall;
        this.speedY = 4;
        this.speedX = 0;
        this.landBoxY = 0;

        // load character image
        imageSprite = Resource.getResourceImage("data/player_1.png");

        // create instance animation for walk animation
        this.walkAnimation = new Animation(400);
        // add image frame for walk animation
        walkAnimation.addFrame(Resource.getResourceImage("data/walk_1.png"));
        walkAnimation.addFrame(Resource.getResourceImage("data/walk_2.png"));
        walkAnimation.addFrame(Resource.getResourceImage("data/walk_3.png"));
        walkAnimation.addFrame(Resource.getResourceImage("data/walk_3.png"));
        walkAnimation.addFrame(Resource.getResourceImage("data/walk_2.png"));
        walkAnimation.addFrame(Resource.getResourceImage("data/walk_1.png"));
    }
    
    public void update(LandBox currentBox, LandBox leftBox, LandBox rightBox)
    {
        // update function
        x+=2.1; // move player
        walkAnimation.update(); 
        
        if (currentBox != null) {
            // change character image to specific frame from animation
            this.imageSprite = this.walkAnimation.getFrame();
            
            if (y >= (GAME_SCREEN_HEIGHT - PLAYER_HEIGHT) - currentBox.height) {
                // if player is not jump
                speedY = 0;
                y = (GAME_SCREEN_HEIGHT - PLAYER_HEIGHT) - currentBox.height;
                this.landBoxY = (GAME_SCREEN_HEIGHT - PLAYER_HEIGHT) - currentBox.height;
                if (currentBox.isStepped == false) {
                    // check whether this current box is stepped already
                    if (currentBox.height > 50) {
                        // add adapt poin if box is empty
                        this.adapt++;
                        
                    }else {
                        // add fall if box is lesser than 50
                        this.fall++;
                        
                    }
                    // set current box to stepped, so that this box will not count on adapt or fall again
                    currentBox.isStepped = true;
                }
            }else{
                // if player is jump
                this.imageSprite = Resource.getResourceImage("data/player_jump.png");

                // implements gravity physics
                speedY += GRAVITY;
                y+=speedY;
                // check colllision on the air
                if (x<= leftBox.posX + 55 && ((GAME_SCREEN_HEIGHT - PLAYER_HEIGHT) - y) <= leftBox.getHeight()) {
                    // if collision detected, than player cannot move to the left again, and fall vertically
                    speedX = 0;
                }else {
                    // continue, to create parabola movement, just like standard jump
                    speedX = -7;
                }
                x+=speedX;
            }
            
            // if the character is of from the screen, then game is over
            if(this.x > 600)
            {
                GameScreen.state = STATE.GAMEOVER;
            }
        }
    }

    public void jump(LandBox leftBox)
    {
        // jumping action
        speedY = -25;
        if(x <= leftBox.posX + 52){
            speedX = 0;

        }else{
            speedX = -10;

        }
        y+=speedY;
        x+=speedX;
    }

    public void moveRight(LandBox rightBox)
    {
        // walk right method
        speedX = 7;
        if (x+27 >= rightBox.posX && ((GAME_SCREEN_HEIGHT - PLAYER_HEIGHT) -y) <= rightBox.getHeight()) {
            speedX = 0;
        }
        x+=speedX;
    }
    
    public void moveLeft(LandBox leftBox)
    {
        // walk left method
        this.imageSprite = this.walkAnimation.getFrame();
        speedX = -7;
        if (x <= leftBox.posX +55 && ((GAME_SCREEN_HEIGHT - PLAYER_HEIGHT) -y) <= leftBox.getHeight()) {
            speedX = 0;
        }
        x += speedX;
    }
    
    public void draw(Graphics g)
    {
        // score display
        String profile = "Adapt: " + this.adapt + "\nFall: " + Integer.toString(this.fall);
        int lineHeight = g.getFontMetrics().getHeight();
        int sy = 20;
        g.setColor(Color.white);
        g.setFont(new Font("Courier New", Font.BOLD, 16));
        for (String line : profile.split("\n"))
            g.drawString(line, 20, sy += lineHeight);
       
        
        // draw character
        g.drawImage(this.imageSprite, (int) x, (int) y-15, 25, 40, null);
    }

    // setter and getter
    public void setX(float x) {
        this.x = x;
    }
    public float getSpeedY() {
        return speedY;
    }
    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }
    public void setY(float y) {
        this.y = y;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public void setAdapt(int adapt) {
        this.adapt = adapt;
    }
    public int getAdapt() {
        return adapt;
    }
    public void setFall(int fall) {
        this.fall = fall;
    }
    public int getFall() {
        return fall;
    }

}
