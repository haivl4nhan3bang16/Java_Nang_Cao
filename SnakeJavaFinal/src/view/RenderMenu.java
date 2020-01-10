package view;

import javax.swing.*;
import java.awt.*;

import snake.Menu;

public class RenderMenu extends JPanel {

    Image img;
    int x = 0;
    int y = 600;
    public RenderMenu(){
        img = Toolkit.getDefaultToolkit().createImage("D:\\Intellij IDEA\\IdeaProjects\\SnakeJavaFinal\\image\\snake.png");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(100000));
        g.fillRect(0, 0, 800, 700);
        g.drawImage(img, 0, 0,400,450, this);

        String stringTitle = "Hướng dẫn";
        g.setColor(Color.black);
        g.setFont(new Font("Tahoma", Font.BOLD, 30));
        g.drawString(stringTitle, 500, 30);

        String stringHowToPlay1 = "Sử dụng các phím A,W,S,D để di chuyển";
        g.setColor(Color.black);
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        g.drawString(stringHowToPlay1, 395, 60);

        String stringHowToPlay2 = "Thức ăn sẽ xuất hiện ngẫu nhiên";
        g.setColor(Color.black);
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        g.drawString(stringHowToPlay2, 410, 85);

        String stringHowToPlay3 = "Cố gắng ăn thật nhiều để kiếm điểm";
        g.setColor(Color.black);
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        g.drawString(stringHowToPlay3, 410, 110);

        String stringHowToPlay4 = "Thua nếu: ";
        g.setColor(Color.black);
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        g.drawString(stringHowToPlay4, 410, 135);

        String stringHowToPlay5 = " * Đi quá giới hạn map";
        g.setColor(Color.black);
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        g.drawString(stringHowToPlay5, 410, 160);

        String stringHowToPlay6 = " * Tự cắn chính mình";
        g.setColor(Color.black);
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        g.drawString(stringHowToPlay6, 410, 185);

        String stringHowToPlay7 = "Sử dụng phím cách để tạm dừng";
        g.setColor(Color.black);
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        g.drawString(stringHowToPlay7, 410, 210);

        String stringHowToPlay8 = "Tùy chỉnh tốc độ bằng cách nhấn ESC";
        g.setColor(Color.black);
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        g.drawString(stringHowToPlay8, 410, 235);

        String stringHowToPlay10 = "Cố đạt điểm cao nhất có thể nhé";
        g.setColor(Color.black);
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        g.drawString(stringHowToPlay10, 410, 260);

        String stringHowToPlay11 = "CHÚC MAY MẮN !!!";
        g.setColor(Color.green);
        g.setFont(new Font("Tahoma", Font.BOLD, 40));
        g.drawString(stringHowToPlay11, 410, 330);

        Graphics2D graphics2D = (Graphics2D) g;
        Font font = new Font("Tahoma", Font.BOLD + Font.PLAIN, 25);
        graphics2D.setFont(font);
        graphics2D.setColor(Color.red);
        graphics2D.drawString("Press SpaceBar to play", x, y);

        try
        {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        x+=10;
        if(x > this.getWidth()){
            x = 0;
        }

        repaint();

    }
}
