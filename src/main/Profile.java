package main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import model.User;

public class Profile extends JFrame {
    JLabel name;
    JLabel email;
    JLabel courses;
    JLabel gender;
    JLabel county;

    JLabel nameLabel;
    JLabel emailLabel;
    JLabel genderLabel;
    JLabel coursesLabel;
    JLabel countyLabel;

    public Profile(User user) {

        setSize(500, 500);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Profile");

        name = new JLabel(user.getName());
        email = new JLabel(user.getEmail());
        gender = new JLabel(user.getGender());
        courses = new JLabel(getListOfCourses(user));
        county = new JLabel(user.getCounty());
        nameLabel = new JLabel("Name");
        emailLabel = new JLabel("Email");
        genderLabel = new JLabel("Gender");
        coursesLabel = new JLabel("Courses");
        countyLabel = new JLabel("County");

        nameLabel.setBounds(30, 10, 100, 30);
        emailLabel.setBounds(30, 50, 100, 30);
        genderLabel.setBounds(30, 90, 100, 30);
        coursesLabel.setBounds(30, 130, 100, 30);
        countyLabel.setBounds(30, 170, 100, 30);
        name.setBounds(175, 10, 300, 30);
        email.setBounds(175, 50, 300, 30);
        gender.setBounds(175, 90, 300, 30);
        courses.setBounds(175, 130, 300, 30);
        county.setBounds(175, 170, 300, 30);

        add(nameLabel);
        add(name);
        add(emailLabel);
        add(email);
        add(genderLabel);
        add(gender);
        add(coursesLabel);
        add(courses);
        add(countyLabel);
        add(county);
        
    }
    private String getListOfCourses(User user) {
        String courseList = "";
        for (int i = 0; i < user.getCourse().length(); i++) {
            if (i == 8 && user.getCourse().length() > 10) {
                courseList = courseList.concat(", ");
            }
            if (i == 16 && user.getCourse().length() > 20) {
                courseList = courseList.concat(", ");
            }
            courseList = courseList.concat(Character.toString(user.getCourse().toCharArray()[i]));
        }
        return courseList;
    }

}
