package OOP;

public class UserEventRegistration extends FileRecord {
    private int userIndex;
    private String eventName;


    public UserEventRegistration(int userIndex, String eventName) {
        setUserIndex(userIndex);
        setEventName(eventName);
    }

    public void setUserIndex(int userIndex) {
        this.userIndex = userIndex;
    }

    public int getUserIndex() {
        return userIndex;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }
    
    public String writeToFile() {
        return userIndex + "," + eventName;
    }

    public static UserEventRegistration readFromFile(String line) {
        String[] parts = line.split(",");

        int userIndex = Integer.parseInt(parts[0]);
        String eventName = parts[1];

        return new UserEventRegistration(userIndex, eventName);
    }
}
