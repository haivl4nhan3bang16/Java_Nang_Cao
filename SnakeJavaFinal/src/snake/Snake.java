package snake;

import countTime.CountTime;
import gson.FileIO;
import gson.infomationGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.*;

import view.RenderPanel;
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
    public int showScore;
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
    public boolean pause = false;
    public boolean isNewGame = false;
    public int Second = 1;
    public String showTimeCountDown;



    public Setting view;

    final String filename = "D:\\Intellij IDEA\\IdeaProjects\\SnakeJavaFinal\\resources\\Infomation.txt";
    final String jsonString = FileIO.readText(filename);
    public infomationGame highScore = Controller.Load(jsonString);

    public Snake() {
        jframe.setVisible(true);
        jframe.setSize(805, 700);
        jframe.setResizable(false);
        jframe.add(renderPanel = new RenderPanel());
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLocationRelativeTo(null);
        jframe.addKeyListener(this);
        startGame();
    }

    public void startGame() {
        over = true;
        pause = true;
        isNewGame = true;
        showScore = highScore.getScore();
        speed = highScore.getSpeed();
        head = new Point(80/2,64/2);
        random = new Random();
        snakeParts.clear();
        food = new Point(random.nextInt(60), random.nextInt(35));
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if(isNewGame == true){
                        try {
                            Thread.sleep(1000);
                            if(Second > 4){
                                showModeGame();
                                over = false;
                                pause = false;
                                time = 0;
                                score = 0;
                                tailLength = 1;
                                ticks = 0;
                                direction = 1;
                                timer.start();
                                Second = 1;
                                break;
                            }
                            CountTime countTime = new CountTime(String.valueOf(Second));
                            Second++;
                            showTimeCountDown = countTime.getTimes();

                        }catch (Exception e){

                        }
                    }
                    else {
                        break;
                    }
                    if(Second > 4){
                        showTimeCountDown = "Go !!!";
                        Controller.playSound("D:\\Intellij IDEA\\IdeaProjects\\SnakeJavaFinal\\sound\\go.wav");
                    }

                    JOptionPane msg = new JOptionPane("");
                    final JDialog dlg = msg.createDialog("Prepare !!!");
                    msg.setBackground(Color.RED);
                    dlg.resize(300, 90);
                    dlg.setLocationRelativeTo(null);
                    JLabel im = new JLabel(showTimeCountDown, JLabel.CENTER);
                    im.setForeground(Color.RED);
                    im.setFont(new Font("Serif", Font.BOLD, 40));
                    dlg.add(im, BorderLayout.NORTH);
                    dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

                    Controller.playSound("D:\\Intellij IDEA\\IdeaProjects\\SnakeJavaFinal\\sound\\getSet.wav");

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            dlg.setVisible(false);
                        }
                    }).start();
                    dlg.setVisible(true);
                    System.out.println(showTimeCountDown);
                }
            }
        });
        thread.start();
    }

    public void actionPerformed(ActionEvent arg0) {
        ++ticks;
        if (ticks % speed == 0 && head != null && !over && !pause) {
            renderPanel.repaint();
            ++time;
            snakeParts.add(new Point(head.x, head.y));
            if (direction == UP) {
                if (head.y - 1 >= 0 && noTailAt(head.x, head.y - 1)) {
                    head = new Point(head.x, head.y - 1);
                } else {
                    over = true;
                    Controller.soundDead();
                    setHighScore();
                }
            }
            if (direction == DOWN) {
                if (head.y + 1 < 60 && noTailAt(head.x, head.y + 1)) {
                    head = new Point(head.x, head.y + 1);
                } else {
                    over = true;
                    Controller.soundDead();
                    setHighScore();
                }
            }
            if (direction == LEFT) {
                if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y)) {
                    head = new Point(head.x - 1, head.y);
                } else {
                    over = true;
                    Controller.soundDead();
                    setHighScore();
                }
            }
            if (direction == RIGHT) {
                if (head.x + 1 < 80 && noTailAt(head.x + 1, head.y)) {
                    head = new Point(head.x + 1, head.y);
                } else {
                    over = true;
                    Controller.soundDead();
                    setHighScore();
                }
            }

            if (snakeParts.size() > tailLength) {
                snakeParts.remove(0);
            }

            if (food != null && head.equals(food)) {
                score += 10;
                ++tailLength;
                food.setLocation(random.nextInt(70), random.nextInt(60));
                Controller.playSound("D:\\Intellij IDEA\\IdeaProjects\\SnakeJavaFinal\\sound\\eatSound.wav");
            }
        }

    }

    public boolean noTailAt(int x, int y) {
        Iterator iterator = snakeParts.iterator();

        while(iterator.hasNext()) {
            Point point = (Point)iterator.next();
            if (point.equals(new Point(x, y))) {
                return false;
            }
        }

        return true;
    }

    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        if (i == KeyEvent.VK_A && direction != RIGHT) {
            direction = LEFT;
            if(pause){
                pause = false;
                showModeGame();
            }
        }

        if (i == KeyEvent.VK_D && direction != LEFT) {
            direction = RIGHT;
            if(pause){
                pause = false;
                showModeGame();
            }
        }

        if (i == KeyEvent.VK_W && direction != DOWN) {
            direction = UP;
            if(pause){
                pause = false;
                showModeGame();
            }
        }

        if (i == KeyEvent.VK_S && direction != UP) {
            direction = DOWN;
            if(pause){
                pause = false;
                showModeGame();
            }
        }

        if(i == KeyEvent.VK_ESCAPE){
            pause = true;
                view = new Setting();
                int options = JOptionPane.showConfirmDialog(jframe, view.getRootPanel(), "Setting speed for snake", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (options == JOptionPane.YES_OPTION) {
                    switch (view.cbSettingSpeed.getSelectedItem().toString()) {
                        case "Eazy":
                            highScore.setSpeed(10);
                            Controller.Save(filename, highScore);
                            speed = 10;
                            break;
                        case "Normal":
                            highScore.setSpeed(5);
                            Controller.Save(filename, highScore);
                            speed = 5;
                            break;
                        case "Hard":
                            highScore.setSpeed(2);
                            Controller.Save(filename, highScore);
                            speed = 2;
                            break;
                        case "Extremely":
                            highScore.setSpeed(1);
                            Controller.Save(filename, highScore);
                            speed = 1;
                            break;
                        default:
                            break;
                    }
                }

        }

        if (i == KeyEvent.VK_SPACE) {
            if (over) {
                startGame();
            } else {
                pause = !pause;
                isNewGame = true;
                showModeGame();
            }
        }

    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }


    public void setHighScore(){
        if(showScore < score){
            Controller.playSound("D:\\Intellij IDEA\\IdeaProjects\\SnakeJavaFinal\\sound\\newScoreSound.wav");
            highScore.setScore(score);
            Controller.Save(filename, highScore);
            JOptionPane.showMessageDialog(null, "Bạn đã đạt " + score + " điểm, chính thức xác lập kỷ lục mới !!! WOW...Xin chúc mừng !!!");
        }
    }

    public String showModeGame(){
        if(speed == 10){
            return mode = "Eazy";
        }else if(speed == 5){
            return mode = "Normal";
        }else if(speed == 2){
            return mode = "Hard";
        }else if(speed == 1){
            return mode = "Extremely";
        }else {
            mode = "Null";
            return mode;
        }
    }

}
