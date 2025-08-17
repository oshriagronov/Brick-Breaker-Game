package GameObjects;
import java.util.ArrayList;
import java.util.List;
public class BrickLines {
    private List <BrickLine> brickLines;
    private static final int initialLineHeight = 50;
    public BrickLines(int num_of_lines){
        brickLines = new ArrayList<>();
        for(int i = 0; i < num_of_lines; i++){
            brickLines.add(new BrickLine((initialLineHeight * (i + 1))));
        }
    }

    public BrickLine getLineByIndex(int index){
        return brickLines.get(index);
    }
    public void removeLineByIndex(int index){
        brickLines.remove(index);
    }
    public int getNumOfLines(){
        return brickLines.size();
    }
}
