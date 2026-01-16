package EventDriven.OOP;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EventSystem {
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Event> events = new ArrayList<>();
    public static ArrayList<UserEventRegistration> user_event = new ArrayList<>();
    public Scanner scanner = new Scanner(System.in);
    public String choice;
    public static int currentUser;


    public void menu() {
        System.out.println("==========================================");
        System.out.println("1- Register");
        System.out.println("2- Login");
        System.out.println("3- Add Event (require access code)");
        System.out.println("4- Exit");
        System.out.println("==========================================");
        System.out.print("Choose (1-4): ");
        choice = scanner.nextLine().trim();


    }

    public void runApplication() throws IOException, InterruptedException {
        loadUserFromFile();
        loadEventFromFile();
        loadUserEventFromFile();
        do {
            menu();
            switch (choice) {
                case "1": {
                    registration();
                    break;
                }
                case "2": {
                    login();
                    break;
                }
                case "3": {
                    addEvents();
                    break;
                }
                case "4": {
                    System.out.println("Goodbye!");
                    break;
                }
                default: {
                    System.err.println("Invalid choice, please try again.");
                    Thread.sleep(50);
                }
            }

        } while (!choice.equals("4"));
    }

    public static void swap(int j) {
        Event tmp;

        tmp = events.get(j);
        events.set(j, events.get(j + 1));
        events.set(j + 1, tmp);

    }

    public static void sortEvent(boolean ascending) {
        for (int i = 0; i < events.size() - 1; i++) {
            for (int j = 0; j < events.size() - 1; j++) {

                int y1 = events.get(j).getYear();
                int m1 = events.get(j).getMonth();
                int d1 = events.get(j).getDay();
                int date1 = y1 * 10000 + m1 * 100 + d1;

                int y2 = events.get(j + 1).getYear();
                int m2 = events.get(j + 1).getMonth();
                int d2 = events.get(j + 1).getDay();
                int date2 = y2 * 10000 + m2 * 100 + d2;

                if (!ascending && date1 < date2) {
                    swap(j);
                } else if (ascending && date1 > date2) {
                    swap(j);
                }
            }
        }
    }

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

    public static void writeToFile(Event event) throws IOException {
        appendToFile("src/EventDriven/database/events.txt", "Name : Year/Month/Day : Venue : Capacity : Length : " +
                "Participants", event);

    }

    public static void writeToFile(UserEventRegistration userEventRegistration) throws IOException {
        appendToFile("src/EventDriven/database/registration.txt", "userIndex,eventName", userEventRegistration);
    }

    public static void loadUserFromFile() throws FileNotFoundException {
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

    public static void loadEventFromFile() throws FileNotFoundException {
        File file2 = new File("src/EventDriven/database/events.txt");
        if (file2.exists()) {
            Scanner scanner = new Scanner(file2);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty() || line.contains("Name")) {
                    continue;
                }
                events.add(Event.readFromFile(line));
            }
        }
    }


    public void addEvents() throws IOException, InterruptedException {
        String code = "";
        while (!code.equals("tavo")) {
            System.out.print("Enter Access Code (type 'back' to leave): ");
            code = scanner.nextLine();
            code = code.toLowerCase();
            if (code.equals("back")) {
                return;
            }
        }


        String name;
        String venue;
        int year;
        int month;
        int day;
        int capacity;
        int length;

        String temp;

        System.out.print("Add Event Name: ");
        name = scanner.nextLine();

        while (true) {
            System.out.print("Add Event Date (Year): ");
            temp = scanner.nextLine();

            if (!isInteger(temp)) {
                continue;
            }

            year = Integer.parseInt(temp);

            if (year < 2026) {
                System.err.println("Invalid year! Please enter a year higher than 2025.");
                Thread.sleep(50);
            } else {
                break;
            }

        }


        while (true) {
            System.out.print("Add Event Date (Month): ");
            temp = scanner.nextLine();

            if (!isInteger(temp)) {
                continue;
            }

            month = Integer.parseInt(temp);

            if (month < 1 || month > 12) {
                System.err.println("Invalid month! Please enter a number between 1 and 12.");
                Thread.sleep(50);
            } else {
                break;
            }

        }


        while (true) {
            System.out.print("Add Event Date (Day): ");
            temp = scanner.nextLine();

            if (!isInteger(temp)) {
                continue;
            }

            day = Integer.parseInt(temp);

            if (day < 1 || day > 31) {
                System.err.println("Invalid day! Please enter a valid day in its appropriate month.");
                Thread.sleep(50);
            } else {
                break;
            }

        }

        System.out.print("Add Event Venue: ");
        venue = scanner.nextLine();

        while (true) {
            System.out.print("Add Event Capacity: ");
            temp = scanner.nextLine();

            if (!isInteger(temp)) {
                continue;
            }

            capacity = Integer.parseInt(temp);

            if (capacity < 1) {
                System.err.println("Invalid capacity! Please enter a valid capacity for an event.");
                Thread.sleep(50);
            } else {
                break;
            }

        }


        while (true) {
            System.out.print("Add Event Length (Hours): ");
            temp = scanner.nextLine();

            if (!isInteger(temp)) {
                continue;
            }

            length = Integer.parseInt(temp);

            if (length < 1) {
                System.err.println("Invalid length! Please enter a valid length for an event.");
                Thread.sleep(50);
            } else {
                break;
            }

        }
        int initial_participants = 0;

        Event newEvent = new Event(name, year, month, day, venue, capacity, length, initial_participants);
        events.add(newEvent);
        writeToFile(newEvent);


    }

    public static boolean isInteger(String input) throws InterruptedException {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void registration() throws IOException, InterruptedException {
        String username;
        String password;
        while (true) {
            System.out.print("Create username (or type 'back' to cancel): ");
            username = scanner.nextLine().toLowerCase().trim();

            if (username.equals("back")) {
                return;
            }


            boolean unique = true;
            if (username.isEmpty()) {
                System.err.println("Please enter a valid username");
                Thread.sleep(50);
                continue;
            }

            for (User user : users) {
                if (username.equals(user.getUsername())) {
                    unique = false;
                    break;
                }
            }
            if (!unique) {
                System.err.println("Username already exists, choose a new one");
            } else {
                System.out.print("Create password: ");
                password = scanner.nextLine().trim();
                while (password.isEmpty()) {
                    System.err.println("Enter a valid password");
                    Thread.sleep(50);
                    System.out.print("Create password: ");
                    password = scanner.nextLine().trim();
                }

                User newUser = new User(username, password);
                users.add(newUser);
                writeToFile(newUser);
                break;
            }
        }
    }

    public void login() throws IOException, InterruptedException {
        String username;
        String password;

        while (true) {
            System.out.print("Enter username (or type 'back' to cancel): ");
            username = scanner.nextLine().toLowerCase();

            if (username.equals("back")) {
                return;
            }
            System.out.print("Enter password: ");
            password = scanner.nextLine();

            boolean match = false;

            for (User user : users) {
                if (user.getUsername().equals((username)) && user.getPassword().equals(password)) {
                    match = true;
                    currentUser = users.indexOf(user);
                    break;
                }
            }
            if (!match) {
                System.err.println("Wrong username/password.");
                Thread.sleep(50);
            } else {
                break;
            }
        }


        int choice;
        System.out.println("\n1) Show Registered Events");
        System.out.println("2) Show Upcoming Events\n");
        System.out.print("Choose(1-2) '0' to exit: ");
        choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 0) {
            return;
        }
        if (choice == 1) {
            showRegisteredEvents();
        }
        if (choice == 2) {
            showEvents();
        }

    }

    public void showRegisteredEvents() {
        System.out.println("Username: " + users.get(currentUser).getUsername());
        for (UserEventRegistration userEventRegistration : user_event) {
            if (userEventRegistration.getUserIndex() == currentUser) {
                String registeredName = userEventRegistration.getEventName();
                for (Event event : events) {
                    if (event.getEvent_name().equals(registeredName)) {
                        System.out.println("==========================================");
                        System.out.println("Event: " + event.getEvent_name());
                        System.out.println("Date: " + event.getYear() + "/" + event.getMonth() + "/" + event.getDay());
                        System.out.println("Venue: " + event.getVenue());
                        System.out.println("Capacity: " + event.getCapacity());
                        System.out.println("Length: " + event.getLength());
                        System.out.println("==========================================");
                        break;
                    }
                }
            }
        }
    }

    public static void loadUserEventFromFile() throws FileNotFoundException {
        File file3 = new File("src/EventDriven/database/registration.txt");
        if (file3.exists()) {
            Scanner scanner = new Scanner(file3);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty() || line.contains("userIndex")) {
                    continue;
                }
                user_event.add(UserEventRegistration.readFromFile(line));
            }

        }
    }

    public static void updateEventsFile() throws IOException {
        File file = new File("src/EventDriven/database/events.txt");
        FileWriter fw = new FileWriter(file, false);

        fw.write("Name : Year/Month/Day : Venue : Capacity : Length : Participants \n");

        for (Event event : events) {
            fw.write(event.writeToFile() + "\n");
        }

        fw.close();
    }

    public void showEvents() throws IOException, InterruptedException {
        int choice;

        System.out.println("==========================================");
        System.out.println("How would you like to sort the events by date?");
        System.out.println("1) Ascending");
        System.out.println("2) Descending");
        System.out.println("0) Keep current order");
        System.out.print("Choose (0-2): ");

        int sortChoice = scanner.nextInt();
        scanner.nextLine();

        if (sortChoice == 1) {
            sortEvent(true);
        }
        if (sortChoice == 2) {
            sortEvent(false);
        }


        for (int i = 1; i < events.size() + 1; i++) {
            System.out.println(i + ") " + events.get(i - 1).getEvent_name());
        }


        System.out.println("==========================================");
        System.out.print("Choice (0-" + events.size() + ")  | pick 0 to exit |: ");
        choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 0) {
            return;
        }

        System.out.println("Upcoming Events!: ");
        System.out.println("==========================================");
        System.out.println("Name: " + events.get(choice - 1).getEvent_name());
        System.out.println("Date: " + events.get(choice - 1).getYear() + "/" + events.get(choice - 1).getMonth() +
                "/" + events.get(choice - 1).getDay());
        System.out.println("Venue: " + events.get(choice - 1).getVenue());
        System.out.println("Capacity: " + events.get(choice - 1).getCapacity());
        System.out.println("Length: " + events.get(choice - 1).getLength());
        System.out.println("Participants: " + events.get(choice - 1).getParticipants());
        System.out.println("==========================================");

        String register;
        System.out.print("Would you like to register for this event? (Y/N):  ");
        register = scanner.nextLine().trim().toLowerCase();

        if (register.equals("y")) {
            int currentEvent = choice - 1;
            String nameOfEvent = events.get(currentEvent).getEvent_name();
            for (UserEventRegistration userEventRegistration : user_event) {
                if (userEventRegistration.getUserIndex() == currentUser && userEventRegistration.getEventName().equals(nameOfEvent)) {
                    System.err.println("You are already registered to this event.");
                    Thread.sleep(50);
                    return;
                }
            }

            events.get(currentEvent).setParticipants(events.get(currentEvent).getParticipants() + 1);
            updateEventsFile();
            UserEventRegistration newUserEventRegistration = new UserEventRegistration(currentUser, nameOfEvent);
            user_event.add(newUserEventRegistration);
            writeToFile(newUserEventRegistration);

        }
    }
}
