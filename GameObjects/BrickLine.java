package GameObjects;
import java.util.ArrayList;
import java.util.List;
import Render.Screen;

/**
 * Represents a single horizontal line of bricks in the game.
 * This class is responsible for creating and managing all the Brick objects within that line.
 */
public class BrickLine {
    /** A list containing all the Brick objects in this line. */
    private List <Brick> listOfBricks;
    /** The vertical position (y-coordinate) of this line of bricks on the screen. */
    private int lineHeight;
    /** The calculated number of bricks that can fit in a single line based on screen width. */
    private static final int NUMBER_OF_BRICKS = Screen.WINDOW_WIDTH / Brick.getWidth();
    /** The calculated gap between each brick to ensure they are evenly spaced across the screen. */
    private static final int BRICK_GAP = ((Screen.WINDOW_WIDTH - (NUMBER_OF_BRICKS * Brick.getWidth())) / NUMBER_OF_BRICKS) / 2;

    /**
     * Constructs a new line of bricks at a specified vertical position.
     * It calculates the horizontal positions for each brick to distribute them evenly.
     * @param lineHeight The y-coordinate for this line of bricks.
     */
    public BrickLine(int lineHeight) {
        listOfBricks = new ArrayList<>();
        this.lineHeight = lineHeight;
        int x_brick_location = 0;
        for (int i = 0; i < NUMBER_OF_BRICKS; i++) {
            x_brick_location += BRICK_GAP;
            listOfBricks.add(new Brick(x_brick_location, lineHeight));
            x_brick_location += Brick.getWidth() + BRICK_GAP;
        }
    }

    /**
     * Removes a brick from the line by its index.
     * @param index The index of the brick to remove.
     */
    public void removeBrickByIndex(int index){
        listOfBricks.remove(index);
    }

    /**
     * Gets the current number of bricks remaining in the line.
     * @return The number of bricks.
     */
    public int getNumOfBricks(){
        return listOfBricks.size();
    }

    /**
     * Retrieves a brick from the line by its index.
     * @param index The index of the brick to retrieve.
     * @return The Brick object at the specified index.
     */
    public Brick getBrickByIndex(int index){
        return listOfBricks.get(index);
    }

    /**
     * Gets the vertical position (y-coordinate) of the entire line.
     * @return The line's height.
     */
    public int getLineHeight(){
        return lineHeight;
    }
}
