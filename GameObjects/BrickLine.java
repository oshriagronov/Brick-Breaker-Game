package GameObjects;
import java.util.ArrayList;
import java.util.List;
import Render.Screen;
public class BrickLine {
    private List <Brick> listOfBricks;
    private int lineHeight;
    private static final int num_of_bricks = Screen.WINDOW_WIDTH / Brick.getWidth();
    private static final int bricks_gap = ((Screen.WINDOW_WIDTH - (num_of_bricks * Brick.getWidth())) / num_of_bricks) / 2;
    public BrickLine(int lineHeight) {
        listOfBricks = new ArrayList<>();
        this.lineHeight = lineHeight;
        int x_brick_location = 0;
        for (int i = 0; i < num_of_bricks; i++) {
            x_brick_location += bricks_gap;
            listOfBricks.add(new Brick(x_brick_location));
            x_brick_location += Brick.getWidth() + bricks_gap;
        }
    }
    public void removeBrickByIndex(int index){
        listOfBricks.remove(index);
    }
    public int getNumOfBricks(){
        return listOfBricks.size();
    }
    public Brick getBrickByIndex(int index){
        return listOfBricks.get(index);
    }
    public int getLineHeight(){
        return lineHeight;
    }
}
