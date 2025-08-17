package GameObjects;
import javax.swing.ImageIcon;
/**
 * The Paddle class represents the player's paddle in the game.
 * It manages the paddle's position, dimensions, speed, and icon.
 */
public class Paddle{
    private static final String ICON_PATH = AssetPaths.PADDLE_ICON_PATH;
    private static final int WIDTH = 210;
    private static final int HEIGHT = 34;
    /**
     * The speed at which the paddle moves horizontally.
     * Suggestion: Consider if this speed should be configurable, for example, through a settings file or difficulty levels.
     */
    private final int SPEED = 20;
    private static ImageIcon paddleIcon;
    private int x;
    private int y;

    /**
     * Constructs a new Paddle at the specified initial position.
     * @param x The initial x-coordinate of the paddle.
     * @param y The initial y-coordinate of the paddle.
     */
    public Paddle(int x, int y){
        paddleIcon = new ImageIcon(ICON_PATH);
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the width of the paddle in pixels.
     * @return The paddle's width.
     */
    public static int getWidth() {
        return WIDTH;
    }

    /**
     * Returns the height of the paddle in pixels.
     * @return The paddle's height.
     */
    public static int getHeight() {
        return HEIGHT;
    }

    /**
     * Returns the current x-coordinate of the paddle.
     * @return The paddle's x-coordinate.
     */
    public int getX(){
        return x;
    }
    
    /**
     * Updates the x-coordinate of the paddle. This is used to move the paddle
     * based on player input.
     * @param x The new x-coordinate for the paddle.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the current y-coordinate of the paddle.
     * @return The paddle's y-coordinate.
     */
    public int getY(){
        return y;
    }

    /**
     * Returns the icon for the paddle.
     * @return The paddle's ImageIcon.
     */
    public static ImageIcon getIcon(){
        return paddleIcon;
    }

    /**
     * Returns the path to the paddle's icon image.
     * @return The file path of the paddle's icon.
     */
    public static String getIconPath(){
        return ICON_PATH;
    }

    /**
     * Returns the moving speed of the paddle in pixels per key press.
     * @return The paddle's speed.
     */
    public int getPaddleSpeed(){
        return SPEED;
    }
}
