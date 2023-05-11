package auth;

import javax.swing.*;

import model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class Registration extends JFrame implements ActionListener, FocusListener {
    private static final String PASSWORD = "ChoosePassword";
    private static final String USER = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/oopLoginDetails";
    private static final String NAME = "Name";
    private static final String EMAIL = "Email";
    private static final String COUNTY = "County";
    private static final String PHONENUMBER = "Phone Number";
    private static final String PASSWORDTEXT = "Password";
    private static final String PASSWORDCONFIRM = "Confirm Password";
    private static final String EMAILREGEX = "^(.+)@(\\S+)$";

    JLabel lbTitle;
    JLabel lbName;
    JLabel lbEmail;
    JLabel lbPassword;
    JLabel lbPasswordConfirm;
    JLabel lbCounty;
    JLabel lbPhoneNumber;
    JLabel lbGender;
    JLabel lbCourse;
    JTextField tfName;
    JLabel tfNameError;
    JTextField tfEmail;
    JLabel tfEmailError;
    JTextField tfCounty;
    JLabel tfCountyError;
    JTextField tfPhoneNumber;
    JLabel tfPhoneNumberError;
    JButton btnSubmit;
    JButton btnClear;
    JButton btnRegistered;
    JPasswordField pfPassword;
    JCheckBox showPassword;
    JPasswordField pfPasswordConfirm;
    JLabel pfPasswordConfirmError;

    // A group of radio buttons
    // necessary to only allow one radio button to be selected at the same time.
    CheckboxGroup radioGroup;
    // The radio buttons to be selected
    Checkbox radioMale;
    Checkbox radioFemale;
    // An independent selection box
    Checkbox optCourse1;
    Checkbox optCourse2;
    Checkbox optCourse3;

    public Registration() {
        setSize(700, 700);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registration Form in Java");

        lbTitle = new JLabel("Registration Form:");
        lbTitle.setForeground(Color.blue);
        lbTitle.setFont(new Font("Serif", Font.BOLD, 20));

        lbName = new JLabel("Name:");
        lbEmail = new JLabel("Email-ID:");
        lbPassword = new JLabel("Create Password:");
        showPassword = new javax.swing.JCheckBox("Show Password");
        lbPasswordConfirm = new JLabel("Confirm Password:");
        lbCounty = new JLabel("County:");
        lbPhoneNumber = new JLabel("Phone No:");
        lbGender = new JLabel("Gender:");
        lbCourse = new JLabel("Course:");
        tfName = new JTextField();// name
        tfNameError = new JLabel();
        tfEmail = new JTextField();// email
        tfEmailError = new JLabel();
        pfPassword = new JPasswordField();// password
        pfPasswordConfirm = new JPasswordField();// password again
        pfPasswordConfirmError = new JLabel();
        tfCounty = new JTextField();// county
        tfCountyError = new JLabel();
        tfPhoneNumber = new JTextField();// phone number
        tfPhoneNumberError = new JLabel();
        // initialize the radio buttons group
        radioGroup = new CheckboxGroup();
        // first radio button. Gives the label text, tells to which
        // group it belongs and sets the default state (unselected)
        radioMale = new Checkbox("Male", radioGroup, false);
        // same but selected
        radioFemale = new Checkbox("Female", radioGroup, true);
        // Label and state of the checkbox
        optCourse1 = new Checkbox("Course 1", false);
        optCourse2 = new Checkbox("Course 2", false);
        optCourse3 = new Checkbox("Course 3", false);

        btnSubmit = new JButton("Submit");
        btnClear = new JButton("Clear");
        btnRegistered = new JButton("Already registered?");
        btnRegistered.setContentAreaFilled(false);
        btnRegistered.setBorderPainted(false);
        btnRegistered.setFocusPainted(false);

        btnSubmit.addActionListener(this);
        btnClear.addActionListener(this);
        btnRegistered.addActionListener(this);

        tfName.addFocusListener(this);
        tfEmail.addFocusListener(this);
        tfCounty.addFocusListener(this);
        tfPhoneNumber.addFocusListener(this);
        pfPassword.addFocusListener(this);
        pfPasswordConfirm.addFocusListener(this);
        showPassword.addActionListener(this::showPasswordActionPerformed);

        lbTitle.setBounds(250, 30, 400, 30);
        lbName.setBounds(80, 70, 200, 30);
        lbEmail.setBounds(80, 115, 200, 30);
        lbPassword.setBounds(80, 160, 200, 30);
        lbPasswordConfirm.setBounds(80, 210, 200, 30);
        lbCounty.setBounds(80, 255, 200, 30);
        lbPhoneNumber.setBounds(80, 300, 200, 30);
        lbGender.setBounds(80, 350, 200, 30);
        lbCourse.setBounds(80, 390, 200, 30);
        tfName.setBounds(300, 70, 200, 30);
        tfNameError.setBounds(300, 90, 200, 30);
        tfEmail.setBounds(300, 115, 200, 30);
        tfEmailError.setBounds(300, 135, 200, 30);
        pfPassword.setBounds(300, 160, 200, 30);
        showPassword.setBounds(300, 190, 150, 15);
        pfPasswordConfirm.setBounds(300, 210, 200, 30);
        pfPasswordConfirmError.setBounds(300, 230, 200, 30);
        tfCounty.setBounds(300, 255, 200, 30);
        tfCountyError.setBounds(300, 275, 200, 30);
        tfPhoneNumber.setBounds(300, 300, 200, 30);
        tfPhoneNumberError.setBounds(300, 325, 200, 30);
        btnSubmit.setBounds(250, 450, 100, 30);
        btnClear.setBounds(370, 450, 100, 30);
        btnRegistered.setBounds(215, 480, 185, 30);
        radioMale.setBounds(300, 350, 100, 30);
        radioFemale.setBounds(400, 350, 100, 30);
        optCourse1.setBounds(300, 390, 100, 30);
        optCourse2.setBounds(400, 390, 100, 30);
        optCourse3.setBounds(500, 390, 100, 30);

        add(lbTitle);
        add(lbName);
        add(tfName);
        add(tfNameError);
        add(lbEmail);
        add(tfEmail);
        add(tfEmailError);
        add(lbPassword);
        add(pfPassword);
        add(lbPasswordConfirm);
        add(pfPasswordConfirm);
        add(showPassword);
        add(pfPasswordConfirmError);
        add(lbCounty);
        add(tfCounty);
        add(tfCountyError);
        add(lbPhoneNumber);
        add(tfPhoneNumber);
        add(tfPhoneNumberError);
        add(btnSubmit);
        add(btnClear);
        add(btnRegistered);
        add(lbGender);
        add(radioMale);
        add(radioFemale);
        add(lbCourse);
        add(optCourse1);
        add(optCourse2);
        add(optCourse3);
    }

    protected void showPasswordActionPerformed(ActionEvent evt) {
        if (showPassword.isSelected()) {
            Logger.getLogger(Login.class.getName()).info("SHow Password CheckBox is selected");
            pfPassword.setEchoChar((char) 0);
        } else {
            Logger.getLogger(Login.class.getName()).info("SHow Password CheckBox is Unselected");
            pfPassword.setEchoChar('*');
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit) {
            Logger.getLogger(Registration.class.getName()).info("Submit Button Clicked");
            if (register()) {
                Login newLogin = new Login();
                newLogin.setVisible(true);
                this.dispose();
            } else {
                wrongInfoInput();
            }
        }
        if (e.getSource() == btnClear) {
            Logger.getLogger(Registration.class.getName()).info("Clear Button Clicked");
            tfName.setText("");
            tfEmail.setText("");
            pfPassword.setText("");
            pfPasswordConfirm.setText("");
            tfCounty.setText("");
            tfPhoneNumber.setText("");
            optCourse1.setState(false);
            optCourse2.setState(false);
            optCourse3.setState(false);
            wrongInfoInput();
            tfName.setForeground(Color.BLACK);
            tfName.requestFocus();
        }
        if (e.getSource() == btnRegistered) {
            Logger.getLogger(Registration.class.getName()).info("Already Registered Button Clicked");
            Login existingUserLogin = new Login();
            existingUserLogin.setVisible(true);
            this.dispose();
        }
    }

    public static Connection connection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Logger.getLogger(Registration.class.getName()).info("Successfully Connected to Database");
        } catch (SQLException e) {
            Logger.getLogger(Registration.class.getName()).warning("Database Not Found");
        }
        return connection;
    }

    private static Connection connection = connection();

    public static Connection getConnection() {
        return connection;
    }

    public boolean register() {
        if(tfName.getForeground() != Color.LIGHT_GRAY && tfCounty.getForeground() != Color.LIGHT_GRAY && patternMatches(tfEmail.getText(), EMAILREGEX)) {
            if (!String.valueOf(pfPasswordConfirm.getPassword()).equals(String.valueOf(pfPassword.getPassword()))) {
                wrongInfoInput();
                return false;
            } else if (User.createUser(tfName.getText(), tfEmail.getText(), String.valueOf(pfPassword.getPassword()),
                    tfCounty.getText(),
                    tfPhoneNumber.getText(), getGender(), getCourse()) != null) {
                Logger.getLogger(Registration.class.getName()).info("User Registered");
                wrongInfoInput();
                JOptionPane.showMessageDialog(this, "Registration successful. Please Login to Continue", "Success",
                        JOptionPane.INFORMATION_MESSAGE, null);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    private void wrongInfoInput() {
        Logger.getLogger(Registration.class.getName()).info("Entered Wrong Input Function");
        String msg = "Wrong Input detected";
        String blankInput = "Field Blank";
        tfNameError.setText("");
        tfEmailError.setText("");
        pfPasswordConfirmError.setText("");
        tfPhoneNumberError.setText("");
        tfCountyError.setText("");
        tfName.requestFocus();

        if (tfName.getText().equals("") || tfName.getForeground() == Color.LIGHT_GRAY) {
            Logger.getLogger(Registration.class.getName()).info(msg);
            tfNameError.setForeground(Color.RED);
            tfNameError.setText(blankInput);
        }
        if (tfEmail.getText().equals("")) {
            Logger.getLogger(Registration.class.getName()).info(msg);
            tfEmailError.setForeground(Color.RED);
            tfEmailError.setText(blankInput);
        } else if (!(patternMatches(tfEmail.getText(), EMAILREGEX))) {
            Logger.getLogger(Registration.class.getName()).log(Level.INFO, "{0}".concat(". Invalid Email"), msg);
            tfEmailError.setForeground(Color.RED);
            tfEmailError.setText("Invalid Email");
        }
        if (String.valueOf(pfPasswordConfirm.getPassword()).equals("")) {
            Logger.getLogger(Registration.class.getName()).info(msg);
            pfPasswordConfirmError.setForeground(Color.RED);
            pfPasswordConfirmError.setText(blankInput);
        }
        if (!String.valueOf(pfPasswordConfirm.getPassword()).equals(String.valueOf(pfPassword.getPassword()))) {
            Logger.getLogger(Registration.class.getName()).info(msg);
            pfPasswordConfirmError.setForeground(Color.RED);
            pfPasswordConfirmError.setText("Passwords Do Not Match");
        }
        if (tfPhoneNumber.getText().equals("")) {
            Logger.getLogger(Registration.class.getName()).info(msg);
            tfPhoneNumberError.setForeground(Color.RED);
            tfPhoneNumberError.setText(blankInput);
        } else {
            try {
                Long.parseLong(tfPhoneNumber.getText());
            } catch (Exception e) {
                Logger.getLogger(Registration.class.getName()).info(msg);
                tfPhoneNumberError.setForeground(Color.RED);
                tfPhoneNumberError.setText("Invalid Input");
            }
        }

        if (tfCounty.getText().equals("") || tfCounty.getForeground() == Color.LIGHT_GRAY) {
            Logger.getLogger(Registration.class.getName()).info(msg);
            tfCountyError.setForeground(Color.RED);
            tfCountyError.setText(blankInput);
        }
    }

    public String getGender() {
        if (radioMale.getState()) {
            return "Male";
        } else if (radioFemale.getState()) {
            return "Female";
        } else {
            return "";
        }
    }

    public String getCourse() {
        String courses = "";
        if (optCourse1.getState()) {
            courses += "Course 1";
        }
        if (optCourse2.getState()) {
            courses += "Course 2";
        }
        if (optCourse3.getState()) {
            courses += "Course 3";
        }
        return courses;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == tfName) {
            tfNameFocusGained();
        }
        if (e.getSource() == tfEmail) {
            tfEmailFocusGained();
        }
        if (e.getSource() == tfCounty) {
            tfCountyFocusGained();
        }
        if (e.getSource() == tfPhoneNumber) {
            tfPhoneNumberFocusGained();
        }
        if (e.getSource() == pfPassword) {
            pfPasswordFocusGained();
        }
        if (e.getSource() == pfPasswordConfirm) {
            pfPasswordConfirmFocusGained();
        }
    }

    private void pfPasswordConfirmFocusGained() {
        Logger.getLogger(Registration.class.getName()).info("Focus on pfPasswordConfirm Gained");
        pfPasswordConfirm.setEchoChar('*');
        String word = String.valueOf(pfPasswordConfirm.getPassword());
        if (word.equals(PASSWORDCONFIRM)) {
            pfPasswordConfirm.setForeground(Color.BLACK);
            pfPasswordConfirm.setText("");
        }
        if (word.length() <= 0 && word.equals(PASSWORDCONFIRM)) {
            pfPasswordConfirm.setText("");
        }
    }

    private void pfPasswordFocusGained() {
        Logger.getLogger(Registration.class.getName()).info("Focus on pfPassword Gained");
        pfPassword.setEchoChar('*');
        String word = String.valueOf(pfPassword.getPassword());
        if (word.equals(PASSWORDTEXT)) {
            pfPassword.setForeground(Color.BLACK);
            pfPassword.setText("");
        }
        if (word.length() <= 0 && word.equals(PASSWORDTEXT)) {
            pfPassword.setText("");
        }
    }

    private void tfPhoneNumberFocusGained() {
        Logger.getLogger(Registration.class.getName()).info("Focus on tfPhoneNumber Gained");
        String word = tfPhoneNumber.getText();
        if (word.equals(PHONENUMBER)) {
            tfPhoneNumber.setForeground(Color.BLACK);
            tfPhoneNumber.setText("");
        }
        if (word.length() <= 0 && word.equals(PHONENUMBER)) {
            tfPhoneNumber.setText("");
        }
    }

    private void tfCountyFocusGained() {
        Logger.getLogger(Registration.class.getName()).info("Focus on tfCounty Gained");
        String word = tfCounty.getText();
        if (word.equals(COUNTY)) {
            tfCounty.setForeground(Color.BLACK);
            tfCounty.setText("");
        }
        if (word.length() <= 0 && word.equals(COUNTY)) {
            tfCounty.setText("");
        }
    }

    private void tfEmailFocusGained() {
        Logger.getLogger(Registration.class.getName()).info("Focus on tfEmail Gained");
        String word = tfEmail.getText();
        if (word.equals(EMAIL)) {
            tfEmail.setForeground(Color.BLACK);
            tfEmail.setText("");
        }
        if (word.length() <= 0 && word.equals(EMAIL)) {
            tfEmail.setText("");
        }
    }

    private void tfNameFocusGained() {
        Logger.getLogger(Registration.class.getName()).info("Focus on tfName Gained");
        String word = tfName.getText();
        if (word.equals(NAME)) {
            tfName.setForeground(Color.BLACK);
            tfName.setText("");
        }
        if (word.length() <= 0 && word.equals(NAME)) {
            tfName.setText("");
        }
        String word2 = String.valueOf(pfPassword.getPassword());
        if (word2.length() <= 0) {
            pfPassword.setForeground(Color.LIGHT_GRAY);
            pfPassword.setText(PASSWORDTEXT);
            pfPassword.setEchoChar((char) 0);
        }
        String word3 = tfCounty.getText();
        if (word3.length() <= 0) {
            tfCounty.setForeground(Color.LIGHT_GRAY);
            tfCounty.setText(COUNTY);
        }
        if (word3.length() <= 0 && word.equals(COUNTY)) {
            tfCounty.setText("");
        }
        String word4 = tfEmail.getText();
        if (word4.length() <= 0) {
            tfEmail.setForeground(Color.LIGHT_GRAY);
            tfEmail.setText(EMAIL);
        }
        if (word4.length() <= 0 && word.equals(EMAIL)) {
            tfEmail.setText("");
        }
        String word5 = String.valueOf(pfPasswordConfirm.getPassword());
        if (word5.length() <= 0) {
            pfPasswordConfirm.setForeground(Color.LIGHT_GRAY);
            pfPasswordConfirm.setText(PASSWORDCONFIRM);
            pfPasswordConfirm.setEchoChar((char) 0);
        }
        String word8 = tfPhoneNumber.getText();
        if (word8.length() <= 0) {
            tfPhoneNumber.setForeground(Color.LIGHT_GRAY);
            tfPhoneNumber.setText(PHONENUMBER);
        }
        if (word8.length() <= 0 && word.equals(PHONENUMBER)) {
            tfPhoneNumber.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == tfName) {
            tfNameFocusLost();
        }
        if (e.getSource() == tfEmail) {
            tfEmailFocusLost();
        }
        if (e.getSource() == tfCounty) {
            tfCountyFocusLost();
        }
        if (e.getSource() == tfPhoneNumber) {
            tfPhoneNumberFocusLost();
        }
        if (e.getSource() == pfPassword) {
            pfPasswordFocusLost();
        }
        if (e.getSource() == pfPasswordConfirm) {
            pfPasswordConfirmFocusLost(); 
        }
    }

    private void pfPasswordConfirmFocusLost() {
        Logger.getLogger(Registration.class.getName()).info("Focus on pfPasswordConfirm Lost");
        String word = String.valueOf(pfPasswordConfirm.getPassword());
        if (word.length() <= 0) {
            pfPasswordConfirm.setForeground(Color.LIGHT_GRAY);
            pfPasswordConfirm.setText(PASSWORDCONFIRM);
            pfPasswordConfirm.setEchoChar((char) 0);
        }
    }

    private void pfPasswordFocusLost() {
        Logger.getLogger(Registration.class.getName()).info("Focus on pfPassword Lost");
        String word = String.valueOf(pfPassword.getPassword());
        if (word.length() <= 0) {
            pfPassword.setForeground(Color.LIGHT_GRAY);
            pfPassword.setText(PASSWORDTEXT);
            pfPassword.setEchoChar((char) 0);
        }
    }

    private void tfPhoneNumberFocusLost() {
        Logger.getLogger(Registration.class.getName()).info("Focus on tfPhoneNumber Lost");
        if (tfPhoneNumber.getText().length() <= 0) {
            tfPhoneNumber.setForeground(Color.LIGHT_GRAY);
            tfPhoneNumber.setText(PHONENUMBER);
        }
    }

    private void tfCountyFocusLost() {
        Logger.getLogger(Registration.class.getName()).info("Focus on tfCounty Lost");
        if (tfCounty.getText().length() <= 0) {
            tfCounty.setForeground(Color.LIGHT_GRAY);
            tfCounty.setText(COUNTY);
        }
    }

    private void tfEmailFocusLost() {
        Logger.getLogger(Registration.class.getName()).info("Focus on tfEmail Lost");
        if (tfEmail.getText().length() <= 0) {
            tfEmail.setForeground(Color.LIGHT_GRAY);
            tfEmail.setText(EMAIL);
        }
    }

    private void tfNameFocusLost() {
        Logger.getLogger(Registration.class.getName()).info("Focus on tfName Lost");
        if (tfName.getText().length() <= 0) {
            tfName.setForeground(Color.LIGHT_GRAY);
            tfName.setText(NAME);
        }
    }

}