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
    // ball basic values for calculating.
    private final int BALL_SCREEN_COLLISION_X = Screen.window_width - Ball.getWidth();; // the screen width edge that if the ball reach it, the ball will bounce.
    private final int PADDLE_SCREEN_LEFT_LIMIT = 0; // the screen left edge that if the paddle will reach it, the paddle couldn't pass it.
    private final int PADDLE_SCREEN_RIGHT_LIMIT = Screen.window_width - Paddle.get_width(); // the screen right edge that if the paddle will reach it, the paddle couldn't pass it.
    // the height where the ball considered missed and the player will lose life point.
    private final int MISS_HEIGHT = Screen.window_height - Ball.getHeight();
    private GameEndListener gameEndListener;
    private Player player;
    private Screen screen;
    private SoundEffect soundEffect;
    private Paddle paddle;
    private Ball ball;
    private List <Brick> brickArrayList;
    private Timer timer; // we use timer to make an interrupt every some amount of time to make the ball move and check for collisions.
    // paddle basic values for calculating.
    private Rectangle ballBounds;
    private Rectangle paddleBounds;
// ## Gameplay constructor.
    public Gameplay(Player player, Screen screen, SoundEffect soundEffect, Ball ball, Paddle paddle, List <Brick> brickArrayList){
        // get all objects related to Gameplay mechanic system.
        screen.addKeyListener(this);
        this.player = player;
        this.screen = screen;
        this.soundEffect = soundEffect;
        this.ball = ball;
        this.paddle = paddle;
        this.brickArrayList = brickArrayList;
        // get the ball moving speeds in x,y(pixels)
        ballBounds = new Rectangle();
        paddleBounds = new Rectangle();

    }
    // a listener when the game end and inform the gamemanger
    public void setGameEndListener(GameEndListener listener) {
        this.gameEndListener = listener;
    }
    //## main section of the run.
        public void run(){        
            // the parameter "ball speed" it's milliseconds as int for the timer(it's just named speed).
            timer = new Timer(ball.getBallSpeed(), this);
            // starting the timer for the ball movement.
            timer.start();
        }

//## The timer loop of checking the game section(here all the function to check all systems).
    // Moving ball function with collision check and play the sound effect every collision.
    @Override
    public void actionPerformed(ActionEvent e) {
        // checking if the game is over(all bricks destroyed/0 life point).
        if(!isGameOver()){
            // check if the player missed the ball, if true the player will lose 1 life point
            if(is_player_missed(ball.getY())){
                ball.setPosition(Screen.window_width / 2, Screen.window_height / 2);
                //**lose life points and end game mechanic, disabled for testing**
                //screen.remove_HeartLabel(player.get_life_points() - 1);
                //player.lose_life_point();
            }
            else
                ball_movement();
        }
        else{
            timer.stop();
            if (gameEndListener != null) {
                gameEndListener.onGameEnd();
            }
        }
    }

// ##Section of all Ball movement functions.
    // the function make the x velocity negative/positive so the ball will bounce when hitting object at left or right part.
    private void ball_bounce_x(){
        ball.setBallXVelocity(ball.getBallXVelocity() * (-1)) ;
    }
    // the function make the y velocity negative/positive so the ball will bounce when hitting object at upper or button part.
    private void ball_bounce_y(){
        ball.setBallYVelocity(ball.getBallYVelocity() * (-1));
    }
    // ## Section of all the collision related function.
    private void ball_movement(){
        ballBounds.setBounds(ball.getX(), ball.getY(), Ball.getWidth(), Ball.getHeight());
        paddleBounds.setBounds(paddle.get_x(), paddle.get_y(), Paddle.get_width(), Paddle.get_height());
        // checking if the ball was hitting the paddle 
        if(obj_collision(ballBounds, paddleBounds)){
            soundEffect.play_collision_soundEffect();
        }
        
        // if the ball was hitting the brick
        else if(is_BrickCollision(ballBounds)){
            player.add_score();
            screen.refresh_player_score(player.get_score());
            soundEffect.play_brick_collision_soundEffect();
        }
        // if the  ball hitting the sides of the screen
        else if(ballBounds.getX() >= BALL_SCREEN_COLLISION_X || ballBounds.getX() < 0){
            ball_bounce_x();
            soundEffect.play_collision_soundEffect();
        }
        // if the ball hitting the top screen
        else if(ballBounds.getY() < 0){
            ball_bounce_y();
            soundEffect.play_collision_soundEffect();
        }
        ball.setPosition(ball.getX() + ball.getBallXVelocity(), ball.getY() + ball.getBallYVelocity());
        // After all the checking conditions, we get the ball the new coordinates and the screen object will move the label "on the screen".
        screen.ball_label.setLocation(ball.getX(), ball.getY());
    }
    private boolean obj_collision(Rectangle ballBounds, Rectangle obj){
            if(ballBounds.intersects(obj)){
                Rectangle intersection = ballBounds.intersection(obj);
                if(intersection.width < intersection.height)
                    ball_bounce_x();  
                else
                    ball_bounce_y();
                return true;
            }
            return false;
    }
    // the function check if ball was hitting one of the bricks.
    private boolean is_BrickCollision(Rectangle ballBounds){
        for(Brick brick: brickArrayList)
            if(obj_collision(ballBounds, brick.get_rectangle_brick())){
                screen.brick_destroy(brickArrayList.indexOf(brick));
                brickArrayList.remove(brick);
                return true;
            }
        return false;
    }

// ## Section of all the functions that check conditions for the program flow.

    // Checking if the game is over(wining or losing).
    private boolean isGameOver(){
        // if it the last brick than the player won.
        if( brickArrayList.size() == 0){
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
        if(ball_position_y > MISS_HEIGHT){
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
        if(paddle_position_x > PADDLE_SCREEN_LEFT_LIMIT && (key == 65 || key == 37)){
            paddle.set_new_cor(paddle_position_x - paddle.get_moving_speed());
        }

        // 'd'/'D'/'>' key was pressed.
        else if((paddle_position_x < PADDLE_SCREEN_RIGHT_LIMIT) && (key == 68 || key == 39)){
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