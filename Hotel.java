package com.mycompany.hotel_management_system;
import java.util.Scanner;

class Hotel{
    static holder ob = new holder();
    static Scanner sc = new Scanner(System.in);

    //return rooms of specific room type
    public static SingleRoom[] rooms_of_roomType(int room_type){
        SingleRoom[] temp;
        switch (room_type){
            case 1 -> temp=ob.drl;
            case 2 -> temp=ob.drd;
            case 3 -> temp=ob.srl;
            case 4 -> temp=ob.srd;
            default ->{
                return null;
            }
        }
        return temp;
    }
    /*
    show available room and book room if available
    */
    static void bookroom(int room_type){
        int room_number;
        int no_of_rooms,k;
        SingleRoom[] temp=rooms_of_roomType(room_type);
        switch (room_type){
            case 1 -> k=1;
            case 2 -> k=11;
            case 3 -> k=31;
            case 4 -> k=41;
            default -> {
                System.out.println("Enter valid option");
                return;
            }
        }
        System.out.println("Choose the room that you want to book: ");
        no_of_rooms=temp.length;
        for(int i = 0;i < no_of_rooms; i++){
            if(temp[i] == null){
                System.out.print(i+k+",");
            }
        }
        System.out.print("\nEnter the room number that you want to select: ");
        try{
            room_number=sc.nextInt();
            room_number=room_number-k;
            if(temp[room_number] != null)
                throw new NotAvailable();

            custDetail(room_type, room_number);
        }
        catch (NotAvailable e){
            System.out.println(e);
            return;
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("enter vaild option");
            return;
        }

        System.out.println("Room Booked");
    }

    //customer detalis
    /*
    take customer detalis and which room he want to book
    used by bookroom method
    */
    static void custDetail(int room_type, int room_number){
        String name, contact, gender;
        String name2 = null, contact2 = null;
        String gender2="";
        System.out.print("\nEnter customer name: ");
        name = sc.next();
        System.out.print("Enter contact number: ");
        contact=sc.next();
        System.out.print("Enter gender: ");
        gender = sc.next();
        if(room_type < 3){ //for double rooms
            System.out.print("Enter second customer name: ");
            name2 = sc.next();
            System.out.print("Enter contact number: ");
            contact2=sc.next();
            System.out.print("Enter gender: ");
            gender2 = sc.next();
        }
        switch (room_type) {
            case 1 -> ob.drl[room_number] = new DoubleRoom(name, contact, gender, name2, contact2, gender2,5000);
            case 2 -> ob.drd[room_number] = new DoubleRoom(name, contact, gender, name2, contact2, gender2,3500);
            case 3 -> ob.srl[room_number] = new SingleRoom(name, contact, gender,2000);
            case 4 -> ob.srd[room_number] = new SingleRoom(name, contact, gender,1200);
            default -> System.out.println("Wrong option");
        }
    }
    /*
    show features of each room type
    */
    static void features(int room_type){
        switch (room_type) {
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
    static void availability(int room_type)
    {
        SingleRoom[] temp=rooms_of_roomType(room_type);
        if(temp==null){
            System.out.println("Enter valid option");
            return;
        }
        int count=0;
        for (SingleRoom temp1 : temp) {
            if (temp1 == null) {
                count++;
            }
        }
        System.out.println("Number of rooms available : "+count);
    }

    static void bill(int room_number, int room_type){
        String[] list={"Sandwich","Pasta","Noodles","Coke"};
        System.out.println("\n*******");
        System.out.println(" Bill:-");
        System.out.println("*******");
        SingleRoom r=ob.getRoom(room_number, room_type);
        
        //SingleRoom[] temp=rooms_of_roomType(room_type);
        if(r==null){
                System.out.println("Not valid");
                return ;
        }
        
        double amount= r.getPrice();
        System.out.println("Room Charge - "+ amount);
        
        if(!r.food.isEmpty()){
            System.out.println("\nFood Charges:- ");
            System.out.println("===============");
            System.out.println("Item   Quantity    Price");
            System.out.println("-------------------------");

            for(Food bi:r.food)
            {
                amount+= bi.getPrice();
                String format = "%-10s%-10s%-10s%n";
                System.out.printf(format,list[bi.getItem_no() -1], bi.getQuantity(), bi.getPrice());
            }
        }
        System.out.println("\nTotal Amount- "+amount);
    }

    static void order(int room_number, int room_type){
        // check that room booked or not
        if(ob.getRoom(room_number,room_type) == null)
        {
            System.out.println("\nRoom not booked");
        } else {
            System.out.println("\n==========\n   Menu:  \n==========\n\n1.Sandwich\tRs.100\n2.Pasta\t\tRs.130\n3.Noodles\tRs.150\n4.Coke\t\tRs.60\n");
            char wish;
            do {
                System.out.print("Enter your choice- ");
                int i = sc.nextInt();
                int q;
                if(i <= 4 && i>0) {
                    System.out.print("Quantity- ");
                    q = sc.nextInt();
                    ob.getRoom(room_number,room_type).food.add(new Food(i, q));
                }else {
                    System.out.println("Invalid choice");
                    return;
                }
                System.out.println("Do you want to order anything else ? (y/n)");
                wish=sc.next().charAt(0);
            } while (wish == 'y' || wish == 'Y');

            System.out.println("Do you want to see the bill ? (y/n)");
            wish = sc.next().charAt(0);
            if(wish == 'y' || wish == 'Y'){
                bill(room_number, room_type);
            }
            System.out.println("Successfully order");
        }
        
    }

    static void deallocate(int room_number, int room_type){
        if(ob.getRoom(room_number,room_type) == null)
        {
            System.out.println("Room is already empty!!");
        }
        else {
            char w;
            System.out.println("This room is used by "+ ob.getRoom(room_number,room_type).getName());
            System.out.println("Do you want to checkout ?(y/n)");
            w=sc.next().charAt(0);
            if(w=='y' || w == 'Y'){
                bill(room_number, room_type);
                ob.setRoom(room_number,room_type,null);
                System.out.println("Room Deallocated successfully");
            }
        }

    }
}
