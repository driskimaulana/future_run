package view.gameobject;

/**
 * Filename : DB.java
 * Programmer : D'Riski Maulana
 * Date : 10 June 2022
 * Email : driskimaulana@upi.edu
 * Website : driskimaulana.c120.me
 * Deskripsi : class for ground or box
 */

public class LandBox {
    
    // class attribute
    public int posX;
    public int height;
    public boolean isStepped; // used to check if the box already count or not

    public LandBox(){
        // class constructors
        // new box, not stepped yet
        isStepped = false;
 
    }

    // getter and setter
    public void setPosX(int x)
    {
        this.posX = x;
    }

    public int getPosX()
    {
        return this.posX;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getHeight()
    {
        return this.height;
    }

}
