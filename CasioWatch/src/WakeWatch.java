import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;

public class WakeWatch {
    String[] Hour = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
    String[] Minute = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
    private JPanel pnlAwake;
    private JComboBox cbHour;
    private JComboBox cbMinute;
    private JButton btnSettime;
    private JButton btnOk;
    private JLabel lblTime;


    public WakeWatch() throws InterruptedException, IOException, FontFormatException {
        cbHour.setEnabled(false);
        cbMinute.setEnabled(false);
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

        btnSettime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnOk.setEnabled(true);
                cbHour.setEnabled(true);
                cbMinute.setEnabled(true);
            }
        });


        btnOk.addActionListener(new ActionListener() {
            int currentHour = 0;
            int remainingMinute = 0;


            @Override
            public void actionPerformed(ActionEvent e) {

                Calendar calendar = Calendar.getInstance();

                //// Em check phút

                int currentTimeInMinute = calendar.get(Calendar.MINUTE);
                int selectedTimeInMinute = Integer.parseInt(cbMinute.getSelectedItem().toString());
                if (selectedTimeInMinute >= currentTimeInMinute) {
                    remainingMinute = selectedTimeInMinute - currentTimeInMinute;
                } else {
                    remainingMinute = (60 - currentTimeInMinute) + selectedTimeInMinute;
                }


                //// Em check giờ

                if (Integer.parseInt(cbHour.getSelectedItem().toString()) > calendar.get(Calendar.HOUR_OF_DAY)) {
                    currentHour = Integer.parseInt(cbHour.getSelectedItem().toString()) - calendar.get(Calendar.HOUR_OF_DAY);
                } else if (Integer.parseInt(cbHour.getSelectedItem().toString()) == calendar.get(Calendar.HOUR_OF_DAY)) {
                    currentHour = 0;
                } else {
                    currentHour = (24 - calendar.get(Calendar.HOUR_OF_DAY)) + Integer.parseInt(cbHour.getSelectedItem().toString());
                }

                JOptionPane.showMessageDialog(null, "Thời điểm chuông reo sẽ là " + currentHour + " giờ " + remainingMinute + "phút sau !!!");

                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                JOptionPane.showMessageDialog(null, "Dậy đi học mày !!!!");
                            }
                        },
                        Math.abs(((currentHour * 60) + remainingMinute) * 60) * 1000
                );
            }
        });
    }

    public JPanel getPnlAwake() {
        for (int i = 0; i < Hour.length; i++) {
            cbHour.addItem(Hour[i]);
        }

        for (int i = 0; i < Minute.length; i++) {
            cbMinute.addItem(Minute[i]);
        }

        return pnlAwake;
    }
}

