package com.example.myactivity.models;

public class User {

    private String username;
    private String password;
    private String db_identifier;
    private String address;
    private String contact_number;
    private String firstname;
    private String lastname;

    public User(String username, String password, String db_identifier, String address, String contact_number, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.db_identifier = db_identifier;
        this.address = address;
        this.contact_number = contact_number;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public User(){

    }

    public String setUsername(String username) {
        this.username = username;
        return username;
    }

    public String setPassword(String password) {
        this.password = password;
        return password;
    }

    public String getDb_identifier() {
        return db_identifier;
    }

    public void setDb_identifier(String db_identifier) {
        this.db_identifier = db_identifier;
    }

    public String getAddress() {
        return address;
    }

    public String setAddress(String address) {
        this.address = address;
        return address;
    }

    public String getContact_number() {
        return contact_number;
    }

    public String setContact_number(String contact_number) {
        this.contact_number = contact_number;
        return contact_number;
    }

    public String getFirstname() {
        return firstname;
    }

    public String setFirstname(String firstname) {
        this.firstname = firstname;
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String setLastname(String lastname) {
        this.lastname = lastname;
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
