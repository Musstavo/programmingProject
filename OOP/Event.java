package OOP;

public class Event extends FileRecord {
    private String event_name;
    private int year;
    private int month;
    private int day;
    private String venue;
    private int capacity;
    private int length;
    private int participants;

    public Event(String event_name, int year, int month, int day, String venue, int capacity,
                 int length, int participants) {
        setEvent_name(event_name);
        setCapacity(capacity);
        setDay(day);
        setMonth(month);
        setYear(year);
        setLength(length);
        setVenue(venue);
        setParticipants(participants);
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public int getParticipants() {
        return participants;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_name() {
        return event_name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String writeToFile() {
        return event_name + " : " + year + "/" + month + "/" + day + " : " + venue + " : " + capacity +
                " : " + length + " : " + participants + "\n";
    }

    public static Event readFromFile(String line) {
        String[] parts = line.split(":");

        String event_name = parts[0].trim();
        String date = parts[1].trim();
        String venue = parts[2].trim();
        int capacity = Integer.parseInt(parts[3].trim());
        int length = Integer.parseInt(parts[4].trim());
        int participants = Integer.parseInt(parts[5].trim());

        String[] date_parts = date.split("/");
        int year = Integer.parseInt(date_parts[0].trim());
        int month = Integer.parseInt(date_parts[1].trim());
        int day = Integer.parseInt(date_parts[2].trim());

        return new Event(event_name, year, month, day, venue, capacity, length, participants);
    }
}

