package view.utils;

/**
 * Filename : GameWindow.java
 * Programmer : D'Riski Maulana
 * Date : 16 June 2022
 * Email : driskimaulana@upi.edu
 * Website : driskimaulana.c120.me
 * Deskripsi : Animation object to store frame to create animation
 */

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {
    // class attribute
    private List<BufferedImage> frames;
    private int currentFrame = 0;

    // timer for changing frame
    private int deltaTime;
    private long previousTime;

    public Animation(int deltaTime)
    {
        // class constructors
        this.deltaTime = deltaTime;
        frames = new ArrayList<BufferedImage>();
    }

    public void update()
    {
        // frame changing every deltaTime (in miliseconds)
        if (System.currentTimeMillis() - previousTime > deltaTime) {
            currentFrame++;
            if (currentFrame >= frames.size()) {
                currentFrame = 0;
            }
            previousTime = System.currentTimeMillis();
        }
        
    }

    public void addFrame(BufferedImage newFrame){
        // add new frame
        frames.add(newFrame);
    }

    public BufferedImage getFrame() {
        // get frame
        if(frames.size() > 0)
        {
            return frames.get(currentFrame);
        }

        return null;
    }

}
