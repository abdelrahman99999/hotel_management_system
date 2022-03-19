package com.mycompany.hotel_management_system;
import java.io.Serializable;
import java.util.ArrayList;

class SingleRoom implements Serializable {
    private String name;
    private String contact;
    private String gender;
    private int price;

    ArrayList<Food> food = new ArrayList<>();

    SingleRoom() {
        setName("");
    }
    SingleRoom(String name, String contact, String gender,int price){
        setName(name);
        setContact(contact);
        setGender(gender);
        setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return price;
    }

    public void setPrice(int p) {
        this.price = p;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
