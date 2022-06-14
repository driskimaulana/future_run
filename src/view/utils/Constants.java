package view.utils;

public class Constants {
    
    public static class GameConstants {

        public static final int GRAVITY = 5;
        public static final int GAME_SCREEN_WIDTH = 600;
        public static final int GAME_SCREEN_HEIGHT = 600;
        public static final int PLAYER_WIDTH = 25;
        public static final int PLAYER_HEIGHT = 25;
        public static final int BOX_WIDTH = 50;

    }

    public static class GameState {
        public static enum STATE {
            MENU,
            PLAYING,
            GAMEOVER
        };
    }


}
