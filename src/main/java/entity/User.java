package entity;

public class User {
    private int id;
    private String username;
    private String password;
    private String phone_num;
    private String email;

    public User(String username, String password, String phone_num, String email) {
        this.username = username;
        this.password = password;
        this.phone_num = phone_num;
        this.email = email;
    }

    public User(int id) {
        this.id = id;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone_num='" + phone_num + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
