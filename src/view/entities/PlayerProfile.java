package view.entities;

/**
 * Filename : PlayerProfile.java
 * Programmer : D'Riski Maulana
 * Date : 10 June 2022
 * Email : driskimaulana@upi.edu
 * Website : driskimaulana.c120.me
 * Deskripsi : class player with attribute same with texperiences table attribute
 */

public class PlayerProfile {
    
    // class attribute
    private int id;
    private String username;
    private int fall;
    private int adapt;

    public PlayerProfile()
    {
        // empty contructors
    }

    public PlayerProfile(int id, String username, int fall, int adapt)
    {
        // constructors for returned player
        this.id = id;
        this.username = username;
        this.fall = fall;
        this.adapt = adapt;
    }

    public PlayerProfile(String username)
    {
        // constructors for new player
        this.id = -1;
        this.username = username;
        this.fall = 0;
        this.adapt = 0;
    }

    // getter and setter
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public void setAdapt(int adapt) {
        this.adapt = adapt;
    }
    public int getAdapt() {
        return adapt;
    }
    public void setFall(int fall) {
        this.fall = fall;
    }
    public int getFall() {
        return fall;
    }
    
}
