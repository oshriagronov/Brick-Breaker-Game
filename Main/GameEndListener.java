package Main;

/**
 * A listener interface for receiving game-end events.
 * Classes interested in processing a game-end event should implement this interface.
 */
public interface GameEndListener {
    /**
     * Invoked when the game has ended.
     */
    void onGameEnd();
} 