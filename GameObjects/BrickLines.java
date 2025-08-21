package GameObjects;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages all the lines of bricks in the game.
 * This class acts as a container for multiple BrickLine objects, representing the entire brick layout.
 */
public class BrickLines {
    /** A list containing all the BrickLine objects for the level. */
    private List <BrickLine> brickLines;
    /** The initial vertical position (y-coordinate) for the topmost line of bricks. */
    private static final int initialLineHeight = 50;

    /**
     * Constructs the complete set of brick lines for the game level.
     * @param num_of_lines The total number of brick lines to create.
     */
    public BrickLines(int numOfLines){
        brickLines = new ArrayList<>();
        for(int i = 0; i < numOfLines; i++){
            // Each new line is placed below the previous one.
            brickLines.add(new BrickLine((initialLineHeight * (i + 1))));
        }
    }

    /**
     * Retrieves a specific line of bricks by its index.
     * @param index The index of the brick line to retrieve.
     * @return The BrickLine object at the specified index.
     */
    public BrickLine getLineByIndex(int index){
        return brickLines.get(index);
    }

    /**
     * Gets the current number of brick lines remaining in the game.
     * @return The number of lines.
     */
    public int getNumOfLines(){
        return brickLines.size();
    }

    /**
     * Removes a specific brick from a specific line.
     * If removing the brick makes the line empty, the entire line is also removed.
     * @param lineIndex The index of the line containing the brick.
     * @param brickIndex The index of the brick to remove from that line.
     */
    public void removeBrickFromLineByIndex(int lineIndex, int brickIndex){
        brickLines.get(lineIndex).removeBrickByIndex(brickIndex);
        // If the line is now empty, remove it from the list of lines.
        if (brickLines.get(lineIndex).getNumOfBricks() == 0) {
            brickLines.remove(lineIndex);
        }
    }

    public void resetBricks(int numOfLines){
        for(int i = 0; i < numOfLines; i++){
            // Each new line is placed below the previous one.
            brickLines.add(new BrickLine((initialLineHeight * (i + 1))));
        } 
    }
}
