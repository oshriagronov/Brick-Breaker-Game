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
    private final int PADDLE_SCREEN_RIGHT_LIMIT = Screen.window_width - Paddle.getWidth(); // the screen right edge that if the paddle will reach it, the paddle couldn't pass it.
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
        ballBounds = new Rectangle();
        paddleBounds = new Rectangle();

    }
    // a listener when the game end and inform the game manger
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
            if(ball.getY() > MISS_HEIGHT){
                ball.setPosition(Screen.window_width / 2, Screen.window_height / 2);
                //**lose life points and end game mechanic, disabled for testing**
                //screen.remove_HeartLabel(player.get_life_points() - 1);
                //player.lose_life_point();
            }
            else
                ballMovement();
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
    private void ballBounceX(){
        ball.setBallXVelocity(ball.getBallXVelocity() * (-1)) ;
    }
    // the function make the y velocity negative/positive so the ball will bounce when hitting object at upper or button part.
    private void ballBounceY(){
        ball.setBallYVelocity(ball.getBallYVelocity() * (-1));
    }
    // ## Section of all the collision related function.
    private void ballMovement(){
        ballBounds.setBounds(ball.getX(), ball.getY(), Ball.getWidth(), Ball.getHeight());
        paddleBounds.setBounds(paddle.getX(), paddle.getY(), Paddle.getWidth(), Paddle.getHeight());
        // checking if the ball was hitting the paddle 
        if(objCollision(ballBounds, paddleBounds)){
            soundEffect.play_collision_soundEffect();
        }
        // if the ball was hitting the brick
        else if(isBrickCollision(ballBounds)){
            player.add_score();
            screen.refresh_player_score(player.get_score());
            soundEffect.play_brick_collision_soundEffect();
        }
        // if the  ball hitting the sides of the screen
        else if(ballBounds.getX() >= BALL_SCREEN_COLLISION_X || ballBounds.getX() < 0){
            ballBounceX();
            soundEffect.play_collision_soundEffect();
        }
        // if the ball hitting the top screen
        else if(ballBounds.getY() < 0){
            ballBounceY();
            soundEffect.play_collision_soundEffect();
        }
        ball.setPosition(ball.getX() + ball.getBallXVelocity(), ball.getY() + ball.getBallYVelocity());
        // After all the checking conditions, we get the ball the new coordinates and the screen object will move the label "on the screen".
        screen.ball_label.setLocation(ball.getX(), ball.getY());
    }
    private boolean objCollision(Rectangle ballBounds, Rectangle obj){
            if(ballBounds.intersects(obj)){
                Rectangle intersection = ballBounds.intersection(obj);
                if(intersection.width < intersection.height)
                    ballBounceX();  
                else
                    ballBounceY();
                return true;
            }
            return false;
    }
    // the function check if ball was hitting one of the bricks.
    private boolean isBrickCollision(Rectangle ballBounds){
        for(Brick brick: brickArrayList)
            if(objCollision(ballBounds, brick.get_rectangle_brick())){
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
    //## Here is the key controlling keys system
    // moving paddle function.
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        // 'a'/'A'/'<' key was pressed.
        int paddlePositionX = paddle.getX();
        if(paddlePositionX > PADDLE_SCREEN_LEFT_LIMIT && (key == 65 || key == 37)){
            paddle.setX(paddlePositionX - paddle.getPaddleSpeed());
        }

        // 'd'/'D'/'>' key was pressed.
        else if((paddlePositionX < PADDLE_SCREEN_RIGHT_LIMIT) && (key == 68 || key == 39)){
            paddle.setX(paddlePositionX + paddle.getPaddleSpeed());
        }
        screen.paddle_label.setLocation(paddle.getX(), paddle.getY());
    }
    // we don't use those methods because we don't any use with them, so i was let them be empty.
    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

}