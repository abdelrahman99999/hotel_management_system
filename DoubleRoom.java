package com.mycompany.hotel_management_system;
import java.io.Serializable;

class DoubleRoom extends SingleRoom implements Serializable {
    private String name2;
    private String contact2;
    private String gender2;

    DoubleRoom() {
        setName("");
        setName2("");
    }

    DoubleRoom(String name,String contact,String gender,String name2,String contact2,String gender2,int price){
        super(name, contact, gender,price);
        setName2(name2);
        setContact2(contact2);
        setGender2(gender2);
    }

    public String getName2() {
        return name2;
    }

    private void setName2(String name2) {
        this.name2 = name2;
    }

    public String getContact2() {
        return contact2;
    }

    private void setContact2(String contact2) {
        this.contact2 = contact2;
    }

    public String getGender2() {
        return gender2;
    }

    private void setGender2(String gender2) {
        this.gender2 = gender2;
    }
}