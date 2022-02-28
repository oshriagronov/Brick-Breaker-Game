package Main;
import javax.swing.ImageIcon;

public class Player {
    private int life_points; // The amount of tries the player will have.
    private int score = 0; // The score the user got in the game(started at 0 and with every brick he break he will get more).
    private int score_amount; // amount of points to give the player for every brick he will break.
    private static ImageIcon heart_icon;
    private final static String iconPath = "assets/heart.png";
    private final int heart_y_position;
    private final int heart_x_position;
    private final int heart_width = 50;
    private final int heart_height = 50;

// ## player constructor.
    //  the constructor need to get the number of life point he player will have and the score bonus for every brick he will break.
    public Player(int life_points, int score_amount){
        this.life_points = life_points; // The game manager will decide the amount of tries to give the player.
        this.score_amount = score_amount; // The game manager will decide the amount of points to give the player for every brick he will break.
        heart_icon = new ImageIcon(iconPath);
        heart_y_position = 0;
        heart_x_position = heart_width;
    }


    // the user lose one life point
    public void lose_life_point(){
        life_points -= 1;
    }

    // Add point to player for every brick he will break, the amount will be decided by game manager.
    public void add_score(){
        score += score_amount;
    }

    // give the score of the player
    public int get_score(){
        return score;
    }
        
    // give the number of player life points.
    public int get_life_points(){
        return life_points;
    }

    // give the heart width logo
    public int get_heart_width() {
        return heart_width;
    }

    // give the heart height logo
    public int get_heart_height() {
        return heart_height;
    }

    // give the heart x coordinate logo
    public int get_heart_x(){
        return heart_x_position;
    }

    // give the heart y coordinate logo
    public int get_heart_y(){
        return heart_y_position;
    }

    // give the heart logo
    public ImageIcon get_heart_icon(){
        return heart_icon;
    }

    // give the heart logo path string
    public static String get_heart_iconPath(){
        return iconPath;
    }
}
