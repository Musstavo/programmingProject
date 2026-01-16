package EventDriven.guis;

import EventDriven.OOP.Event;
import EventDriven.colors;
import EventDriven.OOP.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class AddEvent extends Form {
    public AddEvent() {
        super("Add Event");
        GUIitems();
    }

    public void GUIitems() {
        JLabel addEventLabel = new JLabel("Add Event");
        addEventLabel.setBounds(0, 25, 520, 100);
        addEventLabel.setForeground(colors.TEXT_COLOR);
        addEventLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        addEventLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel accessLabel = new JLabel("Access Code: ");
        accessLabel.setBounds(30, 150, 400, 25);
        accessLabel.setForeground(colors.TEXT_COLOR);
        accessLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JTextField accessCode = new JTextField();
        accessCode.setBounds(30, 185, 450, 55);
        accessCode.setForeground(colors.TEXT_COLOR);
        accessCode.setBackground(colors.SECONARY_COLOR);
        accessCode.setFont(new Font("Dialog", Font.PLAIN, 24));
        accessCode.setMargin(new Insets(5, 5, 5, 5));

        add(addEventLabel);
        add(accessLabel);
        add(accessCode);

        JButton codeButton = new JButton("Enter Code");
        codeButton.setFont(new Font("Dialog", Font.BOLD, 18));
        codeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        codeButton.setBackground(colors.TEXT_COLOR);
        codeButton.setBounds(125, 270, 250, 50);
        add(codeButton);

        JPanel addEventPanel = new JPanel();
        addEventPanel.setBounds(10, 350, 485, 280);
        addEventPanel.setBorder(BorderFactory.createLineBorder(colors.TEXT_COLOR, 1));
        addEventPanel.setBackground(colors.PRIMARY_COLOR);
        addEventPanel.setVisible(false);
        addEventPanel.setLayout(null);
        add(addEventPanel);

        JLabel back = new JLabel();
        back.setForeground(colors.TEXT_COLOR);
        back.setFont(new Font("Dialog", Font.PLAIN, 12));
        back.setBounds(10, 8, 200, 20);
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.setText("<html><u>Return to Event Page</u></html>");
        add(back);

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                AddEvent.this.dispose();
                EventsGUI eventGUI = new EventsGUI();
                eventGUI.setVisible(true);
            }
        });

        JLabel wrongCode = new JLabel("Wrong Access Code.");
        wrongCode.setBounds(160, 310, 500, 50);
        wrongCode.setForeground(Color.red);
        wrongCode.setFont(new Font("Dialog", Font.PLAIN, 18));
        wrongCode.setVisible(false);
        add(wrongCode);

        JLabel nameLabel = new JLabel("Event Name: ");
        nameLabel.setBounds(10, 10, 200, 25);
        nameLabel.setForeground(colors.TEXT_COLOR);
        nameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JTextField eventName = new JTextField();
        eventName.setBounds(130, 10, 200, 29);
        eventName.setForeground(colors.TEXT_COLOR);
        eventName.setBackground(colors.SECONARY_COLOR);
        eventName.setFont(new Font("Dialog", Font.PLAIN, 16));
        eventName.setMargin(new Insets(5, 5, 5, 5));

        JLabel yearLabel = new JLabel("Event Year: ");
        yearLabel.setBounds(10, 50, 400, 25);
        yearLabel.setForeground(colors.TEXT_COLOR);
        yearLabel.setFont(new Font("Dialog", Font.PLAIN, 15));

        JTextField eventYear = new JTextField();
        eventYear.setBounds(100, 50, 53, 29);
        eventYear.setForeground(colors.TEXT_COLOR);
        eventYear.setBackground(colors.SECONARY_COLOR);
        eventYear.setFont(new Font("Dialog", Font.PLAIN, 16));
        eventYear.setMargin(new Insets(5, 5, 5, 5));

        JLabel monthLabel = new JLabel("Event Month: ");
        monthLabel.setBounds(170, 50, 400, 25);
        monthLabel.setForeground(colors.TEXT_COLOR);
        monthLabel.setFont(new Font("Dialog", Font.PLAIN, 15));

        JTextField eventMonth = new JTextField();
        eventMonth.setBounds(270, 50, 35, 29);
        eventMonth.setForeground(colors.TEXT_COLOR);
        eventMonth.setBackground(colors.SECONARY_COLOR);
        eventMonth.setFont(new Font("Dialog", Font.PLAIN, 16));
        eventMonth.setMargin(new Insets(5, 5, 5, 5));

        JLabel dayLabel = new JLabel("Event Day: ");
        dayLabel.setBounds(330, 50, 400, 25);
        dayLabel.setForeground(colors.TEXT_COLOR);
        dayLabel.setFont(new Font("Dialog", Font.PLAIN, 15));

        JTextField eventDay = new JTextField();
        eventDay.setBounds(415, 50, 35, 29);
        eventDay.setForeground(colors.TEXT_COLOR);
        eventDay.setBackground(colors.SECONARY_COLOR);
        eventDay.setFont(new Font("Dialog", Font.PLAIN, 16));
        eventDay.setMargin(new Insets(5, 5, 5, 5));

        JLabel venueLabel = new JLabel("Event Venue: ");
        venueLabel.setBounds(10, 90, 400, 25);
        venueLabel.setForeground(colors.TEXT_COLOR);
        venueLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JTextField eventVenue = new JTextField();
        eventVenue.setBounds(130, 90, 200, 29);
        eventVenue.setForeground(colors.TEXT_COLOR);
        eventVenue.setBackground(colors.SECONARY_COLOR);
        eventVenue.setFont(new Font("Dialog", Font.PLAIN, 16));
        eventVenue.setMargin(new Insets(5, 5, 5, 5));

        JLabel capacityLabel = new JLabel("Event Capacity: ");
        capacityLabel.setBounds(10, 130, 400, 25);
        capacityLabel.setForeground(colors.TEXT_COLOR);
        capacityLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JTextField eventCapacity = new JTextField();
        eventCapacity.setBounds(150, 130, 200, 29);
        eventCapacity.setForeground(colors.TEXT_COLOR);
        eventCapacity.setBackground(colors.SECONARY_COLOR);
        eventCapacity.setFont(new Font("Dialog", Font.PLAIN, 16));
        eventCapacity.setMargin(new Insets(5, 5, 5, 5));

        JLabel lengthLabel = new JLabel("Event Length: ");
        lengthLabel.setBounds(10, 170, 400, 25);
        lengthLabel.setForeground(colors.TEXT_COLOR);
        lengthLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JTextField eventLength = new JTextField();
        eventLength.setBounds(130, 170, 200, 29);
        eventLength.setForeground(colors.TEXT_COLOR);
        eventLength.setBackground(colors.SECONARY_COLOR);
        eventLength.setFont(new Font("Dialog", Font.PLAIN, 16));
        eventLength.setMargin(new Insets(5, 5, 5, 5));

        JLabel participantsLabel = new JLabel("Event Participants: ");
        participantsLabel.setBounds(10, 210, 400, 25);
        participantsLabel.setForeground(colors.TEXT_COLOR);
        participantsLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JTextField eventParticipants = new JTextField();
        eventParticipants.setBounds(170, 210, 200, 29);
        eventParticipants.setForeground(colors.TEXT_COLOR);
        eventParticipants.setBackground(colors.SECONARY_COLOR);
        eventParticipants.setFont(new Font("Dialog", Font.PLAIN, 16));
        eventParticipants.setMargin(new Insets(5, 5, 5, 5));


        addEventPanel.add(nameLabel);
        addEventPanel.add(eventName);

        addEventPanel.add(yearLabel);
        addEventPanel.add(eventYear);

        addEventPanel.add(monthLabel);
        addEventPanel.add(eventMonth);

        addEventPanel.add(dayLabel);
        addEventPanel.add(eventDay);

        addEventPanel.add(venueLabel);
        addEventPanel.add(eventVenue);

        addEventPanel.add(capacityLabel);
        addEventPanel.add(eventCapacity);

        addEventPanel.add(lengthLabel);
        addEventPanel.add(eventLength);

        addEventPanel.add(participantsLabel);
        addEventPanel.add(eventParticipants);

        JButton addEventButton = new JButton("Add Event");
        addEventButton.setFont(new Font("Dialog", Font.BOLD, 18));
        addEventButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addEventButton.setBackground(colors.TEXT_COLOR);
        addEventButton.setBounds(125, 245, 220, 30);
        addEventPanel.add(addEventButton);

        JLabel emptyField = new JLabel("Can't have empty fields.");
        emptyField.setForeground(Color.red);
        emptyField.setFont(new Font("Dialog", Font.PLAIN, 18));
        emptyField.setVisible(false);
        emptyField.setBounds(155, 310, 250, 50);
        add(emptyField);

        JLabel invalidNumbers = new JLabel("Please use valid input.");
        invalidNumbers.setForeground(Color.red);
        invalidNumbers.setFont(new Font("Dialog", Font.PLAIN, 18));
        invalidNumbers.setVisible(false);
        invalidNumbers.setBounds(155, 310, 250, 50);
        add(invalidNumbers);

        JLabel success = new JLabel("Event has been added.");
        success.setForeground(Color.green);
        success.setFont(new Font("Dialog", Font.PLAIN, 18));
        success.setVisible(false);
        success.setBounds(142, 310, 250, 50);
        add(success);


        addEventButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                emptyField.setVisible(false);
                success.setVisible(false);
                invalidNumbers.setVisible(false);
                if (eventName.getText().isEmpty() | eventCapacity.getText().isEmpty() | eventParticipants.getText().isEmpty() | eventDay.getText().isEmpty() | eventYear.getText().isEmpty() | eventMonth.getText().isEmpty() | eventLength.getText().isEmpty() | eventVenue.getText().isEmpty()) {
                    emptyField.setVisible(true);
                    return;
                }
                try {
                    if (!EventSystem.isInteger(eventYear.getText()) | !EventSystem.isInteger(eventMonth.getText()) | !EventSystem.isInteger(eventDay.getText()) | !EventSystem.isInteger(eventCapacity.getText()) | !EventSystem.isInteger(eventParticipants.getText()) | !EventSystem.isInteger(eventLength.getText())) {
                        invalidNumbers.setVisible(true);
                        return;
                    }
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

                Event event = new Event(eventName.getText(), Integer.parseInt(eventYear.getText()),
                        Integer.parseInt(eventMonth.getText()),
                        Integer.parseInt(eventDay.getText()), eventVenue.getText(),
                        Integer.parseInt(eventCapacity.getText()),
                        Integer.parseInt(eventLength.getText()),
                        Integer.parseInt(eventParticipants.getText()));
                EventSystem.events.add(event);
                try {
                    EventSystem.writeToFile(event);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                success.setVisible(true);
            }
        });


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
