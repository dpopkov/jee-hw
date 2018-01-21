package ru.otus.dpopkov.json.model;

public class Owner {
    private String fio;
    private int age;

    public Owner() {
    }

    public Owner(String fio, int age) {
        this.fio = fio;
        this.age = age;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
