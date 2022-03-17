package com.mycompany.hotel_management_system;
import java.io.Serializable;

class holder implements Serializable {

    DoubleRoom[] drl = new DoubleRoom[10]; // Luxury Double Room
    DoubleRoom[] drd = new DoubleRoom[20]; // Deluxe Double Room
    SingleRoom[] srl = new SingleRoom[10]; // Luxury Single Room
    SingleRoom[] srd = new SingleRoom[20]; // Deluxe Single Room
}