package project;

public class Client {
    private int id;
    private String name;
    private String password;
    private String username;
    private String address;
    private int bankNum;

    public Client() {
        // Default constructor
    }

    // Constructor with all attributes
    public Client(int id, String name, String password, String username, String address, int bankNum) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.username = username;
        this.address = address;
        this.bankNum = bankNum;
    }

    // Getters and setters for each attribute

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBankNum() {
        return bankNum;
    }

    public void setBankNum(int bankNum) {
        this.bankNum = bankNum;
    }
}
