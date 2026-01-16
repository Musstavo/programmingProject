package EventDriven;

import EventDriven.OOP.EventSystem;

import EventDriven.guis.LoginGUI;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        EventSystem.loadEventFromFile();
        EventSystem.loadUserFromFile();
        EventSystem.loadUserEventFromFile();

        LoginGUI loginGUI = new LoginGUI();
        loginGUI.setVisible(true);

    }
}
