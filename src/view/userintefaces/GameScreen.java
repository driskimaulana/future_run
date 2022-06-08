package view.userintefaces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import view.gameobject.Land;
import view.gameobject.LandBox;
import view.gameobject.Player;

public class GameScreen extends JPanel implements Runnable, KeyListener {

    public static final int LAND_POS_X = 0;
    public static final int LAND_POS_Y = 400;
    public static final int GRAVITY = 5;

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

    public GameScreen()
    {
        thread = new Thread(this);
        player = new Player();
        // land = new Land();
        listBox = new ArrayList<LandBox>();
        randomGenerator = new Random();

        isPlayerCanMove = true;

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
        player.setX(firstBox.getPosX());
        // randomGenerator = new Random();
    }

    public void startGame()
    {
        thread.start();
    }
    
    @Override
    public void run() {

        while (true) {
            // System.out.println(1);
            try {
                int upperBound = ( (int)player.getX() / 100) * 100;
                boolean canmove = false;
                idx = 1;

                for (LandBox landBox : listBox) {
                    // if (((player.getX() >= landBox.posX ) && (player.getX()+25 < landBox.posX + 50))) {
                    
                    // if(player.getX() >= landBox.posX){
                    if(player.getX() <= landBox.posX + 50){
                        if((player.getX() +5 > listBox.get(idx+1).posX + 50 && player.getX() +30 < listBox.get(idx).posX +50) || (player.getX() +5 > listBox.get(idx+1).posX + 50)){
                        // if(player.getX() +5 > listBox.get(idx+1).posX + 50 ){
                            System.out.println("idx: " + idx);
                            currentBoxHeight = landBox.height;
                            currentBox = listBox.get(idx);
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
                    }
                    
                    

                    // if (((player.getX() >= landBox.posX ) && (player.getX()+25 < landBox.posX +50))) {
                    //     currentBoxHeight = landBox.height;
                    //     currentBox = landBox;
                    //     if (idx <= 11) {
                    //         leftBox = listBox.get(idx +1);
                    //     }else {
                            
                    //         leftBox = listBox.get(11);
                    //     }
                    //     if(idx < 0){
                    //         rightBox = listBox.get(0);
                    //     }else{
                    //         rightBox = listBox.get(idx-1);

                    //     }
                    //     break;
                    // }
                    idx++;
                    
                }
                
                // nextBox = listBox.get(idx+1);

                // if(listBox.get(idx).height < listBox.get(idx+1).height){
                //     isPlayerCanMove = false;
                // }

                player.update(currentBox, leftBox, rightBox);
                // mainCharacter.update();
                // land.update();
                // this.update();
                repaint();
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }

    public void update()
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
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        for (LandBox box : listBox) {
            g.setColor(Color.blue);
            g.fillRect(box.getPosX(),600 - box.getHeight(), 50, box.getHeight());
        }
        // player.draw(g, firstBox.height);
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
            System.out.println("idx" + idx);
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
