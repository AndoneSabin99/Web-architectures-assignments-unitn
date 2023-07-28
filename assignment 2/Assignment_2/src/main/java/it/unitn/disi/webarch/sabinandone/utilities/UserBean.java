package it.unitn.disi.webarch.sabinandone.utilities;

import java.io.Serializable;

public class UserBean implements Serializable {

    private String username;
    private String password;

    public UserBean(){
        this.username = "";
        this.password = "";
    }

    public UserBean(String user, String passw){
        this.username = user;
        this.password = passw;
    }

    public String getUser() {
        return username;
    }

    public void setUser(String user) {
        this.username = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return "Username: " + this.username + "\nPassword: " + this.password;
    }

}
