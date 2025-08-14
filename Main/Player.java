package Main;
import javax.swing.ImageIcon;
public class Player {
    private static final int HEART_WIDTH = 50;
    private static final int HEART_HEIGHT = 50;
    private static final int HEART_Y_POSITION = 0;
    private static final int HEART_X_POSITION = HEART_WIDTH;
    private static final String ICON_PATH = "assets/heart.png";
    private static ImageIcon heartIcon;
    private int lifePoints; // The amount of tries the player will have.
    private int score = 0; // The score the user got in the game(started at 0 and with every brick he break he will get more).
    private int scoreAmount; // amount of points to give the player for every brick he will break.
    // ## player constructor.
    //  the constructor need to get the number of life point he player will have and the score bonus for every brick he will break.
    public Player(int lifePoints, int scoreAmount){
        this.lifePoints = lifePoints; // The game manager will decide the amount of tries to give the player.
        this.scoreAmount = scoreAmount; // The game manager will decide the amount of points to give the player for every brick he will break.
        heartIcon = new ImageIcon(ICON_PATH);
    }


    // the user lose one life point
    public void loseLifePoint(){
        lifePoints -= 1;
    }

    // Add point to player for every brick he will break, the amount will be decided by game manager.
    public void addScore(){
        score += scoreAmount;
    }

    // give the score of the player
    public int getScore(){
        return score;
    }
        
    // give the number of player life points.
    public int getLifePoints(){
        return lifePoints;
    }

    // give the heart width logo
    public int getHeartWidth() {
        return HEART_WIDTH;
    }

    // give the heart height logo
    public int getHeartHeight() {
        return HEART_HEIGHT;
    }

    // give the heart x coordinate logo
    public int getHeartX(){
        return HEART_X_POSITION;
    }

    // give the heart y coordinate logo
    public int getHeartY(){
        return HEART_Y_POSITION;
    }

    // give the heart logo
    public ImageIcon getHeartIcon(){
        return heartIcon;
    }

    // give the heart logo path string
    public static String getHeartIconPath(){
        return ICON_PATH;
    }
}
