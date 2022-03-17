package com.mycompany.hotel_management_system;
import java.util.Scanner;

class Hotel{
    static holder ob = new holder();
    static Scanner sc = new Scanner(System.in);
    
    
    /*
    show available room and book room if available
    */
    static void bookroom(int ch){
        //ch:room type
        int i;
        int rn;
        System.out.println("Choose the room that you want to book: ");
        switch (ch){
            case 1 -> {
                for(i = 0;i < ob.drl.length; i++){
                    if(ob.drl[i] == null){
                        System.out.print(i+1+",");
                    }
                }
                System.out.print("\nEnter the room number that you want to select: ");
                try{
                    rn=sc.nextInt();
                    if(ob.drl[rn] != null)
                        throw new NotAvailable();

                    custDetail(ch, --rn);
                }
                catch (NotAvailable e){
                    System.out.println(e.toString());
                    return;
                }
            }
            case 2 -> {
                for(i = 0;i < ob.drd.length; i++){
                    if(ob.drd[i] == null){
                        System.out.print(i+11+",");
                    }
                }
                System.out.print("\nEnter the room number that you want to select: ");
                try{
                    rn=sc.nextInt();
                    rn=rn-11;
                    if(ob.drd[rn] != null)
                        throw new NotAvailable();
                    custDetail(ch, rn);
                }
                catch (NotAvailable e){
                    System.out.println(e.toString());
                    return;
                }
            }
            case 3 -> {
                for(i = 0;i < ob.srl.length; i++){
                    if(ob.srl[i] == null){
                        System.out.print(i+31+",");
                    }
                }
                System.out.print("\nEnter the room number that you want to select: ");
                try{
                    rn=sc.nextInt();
                    rn=rn-31;
                    if(ob.srl[rn] != null)
                        throw new NotAvailable();
                    custDetail(ch, rn);
                }
                catch (NotAvailable e){
                    System.out.println(e.toString());
                    return;
                }
            }
            case 4 -> {
                for(i = 0;i < ob.srd.length; i++){
                    if(ob.srd[i] == null){
                        System.out.print(i+41+",");
                    }
                }
                System.out.print("\nEnter the room number that you want to select: ");
                try{
                    rn=sc.nextInt();
                    rn=rn-41;
                    if(ob.srd[rn] != null)
                        throw new NotAvailable();
                    custDetail(ch, rn);
                }
                catch (NotAvailable e){
                    System.out.println(e.toString());
                    return;
                }
            }
            default -> System.out.println("Enter valid option");
        }
        System.out.println("Room Booked");
    }

    //customer detalis
    /*
    take customer detalis and which room he want to book
    used by bookroom method
    */
    static void custDetail(int ch, int rn){
        //ch:room type
        //rn:room number
        String name, contact, gender;
        String name2 = null, contact2 = null;
        String gender2="";
        System.out.print("\nEnter customer name: ");
        name = sc.next();
        System.out.print("Enter contact number: ");
        contact=sc.next();
        System.out.print("Enter gender: ");
        gender = sc.next();
        if(ch < 3){ //for double rooms
            System.out.print("Enter second customer name: ");
            name2 = sc.next();
            System.out.print("Enter contact number: ");
            contact2=sc.next();
            System.out.print("Enter gender: ");
            gender2 = sc.next();
        }

        switch (ch){
            case 1: ob.drl[rn] = new DoubleRoom(name, contact, gender, name2, contact2, gender2);
                break;
            case 2: ob.drd[rn] = new DoubleRoom(name, contact, gender, name2, contact2, gender2);
                break;
            case 3: ob.srl[rn] = new SingleRoom(name, contact, gender);
                break;
            case 4: ob.srd[rn] = new SingleRoom(name, contact, gender);
                break;
            default:System.out.println("Wrong option");
                break;
        }
    }

    /*
    show features of each room type
    */
    static void features(int ch){
        //ch:room type
        switch (ch) {
            case 1 -> System.out.println("Number of double beds : 1\nAC : Yes\nFree breakfast : Yes\nCharge per day:5000 ");
            case 2 -> System.out.println("Number of double beds : 1\nAC : No\nFree breakfast : Yes\nCharge per day:3500  ");
            case 3 -> System.out.println("Number of single beds : 1\nAC : Yes\nFree breakfast : Yes\nCharge per day:2000  ");
            case 4 -> System.out.println("Number of single beds : 1\nAC : No\nFree breakfast : Yes\nCharge per day:1200 ");
            default -> System.out.println("Enter valid option");
        }
    }
    
    /*
    show number of available rooms for specific type
    */
    static void availability(int ch)
    {
        //ch:room type
        int j,count=0;
        switch (ch) {
            case 1 -> {
                for(j=0;j<10;j++)
                {
                    if(ob.drl[j]==null)
                        count++;
                }
            }
            case 2 -> {
                for(j=0;j<ob.drd.length;j++)
                {
                    if(ob.drd[j]==null)
                        count++;
                }
            }
            case 3 -> {
                for(j=0;j<ob.srl.length;j++)
                {
                    if(ob.srl[j]==null)
                        count++;
                }
            }
            case 4 -> {
                for(j=0;j<ob.srd.length;j++)
                {
                    if(ob.srd[j]==null)
                        count++;
                }
            }
            default -> System.out.println("Enter valid option");
        }
        System.out.println("Number of rooms available : "+count);
    }

    
    static void bill(int rn, int ch){
        double amount=0;
        String[] list={"Sandwich","Pasta","Noodles","Coke"};
        System.out.println("\n*******");
        System.out.println(" Bill:-");
        System.out.println("*******");

        switch (ch){
            case 1 -> {
                amount += 5000;
                System.out.println("Room Charges: " + 5000);
                System.out.println("\n===============");
                System.out.println("Food Charges:- ");
                System.out.println("===============");
                System.out.println("Item   Quantity    Price");
                System.out.println("-------------------------");
                for(Food bi: ob.drl[rn].food)
                {
                    amount += bi.getPrice();
                    String format = "%-10s%-10s%-10s%n";
                    System.out.printf(format,list[bi.getItem_no()-1],bi.getQuantity(),bi.getPrice() );
                }
            }
            case 2 -> {
                amount+=3500;
                System.out.println("Room Charge - "+3500);
                System.out.println("\nFood Charges:- ");
                System.out.println("===============");
                System.out.println("Item   Quantity    Price");
                System.out.println("-------------------------");
                for(Food bi:ob.drd[rn].food)
                {
                    amount+= bi.getPrice();
                    String format = "%-10s%-10s%-10s%n";
                    System.out.printf(format,list[bi.getItem_no() -1], bi.getQuantity(), bi.getPrice());
                }
            }
            case 3 -> {
                amount+=2000;
                System.out.println("Room Charge - "+2000);
                System.out.println("\nFood Charges:- ");
                System.out.println("===============");
                System.out.println("Item   Quantity    Price");
                System.out.println("-------------------------");
                for(Food bi:ob.srl[rn].food)
                {
                    amount+= bi.getPrice();
                    String format = "%-10s%-10s%-10s%n";
                    System.out.printf(format,list[bi.getItem_no() -1], bi.getQuantity(), bi.getPrice());
                }
            }
            case 4 -> {
                amount+=1200;
                System.out.println("Room Charge - "+1200);
                System.out.println("\nFood Charges:- ");
                System.out.println("===============");
                System.out.println("Item   Quantity    Price");
                System.out.println("-------------------------");
                for(Food bi:ob.srd[rn].food)
                {
                    amount+= bi.getPrice();
                    String format = "%-10s%-10s%-10s%n";
                    System.out.printf(format,list[bi.getItem_no() -1], bi.getQuantity(), bi.getPrice());
                }
            }
            default -> System.out.println("Not valid");
        }
        System.out.println("\nTotal Amount- "+amount);
    }

    static void order(int rn, int ch){
        int i, q;
        char wish;
        try{
            System.out.println("\n==========\n   Menu:  \n==========\n\n1.Sandwich\tRs.100\n2.Pasta\t\tRs.130\n3.Noodles\tRs.150\n4.Coke\t\tRs.60\n");
            do {
                System.out.print("Enter your choice- ");
                i=sc.nextInt();
                System.out.print("Quantity- ");
                q = sc.nextInt();
                switch (ch){
                    case 1 -> {
                        ob.drl[rn].food.add(new Food(i, q));
                        if(i > 4 || q <= 0)
                            ob.drl[rn].food.remove(ob.drl[rn].food.size() - 1);
                    }
                    case 2 -> {
                        ob.drd[rn].food.add(new Food(i, q));
                        if(i > 4 || q <= 0)
                            ob.drd[rn].food.remove(ob.drd[rn].food.size() - 1);
                    }
                    case 3 -> {
                        ob.srl[rn].food.add(new Food(i, q));
                        if(i > 4 || q <= 0)
                            ob.srl[rn].food.remove(ob.srl[rn].food.size() - 1);
                    }
                    case 4 -> {
                        ob.srd[rn].food.add(new Food(i, q));
                        if(i > 4 || q <= 0)
                            ob.srd[rn].food.remove(ob.srd[rn].food.size() - 1);
                    }
                }
                System.out.println("Do you want to order anything else ? (y/n)");
                wish=sc.next().charAt(0);
            }while (wish == 'y' || wish == 'Y');

            System.out.println("Do you want to see the bill ? (y/n)");
            wish = sc.next().charAt(0);
            if(wish == 'y' || wish == 'Y'){
                bill(rn, ch);
            }
        }
        catch (NullPointerException e){
            System.out.println("\nRoom not booked");
        }
        catch (Exception e){
            System.out.println("Cannot be done");
        }
    }

    static void deallocate(int rn, int ch){
        //rn:room number
        //ch:room type
        char w;
        switch (ch){
            case 1 -> {
                if(ob.drl[rn] != null)
                    System.out.println("This room is used by "+ ob.drl[rn].getName());
                else{
                    System.out.println("Room is already empty!!");
                    return;
                }
                System.out.println("Do you want to checkout ?(y/n)");
                w=sc.next().charAt(0);
                if(w=='y' || w == 'Y'){
                    bill(rn, ch);
                    ob.drl[rn] =null;
                    System.out.println("Room Deallocated succesfully");
                }
            }
            case 2 -> {
                if(ob.drd[rn] != null)
                    System.out.println("This room is used by "+ ob.drd[rn].getName());
                else{
                    System.out.println("Room is already empty!!");
                    return;
                }
                System.out.println("Do you want to checkout ?(y/n)");
                w=sc.next().charAt(0);
                if(w=='y' || w == 'Y'){
                    bill(rn, ch);
                    ob.drd[rn] =null;
                    System.out.println("Room Deallocated succesfully");
                }
            }

            case 3 -> {
                if(ob.srl[rn] != null)
                    System.out.println("This room is used by "+ ob.srl[rn].getName());
                else{
                    System.out.println("Room is already empty!!");
                    return;
                }
                System.out.println("Do you want to checkout ?(y/n)");
                w=sc.next().charAt(0);
                if(w=='y' || w == 'Y'){
                    bill(rn, ch);
                    ob.srl[rn] =null;
                    System.out.println("Room Deallocated succesfully");
                }
            }

            case 4 -> {
                if(ob.srd[rn] != null)
                    System.out.println("This room is used by "+ ob.srd[rn].getName());
                else{
                    System.out.println("Room is already empty!!");
                    return;
                }
                System.out.println("Do you want to checkout ?(y/n)");
                w=sc.next().charAt(0);
                if(w=='y' || w == 'Y'){
                    bill(rn, ch);
                    ob.srd[rn] =null;
                    System.out.println("Room Deallocated succesfully");
                }
            }
            default -> System.out.println("\nEnter valid option : ");
        }


    }
}