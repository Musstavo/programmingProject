package EventDriven.database;


import EventDriven.OOP.Event;
import EventDriven.OOP.FileRecord;
import EventDriven.OOP.User;
import EventDriven.OOP.UserEventRegistration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class JavaDatabase extends FileRecord {
    public static ArrayList<User> users = new ArrayList<>();

    public static void appendToFile(String path, String header, FileRecord record) throws IOException {
        File file = new File(path);
        FileWriter fw = new FileWriter(file, true);

        if (file.length() == 0) {
            fw.write(header + "\n");
        }
        fw.write(record.writeToFile() + "\n");
        fw.close();
    }

    public static void writeToFile(User user) throws IOException {
        appendToFile("src/EventDriven/database/users.txt", "username:password", user);
    }

    public void writeToFile(Event event) throws IOException {
        appendToFile("src/EventDriven/database/events.txt", "Name : Year/Month/Day : Venue : Capacity : Length : " +
                        "Participants",
                event);

    }

    public void writeToFile(UserEventRegistration userEventRegistration) throws IOException {
        appendToFile("src/EventDriven/database/registration.txt", "userIndex,eventIndex", userEventRegistration);
    }

    public void loadUserFromFile() throws FileNotFoundException {
        File file = new File("src/EventDriven/database/users.txt");
        if (file.exists()) {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty() || line.contains("username")) {
                    continue;
                }
                users.add(User.readFromFile(line));
            }
        }
    }
}
