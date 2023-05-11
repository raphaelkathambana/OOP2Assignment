package main;

import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Page extends JPanel {

    JLabel greetLabel;

    public JLabel getGreetLabel() {
        return greetLabel;
    }

    Font myFont = new Font("Century Gothic", Font.BOLD, 40);

    public Page() {
        setSize(865, 580);
        setLayout(null);
        setBackground(Color.DARK_GRAY);

        greetLabel = new JLabel(greeting());
        greetLabel.setForeground(Color.WHITE);
        greetLabel.setFont(myFont);
        greetLabel.setBounds(5, 10, 550, 50);

        add(greetLabel);
    }

    private String greeting() {
        Calendar c = Calendar.getInstance();
        int hours = c.get(Calendar.HOUR_OF_DAY);

        String greeting = null;

        if (hours >= 1 && hours <= 12) {
            greeting = "Good Morning";
        } else if (hours >= 12 && hours <= 16) {
            greeting = "Good Afternoon";
        } else if (hours >= 16 && hours <= 21) {
            greeting = "Good Evening";
        } else if (hours >= 21 && hours <= 24) {
            greeting = "Good Night";
        }

        return greeting;
    }
}
