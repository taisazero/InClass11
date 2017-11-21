package com.example.zero.inclass11;

import java.util.ArrayList;

public class Contact {

    public static ArrayList<Contact> contacts;

    public String name;
    public String email;
    public String phone;
    public byte[] profilePic;
    public String id;

    public Contact() {
    }

    public Contact(String name, String email, String phone, byte[] profilePic, String id) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.profilePic = profilePic;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
        if (email != null ? !email.equals(contact.email) : contact.email != null) return false;
        if (phone != null ? !phone.equals(contact.phone) : contact.phone != null) return false;
        return id != null ? id.equals(contact.id) : contact.id == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}