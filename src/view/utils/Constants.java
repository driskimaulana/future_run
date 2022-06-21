package view.utils;
/**
 * Filename : Constants.java
 * Programmer : D'Riski Maulana
 * Date : 10 June 2022
 * Email : driskimaulana@upi.edu
 * Website : driskimaulana.c120.me
 * Deskripsi : every constant used for this project
 */
public class Constants {
    
    public static class GameConstants {
        // game constants
        public static final int GRAVITY = 5;
        public static final int GAME_SCREEN_WIDTH = 600;
        public static final int GAME_SCREEN_HEIGHT = 600;
        public static final int PLAYER_WIDTH = 25;
        public static final int PLAYER_HEIGHT = 25;
        public static final int BOX_WIDTH = 50;

    }

    public static class GameState {
        // game state
        public static enum STATE {
            MENU,
            PLAYING,
            GAMEOVER
        };
    }


}
