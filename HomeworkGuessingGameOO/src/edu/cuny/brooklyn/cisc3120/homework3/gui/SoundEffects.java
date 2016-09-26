package edu.cuny.brooklyn.cisc3120.homework3.gui;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

public enum SoundEffects
{
    // Paths to sound files
    BELL("res/sounds/winner.wav"),
    BUZZER("res/sounds/wrong.wav"),
    GAME_OVER("res/sounds/looser.wav");

    private Clip clip; // create a playable clip object

    // SoundOption enum and option variable for turning sound off or on.
    public enum SoundOption { SOUND_ON, SOUND_OFF }
    public static SoundOption option = SoundOption.SOUND_ON;

    SoundEffects(String sound)
    {
        try {
            URL soundURL = SoundEffects.class.getResource(sound);
            AudioInputStream soundStream =
                    AudioSystem.getAudioInputStream(soundURL);
            clip = AudioSystem.getClip();
            clip.open(soundStream);
        }
        catch (UnsupportedAudioFileException uafe) {
            System.err.println("Audio file not supported.");
        }
        catch (LineUnavailableException lue) {
            System.err.println("Requested audio line may be in use.");
        }
        catch (IOException ioe) {
            System.err.println("Audio file not found.");
        }
    }

    // Method to playSound sound.
    public void playSound()
    {
        if (option.equals(SoundOption.SOUND_ON)) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    // this method loads the resource files into memory on constructor invocation.
    static void init() {
        values();
    }
}