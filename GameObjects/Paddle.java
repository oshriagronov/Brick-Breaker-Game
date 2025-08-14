package GameObjects;
import javax.swing.ImageIcon;
public class Paddle{
    private static final String ICON_PATH = "assets/paddle.png";
    private static final int WIDTH = 210;
    private static final int HEIGHT = 34;
    private final int SPEED = 20;
    private static ImageIcon paddleIcon;
    private int x;
    private int y;
    // paddle constructor.
    public Paddle(int x, int y){
        paddleIcon = new ImageIcon(ICON_PATH);
        this.x = x;
        this.y = y;
    }

    // the function return the paddle width(pixels).
    public static int getWidth() {
        return WIDTH;
    }

    // the function return the paddle height(pixels).
    public static int getHeight() {
        return HEIGHT;
    }

    // the function return the x point of the paddle at the moment.
    public int getX(){
        return x;
    }
    
    // here the program give paddle object updated x coordinates after calculation of moving.
    public void setX(int x) {
        this.x = x;
    }
    // the function return the y point of the paddle at the moment.
    public int getY(){
        return y;
    }

    // the function return the paddle icon as ImageIcon.
    public static ImageIcon getIcon(){
        return paddleIcon;
    }

    // the function return the ball icon path(string).
    public static String getIconPath(){
        return ICON_PATH;
    }

    // the function return moving speed of the paddle(pixel unit).
    public int getPaddleSpeed(){
        return SPEED;
    }


}
