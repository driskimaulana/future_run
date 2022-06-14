package view.gameobject;

import static view.utils.Constants.GameConstants.GAME_SCREEN_HEIGHT;
import static view.utils.Constants.GameConstants.GRAVITY;
import static view.utils.Constants.GameConstants.PLAYER_HEIGHT;

import java.awt.Color;
import java.awt.Graphics;

import view.userintefaces.GameScreen;
import view.utils.Constants.GameState.STATE;

public class Player {
    
    private float x;
    private float y;
    private float speedY;
    private float speedX;
    public float landBoxY;
    private int skor;
    private String username;
    private int adapt;
    private int fall;

    public Player(int adapt, int fall)
    {
        this.x = 500;
        this.y = 0;
        this.adapt = adapt;
        this.fall = fall;
        this.speedY = 4;
        this.speedX = 0;
        this.landBoxY = 0;
        this.skor = 0;
        this.username = "sparrow";
    }

    public void update(LandBox currentBox, LandBox leftBox, LandBox rightBox)
    {
        // System.out.println(y);
        x++;
        // if (y >= 575 - currentBox.height) {
        if (y >= (GAME_SCREEN_HEIGHT - PLAYER_HEIGHT) - currentBox.height) {
            speedY = 0;
            y = (GAME_SCREEN_HEIGHT - PLAYER_HEIGHT) - currentBox.height;
            this.landBoxY = (GAME_SCREEN_HEIGHT - PLAYER_HEIGHT) - currentBox.height;
            if (currentBox.isStepped == false) {
                if (currentBox.height > 35) {
                    this.adapt++;
                    
                }else {
                    this.fall++;
                    
                }
                currentBox.isStepped = true;
            }
            // x+=speedX;
        }else{
            speedY += GRAVITY;
            y+=speedY;
            // System.out.println("y=" + (600 - (int)y) );
            // System.out.println("h=" + (leftBox.getHeight()));
            // if (x <= leftBox.posX) {
            if (x<= leftBox.posX + 55 && ((GAME_SCREEN_HEIGHT - PLAYER_HEIGHT) - y) <= leftBox.getHeight()) {
                // System.out.println("end");
                speedX = 0;
            }else {
                speedX = -7;
            }
            x+=speedX;
        }

        if(this.x > 600)
        {
            GameScreen.state = STATE.GAMEOVER;
        }
    }

    public void jump(LandBox leftBox)
    {
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
        speedX = 7;
        if (x+27 >= rightBox.posX && ((GAME_SCREEN_HEIGHT - PLAYER_HEIGHT) -y) <= rightBox.getHeight()) {
            speedX = 0;
        }
        x+=speedX;
    }
    
    public void moveLeft(LandBox leftBox)
    {
        speedX = -7;
        // if (x <= leftBox.posX + 52 && (575 -y) <= leftBox.getHeight()) {
        if (x <= leftBox.posX +55 && ((GAME_SCREEN_HEIGHT - PLAYER_HEIGHT) -y) <= leftBox.getHeight()) {
            speedX = 0;
        }
        x += speedX;
    }
    
    public void draw(Graphics g)
    {
        String profile = "Adapt: " + this.adapt + "\nFall: " + Integer.toString(this.fall);
        int lineHeight = g.getFontMetrics().getHeight();
        int sy = 20;
        for (String line : profile.split("\n"))
            g.drawString(line, 10, sy += lineHeight);
        // g.drawString( this.username, 10, 20);
        // g.drawString( Integer.toString(this.skor) + "\n" + this.username, 10, 40);
        g.setColor(Color.CYAN);
        // g.fillRect(600 - 25, 537, 25, 25);
        // g.fillRect(500 - 25, 575 - startBoxHeight, 25, 25);
        g.fillRect((int) x, (int) y, 25, 25);
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
