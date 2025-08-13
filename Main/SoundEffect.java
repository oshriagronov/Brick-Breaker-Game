package Main;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEffect {
    private String collision_soundEffect_file_path = "assets/Bubble5_4.wav";
    private String brick_collision_soundEffect_file_path = "assets/blop_cut_silenced.wav";
    Clip collision_soundEffect;
    Clip brick_collision_soundEffect;

    public SoundEffect() throws LineUnavailableException, IOException, UnsupportedAudioFileException{
        // load the regular collision sound effect.
        File collision_soundEffect_file = new File(collision_soundEffect_file_path);
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(collision_soundEffect_file);
		collision_soundEffect = AudioSystem.getClip();
		collision_soundEffect.open(audioStream);

        //load the brick collision sound effect.
        File brick_collision_soundEffect_file = new File(brick_collision_soundEffect_file_path);
		AudioInputStream brick_collision_audioStream = AudioSystem.getAudioInputStream(brick_collision_soundEffect_file);
		brick_collision_soundEffect = AudioSystem.getClip();
		brick_collision_soundEffect.open(brick_collision_audioStream);
    }
    // close everything that is running
    public void close(){
        collision_soundEffect.stop();
        collision_soundEffect.close();
        brick_collision_soundEffect.stop();
        brick_collision_soundEffect.close();
    }

    // play the regular collision sound effect.
    public void play_collision_soundEffect(){
        if(collision_soundEffect.isRunning()){
            collision_soundEffect.stop();
        }
        collision_soundEffect.setFramePosition(0);
        collision_soundEffect.start();
    }

    // play the specific brick collision sound effect.
    public void play_brick_collision_soundEffect(){
        if(brick_collision_soundEffect.isRunning())
            brick_collision_soundEffect.stop();
        brick_collision_soundEffect.setMicrosecondPosition(0);
        brick_collision_soundEffect.start();
    }
}
