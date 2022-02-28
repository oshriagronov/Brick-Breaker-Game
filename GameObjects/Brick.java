package GameObjects;
import javax.swing.ImageIcon;

public class Brick {
    private final static int width = 190;
    private final static int height = 50;
    private final static String iconPath = "assets/brick.png";
    private static ImageIcon brick_icon;
    private int y = 50; // this value deicide where the line of bricks will start.
    private int x;

    // bricks object constructor.
    public Brick(int x){
        brick_icon = new ImageIcon(iconPath);
        this.x = x;
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

    // the function return the ball icon as ImageIcon.
    public ImageIcon get_icon(){
        return brick_icon;
    }

    // the function return path of the icon image of the ball.
    public static String get_iconPath(){
        return iconPath;
    }
}
