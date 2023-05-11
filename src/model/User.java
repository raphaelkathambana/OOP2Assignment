package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import auth.Registration;

public class User {
    private String name;
    private String email;
    private String password;
    private String country;
    private String county;
    private Long phoneNumber;
    private String gender;
    private String course;
    static User aUser = null;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public static User login(String username, String password) {
        if (username.equals("") || password.equals("")) return null;
        String query = "select * from users where name = ? and password = ?";

        try (PreparedStatement pStatement = Registration.getConnection().prepareStatement(query);) {
            pStatement.setString(1, username);
            pStatement.setString(2, password);

            ResultSet rs = pStatement.executeQuery();


            aUser = new User();
            while (rs.next()) {
                aUser.setName(rs.getString(1));
                aUser.setEmail(rs.getString(2));
                aUser.setPassword(rs.getString(3));
                aUser.setCountry(rs.getString(4));
                aUser.setCounty(rs.getString(5));
                aUser.setPhoneNumber(Long.parseLong(rs.getString(6)));
                aUser.setGender(rs.getString(7));
                aUser.setCourse(rs.getString(8));
            }
            if (aUser.getName().equals("")) return null;
            Logger.getLogger(User.class.getName()).info("Login successful.");
        } catch (SQLException e) {
            Logger.getLogger(User.class.getName()).warning("Error: " + e.getMessage());
            aUser = null;
        } catch (NullPointerException e) {
            Logger.getLogger(User.class.getName()).warning("Wrong Credentials Entered.");
            aUser = null;
        }
        return aUser;
    }

    public static User createUser(String name, String email, String password, String country, String phoneNumber,
            String gender, String course) {
        String query = "INSERT INTO users (name, email, password, county, phoneNumber, gender, course) VALUES (?,?,?,?,?,?,?);";

        try (PreparedStatement pStatement = Registration.getConnection().prepareStatement(query);) {
            pStatement.setString(1, name);
            pStatement.setString(2, email);
            pStatement.setString(3, password);
            pStatement.setString(4, country);
            pStatement.setLong(5, Long.parseLong(phoneNumber));
            pStatement.setString(6, gender);
            pStatement.setString(7, course);

            int affectedRows = pStatement.executeUpdate();

            Logger.getLogger(User.class.getName()).log(Level.INFO, "Registration successful. {0}", affectedRows);
            aUser = makeUser(name, email, password, country, phoneNumber, gender, course);
        } catch (SQLException | NumberFormatException e) {
            Logger.getLogger(User.class.getName()).warning("Error In Data" + e.getMessage());
            aUser = null;
        }
        return aUser;
    }

    public static User makeUser(String name, String email, String password, String country, String phoneNumber,
            String gender, String course) {
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setCountry(country);
        newUser.setPhoneNumber(Long.parseLong(phoneNumber));
        newUser.setGender(gender);
        newUser.setCourse(course);
        return newUser;
    }

}
