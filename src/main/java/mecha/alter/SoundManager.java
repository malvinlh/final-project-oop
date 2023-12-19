package mecha.alter;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager 
{	
    private Clip musicClip;
    private Clip soundEffectClip;
    private URL[] soundURLs;
    boolean isMusicPlaying = false;
    
	public SoundManager() 
	{	    
	    String[] audioPaths = {
	        "/sounds/main_menu_bgm.wav",
	        "/sounds/button_click.wav",
	        "/sounds/sonatina_letsadventure_2Harbingers.wav"
	    };

	    try 
	    {
	        loadSounds(audioPaths);

	    } 
	    catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
	    {
	        e.printStackTrace();
	    }        
	}
	
	public boolean isMusicPlaying() {
        return isMusicPlaying;
    }
	
    public void loadSounds(String[] audioPaths) throws UnsupportedAudioFileException, IOException, LineUnavailableException 
    {
        int numberOfSounds = audioPaths.length;
        soundURLs = new URL[numberOfSounds];

        for (int i = 0; i < numberOfSounds; i++) 
        {
            soundURLs[i] = getClass().getResource(audioPaths[i]);
        }
    }
    
    public void setSounds(int i, boolean isMusic) 
    {
        try 
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURLs[i]);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);

            if (isMusic) 
            {
                stopMusic();
                musicClip = clip;
                loopSounds();
                isMusicPlaying = true;
            } 
            else 
            {
                soundEffectClip = clip;
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void playSounds() 
    {
        if (soundEffectClip != null) 
        {
            soundEffectClip.start();
        }
    }

    public void loopSounds() 
    {
        if (musicClip != null) 
        {
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stopSounds() 
    {
        if (soundEffectClip != null && soundEffectClip.isRunning()) 
        {
            soundEffectClip.stop();
        }
    }

    public void stopMusic() 
    {
        if (musicClip != null && musicClip.isRunning()) 
        {
            musicClip.stop();
        }
    }
}