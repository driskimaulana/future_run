package view.gameobject;

import static view.userintefaces.GameScreen.GRAVITY;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
    
    private float x;
    private float y;
    private float speedY;
    private float speedX;

    public Player()
    {
        this.x = 500;
        this.y = 0;
        this.speedY = 4;
        this.speedX = 0;
    }

    public void update(LandBox currentBox, LandBox leftBox, LandBox rightBox)
    {
        // System.out.println(y);
        // x++;
        if (y >= 575 - currentBox.height) {
            speedY = 0;
            y = 575 - currentBox.height;
            // x+=speedX;
        }else{
            speedY += GRAVITY;
            y+=speedY;
            // System.out.println("y=" + (600 - (int)y) );
            // System.out.println("h=" + (leftBox.getHeight()));
            // if (x <= leftBox.posX) {
            if (x<= leftBox.posX + 55 && (575 -y) <= leftBox.getHeight()) {
                // System.out.println("end");
                speedX = 0;
            }else {
                speedX = -7;
            }
            x+=speedX;
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
        if (x+27 >= rightBox.posX && (575 -y) <= rightBox.getHeight()) {
            speedX = 0;
        }
        x+=speedX;
    }
    
    public void moveLeft(LandBox leftBox)
    {
        speedX = -7;
        // if (x <= leftBox.posX + 52 && (575 -y) <= leftBox.getHeight()) {
        if (x <= leftBox.posX +50 && (575 -y) <= leftBox.getHeight()) {
            speedX = 0;
        }
        x += speedX;
    }
    
    public void draw(Graphics g)
    {
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

}
