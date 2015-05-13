package Music;

import java.io.FileInputStream;
import java.io.InputStream;
import sun.audio.*;

/**
 * A simple Java sound file example (i.e., Java code to play a sound file).
 * AudioStream and AudioPlayer code comes from a  example.
 * @author alvin alexander, devdaily.com.
 */
public class PlaySong

{
    
        String gongFile = "src/Music/PenguinGame.wav";
        //String gongFile = "src/Music/Fire.wav";
        
        
 public void PlaySong()
     throws Exception{
    
     InputStream in = new FileInputStream(gongFile);  
     
    // create an audiostream from the inputstream
    AudioStream audioStream = new AudioStream(in);

    // play the audio clip with the audioplayer class
    AudioPlayer.player.start(audioStream);
     
 }


}