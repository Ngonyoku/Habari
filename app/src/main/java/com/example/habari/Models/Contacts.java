package com.example.habari.Models;

public class Contacts {
    private String name, phoneNumber;

    public Contacts() {
    }

    public Contacts(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
