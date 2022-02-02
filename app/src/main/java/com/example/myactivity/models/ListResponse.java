package com.example.myactivity.models;

public class ListResponse {

    private String firstname;
    private String lastname;
    private String contact_number;
    private String address;
    private String db_identifier;

    public ListResponse() {
    }

    public ListResponse(String firstname, String lastname, String contact_number, String address, String db_identifier) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.contact_number = contact_number;
        this.address = address;
        this.db_identifier = db_identifier;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDb_identifier() {
        return db_identifier;
    }

    public void setDb_identifier(String db_identifier) {
        this.db_identifier = db_identifier;
    }
}
