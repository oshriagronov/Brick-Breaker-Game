package Render;
import javax.swing.JFrame;
import javax.swing.JLabel;
import GameObjects.Brick;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
public class Screen extends JFrame{
    // screen variables(basically the resolution).
    public final static int WINDOW_HEIGHT = 720;
    public final static int WINDOW_WIDTH = 1280;
    // Icon variables
    private final String ICON_PATH = AssetPaths.ICON_PATH;
    private final String BACKGROUND_PATH = AssetPaths.BACKGROUND_PATH;
    private final String MENU_ICON_PATH = AssetPaths.MENU_ICON_PATH;
    private final String WINING_ICON_PATH = AssetPaths.WINING_ICON_PATH;
    private final String GAME_OVER_ICON_PATH = AssetPaths.GAME_OVER_ICON_PATH;
    // Labels variables
    public JLabel paddleLabel;
    public JLabel ballLabel;
    private JLabel backgroundLabel;
    private JLabel [] heartLabels;
    private JLabel playerScore;
    private JLabel [] brickLabels;
    private JLabel menuLogoLabel;
    private JLabel winingLogoLabel;
    private JLabel gameOverLogoLabel;
// ## screen constructor.
    public Screen(){
        //  start the basic screen.
        this.setTitle("Brick Breaker");
        ImageIcon icon = new ImageIcon(ICON_PATH);
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        // setting a label as a background to the game.
        backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon(BACKGROUND_PATH));
        backgroundLabel.setLayout(null);
        backgroundLabel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        // here we adding the background label to the jframe panel.
        this.add(backgroundLabel);
        this.setVisible(true);
    }
// ## the start menu screen
    //the function print the logo and text, waiting for user input to start the game.
    public void menuScreen(){
        menuLogoLabel = new JLabel(new ImageIcon(MENU_ICON_PATH));
        menuLogoLabel.setBounds((WINDOW_WIDTH / 2) - 400, 0, 800, 279);
        JLabel menuText = new JLabel("press any key to start!");
        menuText.setBounds(WINDOW_WIDTH / 3 + 90, WINDOW_HEIGHT - 500, 600, 600);
        menuText.setFont(new Font("Serif", Font.BOLD, 25));
        menuText.setForeground(Color.MAGENTA);
        backgroundLabel.add(menuLogoLabel);
        backgroundLabel.add(menuText);
        backgroundLabel.revalidate(); 
        backgroundLabel.repaint();
    }

// ## add/remove/update labels section.
    // the function add labels of ball or paddle, as the user ask.
    public void addLabels(String name,ImageIcon icon, int x, int y, int width, int height){
        switch(name){
            case "paddle":
            paddleLabel = new JLabel(icon, JLabel.CENTER);
            paddleLabel.setBounds(x, y, width, height);
            backgroundLabel.add(paddleLabel);
            break;

            case "ball":
            ballLabel = new JLabel(icon, JLabel.CENTER);
            ballLabel.setBounds(x, y, width, height);
            backgroundLabel.add(ballLabel);
            break;
        }
        backgroundLabel.revalidate(); 
        backgroundLabel.repaint();
    }
    // add bricks to the screen and load array of brick labels as well.
    public void addBricksLabels(List <Brick> brick_array, int numOfBricks){
        this.brickLabels = new JLabel[numOfBricks];
        for(int i = 0; i < numOfBricks; i++){
            JLabel brick_label = new JLabel(brick_array.get(i).getIcon(), JLabel.CENTER);
            brick_label.setBounds(brick_array.get(i).getX(), brick_array.get(i).getY(), Brick.getWidth(), Brick.getHeight());
            brickLabels[i] = brick_label;
            backgroundLabel.add(brickLabels[i]);
        }
        backgroundLabel.revalidate(); 
        backgroundLabel.repaint();
    }
    // the function remove the brick that got hit, and also remove it from the array of the label bricks.
    public void brickDestroy(int brickIndex){
        backgroundLabel.remove(brickLabels[brickIndex]);
        // if it's the last brick there no need to copy the array minus 1, because the game is over.
        if(brickLabels.length == 1){
            backgroundLabel.revalidate(); 
            backgroundLabel.repaint();
        }
        // if it's not the last brick than we need to copy the new array minus the brick that got hit and destroyed, so we keep on track of the amount of bricks.
        else{
            JLabel [] copy = new JLabel[brickLabels.length - 1];
            for (int x = 0, j = 0; x < brickLabels.length; x++) {
                if (x != brickIndex) {
                    copy[j++] = brickLabels[x];
                }
            }
            brickLabels = new JLabel [copy.length - 1];
            brickLabels = copy;
            backgroundLabel.revalidate(); 
            backgroundLabel.repaint();
        }
    }

    // the function add the player score to the background.
    public void addPlayerScore(int score){
        playerScore = new JLabel("score: " + score);
        playerScore.setBounds(25, WINDOW_HEIGHT - 100, 200, 100);
        playerScore.setFont(new Font("Serif", Font.PLAIN, 25));
        playerScore.setForeground(Color.BLACK);
        backgroundLabel.add(playerScore);
        backgroundLabel.revalidate(); 
        backgroundLabel.repaint();
    }

    public void refreshPlayerScore(int score){
        playerScore.setText("score: " + score);
        backgroundLabel.revalidate(); 
        backgroundLabel.repaint();
    }

    // the function create array load with heart icon(the number is in object player), and add them to the background.
    public void addHeartLabels(int numOfTries, ImageIcon icon, int x, int y, int width, int height){
        heartLabels = new JLabel [numOfTries];
        // making array of heart labels to manage life point of the player and represent them on screen.
        for(int i = 0; i < numOfTries; i++){
            heartLabels[i] = new JLabel(icon,JLabel.CENTER);
            heartLabels[i].setBounds((x * i), y, width, height);
            backgroundLabel.add(heartLabels[i]);
        }
        backgroundLabel.revalidate(); 
        backgroundLabel.repaint();
    }
    // the function remove one heart label from the background at every call of the function.
    public void removeHeartLabel(int num){
        backgroundLabel.remove(heartLabels[num]);
        backgroundLabel.revalidate(); 
        backgroundLabel.repaint();
    }

// ## other screens functions, wining/losing/clear.
    // if the user wining, the function print a logo of "wining".
    public void winingScreen(){
        winingLogoLabel = new JLabel(new ImageIcon(WINING_ICON_PATH));
        winingLogoLabel.setBounds((WINDOW_WIDTH / 2) - 235, 100, 500, 435);
        backgroundLabel.add(winingLogoLabel);
        backgroundLabel.revalidate(); 
        backgroundLabel.repaint();
    }
    // if the user lose, the function print a logo of "Game over"
    public void gameOverScreen(){
        gameOverLogoLabel = new JLabel(new ImageIcon(GAME_OVER_ICON_PATH));
        gameOverLogoLabel.setBounds((WINDOW_WIDTH / 2) - 275, 0, 623, 580);
        backgroundLabel.add(gameOverLogoLabel);
        backgroundLabel.revalidate(); 
        backgroundLabel.repaint();
    }

    // general function of clear the screen of all labels, empty screen with background.
    public void clearScreen(){
        backgroundLabel.removeAll();
        backgroundLabel.revalidate(); 
        backgroundLabel.repaint();
    }
}