package view.gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Land {
    
    private List<Box> listBox;

    private int numberOfTile;

    private Random randomGenerator;

    public Land()
    {
        listBox = new ArrayList<Box>();
        numberOfTile = 15;
        randomGenerator = new Random();

        for(int i = 0; i < numberOfTile; i++)
        {
            Box box = new Box();
            int randomWidth = randomGenerator.nextInt(300 - 0) + 0;
            box.boxWidth = randomWidth;
            box.posx = i * 50;

            listBox.add(box);
        }

    }

    public void update()
    {
        for (Box box : listBox) {
            box.posx++;
        }

        // pop the first box if the box is out of screen 
        // and generate new box to replace it
        Box box = listBox.get(11);
        if(box.posx - 50 > 600){
            Box newBox = new Box();
            newBox.posx = 0;
            listBox.add(0, newBox);
            listBox.remove(12);
        }

    }

    public void draw(Graphics g)
    {
        

        for (Box box : listBox) {
            g.setColor(Color.blue);
            g.fillRect(box.posx, 600 - box.boxWidth, 50, box.boxWidth);
        }
    }

    private class Box {
        int posx;
        int boxWidth;
        
        Box() {
            boxWidth = randomGenerator.nextInt(300 - 0) + 0;
        }
    }


}
