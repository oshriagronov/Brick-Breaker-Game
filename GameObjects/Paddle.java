package GameObjects;
import javax.swing.ImageIcon;
import Render.Screen;

public class Paddle{
    private static ImageIcon paddle_icon;
    private final static String iconPath = "assets/paddle.png";
    private final int defult_x = (Screen.window_width / 2) - 100;
    private final int defult_y = Screen.window_height - 70;
    private final int width = 210;
    private final int height = 34;
    private int x;
    private int y;
    private final int moving_speed = 20;
    
    // paddle constructor.
    public Paddle(){
        paddle_icon = new ImageIcon(iconPath);
        x = defult_x;
        y = defult_y;

    }

    // the function return the paddle width(pixels).
    public int get_width() {
        return width;
    }

    // the function return the paddle height(pixels).
    public int get_height() {
        return height;
    }

    // the function return the x point of the paddle at the moment.
    public int get_x(){
        return x;
    }

    // the function return the y point of the paddle at the moment.
    public int get_y(){
        return y;
    }

    // the function return the paddle icon as ImageIcon.
    public ImageIcon get_icon(){
        return paddle_icon;
    }

    // the function return the ball icon path(string).
    public static String get_iconPath(){
        return iconPath;
    }

    // the function return moving speed of the paddle(pixel unit).
    public int get_moving_speed(){
        return moving_speed;
    }

    // here the program give paddle object updated x coordinates after calculation of moving.
    public void set_new_cor(int x) {
        this.x = x;
    }

}
