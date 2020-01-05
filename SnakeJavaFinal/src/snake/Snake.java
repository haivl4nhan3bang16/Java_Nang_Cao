package snake;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gson.FileIO;
import gson.Score;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.*;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import view.Setting;

public class Snake implements ActionListener, KeyListener {
    public static Snake snake;
    public JFrame jframe = new JFrame("Snake");
    public RenderPanel renderPanel;
    public Timer timer = new Timer(20, this);
    public ArrayList<Point> snakeParts = new ArrayList();
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int SCALE = 10;
    public int ticks = 0;
    public int direction = 1;
    public int score;
    public int tailLength = 10;
    public int time;
    public int speed;
    public Point head;
    public Point food;
    public Random random;
    public String mode = "";
    public boolean over = false;
    public boolean pause;
    public boolean isSetSpeed = false;
    public Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public Setting view;

    final String filename = "D:\\Intellij IDEA\\IdeaProjects\\SnakeJavaFinal\\resources\\Infomation.txt";
    final String jsonString = FileIO.readText(filename);
    public Score highScore = Load(jsonString);
    public int showScore;

    public Snake() {
        this.jframe.setVisible(true);
        this.jframe.setSize(805, 700);
        this.jframe.setResizable(false);
        this.jframe.add(this.renderPanel = new RenderPanel());
        this.jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jframe.setLocationRelativeTo(null);
        this.jframe.addKeyListener(this);
        this.startGame();
    }

    public void startGame() {
        this.showScore = highScore.getScore();
        this.isSetSpeed = false;
        this.over = false;
        this.pause = false;
        this.time = 0;
        this.speed = 10;
        this.score = 0;
        this.tailLength = 1;
        this.ticks = 0;
        this.direction = 1;
        this.head = new Point(39,25);
        this.random = new Random();
        this.snakeParts.clear();
        this.food = new Point(this.random.nextInt(60), this.random.nextInt(35));
        this.timer.start();
        showModeGame();
    }

    public void actionPerformed(ActionEvent arg0) {

        ++this.ticks;
        if (this.ticks % this.speed == 0 && this.head != null && !this.over && !this.pause) {
            this.renderPanel.repaint();
            ++this.time;
            this.snakeParts.add(new Point(this.head.x, this.head.y));
            if (this.direction == UP) {
                if (this.head.y - 1 >= 0 && this.noTailAt(this.head.x, this.head.y - 1)) {
                    this.head = new Point(this.head.x, this.head.y - 1);
                } else {
                    this.over = true;
                    playSound("D:\\Intellij IDEA\\IdeaProjects\\SnakeJavaFinal\\sound\\deadSound.wav");
                    setHighScore();
                }
            }


            if (this.direction == DOWN) {
                if (this.head.y + 1 < 67 && this.noTailAt(this.head.x, this.head.y + 1)) {
                    this.head = new Point(this.head.x, this.head.y + 1);
                } else {
                    this.over = true;
                    playSound("D:\\Intellij IDEA\\IdeaProjects\\SnakeJavaFinal\\sound\\deadSound.wav");
                    setHighScore();
                }
            }

            if (this.direction == LEFT) {
                if (this.head.x - 1 >= 0 && this.noTailAt(this.head.x - 1, this.head.y)) {
                    this.head = new Point(this.head.x - 1, this.head.y);
                } else {
                    this.over = true;
                    playSound("D:\\Intellij IDEA\\IdeaProjects\\SnakeJavaFinal\\sound\\deadSound.wav");
                    setHighScore();
                }
            }

            if (this.direction == RIGHT) {
                if (this.head.x + 1 < 80 && this.noTailAt(this.head.x + 1, this.head.y)) {
                    this.head = new Point(this.head.x + 1, this.head.y);
                } else {
                    this.over = true;
                    playSound("D:\\Intellij IDEA\\IdeaProjects\\SnakeJavaFinal\\sound\\deadSound.wav");
                    setHighScore();
                }
            }

            if (this.snakeParts.size() > this.tailLength) {
                this.snakeParts.remove(0);
            }

            if (this.food != null && this.head.equals(this.food)) {
                this.score += 10;
                ++this.tailLength;
                this.food.setLocation(this.random.nextInt(79), this.random.nextInt(35));
                playSound("D:\\Intellij IDEA\\IdeaProjects\\SnakeJavaFinal\\sound\\eatSound.wav");
            }
        }

    }

    public boolean noTailAt(int x, int y) {
        Iterator var4 = this.snakeParts.iterator();

        while(var4.hasNext()) {
            Point point = (Point)var4.next();
            if (point.equals(new Point(x, y))) {
                return false;
            }
        }

        return true;
    }

    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        if (i == KeyEvent.VK_A && direction != RIGHT) {
            this.direction = LEFT;
            if(this.pause){
                this.pause = false;
                showModeGame();
            }
        }

        if (i == KeyEvent.VK_D && this.direction != LEFT) {
            this.direction = RIGHT;
            if(this.pause){
                this.pause = false;
                showModeGame();
            }
        }

        if (i == KeyEvent.VK_W && this.direction != DOWN) {
            this.direction = UP;
            if(this.pause){
                this.pause = false;
                showModeGame();
            }
        }

        if (i == KeyEvent.VK_S && this.direction != UP) {
            this.direction = DOWN;
            if(this.pause){
                this.pause = false;
                showModeGame();
            }
        }

        if(i == KeyEvent.VK_ESCAPE){
            this.pause = true;
            if(isSetSpeed == false) {
                view = new Setting();
                int options = JOptionPane.showConfirmDialog(this.jframe, view.getRootPanel(), "Setting speed for snake", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (options == JOptionPane.YES_OPTION) {
                    switch (view.cbSettingSpeed.getSelectedItem().toString()) {
                        case "Eazy":
                            this.speed = 10;
                            isSetSpeed = true;
                            break;
                        case "Normal":
                            this.speed = 5;
                            isSetSpeed = true;
                            break;
                        case "Hard":
                            this.speed = 2;
                            isSetSpeed = true;
                            break;
                        case "Extremely":
                            this.speed = 1;
                            isSetSpeed = true;
                            break;
                        default:
                            break;
                    }
                }
            }else {
                JOptionPane.showConfirmDialog(this.jframe, "Đã chọn chế độ chơi rồi không thể thay đổi", "Thông báo !!!", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE);
            }
        }

        if (i == KeyEvent.VK_SPACE) {
            if (this.over) {
                this.startGame();
            } else {
                this.pause = !this.pause;
                showModeGame();
            }
        }

    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }


    public static Score Load(String jsonString) {

        Gson gson = new Gson();
        Score score = gson.fromJson(jsonString, Score.class);
        return score;
    }

    public static void Save(String filename, Score score) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(score);
        FileIO.writeText(filename, jsonString);
    }

    public void setHighScore(){
        if(showScore < score){
            highScore.setScore(score);
            Save(filename, highScore);
            JOptionPane.showMessageDialog(null, "Bạn đã đạt " + score + " điểm, chính thức xác lập kỷ lục mới !!! WOW...Xin chúc mừng !!!");

        }
    }

    public String showModeGame(){
        if(this.speed == 10){
            return this.mode = "Eazy";
        }else if(this.speed == 5){
            return this.mode = "Normal";
        }else if(this.speed == 2){
            return this.mode = "Hard";
        }else if(this.speed == 1){
            return this.mode = "Extremely";
        }else {
            this.mode = "Null";
            return this.mode;
        }
    }

    public static void playSound(String pathname){
        InputStream music;
        try {
            music = new FileInputStream(new File(pathname));
            AudioStream audioStream = new AudioStream(music);
            AudioPlayer.player.start(audioStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        snake = new Snake();
    }
}
