package GameObjects;
import javax.swing.ImageIcon;

import Render.AssetPaths;
public class Ball {
    private final int SPEED = 1; // The smaller number, the faster the ball movement will be(it's in timer.start()).
    private static final String ICON_PATH = AssetPaths.BALL_ICON_PATH;
    private static final int WIDTH = 53;
    private static final int HEIGHT = 53;
    private static ImageIcon ballIcon;
    private int x;
    private int y;
    private int xVelocity = 1; // This is how much pixels the ball add every movement, the bigger number he is, the faster the ball will be.
    private int yVelocity = 1; // This is how much pixels the ball add every movement, the bigger number he is, the faster the ball will be.

    public Ball(int x, int y){
        ballIcon = new ImageIcon(ICON_PATH);
        this.x = x;
        this.y = y;
    }

    // the function return the ball width(pixels).
    public static int getWidth() {
        return WIDTH;
    }

    // the function return the ball height(pixels).
    public static int getHeight() {
        return HEIGHT;
    }

    // the function return the x point of the ball at the moment.
    public int getX(){
        return x;
    }

    // the function return the y point of the ball at the moment.
    public int getY(){
        return y;
    }

    // the function return the ball icon as ImageIcon.
    public static ImageIcon getIcon(){
        return ballIcon;
    }

    // the function return path of the icon image of the ball.
    public static String getIconPath(){
        return ICON_PATH;
    }

    // the function return ball x velocity(speed) in pixels as unit.
    public int getBallXVelocity(){
        return xVelocity;
    }
    public void setBallXVelocity(int xVelocity){
        this.xVelocity = xVelocity;
    }
    // the function return ball x velocity(speed) in pixels as unit.
    public int getBallYVelocity(){
        return yVelocity;
    }
    public void setBallYVelocity(int yVelocity){
        this.yVelocity = yVelocity;
    }
    // the function return "speed" of the ball but it more like the time the function will "refresh" and check the ball course(units in msec).
    public int getBallSpeed(){
        return SPEED;
    }

    // here the program give ball object updated x and y coordinates after calculation of moving/collision.
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
