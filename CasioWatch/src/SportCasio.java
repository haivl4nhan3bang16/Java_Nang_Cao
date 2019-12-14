import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;

public class SportCasio extends Thread {
    private JPanel panelSport;
    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;
    private JButton backButton;
    private JLabel lblSportTime;
    private JLabel lblSportMilisecond;

    static int Hour = 0;
    static int Minute = 0;
    static int Second = 0;
    static int Milisecond = 0;

    static boolean startSport = true;


    public SportCasio() throws InterruptedException, IOException, FontFormatException {
        String filename="C:\\Users\\duyha\\IdeaProjects\\CasioWatch\\image\\CursedTimerUlil-Aznm.ttf";

        Font fontTime = Font.createFont(Font.TRUETYPE_FONT, new File(filename));
        Font fontMilisecond = Font.createFont(Font.TRUETYPE_FONT, new File(filename));

        fontTime = fontTime.deriveFont(Font.BOLD,36);
        fontMilisecond = fontTime.deriveFont(Font.BOLD,20);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(fontTime);
        ge.registerFont(fontMilisecond);


        lblSportTime.setFont(fontTime);
        lblSportMilisecond.setFont(fontMilisecond);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSport = true;
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            if(startSport){
                                try {
                                    sleep(1);
                                    if(Milisecond > 999){
                                        Milisecond = 0;
                                        Second++;
                                    }
                                    if(Second > 59){
                                        Milisecond = 0;
                                        Second = 0;
                                        Minute++;
                                    }if(Minute > 59){
                                        Milisecond = 0;
                                        Second = 0;
                                        Minute = 0;
                                        Hour++;
                                    }
                                    GetTime getTime = new GetTime(String.valueOf(Hour), String.valueOf(Minute), String.valueOf(Second), String.valueOf(Milisecond));
                                    lblSportMilisecond.setText(String.valueOf(getTime.getMilisecond()));
                                    Milisecond++;
                                    lblSportTime.setText(getTime.getTimes());

                                }catch (Exception e){

                                }
                            }
                            else {
                                break;
                            }

                        }
                    }
                });
                thread.start();
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSport = false;
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSport = false;
                Hour = 0;
                Minute = 0;
                Second = 0;
                Milisecond = 0;

                GetTime getTime = new GetTime(String.valueOf(0), String.valueOf(0), String.valueOf(0), String.valueOf(0));
                lblSportTime.setText(getTime.getTimes());
                lblSportMilisecond.setText((String.valueOf(getTime.getMilisecond())));
            }
        });
    }


    public JPanel getPanelSport() {
        return panelSport;
    }
}
