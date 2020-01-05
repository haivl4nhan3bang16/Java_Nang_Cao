package view;

import javax.swing.*;

public class Setting {
    public JComboBox cbSettingSpeed;
    private JPanel rootPanel;

    String[] speed = {"Eazy","Normal","Hard","Extremely"};

    public JPanel getRootPanel(){
        for (int i = 0; i < speed.length; i++) {
            cbSettingSpeed.addItem(speed[i]);
        }
        return rootPanel;
    }
}

