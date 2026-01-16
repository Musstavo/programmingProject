package EventDriven.guis;

import EventDriven.OOP.Event;
import EventDriven.OOP.EventSystem;
import EventDriven.OOP.UserEventRegistration;
import EventDriven.colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisteredEventsGUI extends Form {

    private JLabel nameValueLabel, dateValueLabel, venueValueLabel, lengthValueLabel, participantValueLabel, emptyMsg;

    public RegisteredEventsGUI() {
        super("Registered Events");
        GUIitems();
    }

    private void GUIitems() {
        JLabel back = new JLabel();
        back.setForeground(colors.TEXT_COLOR);
        back.setFont(new Font("Dialog", Font.PLAIN, 12));
        back.setBounds(10, 8, 250, 20);
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.setText("<html><u>Return to Events Page</u></html>");
        add(back);

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                RegisteredEventsGUI.this.dispose();
                EventsGUI eventsGUI = new EventsGUI();
                eventsGUI.setVisible(true);
            }
        });

        JLabel title = new JLabel("Registered Events");
        title.setForeground(colors.TEXT_COLOR);
        title.setFont(new Font("Dialog", Font.BOLD, 32));
        title.setBounds(10, 35, 500, 55);
        add(title);

        DefaultListModel<Event> model = new DefaultListModel<>();

        for (UserEventRegistration eventRegistration : EventSystem.user_event) {
            if (eventRegistration.getUserIndex() == EventSystem.currentUser) {
                Event event = findEventByName(eventRegistration.getEventName());
                if (event != null) model.addElement(event);
            }
        }

        JList<Event> registeredList = new JList<>();
        registeredList.setModel(model);
        registeredList.setForeground(colors.TEXT_COLOR);
        registeredList.setBackground(colors.PRIMARY_COLOR);
        registeredList.setFont(new Font("Dialog", Font.BOLD, 20));
        registeredList.setFocusable(false);

        JScrollPane scroll = new JScrollPane(registeredList);
        scroll.setBorder(BorderFactory.createLineBorder(colors.TEXT_COLOR, 1));
        scroll.setBounds(10, 110, 485, 330);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.getVerticalScrollBar().setBackground(colors.PRIMARY_COLOR);
        add(scroll);

        emptyMsg = new JLabel("No registered events yet.");
        emptyMsg.setBounds(120, 240, 500, 50);
        emptyMsg.setForeground(colors.TEXT_COLOR);
        emptyMsg.setFont(new Font("Dialog", Font.BOLD, 20));
        emptyMsg.setVisible(model.getSize() == 0);
        add(emptyMsg);

        JPanel details = new JPanel();
        details.setBounds(10, 460, 485, 170);
        details.setBorder(BorderFactory.createLineBorder(colors.TEXT_COLOR, 1));
        details.setBackground(colors.PRIMARY_COLOR);
        details.setLayout(null);
        add(details);

        nameValueLabel = new JLabel("");
        nameValueLabel.setBounds(10, 10, 460, 30);

        dateValueLabel = new JLabel("");
        dateValueLabel.setBounds(10, 40, 460, 30);

        venueValueLabel = new JLabel("");
        venueValueLabel.setBounds(10, 70, 460, 30);

        lengthValueLabel = new JLabel("");
        lengthValueLabel.setBounds(10, 100, 460, 30);

        participantValueLabel = new JLabel("");
        participantValueLabel.setBounds(10, 130, 460, 30);

        details.add(nameValueLabel);
        details.add(dateValueLabel);
        details.add(venueValueLabel);
        details.add(lengthValueLabel);
        details.add(participantValueLabel);

        registeredList.addListSelectionListener(e -> {
            Event selected = registeredList.getSelectedValue();
            if (selected == null) return;
            updateDetails(selected);
        });

    }

    private Event findEventByName(String eventName) {
        for (Event event : EventSystem.events) {
            if (event.getEvent_name().equals(eventName)) return event;
        }
        return null;
    }

    private void updateDetails(Event selected) {
        nameValueLabel.setText("Name: " + selected.getEvent_name());
        nameValueLabel.setForeground(colors.TEXT_COLOR);
        nameValueLabel.setFont(new Font("Dialog", Font.BOLD, 20));

        venueValueLabel.setText("Venue: " + selected.getVenue());
        venueValueLabel.setForeground(colors.TEXT_COLOR);
        venueValueLabel.setFont(new Font("Dialog", Font.BOLD, 20));

        dateValueLabel.setText("Date: " + selected.getYear() + "-" + selected.getMonth() + "-" + selected.getDay());
        dateValueLabel.setForeground(colors.TEXT_COLOR);
        dateValueLabel.setFont(new Font("Dialog", Font.BOLD, 20));

        lengthValueLabel.setText("Length: " + selected.getLength());
        lengthValueLabel.setForeground(colors.TEXT_COLOR);
        lengthValueLabel.setFont(new Font("Dialog", Font.BOLD, 20));

        participantValueLabel.setText("Participants: " + selected.getParticipants() + "/" + selected.getCapacity());
        participantValueLabel.setForeground(colors.TEXT_COLOR);
        participantValueLabel.setFont(new Font("Dialog", Font.BOLD, 20));
    }
}
