package Procedural;

import java.util.Scanner;
import java.io.*;

public class Main {

    static final int MAX = 100;

    static Scanner scanner = new Scanner(System.in);
    static String choice;
    static int currentUser;

    static int[] registeredUsers = new int[MAX];
    static String[] registeredEvents = new String[MAX];
    static int regCount = 0;

    static String[] eventUsernames = new String[MAX];
    static String[] eventPasswords = new String[MAX];
    static int userCount = 0;

    static String[] eventsName = new String[MAX];
    static int[] eventsYear = new int[MAX];
    static int[] eventsMonth = new int[MAX];
    static int[] eventsDay = new int[MAX];
    static String[] eventsVenue = new String[MAX];
    static int[] eventsCapacity = new int[MAX];
    static int[] eventsLength = new int[MAX];
    static int[] eventsParticipants = new int[MAX];
    static int eventCount = 0;

    public static void main(String[] args) throws IOException, InterruptedException {
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
                case "1":
                    registration();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    addEvents();
                    break;
                case "4":
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.err.println("Invalid choice, please try again.");
                    Thread.sleep(50);
            }
        } while (!choice.equals("4"));
    }

    public static void addEvents() throws IOException, InterruptedException {
        String code = "";
        while (!code.equals("tavo")) {
            System.out.print("Enter Access Code (type 'back' to leave): ");
            code = scanner.nextLine().toLowerCase();
            if (code.equals("back")) return;
        }

        String name, venue;
        int year, month, day, capacity, length;
        String temp;

        System.out.print("Add Event Name: ");
        name = scanner.nextLine().trim();

        while (true) {
            System.out.print("Add Event Date (Year): ");
            temp = scanner.nextLine();
            if (!isInteger(temp)) continue;
            year = Integer.parseInt(temp);
            if (year < 2026) {
                System.err.println("Invalid year! Please enter a year higher than 2025.");
                Thread.sleep(50);
            } else break;
        }

        while (true) {
            System.out.print("Add Event Date (Month): ");
            temp = scanner.nextLine();
            if (!isInteger(temp)) continue;
            month = Integer.parseInt(temp);
            if (month < 1 || month > 12) {
                System.err.println("Invalid month! Please enter a number between 1 and 12.");
                Thread.sleep(50);
            } else break;
        }

        while (true) {
            System.out.print("Add Event Date (Day): ");
            temp = scanner.nextLine();
            if (!isInteger(temp)) continue;
            day = Integer.parseInt(temp);
            if (day < 1 || day > 31) {
                System.err.println("Invalid day! Please enter a valid day in its appropriate month.");
                Thread.sleep(50);
            } else break;
        }

        System.out.print("Add Event Venue: ");
        venue = scanner.nextLine().trim();

        while (true) {
            System.out.print("Add Event Capacity: ");
            temp = scanner.nextLine();
            if (!isInteger(temp)) continue;
            capacity = Integer.parseInt(temp);
            if (capacity < 1) {
                System.err.println("Invalid capacity! Please enter a valid capacity for an event.");
                Thread.sleep(50);
            } else break;
        }

        while (true) {
            System.out.print("Add Event Length (Hours): ");
            temp = scanner.nextLine();
            if (!isInteger(temp)) continue;
            length = Integer.parseInt(temp);
            if (length < 1) {
                System.err.println("Invalid length! Please enter a valid length for an event.");
                Thread.sleep(50);
            } else break;
        }


        eventsName[eventCount] = name;
        eventsYear[eventCount] = year;
        eventsMonth[eventCount] = month;
        eventsDay[eventCount] = day;
        eventsVenue[eventCount] = venue;
        eventsCapacity[eventCount] = capacity;
        eventsLength[eventCount] = length;

        eventCount++;

        int initialParticipants = 0;
        eventsParticipants[eventCount] = initialParticipants;

        saveEventToFile(name, year, month, day, venue, capacity, length, initialParticipants);
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
        String tmpS;
        int tmpI;

        tmpS = eventsName[j];
        eventsName[j] = eventsName[j + 1];
        eventsName[j + 1] = tmpS;

        tmpI = eventsYear[j];
        eventsYear[j] = eventsYear[j + 1];
        eventsYear[j + 1] = tmpI;

        tmpI = eventsMonth[j];
        eventsMonth[j] = eventsMonth[j + 1];
        eventsMonth[j + 1] = tmpI;

        tmpI = eventsDay[j];
        eventsDay[j] = eventsDay[j + 1];
        eventsDay[j + 1] = tmpI;

        tmpS = eventsVenue[j];
        eventsVenue[j] = eventsVenue[j + 1];
        eventsVenue[j + 1] = tmpS;

        tmpI = eventsCapacity[j];
        eventsCapacity[j] = eventsCapacity[j + 1];
        eventsCapacity[j + 1] = tmpI;

        tmpI = eventsLength[j];
        eventsLength[j] = eventsLength[j + 1];
        eventsLength[j + 1] = tmpI;

        tmpI = eventsParticipants[j];
        eventsParticipants[j] = eventsParticipants[j + 1];
        eventsParticipants[j + 1] = tmpI;
    }

    public static void sortEvent(boolean ascending) {
        for (int i = 0; i < eventCount - 1; i++) {
            for (int j = 0; j < eventCount - 1; j++) {

                int date1 = eventsYear[j] * 10000 + eventsMonth[j] * 100 + eventsDay[j];
                int date2 = eventsYear[j + 1] * 10000 + eventsMonth[j + 1] * 100 + eventsDay[j + 1];

                if (!ascending && date1 < date2) swap(j);
                else if (ascending && date1 > date2) swap(j);
            }
        }
    }

    public static void updateEventsFile() throws IOException {
        File file = new File("src/Procedural/events.txt");
        FileWriter fw = new FileWriter(file, false);

        fw.write("Name : Year/Month/Day : Venue : Capacity : Length : Participants \n");

        for (int i = 0; i < eventCount; i++) {
            fw.write(eventsName[i] + " : " +
                    eventsYear[i] + "/" + eventsMonth[i] + "/" + eventsDay[i] + " : " +
                    eventsVenue[i] + " : " +
                    eventsCapacity[i] + " : " +
                    eventsLength[i] + " : " +
                    eventsParticipants[i] + "\n");
        }
        fw.close();
    }

    public static void showEvents() throws IOException, InterruptedException {
        if (eventCount == 0) {
            System.out.println("No events available.");
            return;
        }

        System.out.println("==========================================");
        System.out.println("How would you like to sort the events by date?");
        System.out.println("1) Ascending");
        System.out.println("2) Descending");
        System.out.println("0) Keep current order");
        System.out.print("Choose (0-2): ");

        int sortChoice = scanner.nextInt();
        scanner.nextLine();

        if (sortChoice == 1) sortEvent(true);
        if (sortChoice == 2) sortEvent(false);

        for (int i = 1; i <= eventCount; i++) {
            System.out.println(i + ") " + eventsName[i - 1]);
        }

        System.out.println("==========================================");
        System.out.print("Choice (0-" + eventCount + ") | '0' to exit |: ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 0) return;

        if (choice < 1 || choice > eventCount) {
            System.err.println("Invalid event choice.");
            Thread.sleep(50);
            return;
        }

        int index = choice - 1;

        System.out.println("Upcoming Events!: ");
        System.out.println("==========================================");
        System.out.println("Name: " + eventsName[index]);
        System.out.println("Date: " + eventsYear[index] + "/" + eventsMonth[index] + "/" + eventsDay[index]);
        System.out.println("Venue: " + eventsVenue[index]);
        System.out.println("Capacity: " + eventsCapacity[index]);
        System.out.println("Length: " + eventsLength[index]);
        System.out.println("Participants: " + eventsParticipants[index]);
        System.out.println("==========================================");

        System.out.print("Would you like to register for this event? (Y/N):  ");
        String register = scanner.nextLine().trim().toLowerCase();
    
        if (register.equals("y")) {
            if (eventsParticipants[index] >= eventsCapacity[index]) {
                System.err.println("This Event is Full.");
                Thread.sleep(50);
                return;
            }

            String nameOfEvent = eventsName[index];
            for (int i = 0; i < regCount; i++) {
                if (registeredUsers[i] == currentUser && registeredEvents[i].equals(nameOfEvent)) {
                    System.err.println("You are already registered to this event.");
                    Thread.sleep(50);
                    return;
                }
            }

            eventsParticipants[index] = eventsParticipants[index] + 1;
            updateEventsFile();

            registeredUsers[regCount] = currentUser;
            registeredEvents[regCount] = nameOfEvent;
            regCount++;

            saveRegistrationToFile(currentUser, nameOfEvent);
        }
    }

    public static void showRegisteredEvents() {
        System.out.println("Username: " + eventUsernames[currentUser]);
        for (int i = 0; i < regCount; i++) {
            if (registeredUsers[i] == currentUser) {
                String registered = registeredEvents[i];
                int eventIndex = findEventIndexByName(registered);

                if (eventIndex == -1) continue;

                System.out.println("==========================================");
                System.out.println("Event: " + eventsName[eventIndex]);
                System.out.println("Date: " + eventsYear[eventIndex] + "/" + eventsMonth[eventIndex] + "/" + eventsDay[eventIndex]);
                System.out.println("Venue: " + eventsVenue[eventIndex]);
                System.out.println("Capacity: " + eventsCapacity[eventIndex]);
                System.out.println("Length: " + eventsLength[eventIndex]);
                System.out.println("Participants: " + eventsParticipants[eventIndex]);
                System.out.println("==========================================");
            }
        }
    }

    public static int findEventIndexByName(String name) {
        for (int i = 0; i < eventCount; i++) {
            if (eventsName[i].equals(name)) return i;
        }
        return -1;
    }

    public static void loadFromFile() throws FileNotFoundException {
        File file = new File("src/Procedural/users.txt");
        File file2 = new File("src/Procedural/events.txt");
        File file3 = new File("src/Procedural/registration.txt");

        if (file3.exists()) {
            Scanner file3Scanner = new Scanner(file3);
            while (file3Scanner.hasNextLine()) {
                String line = file3Scanner.nextLine().trim();
                if (line.isEmpty() || line.contains("username")) continue;

                String[] parts = line.split(",");
                int userIndex = Integer.parseInt(parts[0].trim());
                String eventName = parts[1].trim();

                registeredUsers[regCount] = userIndex;
                registeredEvents[regCount] = eventName;
                regCount++;
            }
            file3Scanner.close();
        }

        if (file2.exists()) {
            Scanner file2Scanner = new Scanner(file2);
            while (file2Scanner.hasNextLine()) {
                String line = file2Scanner.nextLine().trim();
                if (line.isEmpty() || line.contains("Name")) continue;

                String[] parts = line.split(":");
                String name = parts[0].trim();
                String date = parts[1].trim();
                String venue = parts[2].trim();
                String capacity = parts[3].trim();
                String length = parts[4].trim();
                String participants = parts[5].trim();

                String[] dateParts = date.split("/");
                int year = Integer.parseInt(dateParts[0].trim());
                int month = Integer.parseInt(dateParts[1].trim());
                int day = Integer.parseInt(dateParts[2].trim());

                eventsName[eventCount] = name;
                eventsYear[eventCount] = year;
                eventsMonth[eventCount] = month;
                eventsDay[eventCount] = day;
                eventsVenue[eventCount] = venue;
                eventsCapacity[eventCount] = Integer.parseInt(capacity);
                eventsLength[eventCount] = Integer.parseInt(length);
                eventsParticipants[eventCount] = Integer.parseInt(participants);
                eventCount++;
            }
            file2Scanner.close();
        }

        if (file.exists()) {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty() || line.contains("username")) continue;

                String[] parts = line.split(":");
                String username = parts[0].trim();
                String password = parts[1].trim();

                eventUsernames[userCount] = username;
                eventPasswords[userCount] = password;
                userCount++;
            }
            fileScanner.close();
        }
    }

    public static void saveUserToFile(String username, String password) throws IOException {
        File file = new File("src/Procedural/users.txt");
        FileWriter fw = new FileWriter(file, true);

        if (file.length() == 0) fw.write("username:password\n");
        fw.write(username + ":" + password + "\n");
        fw.close();
    }

    public static void saveRegistrationToFile(int currentUser, String currentEvent) throws IOException {
        File file3 = new File("src/Procedural/registration.txt");
        FileWriter fw3 = new FileWriter(file3, true);

        if (file3.length() == 0) fw3.write("username,event\n");
        fw3.write(currentUser + "," + currentEvent + "\n");
        fw3.close();
    }

    public static void saveEventToFile(String name, int year, int month, int day, String venue,
                                       int capacity, int length, int participants) throws IOException {
        File file2 = new File("src/Procedural/events.txt");
        FileWriter fw2 = new FileWriter(file2, true);

        if (file2.length() == 0) {
            fw2.write("Name : Year/Month/Day : Venue : Capacity : Length : Participants \n");
        }
        fw2.write(name + " : " + year + "/" + month + "/" + day + " : " + venue + " : " +
                capacity + " : " + length + " : " + participants + "\n");
        fw2.close();
    }

    public static void login() throws IOException, InterruptedException {
        String username;
        String password;

        while (true) {
            System.out.print("Enter username (or type 'back' to cancel): ");
            username = scanner.nextLine().toLowerCase();

            if (username.equals("back")) return;

            System.out.print("Enter password: ");
            password = scanner.nextLine();

            boolean match = false;
            for (int i = 0; i < userCount; i++) {
                if (eventUsernames[i].equals(username) && eventPasswords[i].equals(password)) {
                    match = true;
                    break;
                }
            }

            if (!match) {
                System.err.println("Wrong username/password.");
                Thread.sleep(50);
            } else break;
        }

        currentUser = indexOfUsername(username);

        System.out.println("\n1) Show Registered Events");
        System.out.println("2) Show Upcoming Events\n");
        System.out.print("Choose(1-2) '0' to exit: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 0) return;
        if (choice == 1) showRegisteredEvents();
        if (choice == 2) showEvents();
    }

    public static int indexOfUsername(String username) {
        for (int i = 0; i < userCount; i++) {
            if (eventUsernames[i].equals(username)) return i;
        }
        return -1;
    }

    public static void registration() throws IOException, InterruptedException {
        String username;
        String password;

        while (true) {
            System.out.print("Create username (or type 'back' to cancel): ");
            username = scanner.nextLine().toLowerCase().trim();

            if (username.equals("back")) return;

            if (username.isEmpty()) {
                System.err.println("Please enter a valid username");
                Thread.sleep(50);
                continue;
            }

            boolean unique = true;
            for (int i = 0; i < userCount; i++) {
                if (username.equals(eventUsernames[i])) {
                    unique = false;
                    break;
                }
            }

            if (!unique) {
                System.err.println("Username already exists, choose a new one");
                Thread.sleep(50);
            } else {
                System.out.print("Create password: ");
                password = scanner.nextLine().trim();

                while (password.isEmpty()) {
                    System.err.println("Enter a valid password");
                    Thread.sleep(50);
                    System.out.print("Create password: ");
                    password = scanner.nextLine().trim();
                }

                eventUsernames[userCount] = username;
                eventPasswords[userCount] = password;
                userCount++;

                saveUserToFile(username, password);
                break;
            }
        }
    }
}
