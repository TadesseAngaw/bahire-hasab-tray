/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tadesse.bahirehasab.tray.tools;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Tadesse
 */
public class Sound {

    public static final int HOLYDAY_SOUND = 0;
    public static final int WARNING_SOUND = 1;
    public static final int BIRTHDAY_SOUND = 3;
    public static final int MEETING_SOUND = 4;

    private static final String[] SOUND_LOCATIONS = {"HolidayNotification.wav", "", "", ""};

    public static void playSound(int soundType) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(Sound.class.getResource(SOUND_LOCATIONS[soundType]));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
        }
    }
}
