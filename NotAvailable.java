package com.mycompany.hotel_management_system;

class NotAvailable extends Exception{
    
    @Override
    public String toString() {
        return "Not Available";
    }
}