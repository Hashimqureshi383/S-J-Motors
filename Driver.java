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
    public static void main(String[] args)
    {
        byte choice;
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
            {
                //manager login
            }
            else if(choice==2)
                firstPage();
            else if(choice==3)
                System.out.println("");
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
            {
                User loginUser=new User();
                System.out.println("Enter CNIC= ");
                loginUser.setCnic(input.nextInt());
                Iterator<User> it=app.users.iterator();
                while(it.hasNext())
                {
                    if(it.next().getCnic()==loginUser.getCnic())
                    {
                        System.out.println("The User with this CNIC already exists.\n");
                        loginUser.setPhone(input.nextLine());
                        System.out.println("Enter Password= ");
                        loginUser.setPassword(input.nextLine());
                        if(loginUser.getPassword()==it.next().getPassword())
                            userPage(loginUser);
                        else
                        {
                            System.out.println("Wrong Password");
                            continue;
                        }
                    }
                }
                System.out.println("Incorrect CNIC\n");
            }
            else if(choice==2)
            {
                User newUser=new User();
                System.out.println("Enter CNIC= ");
                newUser.setCnic(input.nextInt());
                Iterator<User> it=app.users.iterator();
                while(it.hasNext())
                {
                    if(it.next().getCnic()==newUser.getCnic())
                    {
                        System.out.println("The User with this CNIC already exists.\n");
                        continue;
                    }
                }
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
                    insertq.write(Integer.toString(newUser.getCnic())+",\'"+newUser.getName()+"\',\'"+newUser.getPhone()+"\',\'"+newUser.getPassword()+"\',\'"+newUser.getAddress()+"\',0,1");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(choice==3)
                return;
        }

    }
    private static void userPage(User loggedInUser)
    {
        while(true)
        {
            System.out.flush();
            byte choice=0;
            System.out.flush();
            System.out.print("\n\t\t\tS&JMotors:User\n\n");
            System.out.print("You can do following actions on this page\n");
            System.out.print("1. See mileage of your Vehicle \n");
            System.out.print("2. See your job history(Repair work) \n");
            System.out.print("3. See cost spent on all jobs \n");
            System.out.print("4. Log Out \n");
            Scanner input=new Scanner(System.in);
            choice=input.nextByte();
            if(choice==1)
            {
                System.out.flush();
                System.out.println("Your Vehicles and their Mileages:\n\n");
                int count=0;
                Iterator<Vehicle> it=app.vehicles.iterator();
                while (it.hasNext())
                {
                    if (it.next().getRegBy()==loggedInUser.getCnic())
                    {
                        System.out.println(it.next().getCompany()+"Model No: "+it.next().getModel()+" Mileage: 100 units\n");
                        count++;
                    }
                }
                if(count==0)
                    System.out.println("You do not own any Vehicle");
            }
            else if (choice==2)
            {
                int count=0;
                Iterator<Job> it = app.jobs.iterator();
                while (it.hasNext())
                {
                    if((it.next().getCustomerId()==loggedInUser.getCnic())&&(it.next().getStatus()))
                    {
                        System.out.println(it.next().getDescription()+" Date "+it.next().getScheduleDate()+"\n");
                        count++;
                    }
                }
                if (count==0)
                    System.out.println("You have not taken any Service yet.\n");
            }
            else if (choice == 3)
            {
                int count=0;
                float cost=0;
                Iterator<Job> it=app.jobs.iterator();
                while (it.hasNext())
                {
                    if((it.next().getCustomerId()==loggedInUser.getCnic())&&(it.next().getStatus()))
                    {
                        System.out.println(it.next().getDescription()+" Date "+it.next().getScheduleDate()+" Costed: 1000 PKR\n");
                        count++;
                    }
                }
                if(count==0)
                    System.out.println("You have not taken any Service yet.\n");
                else
                {
                    cost=1000*count;
                    System.out.println("The Total Cost is : "+Float.toString(cost)+" PKR\n");
                }
            }
            else if(choice==4)
                return;
        }
    }
}
