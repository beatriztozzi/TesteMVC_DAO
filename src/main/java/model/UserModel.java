package model;

public class UserModel {
    private long id;
    private String username;
    private String email;
    private String upassword;

    public UserModel() {
    }

    public UserModel(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.upassword =password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }
}
