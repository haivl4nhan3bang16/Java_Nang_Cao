import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main{

    public static void main(String[] args) throws InterruptedException, IOException, FontFormatException {

        JFrame frameWatch = new JFrame();
        frameWatch.setTitle("Default Mode");
        frameWatch.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameWatch.setContentPane(new MainCasio().getBackground());
        frameWatch.setPreferredSize(new Dimension(600, 400));
        frameWatch.setLocationRelativeTo(null);
        frameWatch.pack();
        frameWatch.setVisible(true);

    }
}
