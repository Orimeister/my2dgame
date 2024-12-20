package org.main;

import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

public class Sound {
    Clip clip;
    final URL[] soundFiles = new URL[30];
    FloatControl volumeControl;

    public Sound() {
        soundFiles[0] = getClass().getResource("/sound/pickupCoin.wav");
        soundFiles[1] = getClass().getResource("/sound/openDoor.wav");
        soundFiles[2] = getClass().getResource("/sound/powerUp.wav");
        soundFiles[3] = getClass().getResource("/sound/driftveil.wav");

    }

    public void setFiles(int index) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFiles[index]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (Exception e) {

        }

    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void setVolume(float value) {
        if (volumeControl != null) {
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();

            value = Math.max(min, Math.min(max, value));
            volumeControl.setValue(value);
        }


    }
}

