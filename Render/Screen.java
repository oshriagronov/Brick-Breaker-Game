package Render;
import javax.swing.JFrame;
import javax.swing.JLabel;
import GameObjects.Brick;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class Screen extends JFrame{
    // screen variables(basically the resolution).
    public final static int window_height = 720;
    public final static int window_width = 1280;
    // Icon variables
    private final String icon_path = "assets/icon.jpeg";
    private final String background_path = "assets/DARK_BG2_small.jpeg";
    private final String manu_icon_path = "assets/brick breaker menu.png";
    private final String wining_logo_path = "assets/wining logo.png";
    private final String gameover_logo_path = "assets/gameover logo.png";
    // Labels variables
    public JLabel paddle_label;
    public JLabel ball_label;
    private JLabel background_label;
    private JLabel [] heart_label;
    private JLabel player_score;
    private JLabel [] brick_labels;
    private JLabel manu_logo_label;
    private JLabel wining_logo_label;
    private JLabel gameover_logo_label;
    
// ## screen constructor.
    public Screen(){
        //  start the basic screen.
        this.setTitle("Brick Breaker");
        ImageIcon icon = new ImageIcon(icon_path);
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setSize(new Dimension(window_width, window_height));
        // setting a label as a background to the game.
        background_label = new JLabel();
        background_label.setIcon(new ImageIcon(background_path));
        background_label.setLayout(null);
        background_label.setBounds(0, 0, window_width, window_height);
        // here we adding the background label to the jframe panel.
        this.add(background_label);
        this.setVisible(true);
    }
// ## the start manu screen
    //the function print the logo and text, waiting for user input to start the game.
    public void manu_screen(){
        manu_logo_label = new JLabel(new ImageIcon(manu_icon_path));
        manu_logo_label.setBounds((window_width / 2) - 400, 0, 800, 279);
        JLabel manu_text = new JLabel("press any key to start!");
        manu_text.setBounds(window_width / 3 + 90, window_height - 500, 600, 600);
        manu_text.setFont(new Font("Serif", Font.BOLD, 25));
        manu_text.setForeground(Color.MAGENTA);
        background_label.add(manu_logo_label);
        background_label.add(manu_text);
        background_label.revalidate(); 
        background_label.repaint();
    }

// ## add/remove/update labels section.
    // the function add labels of ball or paddle, as the user ask.
    public void addLabels(String name,ImageIcon icon, int x, int y, int width, int height){
        switch(name){
            case "paddle":
            paddle_label = new JLabel(icon, JLabel.CENTER);
            paddle_label.setBounds(x, y, width, height);
            background_label.add(paddle_label);
            break;

            case "ball":
            ball_label = new JLabel(icon, JLabel.CENTER);
            ball_label.setBounds(x, y, width, height);
            background_label.add(ball_label);
            break;
        }
        background_label.revalidate(); 
        background_label.repaint();
    }
    // add bricks to the screen and load array of brick labels as well.
    public void addBricksLabels(Brick [] brick_array, int num_of_bricks){
        this.brick_labels = new JLabel[num_of_bricks];
        for(int i = 0; i < num_of_bricks; i++){
            JLabel brick_label = new JLabel(brick_array[i].get_icon(), JLabel.CENTER);
            brick_label.setBounds(brick_array[i].get_x(), brick_array[i].get_y(), brick_array[i].get_width(), brick_array[i].get_height());
            brick_labels[i] = brick_label;
            background_label.add(brick_labels[i]);
        }
        background_label.revalidate(); 
        background_label.repaint();
    }
    // the function remove the brick that got hit, and also remove it from the array of the label bricks.
    public void brick_destroy(int brick_num){
        background_label.remove(brick_labels[brick_num]);
        // if it's the last brick there no need to copy the array minus 1, because the game is over.
        if(brick_labels.length == 1){
            background_label.revalidate(); 
            background_label.repaint();
        }
        // if it's not the last brick than we need to copy the new array minus the brick that got hit and destroyed, so we keep on track of the amount of bricks.
        else{
            JLabel [] copy = new JLabel[brick_labels.length - 1];
            for (int x = 0, j = 0; x < brick_labels.length; x++) {
                if (x != brick_num) {
                    copy[j++] = brick_labels[x];
                }
            }
            brick_labels = new JLabel [copy.length - 1];
            brick_labels = copy;
            background_label.revalidate(); 
            background_label.repaint();
        }
    }

    // the function add the player score to the background.
    public void add_player_score(int score){
        String score_string = "score: " + score;
        player_score = new JLabel(score_string);
        player_score.setBounds(25, window_height - 100, 200, 100);
        player_score.setFont(new Font("Serif", Font.PLAIN, 25));
        player_score.setForeground(Color.BLACK);
        background_label.add(player_score);
        background_label.revalidate(); 
        background_label.repaint();
    }

    public void refresh_player_score(int score){
        String score_string = "score:" + score;
        player_score.setText("score: " + score);
        background_label.revalidate(); 
        background_label.repaint();
    }

    // the function create array load with heart icon(the number is in object player), and add them to the background.
    public void addHeartLabels(int num_of_tries, ImageIcon icon, int x, int y, int width, int height){
        this.heart_label = new JLabel [num_of_tries];
        // making array of heart labels to manage life point of the player and represent them on screen.
        for(int i = 0; i < num_of_tries; i++){
            heart_label[i] = new JLabel(icon,JLabel.CENTER);
            heart_label[i].setBounds((x * i), y, width, height);
            background_label.add(heart_label[i]);
        }
        background_label.revalidate(); 
        background_label.repaint();
    }
    // the function remove one heart label from the background at every call of the function.
    public void remove_HeartLabel(int num){
        background_label.remove(heart_label[num]);
        background_label.revalidate(); 
        background_label.repaint();
    }

// ## other screens functions, wining/losing/clear.
    // if the user wining, the function print a logo of "wining".
    public void wining_screen(){
        wining_logo_label = new JLabel(new ImageIcon(wining_logo_path));
        wining_logo_label.setBounds((window_width / 2) - 235, 100, 500, 435);
        background_label.add(wining_logo_label);
        background_label.revalidate(); 
        background_label.repaint();
    }
    // if the user lose, the function print a logo of "Game over"
    public void gameover_screen(){
        gameover_logo_label = new JLabel(new ImageIcon(gameover_logo_path));
        gameover_logo_label.setBounds((window_width / 2) - 275, 0, 623, 580);
        background_label.add(gameover_logo_label);
        background_label.revalidate(); 
        background_label.repaint();
    }

    // general function of clear the screen of all labels, empty screen with background.
    public void clear_screen(){
        background_label.removeAll();
        background_label.revalidate(); 
        background_label.repaint();
    }
}