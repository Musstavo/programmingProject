package EventDriven.guis;

import EventDriven.constants.CommonConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEvent extends Form {
    public AddEvent() {
        super("Add Event");
        GUIitems();
    }

    public void GUIitems() {
        JLabel addEventLabel = new JLabel("Add Event");
        addEventLabel.setBounds(0, 25, 520, 100);
        addEventLabel.setForeground(CommonConstants.textColor);
        addEventLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        addEventLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel accessLabel = new JLabel("Access Code: ");
        accessLabel.setBounds(30, 150, 400, 25);
        accessLabel.setForeground(CommonConstants.textColor);
        accessLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JTextField accessCode = new JTextField();
        accessCode.setBounds(30, 185, 450, 55);
        accessCode.setForeground(CommonConstants.textColor);
        accessCode.setBackground(CommonConstants.secondryColor);
        accessCode.setFont(new Font("Dialog", Font.PLAIN, 24));
        accessCode.setMargin(new Insets(5, 5, 5, 5));

        add(addEventLabel);
        add(accessLabel);
        add(accessCode);

        JButton codeButton = new JButton("Enter Code");
        codeButton.setFont(new Font("Dialog", Font.BOLD, 18));
        codeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        codeButton.setBackground(CommonConstants.textColor);
        codeButton.setBounds(125, 270, 250, 50);
        add(codeButton);

        JPanel addEventPanel = new JPanel();
        addEventPanel.setBounds(10, 350, 485, 280);
        addEventPanel.setBorder(BorderFactory.createLineBorder(CommonConstants.textColor, 1));
        addEventPanel.setBackground(CommonConstants.primaryColor);
        addEventPanel.setVisible(false);
        add(addEventPanel);

        JLabel wrongCode = new JLabel("Wrong Access Code.");
        wrongCode.setBounds(160, 310, 500, 50);
        wrongCode.setForeground(Color.red);
        wrongCode.setFont(new Font("Dialog", Font.PLAIN, 18));
        wrongCode.setVisible(false);
        add(wrongCode);

        codeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                wrongCode.setVisible(false);
                addEventPanel.setVisible(false);

                String code = accessCode.getText().toLowerCase();

                if (!code.equals("tavo")) {
                    wrongCode.setVisible(true);
                    return;
                }
                addEventPanel.setVisible(true);

            }
        });

    }
}
