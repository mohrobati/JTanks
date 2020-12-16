import javax.sound.sampled.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class Sounds {

    /**
     * This class contains the needed audios
     * for different occasions in the game
     * and open up the stream for buffering the sound...
     */

    private Clip clip;

    public Sounds() {
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playMissionTheme() {
        playSound("missionTheme.wav",true);
    }

    public void playMainMenuTheme() {
        playSound("mainMenuTheme.wav",true);
    }

    public void playExplosionSound() {
        playSound("explosion.wav",false);
    }

    public void playCannonSound() {
        playSound("cannon.wav",false);
    }

    public void playMachineGunSound() {
        playSound("machineGun.wav",false);
    }

    public void playUpgradeSound() {
        playSound("upgrade.wav",false);
    }

    public void playReloadSound() {
        playSound("reload.wav",false);
    }

    public void playHealSound() {
        playSound("heal.wav",false);
    }




    private void playSound(String name, boolean loop) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(
                new File("../res/"+name));
            clip.open(audioIn);
            clip.start();
            if(loop) clip.loop(10);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public Clip getClip() {
        return clip;
    }
}
