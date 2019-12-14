import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class MainCasio {
    private JPanel background;
    private JButton sportButton;
    private JLabel lblTime;
    private JButton btnAlarm;


    public MainCasio() throws InterruptedException, IOException, FontFormatException {
        String filename="C:\\Users\\duyha\\IdeaProjects\\CasioWatch\\image\\CursedTimerUlil-Aznm.ttf";

        Font font = Font.createFont(Font.TRUETYPE_FONT, new File(filename));
        font = font.deriveFont(Font.BOLD,36);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);


        lblTime.setFont(font);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Calendar calendar = Calendar.getInstance();
                    int hour = calendar.get(Calendar.HOUR_OF_DAY);
                    int minute = calendar.get(Calendar.MINUTE);
                    int second = calendar.get(Calendar.SECOND);

                    GetTime getTime = new GetTime(String.valueOf(hour), String.valueOf(minute), String.valueOf(second), "");
                    lblTime.setText(getTime.getTimes());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

        sportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameSport = new JFrame();
                frameSport.setTitle("Sport Mode");
                try {
                    frameSport.setContentPane(new SportCasio().getPanelSport());
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (FontFormatException ex) {
                    ex.printStackTrace();
                }
                frameSport.setPreferredSize(new Dimension(600, 400));
                frameSport.setLocationRelativeTo(null);
                frameSport.pack();
                frameSport.setVisible(true);
            }
        });

        btnAlarm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame awakeFrame = new JFrame();
                awakeFrame.setTitle("Awake Frame");
                awakeFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                try {
                    awakeFrame.setContentPane(new WakeWatch().getPnlAwake());
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (FontFormatException ex) {
                    ex.printStackTrace();
                }
                awakeFrame.setPreferredSize(new Dimension(600, 400));
                awakeFrame.setLocationRelativeTo(null);
                awakeFrame.pack();
                awakeFrame.setVisible(true);
            }
        });
    }

    public JPanel getBackground() {
        return background;
    }


}
