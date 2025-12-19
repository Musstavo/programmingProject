package EventDriven.guis;

import EventDriven.OOP.Event;
import EventDriven.constants.CommonConstants;
import EventDriven.OOP.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class EventsGUI extends Form {

    public EventsGUI() {
        super("Events");
        GUIitems();
    }

    public void GUIitems() {
        JLabel back = new JLabel();
        back.setForeground(CommonConstants.textColor);
        back.setFont(new Font("Dialog", Font.PLAIN, 12));
        back.setBounds(10, 8, 200, 20);
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.setText("<html><u>Return to Login Page</u></html>"); // to get underline effect
        add(back);

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                EventsGUI.this.dispose();
                LoginGUI loginGUI = new LoginGUI();
                loginGUI.setVisible(true);
            }
        });

        JLabel eventLabel = new JLabel("Events");
        eventLabel.setForeground(CommonConstants.textColor);
        eventLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        eventLabel.setBounds(10, 35, 240, 55);
        add(eventLabel);

        String[] choices = new String[]{"Date ↑", "Date ↓"};
        JComboBox<String> combo = new JComboBox<>(choices);
        combo.setBounds(340, 48, 165, 30);
        combo.setSelectedIndex(0);
        combo.setFocusable(false);
        combo.setFont(new Font("Dialog", Font.BOLD, 14));
        combo.setBorder(BorderFactory.createLineBorder(CommonConstants.secondryColor, 1));
        combo.setBackground(CommonConstants.secondryColor);
        combo.setForeground(CommonConstants.textColor);
        add(combo);

        DefaultListModel<Event> model = new DefaultListModel<>();
        model.addElement(new Event("Test", 2026, 1, 10, "HEHE", 120, 3, 78));
        model.addElement(new Event("Solar Workshop", 2026, 1, 10, "HTU", 120, 3, 120));
        model.addElement(new Event("Smth else", 2026, 1, 10, "HTU", 120, 3, 78));
        JList<Event> eventList = new JList<>();
        eventList.setModel(model);
        JScrollPane scroll = new JScrollPane(eventList);
        scroll.setBounds(10, 110, 500, 330);

        eventList.setForeground(CommonConstants.textColor);
        eventList.setBackground(CommonConstants.primaryColor);
        eventList.setFont(new Font("Dialog", Font.BOLD, 20));
        eventList.setFocusable(false);

        scroll.setBorder(BorderFactory.createLineBorder(CommonConstants.textColor, 1));
        scroll.setBounds(10, 110, 485, 330);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.getVerticalScrollBar().setBackground(CommonConstants.primaryColor);

        add(scroll);

        JPanel details = new JPanel();
        details.setBounds(10, 460, 485, 170);
        details.setBorder(BorderFactory.createLineBorder(CommonConstants.textColor, 1));
        details.setBackground(CommonConstants.primaryColor);
        details.setLayout(null);
        add(details);

        JLabel nameValueLabel = new JLabel("");
        nameValueLabel.setBounds(10, 10, 400, 30);

        JLabel dateValueLabel = new JLabel("");
        dateValueLabel.setBounds(10, 40, 400, 30);

        JLabel venueValueLabel = new JLabel("");
        venueValueLabel.setBounds(10, 70, 400, 30);

        JLabel lengthValueLabel = new JLabel("");
        lengthValueLabel.setBounds(10, 100, 400, 30);

        JLabel participantValueLabel = new JLabel("");
        participantValueLabel.setBounds(10, 130, 400, 30);

        JLabel FULL = new JLabel("");
        FULL.setBounds(90, 20, 480, 120);

        details.add(nameValueLabel);
        details.add(dateValueLabel);
        details.add(venueValueLabel);
        details.add(lengthValueLabel);
        details.add(participantValueLabel);
        details.add(FULL);

        JButton registerEvent = new JButton("Register");
        registerEvent.setFont(new Font("Dialog", Font.BOLD, 18));
        registerEvent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerEvent.setBackground(CommonConstants.textColor);
        registerEvent.setBounds(304, 10, 170, 40);
        registerEvent.setVisible(false);
        details.add(registerEvent);

        JButton addEvent = new JButton("Add Event");
        addEvent.setFont(new Font("Dialog", Font.BOLD, 18));
        addEvent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addEvent.setBackground(CommonConstants.textColor);
        addEvent.setBounds(304, 50, 170, 40);
        addEvent.setVisible(false);
        details.add(addEvent);

        JLabel successfulRegister = new JLabel("You have successfully registered for the event.");
        successfulRegister.setBounds(180, 420, 500, 50);
        successfulRegister.setForeground(Color.green);
        successfulRegister.setFont(new Font("Dialog", Font.PLAIN, 18));
        successfulRegister.setVisible(false);
        add(successfulRegister);

        JLabel alreadyRegistered = new JLabel("You are already regisitered to this event.");
        alreadyRegistered.setBounds(180, 420, 500, 50);
        alreadyRegistered.setForeground(Color.red);
        alreadyRegistered.setFont(new Font("Dialog", Font.PLAIN, 18));
        alreadyRegistered.setVisible(false);
        add(alreadyRegistered);

        registerEvent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                alreadyRegistered.setVisible(false);
                successfulRegister.setVisible(false);

                Event selected = eventList.getSelectedValue();
                String nameOfEvent = selected.getEvent_name();
                for (UserEventRegistration userEventRegistration : EventSystem.user_event) {
                    if (userEventRegistration.getUserIndex() == EventSystem.currentUser && userEventRegistration.getEventName().equals(nameOfEvent)) {
                        alreadyRegistered.setVisible(true);
                        return;
                    }
                }
                alreadyRegistered.setVisible(false);
                successfulRegister.setVisible(true);
                selected.setParticipants(selected.getParticipants() + 1);
                model.setElementAt(selected, eventList.getSelectedIndex());

                UserEventRegistration newUserEventRegistration = new UserEventRegistration(EventSystem.currentUser, selected.getEvent_name());

                EventSystem.user_event.add(newUserEventRegistration);
                try {
                    EventSystem.writeToFile(newUserEventRegistration);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });

        eventList.addListSelectionListener(e -> {

            Event selected = eventList.getSelectedValue();
            if (selected.getCapacity() == selected.getParticipants()) {
                registerEvent.setVisible(false);
                nameValueLabel.setVisible(false);
                dateValueLabel.setVisible(false);
                venueValueLabel.setVisible(false);
                lengthValueLabel.setVisible(false);
                participantValueLabel.setVisible(false);
                addEvent.setVisible(false);

                FULL.setText("Event is full.");
                FULL.setForeground(Color.RED);
                FULL.setFont(new Font("Dialog", Font.BOLD, 50));
                FULL.setVisible(true);
                return;
            }
            registerEvent.setVisible(true);
            addEvent.setVisible(true);

            nameValueLabel.setText("Name: " + selected.getEvent_name());
            nameValueLabel.setForeground(CommonConstants.textColor);
            nameValueLabel.setFont(new Font("Dialog", Font.BOLD, 20));

            venueValueLabel.setText("Venue: " + selected.getVenue());
            venueValueLabel.setForeground(CommonConstants.textColor);
            venueValueLabel.setFont(new Font("Dialog", Font.BOLD, 20));

            dateValueLabel.setText("Date: " + selected.getYear() + "-" + selected.getMonth() + "-" + selected.getDay());
            dateValueLabel.setForeground(CommonConstants.textColor);
            dateValueLabel.setFont(new Font("Dialog", Font.BOLD, 20));

            lengthValueLabel.setText("Length: " + selected.getLength());
            lengthValueLabel.setForeground(CommonConstants.textColor);
            lengthValueLabel.setFont(new Font("Dialog", Font.BOLD, 20));

            participantValueLabel.setText("Participants: " + selected.getParticipants() + "/" + selected.getCapacity());
            participantValueLabel.setForeground(CommonConstants.textColor);
            participantValueLabel.setFont(new Font("Dialog", Font.BOLD, 20));

            FULL.setVisible(false);
            nameValueLabel.setVisible(true);
            dateValueLabel.setVisible(true);
            venueValueLabel.setVisible(true);
            lengthValueLabel.setVisible(true);
            participantValueLabel.setVisible(true);


        });

    }
}
