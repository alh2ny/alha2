package com.example.registration;

public class UserInformation {
    private String name;
    private String address;
    private int role;

    public UserInformation(String name, String address) {
        this.name = name;
        this.address = address;
        this.role = Constants.USER_ROLE_NORMAL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
