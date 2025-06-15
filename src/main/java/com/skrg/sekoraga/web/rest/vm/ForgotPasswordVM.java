package com.skrg.sekoraga.web.rest.vm;

public class ForgotPasswordVM {
    private String username;
    private String email;

    // getter & setter
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
}
