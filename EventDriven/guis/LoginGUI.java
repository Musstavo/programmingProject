package EventDriven.guis;

import EventDriven.constants.CommonConstants;
import EventDriven.OOP.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginGUI extends Form {
    public LoginGUI() {
        super("Login");
        GUIitems();
    }

    private void GUIitems() {
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(0, 25, 520, 100);
        loginLabel.setForeground(CommonConstants.textColor);
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(loginLabel);

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
        passwordLabel.setBounds(30, 300, 400, 25);
        passwordLabel.setForeground(CommonConstants.textColor);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JTextField passwordField = new JTextField();
        passwordField.setBounds(30, 335, 450, 55);
        passwordField.setForeground(CommonConstants.textColor);
        passwordField.setBackground(CommonConstants.secondryColor);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 24));
        passwordField.setMargin(new Insets(5, 5, 5, 5));

        add(passwordField);
        add(passwordLabel);

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

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Dialog", Font.BOLD, 18));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBackground(CommonConstants.textColor);
        loginButton.setBounds(125, 520, 250, 50);
        add(loginButton);


        loginButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                emptyUsername.setVisible(false);
                emptyPassword.setVisible(false);
                String username = usernameField.getText();
                String password = passwordField.getText();

                if (username.isEmpty()) {
                    emptyUsername.setVisible(true);
                    return;
                }
                if (password.isEmpty()) {
                    emptyPassword.setVisible(true);
                    return;
                }
                boolean exists = false;

                for (User user : EventSystem.users) {
                    if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                        EventSystem.currentUser = EventSystem.users.indexOf(user);
                        LoginGUI.this.dispose();
                        EventsGUI eventsGUI = new EventsGUI();
                        eventsGUI.setVisible(true);
                    }
                }

            }
        });
        JLabel registerLabel = new JLabel();
        registerLabel.setText("<html><u>New user? Create new account here</u></html>");
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.setForeground(CommonConstants.textColor);

        registerLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                LoginGUI.this.dispose();
                RegisterGUI registerGUI = new RegisterGUI();
                registerGUI.setVisible(true);
            }
        });


        registerLabel.setBounds(125, 600, 250, 30);
        add(registerLabel);
    }
}
