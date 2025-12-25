package EventDriven.guis;

import EventDriven.colors;
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
        loginLabel.setForeground(colors.textColor);
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(loginLabel);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(30, 150, 400, 25);
        usernameLabel.setForeground(colors.textColor);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JTextField usernameField = new JTextField();
        usernameField.setBounds(30, 185, 450, 55);
        usernameField.setForeground(colors.textColor);
        usernameField.setBackground(colors.secondryColor);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 24));
        usernameField.setMargin(new Insets(5, 5, 5, 5));

        add(usernameField);
        add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(30, 300, 400, 25);
        passwordLabel.setForeground(colors.textColor);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30, 335, 450, 55);
        passwordField.setForeground(colors.textColor);
        passwordField.setBackground(colors.secondryColor);
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

        JLabel wrongCred = new JLabel("Wrong Username/Password");
        wrongCred.setBounds(137, 565, 500, 50);
        wrongCred.setForeground(Color.red);
        wrongCred.setFont(new Font("Dialog", Font.PLAIN, 18));
        wrongCred.setVisible(false);
        add(wrongCred);

        JLabel emptyPassword = new JLabel("You can't have an empty password.");
        emptyPassword.setBounds(115, 565, 500, 50);
        emptyPassword.setForeground(Color.red);
        emptyPassword.setFont(new Font("Dialog", Font.PLAIN, 18));
        emptyPassword.setVisible(false);
        add(emptyPassword);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Dialog", Font.BOLD, 18));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBackground(colors.textColor);
        loginButton.setBounds(125, 520, 250, 50);
        add(loginButton);


        loginButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                emptyUsername.setVisible(false);
                emptyPassword.setVisible(false);
                wrongCred.setVisible(false);
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty()) {
                    emptyUsername.setVisible(true);
                    return;
                }
                if (password.isEmpty()) {
                    emptyPassword.setVisible(true);
                    return;
                }
                boolean exists = true;

                for (User user : EventSystem.users) {
                    exists = false;
                    if (username.equalsIgnoreCase(user.getUsername()) && password.equals(user.getPassword())) {
                        exists = true;
                        EventSystem.currentUser = EventSystem.users.indexOf(user);
                        LoginGUI.this.dispose();
                        EventsGUI eventsGUI = new EventsGUI();
                        eventsGUI.setVisible(true);
                    }
                }
                if (!exists) {
                    wrongCred.setVisible(true);
                    return;
                }

            }
        });
        JLabel registerLabel = new JLabel();
        registerLabel.setText("<html><u>New user? Create new account here</u></html>");
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.setForeground(colors.textColor);

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
