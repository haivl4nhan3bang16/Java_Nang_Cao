package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;
import javax.swing.JPanel;

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
        g.setColor(Color.white);
        g.drawString(stringShow, 600, 650);

        String string = "Score: " + snake.score + " -- Length: " + snake.tailLength + " -- Time: " + snake.time / 20;
        g.setColor(Color.white);
        g.drawString(string, 20, 650);

        String stringGameOver = "Game Over!";
        if (snake.over) {
            g.drawString(stringGameOver, (int)((float)(this.getWidth() / 2) - (float)string.length() * 1F), (int)snake.dim.getHeight() / 4);
        }

        String stringMode = "Hard Mode: " + snake.mode;
        if (!snake.over) {
            g.drawString(stringMode, 350, 650);
        }

    }
}
