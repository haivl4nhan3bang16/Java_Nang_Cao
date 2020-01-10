package snake;


import view.RenderMenu;


import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Menu implements KeyListener {
    public static Menu menu;
    public boolean playMusic = true;
    public JFrame jframeMenu = new JFrame("Menu");
    public RenderMenu renderMenu;
    Controller controller = new Controller();

    public Menu() {
        this.jframeMenu.setVisible(true);
        this.jframeMenu.setSize(805, 700);
        this.jframeMenu.setResizable(false);
        this.jframeMenu.add(this.renderMenu = new RenderMenu());
        this.jframeMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jframeMenu.setLocationRelativeTo(null);
        this.jframeMenu.addKeyListener(this);
    }



    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.jframeMenu.setVisible(false);
            Snake.snake = new Snake();
        }
    }


    public void keyTyped(KeyEvent e) {

    }


    public void keyReleased(KeyEvent e) {

    }
}
