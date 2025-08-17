package GameObjects;
import javax.swing.ImageIcon;
/**
 * The Ball class represents the ball object in the game.
 * It stores information about the ball's position, velocity, and appearance.
 */
public class Ball {
    /**
     * The speed of the ball, which determines the delay of the game's timer.
     * A smaller number results in a faster ball movement.
     * Suggestion: Consider renaming this to something like 'TIMER_DELAY_MS' for clarity.
     */
    private final int SPEED = 1;
    private static final String ICON_PATH = "assets/ball.png";
    private static final int WIDTH = 53;
    private static final int HEIGHT = 53;
    private static ImageIcon ballIcon;
    private int x;
    private int y;
    /**
     * The horizontal velocity of the ball in pixels per timer tick.
     * A larger number means a faster ball.
     */
    private int xVelocity = 1;
    /**
     * The vertical velocity of the ball in pixels per timer tick.
     * A larger number means a faster ball.
     */
    private int yVelocity = 1;

    /**
     * Constructs a new Ball object at the specified coordinates.
     * @param x The initial x-coordinate of the ball.
     * @param y The initial y-coordinate of the ball.
     */
    public Ball(int x, int y){
        ballIcon = new ImageIcon(ICON_PATH);
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the width of the ball in pixels.
     * @return The width of the ball.
     */
    public static int getWidth() {
        return WIDTH;
    }

    /**
     * Returns the height of the ball in pixels.
     * @return The height of the ball.
     */
    public static int getHeight() {
        return HEIGHT;
    }

    /**
     * Returns the current x-coordinate of the ball.
     * @return The x-coordinate of the ball.
     */
    public int getX(){
        return x;
    }

    /**
     * Returns the current y-coordinate of the ball.
     * @return The y-coordinate of the ball.
     */
    public int getY(){
        return y;
    }

    /**
     * Returns the icon for the ball.
     * @return The ImageIcon of the ball.
     */
    public static ImageIcon getIcon(){
        return ballIcon;
    }

    /**
     * Returns the path to the ball's icon image.
     * @return The file path of the ball's icon.
     */
    public static String getIconPath(){
        return ICON_PATH;
    }

    /**
     * Returns the horizontal velocity of the ball.
     * @return The ball's horizontal velocity.
     */
    public int getBallXVelocity(){
        return xVelocity;
    }

    /**
     * Sets the horizontal velocity of the ball.
     * @param xVelocity The new horizontal velocity.
     */
    public void setBallXVelocity(int xVelocity){
        this.xVelocity = xVelocity;
    }
    /**
     * Returns the vertical velocity of the ball.
     * @return The ball's vertical velocity.
     */
    public int getBallYVelocity(){
        return yVelocity;
    }
    /**
     * Sets the vertical velocity of the ball.
     * @param yVelocity The new vertical velocity.
     */
    public void setBallYVelocity(int yVelocity){
        this.yVelocity = yVelocity;
    }
    /**
     * Returns the speed of the ball, which corresponds to the timer delay.
     * @return The timer delay in milliseconds.
     */
    public int getBallSpeed(){
        return SPEED;
    }

    /**
     * Sets the position of the ball to the specified coordinates.
     * This is used to update the ball's location after movement or collision calculations.
     * @param x The new x-coordinate.
     * @param y The new y-coordinate.
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
