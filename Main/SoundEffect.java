package Main;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Render.AssetPaths;
public class SoundEffect {
    private String COLLISION_SOUND_EFFECT_FILE_PATH = AssetPaths.COLLISION_SOUND_EFFECT_FILE_PATH;
    private String BRICK_COLLISION_SOUND_EFFECT_FILE_PATH = AssetPaths.BRICK_COLLISION_SOUND_EFFECT_FILE_PATH;
    Clip collisionSoundEffect;
    Clip brickCollisionSoundEffect;

    public SoundEffect() throws LineUnavailableException, IOException, UnsupportedAudioFileException{
        // load the regular collision sound effect.
        File collisionSoundEffectFile = new File(COLLISION_SOUND_EFFECT_FILE_PATH);
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(collisionSoundEffectFile);
		collisionSoundEffect = AudioSystem.getClip();
		collisionSoundEffect.open(audioStream);
        //load the brick collision sound effect.
        File brickCollisionSoundEffectFile = new File(BRICK_COLLISION_SOUND_EFFECT_FILE_PATH);
		AudioInputStream brickAudioStream = AudioSystem.getAudioInputStream(brickCollisionSoundEffectFile);
		brickCollisionSoundEffect = AudioSystem.getClip();
		brickCollisionSoundEffect.open(brickAudioStream);
    }
    // close everything that is running
    public void close(){
        collisionSoundEffect.stop();
        collisionSoundEffect.close();
        brickCollisionSoundEffect.stop();
        brickCollisionSoundEffect.close();
    }

    // play the regular collision sound effect.
    public void playCollisionSoundEffect(){
        if(collisionSoundEffect.isRunning())
            collisionSoundEffect.stop();
        collisionSoundEffect.setFramePosition(0);
        collisionSoundEffect.start();
    }

    // play the specific brick collision sound effect.
    public void playBrickCollisionSoundEffect(){
        if(brickCollisionSoundEffect.isRunning())
            brickCollisionSoundEffect.stop();
        brickCollisionSoundEffect.setMicrosecondPosition(0);
        brickCollisionSoundEffect.start();
    }
}
