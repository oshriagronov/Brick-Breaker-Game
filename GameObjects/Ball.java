package GameObjects;
import javax.swing.ImageIcon;
/**
 * The Ball class represents the ball object in the game.
 * It stores information about the ball's position, velocity, and appearance.
 */

import Render.AssetPaths;
import Render.Screen;
public class Ball {
    private static final String ICON_PATH = AssetPaths.BALL_ICON_PATH;
    private static final int WIDTH = 53;
    private static final int HEIGHT = 53;
    private static ImageIcon ballIcon;
    private int x;
    private int y;
    /**
     * The horizontal velocity of the ball in pixels per timer tick.
     * A larger number means a faster ball.
     */
    private int defaultXVelocity = 0;
    private int xVelocity = 0;
    /**
     * The vertical velocity of the ball in pixels per timer tick.
     * A larger number means a faster ball.
     */
    private int defaultYVelocity = 10;
    private int yVelocity = 0;

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

    public void resetPosition(){
        this.setPosition(Screen.WINDOW_WIDTH / 2, Screen.WINDOW_HEIGHT / 2);
        this.setBallXVelocity(0);
        this.setBallYVelocity(0);
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

    public int getDefaultBallXVelocity(){
        return defaultXVelocity;
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

    public int getDefaultBallYVelocity(){
        return defaultYVelocity;
    }
    /**
     * Sets the vertical velocity of the ball.
     * @param yVelocity The new vertical velocity.
     */
    public void setBallYVelocity(int yVelocity){
        this.yVelocity = yVelocity;
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
