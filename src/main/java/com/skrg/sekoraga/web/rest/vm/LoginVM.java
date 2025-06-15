package com.skrg.sekoraga.web.rest.vm;

public class LoginVM {
    private String usernameOrEmail;
    private String password;

    // getter & setter
    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
