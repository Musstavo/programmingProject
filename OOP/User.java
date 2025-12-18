package OOP;

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

    public static User readFromFile(String line) { // ya allah...
        // we used static because we call it from the class name rather than an object
        // User means we're essentially returning an object rather than a int or string etc..
        // yea.. SIUUUU
        String[] parts = line.split(":");
        String username = parts[0];
        String password = parts[1];
        return new User(username, password);
    }
}
