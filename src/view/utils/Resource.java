package view.utils;

/**
 * Filename : Resource.java
 * Programmer : D'Riski Maulana
 * Date : 10 June 2022
 * Email : driskimaulana@upi.edu
 * Website : driskimaulana.c120.me
 * Deskripsi : class for manage loading image
 */

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class Resource {

    public Resource()
    {
        // empty constructors
    }

    public static BufferedImage getResourceImage(String path)
    {
        // get bufferedimage
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (Exception e) {
            System.err.println(e);
        }

        return img;
    }
    
}