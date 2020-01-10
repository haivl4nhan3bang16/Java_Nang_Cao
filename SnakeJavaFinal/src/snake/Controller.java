package snake;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gson.FileIO;
import gson.infomationGame;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.*;

public class Controller {
    public static infomationGame Load(String jsonString) {
        Gson gson = new Gson();
        infomationGame score = gson.fromJson(jsonString, infomationGame.class);
        return score;
    }

    public static void Save(String filename, infomationGame score) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(score);
        FileIO.writeText(filename, jsonString);
    }

    public static void playSound(String pathName){
        InputStream music;
        try {
            music = new FileInputStream(new File(pathName));
            AudioStream audioStream = new AudioStream(music);
            AudioPlayer.player.start(audioStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void soundDead(){
        playSound("D:\\Intellij IDEA\\IdeaProjects\\SnakeJavaFinal\\sound\\deadSound.wav");
    }
}
