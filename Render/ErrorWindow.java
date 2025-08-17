package Render;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;

/**
 * The ErrorWindow class creates and displays a standardized error window.
 * This is used to inform the user of critical errors, such as missing asset files,
 * that prevent the game from running.
 */
public class ErrorWindow {
    private JFrame error_frame = new JFrame();
    // Suggestion: These constants could be made `private static final`.
    private final int window_height = 150;
    private final int window_width = 500;
    private JLabel error_label = new JLabel();
    private final ImageIcon error_icon = new ImageIcon(AssetPaths.ERROR_ICON_PATH);

    /**
     * Constructs and displays an error window with a specific message.
     * The window is centered on the screen and exits the application when closed.
     * @param error_message The error message to be displayed in the window.
     */
    public ErrorWindow(String error_message){
        error_frame.setTitle("Error");
        error_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        error_frame.setResizable(false);
        error_frame.setLayout(new GridBagLayout());
        error_frame.setLocationRelativeTo(null);
        error_frame.setSize(window_width, window_height);
        error_label.setIcon(error_icon);
        error_label.setText(error_message);
        error_frame.add(error_label);
        error_frame.setVisible(true);
    }
}
