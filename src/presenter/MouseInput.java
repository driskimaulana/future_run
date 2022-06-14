package presenter;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.userintefaces.GameScreen;
import view.utils.Constants.GameState.STATE;

public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        int mX = e.getX();
        int mY = e.getY();
        
        /**
         *  public Rectangle playButton = new Rectangle(250, 200, 100, 50);
            public Rectangle exitButton = new Rectangle(250, 300, 100, 50);
            */
            
            if(mX >= 250 && mX <= 350 ){
                if(mY >= 335 && mY <= 490)
                {
                    System.out.println("clicked");
                    // playButton is pressed
                    GameScreen.state = STATE.PLAYING;
                }
                if(mY >= 335 && mY <= 390)
                {
                    // playButton is pressed
                    // GameScreen.state = STATE.LEADERBOARD;

                }
            }
        
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    
    

}
