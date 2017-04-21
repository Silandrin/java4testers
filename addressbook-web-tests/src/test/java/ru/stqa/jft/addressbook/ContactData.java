package ru.stqa.jft.addressbook;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String address;
    private final String mobPhone;
    private final String email;

    public ContactData(String firstname, String lastname, String address, String mobPhone, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.mobPhone = mobPhone;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getMobPhone() {
        return mobPhone;
    }

    public String getEmail() {
        return email;
    }
}
