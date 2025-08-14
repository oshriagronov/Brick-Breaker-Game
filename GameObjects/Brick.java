package GameObjects;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
public class Brick{
    private final static int WIDTH = 190;
    private final static int HEIGHT = 50;
    private final static String ICON_PATH = "assets/brick.png";
    private static ImageIcon brickIcon;
    private int y = 50; // this value deicide where the line of bricks will start.
    private int x;
    private Rectangle rectangle_brick;

    // bricks object constructor.
    public Brick(int x){
        brickIcon = new ImageIcon(ICON_PATH);
        this.x = x;
        rectangle_brick = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    // the function return the width in pixel units.
    public static int getWidth() {
        return WIDTH;
    }
    
    // the function return the height in pixel units.
    public static int getHeight() {
        return HEIGHT;
    }

    // the function return the right side x coordinate in pixel units.
    public int getX(){
        return x;
    }

    // the function return the y where bricks line start on screen(pixel units).
    public int getY(){
        return y;
    }
    // the function return Rectangle object of the brick
    public Rectangle getRectangleBrick(){
        return rectangle_brick;
    }

    // the function return the ball icon as ImageIcon.
    public ImageIcon getIcon(){
        return brickIcon;
    }

    // the function return path of the icon image of the ball.
    public static String getIconPath(){
        return ICON_PATH;
    }
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
    @Override
    public int hashCode(){
        return x + y;
    }


}
