package ui.preferences;

public class Preferences {
    private String username;
    private String password;
    public Preferences(){
        username="admin";
        setPassword("admin");
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
}
