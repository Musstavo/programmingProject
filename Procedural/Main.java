package Procedural;

import OOP.User;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class Main {
    static String choice;
    static int currentUser;
    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Integer> registeredUsers = new ArrayList<Integer>();
    static ArrayList<String> registeredEvents = new ArrayList<String>();

    static ArrayList<String> eventUsernames = new ArrayList<String>();
    static ArrayList<String> eventPasswords = new ArrayList<String>();
    static ArrayList<String> eventsName = new ArrayList<String>();
    static ArrayList<Integer> eventsYear = new ArrayList<Integer>();
    static ArrayList<Integer> eventsMonth = new ArrayList<Integer>();
    static ArrayList<Integer> eventsDay = new ArrayList<Integer>();
    static ArrayList<String> eventsVenue = new ArrayList<String>();
    static ArrayList<Integer> eventsCapacity = new ArrayList<Integer>();
    static ArrayList<Integer> eventsLength = new ArrayList<Integer>();
    static ArrayList<Integer> eventsParticipants = new ArrayList<Integer>();

    public static void main(String[] args) throws IOException, InterruptedException { // IDE added the exception
        runApplication();
        scanner.close();
    }

    public static void menu() {

        System.out.println("==========================================");
        System.out.println("1- Register");
        System.out.println("2- Login");
        System.out.println("3- Add Event (require access code)");
        System.out.println("4- Exit");
        System.out.println("==========================================");
        System.out.print("Choose (1-4): ");
        choice = scanner.nextLine().trim();
    }

    public static void runApplication() throws IOException, InterruptedException {
        loadFromFile();
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

    public static void addEvents() throws IOException, InterruptedException {
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
        eventsName.add(name);

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
                eventsYear.add(year);
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
                eventsMonth.add(month);
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
                eventsDay.add(day);
                break;
            }

        }

        System.out.print("Add Event Venue: ");
        venue = scanner.nextLine();
        eventsVenue.add(venue);

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
                eventsCapacity.add(capacity);
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
                eventsLength.add(length);
                break;
            }

        }

        int initial_participants = 0;
        eventsParticipants.add(initial_participants);

        saveEventToFile(name, year, month, day, venue, capacity, length, initial_participants);

    }

    public static boolean isInteger(String input) throws InterruptedException {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            System.err.println("Enter a number");
            Thread.sleep(50);
            return false;
        }

    }

    public static void swap(int j) {
        String tmp;
        int tmp1;

        tmp = eventsName.get(j);
        eventsName.set(j, eventsName.get(j + 1));
        eventsName.set(j + 1, tmp);

        tmp1 = eventsYear.get(j);
        eventsYear.set(j, eventsYear.get(j + 1));
        eventsYear.set(j + 1, tmp1);

        tmp1 = eventsMonth.get(j);
        eventsMonth.set(j, eventsMonth.get(j + 1));
        eventsMonth.set(j + 1, tmp1);

        tmp1 = eventsDay.get(j);
        eventsDay.set(j, eventsDay.get(j + 1));
        eventsDay.set(j + 1, tmp1);

        tmp = eventsVenue.get(j);
        eventsVenue.set(j, eventsVenue.get(j + 1));
        eventsVenue.set(j + 1, tmp);

        tmp1 = eventsCapacity.get(j);
        eventsCapacity.set(j, eventsCapacity.get(j + 1));
        eventsCapacity.set(j + 1, tmp1);

        tmp1 = eventsLength.get(j);
        eventsLength.set(j, eventsLength.get(j + 1));
        eventsLength.set(j + 1, tmp1);

        tmp1 = eventsParticipants.get(j);
        eventsParticipants.set(j, eventsParticipants.get(j + 1));
        eventsParticipants.set(j + 1, tmp1);
    }

    public static void sortEvent(boolean ascending) {
        for (int i = 0; i < eventsName.size() - 1; i++) {
            for (int j = 0; j < eventsName.size() - 1; j++) {

                int y1 = eventsYear.get(j);
                int m1 = eventsMonth.get(j);
                int d1 = eventsDay.get(j);
                int date1 = y1 * 10000 + m1 * 100 + d1;

                int y2 = eventsYear.get(j + 1);
                int m2 = eventsMonth.get(j + 1);
                int d2 = eventsDay.get(j + 1);
                int date2 = y2 * 10000 + m2 * 100 + d2;

                if (!ascending && date1 < date2) {
                    swap(j);
                } else if (ascending && date1 > date2) {
                    swap(j);
                }
            }
        }
    }

    public static void updateEventsFile() throws IOException {
        File file = new File("src/Procedural/events.txt");
        FileWriter fw = new FileWriter(file, false);

        fw.write("Name : Year/Month/Day : Venue : Capacity : Length : Participants \n");

        for (int i = 0; i < eventsName.size(); i++) {
            fw.write(eventsName.get(i) + " : " +
                    eventsYear.get(i) + "/" + eventsMonth.get(i) + "/" + eventsDay.get(i) + " : " +
                    eventsVenue.get(i) + " : " +
                    eventsCapacity.get(i) + " : " +
                    eventsLength.get(i) + " : " +
                    eventsParticipants.get(i) + "\n");
        }
        fw.close();
    }

    public static void showEvents() throws IOException, InterruptedException {
        if (eventsName.isEmpty()) {
            System.out.println("No events available.");
            return;
        }

        System.out.println("==========================================");
        System.out.println("How would you like to sort the events by date?");
        System.out.println("1) Ascending  (oldest first)");
        System.out.println("2) Descending (newest first)");
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

        for (int i = 1; i < eventsName.size() + 1; i++) {
            System.out.println(i + ") " + eventsName.get(i - 1));
        }


        System.out.println("==========================================");
        System.out.print("Choice (0-" + eventsName.size() + ") | '0' to exit |: ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 0) {
            return;
        }

        System.out.println("Upcoming Events!: ");
        System.out.println("==========================================");
        System.out.println("Name: " + eventsName.get(choice - 1));
        System.out.println("Date: " + eventsYear.get(choice - 1) + "/" + eventsMonth.get(choice - 1) + "/" + eventsDay.get(choice - 1));
        System.out.println("Venue: " + eventsVenue.get(choice - 1));
        System.out.println("Capacity: " + eventsCapacity.get(choice - 1));
        System.out.println("Length: " + eventsLength.get(choice - 1));
        System.out.println("Participants: " + eventsParticipants.get(choice - 1));
        System.out.println("==========================================");

        String register;
        System.out.print("Would you like to register for this event? (Y/N):  ");
        register = scanner.nextLine().trim().toLowerCase();

        if (register.equals("y")) {
            int currentEvent = choice - 1;
            String nameOfEvent = eventsName.get(currentEvent);
            for (int i = 0; i < registeredUsers.size(); i++) {
                if (registeredUsers.get(i) == currentUser && registeredEvents.get(i).equals(nameOfEvent)) {
                    System.err.println("You are already registered to this event.");
                    Thread.sleep(50);
                    return;
                }
            }
            eventsParticipants.set(currentEvent, eventsParticipants.get(currentEvent) + 1);
            updateEventsFile();
            registeredEvents.add(nameOfEvent);
            registeredUsers.add(currentUser);
            saveRegistrationToFile(currentUser, nameOfEvent);
        }

    }

    public static void showRegisteredEvents() {
        System.out.println("Username: " + eventUsernames.get(currentUser));
        for (int i = 0; i < registeredUsers.size(); i++) {
            if (registeredUsers.get(i) == currentUser) {
                String registered = registeredEvents.get(i);
                int currentEvent = eventsName.indexOf(registered);
                System.out.println("==========================================");
                System.out.println("Event: " + eventsName.get(currentEvent));
                System.out.println("Date: " + eventsYear.get(currentEvent) + "/" + eventsMonth.get(currentEvent) + "/" + eventsDay.get(currentEvent));
                System.out.println("Venue: " + eventsVenue.get(currentEvent));
                System.out.println("Capacity: " + eventsCapacity.get(currentEvent));
                System.out.println("Length: " + eventsLength.get(currentEvent));
                System.out.println("==========================================");
            }
        }

    }

    public static void loadFromFile() throws FileNotFoundException { // The IDE added the exception
        File file = new File("src/Procedural/users.txt");
        File file2 = new File("src/Procedural/events.txt");
        File file3 = new File("src/Procedural/registration.txt");

        if (file3.exists()) {
            Scanner file3Scanner = new Scanner(file3);
            while (file3Scanner.hasNextLine()) {
                String line = file3Scanner.nextLine().trim();
                if (line.isEmpty() || line.contains("username")) {
                    continue;
                }

                String[] parts = line.split(",");
                int userIndex = Integer.parseInt(parts[0]);
                String eventName = parts[1];

                registeredUsers.add(userIndex);
                registeredEvents.add(eventName);
            }
        }
        if (file2.exists()) {
            Scanner file2Scanner = new Scanner(file2);
            while (file2Scanner.hasNextLine()) { // Name : Year/Month/Day : Venue : Capacity : Length
                String line = file2Scanner.nextLine().trim();
                if (line.isEmpty() || line.contains("Name")) {
                    continue;
                }

                String[] parts = line.split(":");

                String name = parts[0].trim();
                String date = parts[1].trim();
                String venue = parts[2].trim();

                String capacity = parts[3].trim();

                String length = parts[4].trim();

                String participants = parts[5].trim();

                String[] date_parts = date.split("/");
                String year = date_parts[0].trim();

                String month = date_parts[1].trim();

                String day = date_parts[2].trim();


                eventsLength.add(Integer.valueOf(length));
                eventsCapacity.add(Integer.valueOf(capacity));
                eventsDay.add(Integer.valueOf(day));
                eventsMonth.add(Integer.valueOf(month));
                eventsYear.add(Integer.valueOf(year));
                eventsVenue.add(venue);
                eventsName.add(name);
                eventsParticipants.add(Integer.valueOf(participants));
            }
        }
        if (file.exists()) {
            Scanner fileScanner = new Scanner(file); // reads the file rather than the keyboard... noice
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty() || line.contains("username")) {
                    continue;
                }

                String[] parts = line.split(":"); // test:1234 -> ["test","1234"]
                String username = parts[0];
                String password = parts[1];

                eventUsernames.add(username);
                eventPasswords.add(password);

            }
        }
    }

    public static void saveUserToFile(String username, String password) throws IOException {
        File file = new File("src/Procedural/users.txt");

        FileWriter fw = new FileWriter(file, true);

        if (file.length() == 0) {
            fw.write("username:password\n");
        }

        fw.write(username + ":" + password + "\n");
        fw.close();
    }

    public static void saveRegistrationToFile(int currentUser, String currentEvent) throws IOException {
        File file3 = new File("src/Procedural/registration.txt");
        FileWriter fw3 = new FileWriter(file3, true);
        if (file3.length() == 0) {
            fw3.write("username,event\n");
        }
        fw3.write(currentUser + "," + currentEvent + "\n");
        fw3.close();
    }

    public static void saveEventToFile(String name, int year, int month, int day, String venue, int capacity,
                                       int length, int participants) throws IOException {
        File file2 = new File("src/Procedural/events.txt");
        FileWriter fw2 = new FileWriter(file2, true);


        if (file2.length() == 0) {
            fw2.write("Name : Year/Month/Day : Venue : Capacity : Length : Participants \n");
        }
        fw2.write(name + " : " + year + "/" + month + "/" + day + " : " + venue + " : " + capacity + " : " + length + " : " + participants + "\n");
        fw2.close();

    }

    public static void login() throws IOException, InterruptedException {
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

            for (int i = 0; i < eventUsernames.size(); i++) {
                if (eventUsernames.get(i).equals(username) && eventPasswords.get(i).equals(password)) {
                    match = true;
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

        currentUser = eventUsernames.indexOf(username);

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

    public static void registration() throws IOException, InterruptedException {
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
            for (String eventUsername : eventUsernames) {
                if (username.equals(eventUsername)) {
                    unique = false;
                    break;
                }
            }
            if (!unique) {
                System.err.println("Username already exists, choose a new one");
                Thread.sleep(50);
            } else {
                eventUsernames.add(username);
                System.out.print("Create password: ");
                password = scanner.nextLine().trim();
                while (password.isEmpty()) {
                    System.err.println("Enter a valid password");
                    Thread.sleep(50);
                    System.out.print("Create password: ");
                    password = scanner.nextLine().trim();
                }
                eventPasswords.add(password);

                saveUserToFile(username, password);
                break;
            }
        }
    }
}




