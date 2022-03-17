package com.mycompany.hotel_management_system;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

class write implements Runnable{

    holder hotel_ob;
    write(holder hotel_ob){
        this.hotel_ob = hotel_ob;
    }
    @Override
    public void run() {
        try{
            FileOutputStream fout=new FileOutputStream("backup.txt");
            ObjectOutputStream oos=new ObjectOutputStream(fout);
            oos.writeObject(hotel_ob);
        }
        catch(IOException e)
        {
            System.out.println("Error in writing "+e);
        }
    }
}