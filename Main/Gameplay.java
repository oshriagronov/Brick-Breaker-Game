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

/**
 * The Gameplay class implements the core logic of the game.
 * It handles the game loop, listens for player input, and manages interactions
 * between the ball, paddle, and bricks, including collision detection.
 */
public class Gameplay implements KeyListener, ActionListener{
    /** The x-coordinate where the ball will bounce off the right edge of the screen. */
    private final int BALL_SCREEN_COLLISION_X = Screen.WINDOW_WIDTH - Ball.getWidth();
    /** The leftmost limit for the paddle's movement. */
    private final int PADDLE_SCREEN_LEFT_LIMIT = 0;
    /** The rightmost limit for the paddle's movement. */
    private final int PADDLE_SCREEN_RIGHT_LIMIT = Screen.WINDOW_WIDTH - Paddle.getWidth();
    /** The y-coordinate at which the ball is considered missed, resulting in a loss of life. */
    private final int MISS_HEIGHT = Screen.WINDOW_HEIGHT - Ball.getHeight();
    private GameEndListener gameEndListener;
    private Player player;
    private Screen screen;
    private SoundEffect soundEffect;
    private Paddle paddle;
    private Ball ball;
    private List <Brick> brickArrayList;
    /** The main game timer that triggers an action event at a regular interval to drive the game's state. */
    private Timer timer;
    private Rectangle ballBounds;
    private Rectangle paddleBounds;

    /**
     * Constructs the Gameplay object.
     * @param player The player object, containing score and life data.
     * @param screen The screen where the game is rendered.
     * @param soundEffect The object for playing sound effects.
     * @param ball The ball object.
     * @param paddle The paddle object.
     * @param brickArrayList The list of bricks in the level.
     */
    public Gameplay(Player player, Screen screen, SoundEffect soundEffect, Ball ball, Paddle paddle, List <Brick> brickArrayList){
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

    /**
     * Sets a listener that will be notified when the game ends (either by winning or losing).
     * @param listener The listener to be notified.
     */
    public void setGameEndListener(GameEndListener listener) {
        this.gameEndListener = listener;
    }

    /**
     * Starts the game loop by initializing and starting the timer.
     * The timer's delay is determined by the ball's speed property.
     */
    public void run(){
        timer = new Timer(ball.getBallSpeed(), this);
        timer.start();
    }

    /**
     * This method is called by the Timer at each interval. It serves as the main game loop,
     * updating the game state, checking for collisions, and determining if the game is over.
     * @param e The ActionEvent triggered by the timer.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!isGameOver()){
            // Check if the player missed the ball.
            if(ball.getY() > MISS_HEIGHT){
                ball.setPosition(Screen.WINDOW_WIDTH / 2, Screen.WINDOW_HEIGHT / 2);
                // Suggestion: The life point deduction logic is commented out.
                // This should be re-enabled for the game to function as intended.
                // screen.remove_HeartLabel(player.get_life_points() - 1);
                // player.lose_life_point();
            }
            else {
                ballMovement();
            }
        }
        else{
            // Stop the game and notify the listener that the game has ended.
            timer.stop();
            if (gameEndListener != null) {
                gameEndListener.onGameEnd();
            }
        }
    }

    /** Reverses the ball's horizontal velocity to simulate a bounce. */
    private void ballBounceX(){
        ball.setBallXVelocity(ball.getBallXVelocity() * (-1)) ;
    }

    /** Reverses the ball's vertical velocity to simulate a bounce. */
    private void ballBounceY(){
        ball.setBallYVelocity(ball.getBallYVelocity() * (-1));
    }

    /**
     * Manages the ball's movement and checks for collisions with the paddle, bricks, and screen boundaries.
     */
    private void ballMovement(){
        ballBounds.setBounds(ball.getX(), ball.getY(), Ball.getWidth(), Ball.getHeight());
        paddleBounds.setBounds(paddle.getX(), paddle.getY(), Paddle.getWidth(), Paddle.getHeight());

        if(objCollision(ballBounds, paddleBounds)){
            soundEffect.playCollisionSoundEffect();
        }
        else if(isBrickCollision(ballBounds)){
            player.addScore();
            screen.refreshPlayerScore(player.getScore());
            soundEffect.playBrickCollisionSoundEffect();
        }
        else if(ballBounds.getX() >= BALL_SCREEN_COLLISION_X || ballBounds.getX() < 0){
            ballBounceX();
            soundEffect.playCollisionSoundEffect();
        }
        else if(ballBounds.getY() < 0){
            ballBounceY();
            soundEffect.playCollisionSoundEffect();
        }

        // Update the ball's position based on its velocity.
        ball.setPosition(ball.getX() + ball.getBallXVelocity(), ball.getY() + ball.getBallYVelocity());
        screen.ballLabel.setLocation(ball.getX(), ball.getY());
    }

    /**
     * Checks for and handles collision between the ball and a generic rectangular object.
     * @param ballBounds The bounding rectangle of the ball.
     * @param obj The bounding rectangle of the object to check against.
     * @return true if a collision occurred, false otherwise.
     */
    private boolean objCollision(Rectangle ballBounds, Rectangle obj){
            if(ballBounds.intersects(obj)){
                Rectangle intersection = ballBounds.intersection(obj);
                // Determine bounce direction based on the intersection dimensions.
                if(intersection.width < intersection.height)
                    ballBounceX();  
                else
                    ballBounceY();
                return true;
            }
            return false;
    }

    /**
     * Checks if the ball has collided with any of the bricks. If a collision occurs,
     * the brick is removed from the game.
     * @param ballBounds The bounding rectangle of the ball.
     * @return true if a collision with a brick occurred, false otherwise.
     */
    private boolean isBrickCollision(Rectangle ballBounds){
        for(Brick brick: brickArrayList) {
            if(objCollision(ballBounds, brick.getRectangleBrick())){
                screen.brickDestroy(brickArrayList.indexOf(brick));
                brickArrayList.remove(brick);
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if the game has ended, either by destroying all bricks (win)
     * or losing all life points (lose).
     * @return true if the game is over, false otherwise.
     */
    private boolean isGameOver(){
        if(brickArrayList.isEmpty()){
            return true;
        }
        else if(player.getLifePoints() == 0){
            return true;
        }
        return false;
    }

    /**
     * Handles key presses for paddle movement.
     * Suggestion: Use KeyEvent constants like `KeyEvent.VK_A` and `KeyEvent.VK_LEFT`
     * instead of magic numbers (65, 37) to improve readability.
     * @param e The KeyEvent generated by the key press.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        int paddlePositionX = paddle.getX();

        // Move left with 'A' or the left arrow key.
        if(paddlePositionX > PADDLE_SCREEN_LEFT_LIMIT && (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)){
            paddle.setX(paddlePositionX - paddle.getPaddleSpeed());
        }
        // Move right with 'D' or the right arrow key.
        else if((paddlePositionX < PADDLE_SCREEN_RIGHT_LIMIT) && (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)){
            paddle.setX(paddlePositionX + paddle.getPaddleSpeed());
        }
        screen.paddleLabel.setLocation(paddle.getX(), paddle.getY());
    }

    /** This method is intentionally left empty as it is not needed. */
    @Override
    public void keyReleased(KeyEvent e) {}

    /** This method is intentionally left empty as it is not needed. */
    @Override
    public void keyTyped(KeyEvent e) {}
}