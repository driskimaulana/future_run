package view.gameobject;

public class LandBox {
    
    public int posX;
    public int height;
    public boolean isStepped;

    public LandBox(){
        isStepped = false;
 
    }

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
