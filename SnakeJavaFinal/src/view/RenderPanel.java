package view;

import snake.Snake;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class RenderPanel extends JPanel {
    public static Color green = new Color(1666073);

    public RenderPanel() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(green);
        g.fillRect(0, 0, 800, 700);
        Snake snake = Snake.snake;
        g.setColor(Color.BLUE);
        Iterator var4 = snake.snakeParts.iterator();

        while(var4.hasNext()) {
            Point point = (Point)var4.next();
            g.fillRect(point.x * 10, point.y * 10, 10, 10);
        }

        g.fillRect(snake.head.x * 10, snake.head.y * 10, 10, 10);
        g.setColor(Color.RED);
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
        if (snake.over) {
            g.setFont(new Font("Tahoma", Font.BOLD, 50));
            g.setColor(Color.MAGENTA);
            g.drawString(stringGameOver, (this.getWidth() / 2) - 150, this.getHeight() / 2);
        }

        String stringMode = "Hard Mode: " + snake.mode;
        if (!snake.over) {
            g.setFont(new Font("Tahoma", Font.BOLD, 15));
            g.setColor(Color.white);
            g.drawString(stringMode, 300, 650);
        }

    }
}
