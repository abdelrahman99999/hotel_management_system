package com.mycompany.hotel_management_system;
import java.io.Serializable;

class holder implements Serializable {

    DoubleRoom[] drl = new DoubleRoom[10]; // Luxury Double Room
    DoubleRoom[] drd = new DoubleRoom[20]; // Deluxe Double Room
    SingleRoom[] srl = new SingleRoom[10]; // Luxury Single Room
    SingleRoom[] srd = new SingleRoom[20]; // Deluxe Single Room


    public SingleRoom getRoom(int room_number, int room_type){
        SingleRoom room;
        switch (room_type) {
            case 1 -> room = drl[room_number];
            case 2 -> room = drd[room_number];
            case 3 -> room = srl[room_number];
            case 4 -> room = srd[room_number];
            default -> room = null;
        }
        return room;
    }
    public void setRoom(int room_number, int room_type , SingleRoom room){
        switch (room_type) {
            case 1 -> drl[room_number] = (DoubleRoom) room;
            case 2 -> drd[room_number] = (DoubleRoom) room;
            case 3 -> srl[room_number] = room;
            case 4 -> srd[room_number] = room;
        }
    }

}