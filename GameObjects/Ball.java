package GameObjects;
import Render.Screen;
import javax.swing.ImageIcon;
public class Ball {
    private final int defult_x = Screen.window_width / 2;
    private final int defult_y = Screen.window_height / 2;
    private static ImageIcon ball_icon;
    private final static String iconPath = "assets/ball.png";
    private final int width = 53;
    private final int height = 53;
    private int x;
    private int y;
    private int x_velocity = 3; // This is how much pixels the ball add every movement, the bigger number he is, the faster the ball will be.
    private int y_velocity = 3; // This is how much pixels the ball add every movement, the bigger number he is, the faster the ball will be.
    private int moving_spped = 1; // The smaller number, the faster the ball movement will be(it's in timer.start()).

    public Ball(){
        ball_icon = new ImageIcon(iconPath);
        x = defult_x;
        y = defult_y;
    }

    // the function return the ball width(pixels).
    public int get_width() {
        return width;
    }

    // the function return the ball height(pixels).
    public int get_height() {
        return height;
    }

    // the function return the x point of the ball at the moment.
    public int get_x(){
        return x;
    }

    // the function return the y point of the ball at the moment.
    public int get_y(){
        return y;
    }

    // the function return the ball icon as ImageIcon.
    public ImageIcon get_icon(){
        return ball_icon;
    }

    // the function return path of the icon image of the ball.
    public static String get_iconPath(){
        return iconPath;
    }

    // the function return ball x velocity(speed) in pixels as unit.
    public int get_ball_x_velocity(){
        return x_velocity;
    }

    // the function return ball x velocity(speed) in pixels as unit.
    public int get_ball_y_velocity(){
        return y_velocity;
    }

    // the function return "speed" of the ball but it more like the time the function will "refresh" and check the ball course(units in msec).
    public int get_moving_speed(){
        return moving_spped;
    }

    // here the program give ball object updated x and y coordinates after calculation of moving/collision.
    public void set_new_cor(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
