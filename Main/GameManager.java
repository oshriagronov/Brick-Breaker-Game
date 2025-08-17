package Main;
import Render.*;
import GameObjects.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The GameManager class is the central component of the game.
 * It is responsible for initializing the game objects, managing the game state,
 * handling user input, and coordinating the overall game flow from the menu to the game's end.
 *
 * Suggestion: Consider creating a dedicated GameState enum (e.g., MENU, PLAYING, GAME_OVER)
 * to manage the state transitions more explicitly, rather than relying on boolean flags like `key_pressed`.
 */
public class GameManager implements KeyListener{
    private final int Ball_DEFAULT_X = Screen.WINDOW_WIDTH / 2;
    private final int BALL_DEFAULT_Y = Screen.WINDOW_HEIGHT / 2;
    // Suggestion: Avoid magic numbers. The paddle's default X should be calculated based on its width,
    // e.g., `(Screen.WINDOW_WIDTH / 2) - (Paddle.getWidth() / 2)`.
    private final int PADDLE_DEFAULT_X = (Screen.WINDOW_WIDTH / 2) - (Paddle.getWidth() / 2);
    private final int PADDLE_DEFAULT_Y = Screen.WINDOW_HEIGHT - 70;
    private Screen screen;
    private SoundEffect sound_effect;
    private Paddle paddle;
    private Ball ball;
    private List<Brick> brick_array;
    private int num_of_bricks;
    private int bricks_gap;
    private Player player;
    /** The number of lives the player has before the game is over. */
    private int life_points = 3;
    /** The points awarded for breaking a single brick. */
    private int score_points = 100;
    /** Tracks if a key has been pressed to start the game from the menu. */
    private boolean key_pressed;

    /**
     * Constructs a GameManager, initializing all game components.
     * It performs a pre-launch check for necessary asset files and exits if any are missing.
     * It sets up the screen, game objects (paddle, ball, player), and calculates brick layout.
     */
    public GameManager(){
        // Checks for the existence of all required game assets. If any are missing,
        // an error window is shown, and the constructor returns early.
        if(CheckPath()){
            return;
        }
        // Initialize core game components.
        screen = new Screen();
        paddle = new Paddle(PADDLE_DEFAULT_X, PADDLE_DEFAULT_Y);
        ball = new Ball(Ball_DEFAULT_X, BALL_DEFAULT_Y);
        player = new Player(life_points, score_points);
        // Calculate how many bricks can fit in a single row across the screen width.
        num_of_bricks = Screen.WINDOW_WIDTH / Brick.getWidth();
        brick_array = new ArrayList<>();
        // Calculate the horizontal gap between each brick to distribute them evenly.
        bricks_gap = ((Screen.WINDOW_WIDTH - (num_of_bricks * Brick.getWidth())) / num_of_bricks) / 2;
        screen.addKeyListener(this);
    }

    /**
     * The main entry point of the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.menu_screen();
    }

    /**
     * Displays the initial menu screen, waiting for player input to start the game.
     */
    public void menu_screen(){
        screen.menuScreen();
    }

    /**
     * Starts the main gameplay loop. This method is called once the player
     * initiates the game from the menu. It sets up the game level by creating bricks,
     * adding all game objects to the screen, and starting the Gameplay timer.
     */
    public void start(){
        screen.clearScreen();
        screen.removeKeyListener(this); // Remove this listener to pass control to Gameplay's listener.

        // Create a row of bricks and add them to the brick list.
        int x_brick_location = 0;
        for(int i = 0; i < num_of_bricks; i++){
            x_brick_location += bricks_gap;
            brick_array.add(new Brick(x_brick_location));
            x_brick_location += Brick.getWidth() + bricks_gap;
        }
        // Render all game objects on the screen.
        screen.addPaddleLabel(Paddle.getIcon(), paddle.getX(), paddle.getY(), Paddle.getWidth(), Paddle.getHeight());
        screen.addBallLabel(Ball.getIcon(), ball.getX(), ball.getY(), Ball.getWidth(), Ball.getHeight());
        screen.addHeartLabels(player.getLifePoints(), player.getHeartIcon(), player.getHeartX(), player.getHeartY(), player.getHeartWidth(), player.getHeartHeight());
        screen.addBricksLabels(brick_array, num_of_bricks);
        screen.addPlayerScore(player.getScore());

        // Initialize and run the core gameplay logic.
        Gameplay gameplay = new Gameplay(player, screen, sound_effect, ball, paddle, brick_array);

        // Set up a listener to handle game-end conditions (win or lose).
        gameplay.setGameEndListener(() -> {
        if (player.getLifePoints() == 0) {
            screen.clearScreen();
            screen.gameOverScreen();
        } else {
            screen.clearScreen();
            screen.winingScreen();
        }});
        gameplay.run();
    }

    /**
     * Invoked when a key has been pressed. Used here to detect the first key press
     * on the menu screen to start the game.
     * @param e The KeyEvent generated by the key press.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(!key_pressed){
            key_pressed = true;
            start();
        }
    }

    /**
     * This method is not used in this context.
     * @param e The KeyEvent.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Intentionally left empty.
    }

    /**
     * This method is not used in this context.
     * @param e The KeyEvent.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // Intentionally left empty.
    }

    /**
     * Verifies the existence of all required asset files (images and sounds).
     * If a file is not found, it displays an error window.
     * Suggestion: This method could be improved to report all missing files at once
     * instead of stopping at the first one.
     * @return true if an error occurred (e.g., a file is missing), false otherwise.
     */
    public boolean CheckPath(){
        // Check for image assets.
        if(!Files.exists(Paths.get(Paddle.getIconPath()))){
            new ErrorWindow("The paddle PNG file is missing from the assets folder.");
            return true;
        }
        if(!Files.exists(Paths.get(Ball.getIconPath()))){
            new ErrorWindow("The ball PNG file is missing from the assets folder.");
            return true;
        }
        if(!Files.exists(Paths.get(Brick.getIconPath()))){
            new ErrorWindow("The brick PNG file is missing from the assets folder.");
            return true;
        }
        if(!Files.exists(Paths.get(Player.getHeartIconPath()))){
            new ErrorWindow("The heart PNG file is missing from the assets folder.");
            return true;
        }
        // Check for sound assets.
        // Suggestion: For better debugging, log the stack trace (e.g., e.printStackTrace())
        // in the catch blocks in addition to showing an error window.
        try {
            sound_effect = new SoundEffect();
        } catch (LineUnavailableException e) {
            new ErrorWindow("Audio line is unavailable. The sound device may be in use.");
            return true;
        } catch (UnsupportedAudioFileException e) {
            new ErrorWindow("One or more audio files are in an unsupported format.");
            return true;
        } catch (IOException e) {
            new ErrorWindow("An audio file is missing or cannot be read from the assets folder.");
            return true;
        }
        return false;
    }
}
