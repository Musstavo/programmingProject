package EventDriven.guis;

import EventDriven.colors.CommonConstants;


import EventDriven.OOP.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;


public class RegisterGUI extends Form {
    public RegisterGUI() {
        super("Register");
        GUIitems();
    }

    public void GUIitems() {
        JLabel registerLabel = new JLabel("Register");
        registerLabel.setBounds(0, 25, 520, 100);
        registerLabel.setForeground(CommonConstants.textColor);
        registerLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(registerLabel);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(30, 150, 400, 25);
        usernameLabel.setForeground(CommonConstants.textColor);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JTextField usernameField = new JTextField();
        usernameField.setBounds(30, 185, 450, 55);
        usernameField.setForeground(CommonConstants.textColor);
        usernameField.setBackground(CommonConstants.secondryColor);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 24));
        usernameField.setMargin(new Insets(5, 5, 5, 5)); // adds margin to the text in the text field

        add(usernameField);
        add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(30, 255, 400, 25);
        passwordLabel.setForeground(CommonConstants.textColor);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JTextField passwordField = new JTextField();
        passwordField.setBounds(30, 290, 450, 55);
        passwordField.setForeground(CommonConstants.textColor);
        passwordField.setBackground(CommonConstants.secondryColor);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 24));
        passwordField.setMargin(new Insets(5, 5, 5, 5));

        add(passwordField);
        add(passwordLabel);

        JLabel rePasswordLabel = new JLabel("Re-enter Password: ");
        rePasswordLabel.setBounds(30, 365, 400, 25);
        rePasswordLabel.setForeground(CommonConstants.textColor);
        rePasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JTextField rePasswordField = new JTextField();
        rePasswordField.setBounds(30, 400, 450, 55);
        rePasswordField.setForeground(CommonConstants.textColor);
        rePasswordField.setBackground(CommonConstants.secondryColor);
        rePasswordField.setFont(new Font("Dialog", Font.PLAIN, 24));
        rePasswordField.setMargin(new Insets(5, 5, 5, 5));

        add(rePasswordField);
        add(rePasswordLabel);

        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Dialog", Font.BOLD, 18));
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setBackground(CommonConstants.textColor);
        registerButton.setBounds(125, 520, 250, 50);


        JLabel usernameExists = new JLabel("Username already exists.");
        usernameExists.setBounds(150, 565, 500, 50);
        usernameExists.setForeground(Color.red);
        usernameExists.setFont(new Font("Dialog", Font.PLAIN, 18));
        usernameExists.setVisible(false);
        add(usernameExists);

        JLabel passwordsNotMatching = new JLabel("Passwords don't match.");
        passwordsNotMatching.setBounds(140, 565, 500, 50);
        passwordsNotMatching.setForeground(Color.red);
        passwordsNotMatching.setFont(new Font("Dialog", Font.PLAIN, 18));
        passwordsNotMatching.setVisible(false);
        add(passwordsNotMatching);

        JLabel successfulRegister = new JLabel("You have successfully created an account.");
        successfulRegister.setBounds(90, 565, 500, 50);
        successfulRegister.setForeground(Color.green);
        successfulRegister.setFont(new Font("Dialog", Font.PLAIN, 18));
        successfulRegister.setVisible(false);
        add(successfulRegister);

        JLabel emptyUsername = new JLabel("You can't have an empty username.");
        emptyUsername.setBounds(115, 565, 500, 50);
        emptyUsername.setForeground(Color.red);
        emptyUsername.setFont(new Font("Dialog", Font.PLAIN, 18));
        emptyUsername.setVisible(false);
        add(emptyUsername);

        JLabel emptyPassword = new JLabel("You can't have an empty password.");
        emptyPassword.setBounds(115, 565, 500, 50);
        emptyPassword.setForeground(Color.red);
        emptyPassword.setFont(new Font("Dialog", Font.PLAIN, 18));
        emptyPassword.setVisible(false);
        add(emptyPassword);

        registerButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                emptyUsername.setVisible(false);
                successfulRegister.setVisible(false);
                passwordsNotMatching.setVisible(false);
                usernameExists.setVisible(false);
                emptyPassword.setVisible(false);

                String username = usernameField.getText();
                String password = passwordField.getText();
                String rePassword = rePasswordField.getText();

                if (username.isEmpty()) {
                    emptyUsername.setVisible(true);
                    return;
                }
                if (password.isEmpty() || rePassword.isEmpty()) {
                    emptyPassword.setVisible(true);
                    return;
                }

                for (User user : EventSystem.users) {
                    if (user.getUsername().equals(username)) {
                        usernameExists.setVisible(true);
                        return;
                    }
                }

                if (!password.equals(rePassword)) {
                    passwordsNotMatching.setVisible(true);
                    return;
                }

                User newUser = new User(username, password);
                try {
                    EventSystem.writeToFile(newUser);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                EventSystem.users.add(newUser);
                successfulRegister.setVisible(true);

            }
        });

        add(registerButton);

        JLabel loginLabel = new JLabel();
        loginLabel.setText("<html><u>Have an account? Login here</u></html>");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.setForeground(CommonConstants.textColor);

        loginLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                RegisterGUI.this.dispose();
                LoginGUI loginGUI = new LoginGUI();
                loginGUI.setVisible(true);
            }
        });

        loginLabel.setBounds(125, 600, 250, 30);
        add(loginLabel);

    }


}
