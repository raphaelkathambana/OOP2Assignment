package auth;

import javax.swing.*;

import main.App;
import model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

public class Login extends JFrame implements ActionListener, FocusListener {

    static final String USERNAME = "Name";
    static final String PASSWORD = "Password";
    JLabel lbTitle;
    JLabel lbUsername;
    JLabel lbPassword;
    JTextField tfUsername;
    JButton btnLogin;
    JButton btnRegister;
    JPasswordField pfPassword;
    JButton guestLogin;
    JCheckBox showPassword;

    public Login() {
        // initialize container properties
        setSize(700, 700);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login Page");

        // initialize GUI components
        lbTitle = new JLabel("Login Page");
        lbTitle.setForeground(Color.blue);
        lbTitle.setFont(new Font("Serif", Font.BOLD, 20));
        lbUsername = new JLabel("Username:");
        lbPassword = new JLabel("Password:");
        tfUsername = new JTextField();
        tfUsername.setText("Name");
        tfUsername.setForeground(Color.LIGHT_GRAY);
        JTextField invisible = new JTextField();

        pfPassword = new JPasswordField();
        btnLogin = new JButton("Login");
        btnRegister = new JButton("Register");

        showPassword = new javax.swing.JCheckBox("Show Password");

        guestLogin = new JButton("Sign in as Guest");
        guestLogin.setContentAreaFilled(false);
        guestLogin.setBorderPainted(false);
        guestLogin.setFocusPainted(false);

        // deciding location for the components since we have no layout
        lbTitle.setBounds(300, 110, 400, 30);
        invisible.setBounds(300, 130, 10, 10);
        lbUsername.setBounds(80, 160, 200, 30);
        lbPassword.setBounds(80, 210, 200, 30);
        tfUsername.setBounds(300, 160, 200, 30);
        pfPassword.setBounds(300, 210, 200, 30);
        showPassword.setBounds(300, 250, 150, 15);
        btnLogin.setBounds(250, 270, 100, 30);
        guestLogin.setBounds(225, 300, 150, 30);
        btnRegister.setBounds(370, 270, 100, 30);

        // add to container
        add(lbTitle);
        add(lbUsername);
        add(lbPassword);
        add(tfUsername);
        add(pfPassword);
        add(showPassword);
        add(btnLogin);
        add(guestLogin);
        add(btnRegister);

        // actions
        btnRegister.addActionListener(this);
        btnLogin.addActionListener(this);
        guestLogin.addActionListener(this);
        tfUsername.addFocusListener(this);
        pfPassword.addFocusListener(this);
        showPassword.addActionListener(this::showPasswordActionPerformed);

        pfPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pfPasswordPressed(evt);
            }
        });
    }

    protected void showPasswordActionPerformed(ActionEvent evt) {
        if (showPassword.isSelected()) {
            Logger.getLogger(Login.class.getName()).info("Show Password CheckBox is selected");
            pfPassword.setEchoChar((char) 0);
            pfPassword.setFocusable(false);
        } else {
            Logger.getLogger(Login.class.getName()).info("Show Password CheckBox is Unselected");
            pfPassword.setEchoChar('*');
            pfPassword.setFocusable(true);
            pfPassword.requestFocus();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guestLogin) {
            Logger.getLogger(Login.class.getName()).info("Guest Login Button Clicked");
            App app = new App();
            app.setVisible(true);
            this.dispose();
        }
        if (e.getSource() == btnRegister) {
            Logger.getLogger(Login.class.getName()).info("Register Button Clicked");
            Registration newUser = new Registration();
            newUser.setVisible(true);
            this.dispose();

        } else if (e.getSource() == btnLogin) {
            Logger.getLogger(Login.class.getName()).info("Login Button Clicked");
            new User();
            if (User.login(tfUsername.getText(), String.valueOf(pfPassword.getPassword())) != null) {
                JOptionPane.showMessageDialog(this, ("Welcome " + tfUsername.getText()), "Login Success",
                        JOptionPane.INFORMATION_MESSAGE, null);
                App app = new App();
                app.setVisible(true);
                app.signedIn(User.login(tfUsername.getText(), String.valueOf(pfPassword.getPassword())));
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE,
                        null);
            }
        }
    }

    private void pfPasswordPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Logger.getLogger(Login.class.getName()).info("Login Button Clicked");
            new User();
            if (User.login(tfUsername.getText(), String.valueOf(pfPassword.getPassword())) != null) {
                JOptionPane.showMessageDialog(this, ("Welcome " + tfUsername.getText()), "Login Success",
                        JOptionPane.INFORMATION_MESSAGE, null);
                App app = new App();
                app.setVisible(true);
                app.signedIn(User.login(tfUsername.getText(), String.valueOf(pfPassword.getPassword())));
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE,
                        null);
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == pfPassword) {
            Logger.getLogger(Login.class.getName()).info("Focus Gained on Password");
            pfPassword.setEchoChar('*');
            String word = String.valueOf(pfPassword.getPassword());
            if (word.equals(PASSWORD)) {
                pfPassword.setForeground(Color.BLACK);
                pfPassword.setText("");
            }
            if (word.length() <= 0 && word.equals(PASSWORD)) {
                pfPassword.setText("");
            }
        }
        if (e.getSource() == tfUsername) {
            Logger.getLogger(Login.class.getName()).info("Focus Gained on Name");
            String word = tfUsername.getText();
            if (word.equals(USERNAME)) {
                tfUsername.setForeground(Color.BLACK);
                tfUsername.setText("");
            }
            if (word.length() <= 0 && word.equals(USERNAME)) {
                tfUsername.setText("");
            }
            String word2 = String.valueOf(pfPassword.getPassword());
            if (word2.length() <= 0) {
                pfPassword.setForeground(Color.LIGHT_GRAY);
                pfPassword.setText(PASSWORD);
                pfPassword.setEchoChar((char) 0);
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == pfPassword) {
            Logger.getLogger(Login.class.getName()).info("Focus Lost On Password");
            String word = String.valueOf(pfPassword.getPassword());
            if (word.length() <= 0) {
                pfPassword.setForeground(Color.LIGHT_GRAY);
                pfPassword.setText(PASSWORD);
                pfPassword.setEchoChar((char) 0);
            }
        }
        if (e.getSource() == tfUsername) {
            Logger.getLogger(Login.class.getName()).info("Focus Lost On Name");
            if (tfUsername.getText().length() <= 0) {
                tfUsername.setForeground(Color.LIGHT_GRAY);
                tfUsername.setText(USERNAME);
            }
        }
    }
}
