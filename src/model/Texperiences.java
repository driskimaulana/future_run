package model;

import java.sql.SQLException;

public class Texperiences extends DB{

    private int id; //id pengguna
    private String username; //username pengguna
    private int fall; //telepon pengguna
    private int adapt; //telepon pengguna

    public Texperiences() throws Exception, SQLException {
        super();
    }

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
    public void setFall(int fall) {
        this.fall = fall;
    }
    public int getFall() {
        return fall;
    }
    public void setAdapt(int adapt) {
        this.adapt = adapt;
    }
    public int getAdapt() {
        return adapt;
    }
    
}