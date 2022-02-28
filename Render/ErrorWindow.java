package Render;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
public class ErrorWindow {
    private JFrame error_frame = new JFrame();
    private final int window_height = 150;
    private final int window_width = 500;
    private JLabel error_label = new JLabel();
    private final ImageIcon error_icon = new ImageIcon("assets/error.png");
    // popup error windows if a some of files are missing
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
