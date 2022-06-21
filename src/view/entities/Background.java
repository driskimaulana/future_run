package view.entities;

/**
 * Filename : Background.java
 * Programmer : D'Riski Maulana
 * Date : 10 June 2022
 * Email : driskimaulana@upi.edu
 * Website : driskimaulana.c120.me
 * Deskripsi : class for creating background
 */

import java.awt.image.BufferedImage;

public class Background {
    // class attrivute
    public float x;
    private BufferedImage image;

    public Background()
    {
        // empth constructors
    }

    public Background(float x, BufferedImage image)
    {
        // constructors
        this.x = x;
        this.image = image;
    }

    // getter and setter
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    public BufferedImage getImage() {
        return image;
    }
    public void setX(float x) {
        this.x = x;
    }
    public float getX() {
        return x;
    }

}
