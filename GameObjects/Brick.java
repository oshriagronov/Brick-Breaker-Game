package GameObjects;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
public class Brick{
    private final static int width = 190;
    private final static int height = 50;
    private final static String iconPath = "assets/brick.png";
    private static ImageIcon brick_icon;
    private int y = 50; // this value deicide where the line of bricks will start.
    private int x;
    private Rectangle rectangle_brick;

    // bricks object constructor.
    public Brick(int x){
        brick_icon = new ImageIcon(iconPath);
        this.x = x;
        rectangle_brick = new Rectangle(x, y, width, height);
    }

    // the function return the width in pixel units.
    public static int get_width() {
        return width;
    }
    
    // the function return the height in pixel units.
    public static int get_height() {
        return height;
    }

    // the function return the right side x coordinate in pixel units.
    public int get_x(){
        return x;
    }

    // the function return the y where bricks line start on screen(pixel units).
    public int get_y(){
        return y;
    }
    // the function return Rectangle object of the brick
    public Rectangle get_rectangle_brick(){
        return rectangle_brick;
    }

    // the function return the ball icon as ImageIcon.
    public ImageIcon get_icon(){
        return brick_icon;
    }

    // the function return path of the icon image of the ball.
    public static String get_iconPath(){
        return iconPath;
    }
    @Override
    public boolean equals(Object other){
        if(other instanceof Brick){
            Brick brick = (Brick)other;
            if(x == brick.get_x() && y == brick.get_y()){
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
