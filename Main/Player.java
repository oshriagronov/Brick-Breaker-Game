package Main;
import javax.swing.ImageIcon;
import Render.AssetPaths;
/**
 * The Player class encapsulates all data related to the player,
 * including life points, score, and the visual representation of lives (hearts).
 */
public class Player {
    private static final int HEART_WIDTH = 50;
    private static final int HEART_HEIGHT = 50;
    private static final int HEART_Y_POSITION = 0;
    private static final int HEART_X_POSITION = HEART_WIDTH;
    private static final String ICON_PATH = AssetPaths.HEART_ICON_PATH;
    private static ImageIcon heartIcon;
    /** The number of remaining lives for the player. */
    private int lifePoints;
    /** The player's current score, which starts at 0. */
    private int score = 0;
    /** The number of points awarded for breaking a single brick. */
    private int scoreAmount;

    /**
     * Constructs a new Player object with a specified number of lives and score increment amount.
     * @param lifePoints The initial number of lives for the player.
     * @param scoreAmount The amount of points to add to the score for each broken brick.
     */
    public Player(int lifePoints, int scoreAmount){
        this.lifePoints = lifePoints;
        this.scoreAmount = scoreAmount;
        heartIcon = new ImageIcon(ICON_PATH);
    }

    /**
     * Decrements the player's life points by one.
     */
    public void loseLifePoint(){
        lifePoints -= 1;
    }

    /**
     * Increases the player's score by the pre-configured score amount.
     */
    public void addScore(){
        score += scoreAmount;
    }

    /**
     * Returns the player's current score.
     * @return The current score.
     */
    public int getScore(){
        return score;
    }
        
    /**
     * Returns the player's remaining life points.
     * @return The number of life points.
     */
    public int getLifePoints(){
        return lifePoints;
    }

    /**
     * Returns the width of the heart icon.
     * @return The heart icon's width in pixels.
     */
    public int getHeartWidth() {
        return HEART_WIDTH;
    }

    /**
     * Returns the height of the heart icon.
     * @return The heart icon's height in pixels.
     */
    public int getHeartHeight() {
        return HEART_HEIGHT;
    }

    /**
     * Returns the x-coordinate for positioning the heart icons.
     * @return The x-coordinate.
     */
    public int getHeartX(){
        return HEART_X_POSITION;
    }

    /**
     * Returns the y-coordinate for positioning the heart icons.
     * @return The y-coordinate.
     */
    public int getHeartY(){
        return HEART_Y_POSITION;
    }

    /**
     * Returns the ImageIcon for the heart.
     * @return The heart's ImageIcon.
     */
    public ImageIcon getHeartIcon(){
        return heartIcon;
    }

    /**
     * Returns the file path for the heart icon image.
     * @return The path to the heart icon.
     */
    public static String getHeartIconPath(){
        return ICON_PATH;
    }
}
