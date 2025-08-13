package Main;
import Render.*;
import GameObjects.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameManager implements KeyListener{
    private Screen screen;
    private SoundEffect sound_effect;
    private Paddle paddle;
    private Ball ball;
    private List<Brick> brick_array;
    private int num_of_bricks;
    private int bricks_gap;
    private Player player;
    private int life_points = 3; // number of tries the player have before loosing.
    private int score_points = 100; // the points the player get for every brick he break.
    private boolean key_pressed; // true == player pressed a key, false == player didn't pressed anything.

// ## game manager constructor.
    public GameManager(){
        //the function check if all the needed files exists, if not a error window will popup.
        if(CheckPath()){
            return;
        }
        // here we create all the needed objects.
        screen = new Screen();
        paddle = new Paddle();
        ball = new Ball();
        player = new Player(life_points, score_points);
        // we do this calculation to check how much bricks we can add to the screen in one line.
        num_of_bricks = Screen.window_width / Brick.get_width();
        brick_array = new ArrayList<>();
        bricks_gap = ((Screen.window_width - (num_of_bricks * Brick.get_width())) / num_of_bricks) / 2;
        screen.addKeyListener(this);
    }

// ## main section.
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.start();
    }

// ## game start section.  
    public void start(){
        //here we wait for the user to press any key to start the game.
        screen.manu_screen();
        while(true){
            if(key_pressed == true){
                break;
            }
            System.out.println();
        }
        screen.clear_screen();
        screen.removeKeyListener(this);
        // creating bricks with the necessary coordinates, that stored in array of brick object(with all the information needed to detect a ball collision with the brick).
        int x_brick_location = 0;
        for(int i = 0; i < num_of_bricks; i++){
            x_brick_location += bricks_gap;
            brick_array.add(new Brick(x_brick_location));
            x_brick_location += Brick.get_width() + bricks_gap;
        }
        // adding the objects to the screen that include: ball, paddle, bricks etc...
        screen.addLabels("paddle", paddle.get_icon(), paddle.get_x(), paddle.get_y(), paddle.get_width(), paddle.get_height());
        screen.addLabels("ball", ball.get_icon(), ball.get_x(), ball.get_y(), ball.get_width(), ball.get_height());
        screen.addHeartLabels(player.get_life_points(), player.get_heart_icon(), player.get_heart_x(), player.get_heart_y(), player.get_heart_width(), player.get_heart_height());
        screen.addBricksLabels(brick_array, num_of_bricks);
        screen.add_player_score(player.get_score());
        // here we creating the gameplay object to control the objects and basically let the game run.
        Gameplay gameplay = new Gameplay(player, screen, sound_effect, ball, paddle, brick_array);
        gameplay.run();
        // this loop check if the user lose or win, depends on the number of life point the player have(0 == gameover, (0 < life points) == wining).
        while(true){
            System.out.println();
            if(player.get_life_points() == 0 && !gameplay.is_gameInprogress()){
                screen.clear_screen();
                screen.gameover_screen();
                break;
            }

            else if(player.get_life_points() != 0 && !gameplay.is_gameInprogress()){
                screen.clear_screen();
                screen.wining_screen();
                break;
            }
        }
        
    }

// ## keys input section.
    // here we check if the user pressed any key(and then the user can start playing).
    @Override
    public void keyPressed(KeyEvent e) {
        key_pressed = true; 
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

// ## possible errors checking.

    // check if all the needed files exists.
    public boolean CheckPath(){
        Path path;
        path = Paths.get(Paddle.get_iconPath());
        if(!Files.exists(path)){
            new ErrorWindow("The paddle png path isn't correct or the file doesn't exists");
            return true;
        }
        path = Paths.get(Ball.get_iconPath());
        if(!Files.exists(path)){
            new ErrorWindow("The ball png path isn't correct or the file doesn't exists");
            return true;
        }
        path = Paths.get(Brick.get_iconPath());
        if(!Files.exists(path)){
            new ErrorWindow("The bricks png path isn't correct or the file doesn't exists");
            return true;
        }
        path = Paths.get(Player.get_heart_iconPath());
        if(!Files.exists(path)){
            new ErrorWindow("The bricks png path isn't correct or the file doesn't exists");
            return true;
        }
        // sound effect try and catch section, need to add popup error if the files does'nt found.
        try {
            sound_effect = new SoundEffect();
        } catch (LineUnavailableException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            new ErrorWindow("Something went wrong.");
        } catch (UnsupportedAudioFileException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            new ErrorWindow("The one of the audio files is unsupported!");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            new ErrorWindow("The audio file path isn't correct or the file not founded");
        }
        return false;
    }
}
