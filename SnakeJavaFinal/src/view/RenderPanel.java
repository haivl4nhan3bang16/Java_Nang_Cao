package view;

import snake.Snake;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class RenderPanel extends JPanel {

    public RenderPanel() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 800, 700);
        Snake snake = Snake.snake;
        g.setColor(Color.WHITE);
        Iterator iterator = snake.snakeParts.iterator();

        while(iterator.hasNext()) {
            Point point = (Point)iterator.next();
            g.fillRect(point.x * 10, point.y * 10, 10, 10);
        }
        g.setColor(Color.BLUE);
        g.fillRect(snake.head.x * 10, snake.head.y * 10, 10, 10);

        g.setColor(Color.YELLOW);
        g.fillRect(snake.food.x * 10, snake.food.y * 10, 10, 10);


        String stringShow = "High Score: " + snake.showScore;
        g.setFont(new Font("Tahoma", Font.BOLD, 15));
        g.setColor(Color.white);
        g.drawString(stringShow, 600, 650);

        String string = "Score: " + snake.score;
        g.setFont(new Font("Tahoma", Font.BOLD, 25));
        g.setColor(Color.white);
        g.drawString(string, 20, 650);


        String stringGameOver = "Game Over!";
        if (snake.over && snake.pause == false) {
            g.setFont(new Font("Tahoma", Font.BOLD, 50));
            g.setColor(Color.MAGENTA);
            g.drawString(stringGameOver, (this.getWidth() / 2) - 150, this.getHeight() / 2);
        }

        String stringAgain = "Nhấn space để chơi lại !";
        if (snake.over && snake.pause == false) {
            g.setFont(new Font("Tahoma", Font.BOLD, 20));
            g.setColor(Color.MAGENTA);
            g.drawString(stringAgain, (this.getWidth() / 2) - 150, 200);
        }


        String stringMode = "Hard Mode: " + snake.mode;
        if (!snake.over) {
            g.setFont(new Font("Tahoma", Font.BOLD, 15));
            g.setColor(Color.white);
            g.drawString(stringMode, 300, 650);
        }

        String stringLine = "----------------------------------------------------------------------------------------------------------------------------------------------";
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.PLAIN, 20));
        g.drawString(stringLine, 0, 605);

    }
}
