package GameObjects;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
/**
 * The Brick class represents a single brick object in the game.
 * It stores the brick's position, dimensions, and icon.
 */
public class Brick{
    private final static int WIDTH = 190;
    private final static int HEIGHT = 50;
    private final static String ICON_PATH = "assets/brick.png";
    private static ImageIcon brickIcon;
    /**
     * The y-coordinate for the line of bricks. This value determines the
     * starting vertical position of the bricks on the screen.
     * Suggestion: Make this a constructor parameter if bricks can appear at different y-coordinates.
     */
    private int y = 50;
    private int x;
    private Rectangle rectangle_brick;

    /**
     * Constructs a new Brick object at the specified x-coordinate.
     * @param x The x-coordinate of the brick.
     */
    public Brick(int x){
        brickIcon = new ImageIcon(ICON_PATH);
        this.x = x;
        rectangle_brick = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    /**
     * Returns the width of the brick in pixels.
     * @return The width of the brick.
     */
    public static int getWidth() {
        return WIDTH;
    }
    
    /**
     * Returns the height of the brick in pixels.
     * @return The height of the brick.
     */
    public static int getHeight() {
        return HEIGHT;
    }

    /**
     * Returns the x-coordinate of the brick.
     * @return The x-coordinate of the brick.
     */
    public int getX(){
        return x;
    }

    /**
     * Returns the y-coordinate of the brick.
     * @return The y-coordinate of the brick.
     */
    public int getY(){
        return y;
    }

    /**
     * Returns the bounding box of the brick as a Rectangle object.
     * @return The Rectangle object representing the brick's boundaries.
     */
    public Rectangle getRectangleBrick(){
        return rectangle_brick;
    }

    /**
     * Returns the icon for the brick.
     * @return The ImageIcon of the brick.
     */
    public ImageIcon getIcon(){
        return brickIcon;
    }

    /**
     * Returns the path to the brick's icon image.
     * @return The file path of the brick's icon.
     */
    public static String getIconPath(){
        return ICON_PATH;
    }

    /**
     * Compares this brick to another object for equality.
     * Two bricks are considered equal if they have the same x and y coordinates.
     * @param other The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other){
        if(other instanceof Brick){
            Brick brick = (Brick)other;
            if(x == brick.getX() && y == brick.getY()){
                return true;
            }
        }
        return false;        
    }

    /**
     * Returns a hash code value for the brick.
     * This is used for efficient storage in hash-based collections.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode(){
        return x + y;
    }
}
