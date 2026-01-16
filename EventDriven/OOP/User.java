package EventDriven.OOP;

public class User extends FileRecord {
    private String username;
    private String password;

    public User(String username, String password) {
        setPassword(password);
        setUsername(username);
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase().trim();
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String writeToFile() {
        return username + ":" + password;
    }

    public static User readFromFile(String line) {

        String[] parts = line.split(":");
        String username = parts[0];
        String password = parts[1];
        return new User(username, password);
    }
}
