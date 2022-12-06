import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

public class Driver
{
    static Server app;

    //my commit
    //branched commit
    public static void main(String[] args)
    {
        byte choice=0;
        while(true)
        {
            System.out.flush();
            System.out.print("\n\t\t\tWelcome to S&J Motors\n\n");
            System.out.print("We have a vast setup of Motor Mechanics that deploys a large number of expert\n");
            System.out.print("and experienced employees along with managers having excellence in their work.\n");
            System.out.print("We have different Outlets that help users in service and maintenance of their vehicles\n");
            System.out.print("anytime and anywhere. Moreover, Users can also share us with data of their vehicles\n");
            System.out.print("and we will give them statistics like mileage and history. Not only, Users can buy some\n");
            System.out.print("spare parts for Vehicles as well.\n\n");
            System.out.print("You can move forward as a\n\n");
            System.out.print("1. Manager\n");
            System.out.print("2. User\n");
            System.out.print("3. Employee/Staff\n");
            System.out.print("4. Exit\n");
            Scanner input=new Scanner(System.in);
            choice=input.nextByte();
            if(choice==1)
                firstPage();
            else if(choice==2)
                firstPage();
            else if(choice==3)
                firstPage();
            else if(choice==4)
                return;
        }
    }
    private static void bookRequest(User requestee)
    {
        String jobDesc;
        Scanner input=new Scanner(System.in);
        jobDesc=input.nextLine();
        FileWriter req;
        try {
            req=new FileWriter("Service Requests.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            req.write(requestee.getCnic());
            req.write(" "+jobDesc);
            req.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private static void firstPage()
    {
        while(true)
        {
            System.out.flush();
            byte choice=0;
            int id=0;
            String temp;
            System.out.println("1. Login\n");
            System.out.println("2. Signup\n");
            System.out.println("3. Go Back\n");
            Scanner input=new Scanner(System.in);
            choice=input.nextByte();
            if(choice==1)
                System.out.println("");
            else if(choice==2)
            {
                User newUser=new User();
                System.out.println("Enter CNIC= ");
                newUser.setCnic(input.nextInt());
                System.out.println("Enter Name= ");
                newUser.setName(input.nextLine());
                System.out.println("Enter Phone Number= ");
                newUser.setPhone(input.nextLine());
                System.out.println("Enter Password= ");
                newUser.setPassword(input.nextLine());
                System.out.println("Enter Address= ");
                newUser.setAddress(input.nextLine());
                app.users.add(newUser);
                FileWriter insertq= null;
                try {
                    insertq = new FileWriter("DDL Queries.txt");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    insertq.write("Insert into user values ");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    insertq.write(newUser.getCnic());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    insertq.write(newUser.getCnic()+",\'"+newUser.getName()+"\',\'"+newUser.getPhone()+"\',\'"+newUser.getPassword()+"\',\'"+newUser.getAddress()+"\',0,1");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            else if(choice==3)
                return;
        }

    }
}
