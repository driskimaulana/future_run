package view.utils;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;


/**
 * Filename : MediaResource.java
 * Programmer : D'Riski Maulana
 * Date : 15 June 2022
 * Email : driskimaulana@upi.edu
 * Website : driskimaulana.c120.me
 * Deskripsi : class to manage game sound
 */
public class MusicResource {

    Clip clip;

    public MusicResource(String path)
    {
        // get audio from computer
        File file = new File(path);
        AudioInputStream audioStream;
        try {
            audioStream = AudioSystem.getAudioInputStream(file);
            try {
                clip = AudioSystem.getClip();
                clip.open(audioStream);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startLooop()
    {
        // start and loop audio
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopMusic()
    {
        // stop music
        clip.stop();
    }
}