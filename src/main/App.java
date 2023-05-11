package main;

import javax.swing.*;

import auth.Login;
import auth.Registration;
import model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class App extends JFrame implements ActionListener {

    JButton registerBtn;
    JButton loginBtn;
    JButton loggedInBtn;
    JButton logOutBtn;
    JLabel l1;
    ActionListener profile;
    User signedInUser;
    Page contentPanel;

    public App() {
        // initialize container properties
        setSize(900, 700);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dashboard");

        // initialize GUI components
        l1 = new JLabel("Dashboard");
        registerBtn = new JButton("Register");
        loginBtn = new JButton("Login");
        loggedInBtn = new JButton();
        logOutBtn = new JButton();
        contentPanel = new Page();
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));

        registerBtn.setContentAreaFilled(false);
        registerBtn.setBorderPainted(false);
        registerBtn.setFocusPainted(false);
        loginBtn.setContentAreaFilled(false);
        logOutBtn.setContentAreaFilled(false);
        loggedInBtn.setContentAreaFilled(false);
        loginBtn.setBorderPainted(false);
        logOutBtn.setBorderPainted(false);
        loggedInBtn.setBorderPainted(false);
        loginBtn.setFocusPainted(false);
        logOutBtn.setFocusPainted(false);
        loggedInBtn.setFocusPainted(false);

        // deciding location for the components since we have no layout
        l1.setBounds(10, 5, 400, 30);
        registerBtn.setBounds(799, 5, 100, 30);
        logOutBtn.setBounds(800, 5, 100, 30);
        loginBtn.setBounds(700, 5, 100, 30);
        loggedInBtn.setBounds(700, 5, 100, 30);
        contentPanel.setBounds(10, 50, 865, 580);

        // add to container
        add(l1);
        add(loginBtn);
        add(registerBtn);
        add(loggedInBtn);
        add(logOutBtn);
        add(contentPanel);

        // Action Listeners
        loginBtn.addActionListener(this);
        loggedInBtn.addActionListener(this);
        registerBtn.addActionListener(this);
        logOutBtn.addActionListener(this);

    }

    public static void main(String[] args) {
        App app = new App();
        app.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loggedInBtn) {
            Logger.getLogger(App.class.getName()).info("Profile Button clicked.");
            Profile profileView = new Profile(signedInUser);
            profileView.setVisible(true);
        }
        if (e.getSource() == logOutBtn) {
            Logger.getLogger(App.class.getName()).info("Log Out Button clicked.");
            App loggedOutGuest = new App();
            loggedOutGuest.setVisible(true);
            this.dispose();
        }
        if (e.getSource() == loginBtn) {
            Logger.getLogger(App.class.getName()).info("Login Button clicked.");
            Login login = new Login();
            login.setVisible(true);
            this.dispose();
        }
        if (e.getSource() == registerBtn) {
            Logger.getLogger(App.class.getName()).info("Register Button clicked.");
            Registration registration = new Registration();
            registration.setVisible(true);
            this.dispose();
        }
    }

    public void signedIn(User user) {
        Logger.getLogger(App.class.getName()).info("User " + user.getName() + " signed in.");
        loggedInBtn.setText(user.getName());
        logOutBtn.setText("Log Out");
        loginBtn.setVisible(false);
        registerBtn.setVisible(false);
        if (user.getCourse().length() > 10) {
            contentPanel.setBackground(Color.LIGHT_GRAY);
        } else {
            contentPanel.setBackground(Color.DARK_GRAY);
        }
        contentPanel.getGreetLabel()
                .setText(contentPanel.getGreetLabel().getText().concat(" " + loggedInBtn.getText()));
        signedInUser = user;
    }


}