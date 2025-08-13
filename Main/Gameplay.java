package Main;
import Render.Screen;
import GameObjects.Paddle;
import GameObjects.Ball;
import GameObjects.Brick;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.Timer;

public class Gameplay implements KeyListener, ActionListener{
    private GameEndListener gameEndListener;
    private Player player;
    private Screen screen;
    private SoundEffect sound_effect;
    private Paddle paddle;
    private Ball ball;
    private List <Brick> brick_array;
    private Timer timer; // we use timer to make an interrupt every some amount of time to make the ball move and check for collisions.
    // ball basic values for calculating.
    private int ball_screen_collision_x; // the screen width edge that if the ball reach it, the ball will bounce.
    private int ball_moving_speed; // The smaller number, the faster the ball movement will be(it's in timer.start()).
    private int ball_x_velocity;
    private int ball_y_velocity;
    // paddle basic values for calculating.
    private int paddle_screen_left_limit; // the screen left edge that if the paddle will reach it, the paddle couldn't pass it.
    private int paddle_screen_right_limit; // the screen right edge that if the paddle will reach it, the paddle couldn't pass it.
    // the hegiht where the ball considered missed and the player will lose life point.
    private int miss_height;
    private Rectangle ballBounds = new Rectangle();
    private Rectangle paddleBounds = new Rectangle();
// ## Gameplay constructor.
    public Gameplay(Player player, Screen screen, SoundEffect sound_effect, Ball ball, Paddle paddle, List <Brick> brick_array){
        // get all objects related to Gameplay mechanic system.
        this.player = player;
        this.screen = screen;
        screen.addKeyListener(this);
        this.sound_effect = sound_effect;
        this.ball = ball;
        this.paddle = paddle;
        this.brick_array = brick_array;
        // calculating final values for collision/screen limit. 
        ball_screen_collision_x = Screen.window_width - ball.get_width();
        paddle_screen_left_limit = 0;
        paddle_screen_right_limit = Screen.window_width - paddle.get_width();
        // get the ball moving speeds in x,y(pixels)
        ball_x_velocity = ball.get_ball_x_velocity();
        ball_y_velocity = ball.get_ball_y_velocity();
        // get the amount of time for every movement on screen("refresh")
        ball_moving_speed = ball.get_moving_speed();
        // calculating final values of hitting objects(or missing).
        miss_height = Screen.window_height - ball.get_height();

    }
    // a listener when the game end and inform the gamemanger
    public void setGameEndListener(GameEndListener listener) {
        this.gameEndListener = listener;
    }
//## main section of the run.
    public void run(){        
        // the parameter "ball speed" it's milliseconds as int for the timer(it's just named speed).
        timer = new Timer(ball_moving_speed, this);
        // starting the timer for the ball movement.
        timer.start();
    }

//## The timer loop of checking the game section(here all the function to check all systems).

    // Moving ball function with collision check and play the sound effect every collision.
    @Override
    public void actionPerformed(ActionEvent e) {
        int ball_position_x = ball.get_x();
        int ball_position_y = ball.get_y();
        ballBounds.setBounds(ball_position_x, ball_position_y, ball.get_width(), ball.get_height());
        paddleBounds.setBounds(paddle.get_x(), paddle.get_y(), paddle.get_width(), paddle.get_height());
        // checking if the game is over(all bricks destroyed/0 life point).
        if(!isGameOver()){
            // check if the player missed the ball, if true the player will lose 1 life point
            if(is_player_missed(ball_position_y)){
                ball.set_new_cor(Screen.window_width / 2, Screen.window_height / 2);
                //**lose life points and end game mechanic, disabled for testing**
                //screen.remove_HeartLabel(player.get_life_points() - 1);
                //player.lose_life_point();
            }
            // checking if the ball was hitting the paddle 
            else if(ballBounds.intersects(paddleBounds)){
                ball_bounce_y();
                sound_effect.play_collision_soundEffect();
            }
            
            // Here we checking if the ball was hitting the bricks 
            else if(is_BrickCollision(ball_position_x)){
                player.add_score();
                screen.refresh_player_score(player.get_score());
                sound_effect.play_brick_collision_soundEffect();
            }
            
            else if(ball_position_x >= ball_screen_collision_x || ball_position_x < 0){
                ball_bounce_x();
                sound_effect.play_collision_soundEffect();
            }
            else if(ball_position_y < 0){
                ball_bounce_y();
                sound_effect.play_collision_soundEffect();
            }
            ball_moving();
            // After all the checking conditions, we get the ball the new coordinates and the screen object will move the label "on the screen".
            screen.ball_label.setLocation(ball.get_x(), ball.get_y());
        }
        else{
            timer.stop();
            if (gameEndListener != null) {
                gameEndListener.onGameEnd();
            }
        }
    }

// ##Section of all Ball movement functions.
    // the function give the ball new values to move at same direction.
    private void ball_moving(){
        ball.set_new_cor(ball.get_x() + ball_x_velocity, ball.get_y() + ball_y_velocity);
    }
    // the function make the x velocity negative/positive so the ball will bounce when hitting object at left or right part.
    private void ball_bounce_x(){
        ball_x_velocity = ball_x_velocity * (-1);
    }
    // the function make the y velocity negative/positive so the ball will bounce when hitting object at upper or button part.
    private void ball_bounce_y(){
        ball_y_velocity = ball_y_velocity * (-1);
    }

// ## Section of all the collision related function.

    // the function check if ball was hitting one of the bricks.
    private boolean is_BrickCollision(int ball_position_x){
        ballBounds.setBounds(ball_position_x, ball.get_y(), ball.get_width(), ball.get_height());
        for(Brick brick: brick_array){
            Rectangle brick_r = brick.get_rectangle_brick();
            if(ballBounds.intersects(brick_r)){
                if(ballBounds.x + ballBounds.width <= brick_r.x || ballBounds.x + 1 >= brick_r.x + brick_r.width){
                    ball_bounce_x();
                }
                else{
                    ball_bounce_y();
                }
                ball_moving();
                screen.brick_destroy(brick_array.indexOf(brick));
                brick_array.remove(brick);
                return true;
            }
        }
        return false;
    }

// ## Section of all the functions that check conditions for the program flow.

    // Checking if the game is over(wining or losing).
    private boolean isGameOver(){
        // if it the last brick than the player won.
        if( brick_array.size() == 0){
            screen.brick_destroy(0);
            return true;
        }
        // if the player lost all his life points.
        else if(player.get_life_points() == 0){
            return true;
        }
        else{
            return false;
        }
    }

    // need to change the name and add comment.
    public boolean is_gameInprogress(){
        return timer.isRunning(); // return false if the timer stopped(game over or win), return true if the game still in progress(didn't lose or wining).
    }

    // checking if the player missed the ball with the paddle.
    private boolean is_player_missed(int ball_position_y){
        if(ball_position_y > miss_height){
            return true;
        }
        else{
            return false;
        }
    }


    //## Here is the key controlling keys system
    // moving paddle function.
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        // 'a'/'A'/'<' key was pressed.
        int paddle_position_x = paddle.get_x();
        if(paddle_position_x > paddle_screen_left_limit && (key == 65 || key == 37)){
            paddle.set_new_cor(paddle_position_x - paddle.get_moving_speed());
        }

        // 'd'/'D'/'>' key was pressed.
        else if((paddle_position_x < paddle_screen_right_limit) && (key == 68 || key == 39)){
            paddle.set_new_cor(paddle_position_x + paddle.get_moving_speed());
        }
        screen.paddle_label.setLocation(paddle.get_x(), paddle.get_y());
    }
    // we don't use those methods because we don't any use with them, so i was let them be empty.
    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

}