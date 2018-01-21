package ru.otus.dpopkov.json.model;

public class User {
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(char[] passwordChars) {
        this.password = String.valueOf(passwordChars);
    }
}
