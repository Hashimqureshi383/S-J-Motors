import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class Driver
{
    private static Server app;

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
                managerPage();
            }
            else if(choice==2)
                firstPage();
            else if(choice==3)
                staffPage();
            else if(choice==4)
                return;
        }
    }
    private static void staffPage()
    {
        while(true)
        {
            System.out.flush();
            System.out.println("S&JMotors:Staff\n\n");
            System.out.println("1. Mark Attendance\n");
            System.out.println("2. Go Back");
            Scanner input_staff=new Scanner(System.in);
            int option;
            option =input_staff.nextInt();
            if(option==1)
            {
                Staff.checkIn(false,app);
            }
            else if(option==2)
            {
                System.out.println("GoodBye\n");
                input_staff.close();
                return;
            }
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
                    insertq = new FileWriter("DDL Queries.txt",true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    insertq.write("Insert into user (cnic,name,phone,password,address,outletid,status) values (");
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
    private static void managerPage()
    {
        while(true)
        {
            System.out.flush();
            byte choice=0;
            String temp;
            System.out.println("1. Login\n");
            System.out.println("2. Go Back\n");
            Scanner input=new Scanner(System.in);
            choice=input.nextByte();
            if(choice==1)
            {
                Manager loginManager=new Manager();
                System.out.println("Enter id= ");
                loginManager.setId(input.nextInt());
                Iterator<Manager> it=app.managers.iterator();
                while(it.hasNext())
                {
                    if(it.next().getId()==loginManager.getId())
                    {
                        System.out.println("Enter Password= ");
                        loginManager.setPassword(input.nextLine());
                        if(loginManager.getPassword()==it.next().getPassword())
                        {
                            if(it.next().getType()=="Outlet")
                            {
                                OutletAdmin admin=new OutletAdmin(loginManager);
                                adminMenu(admin);
                            }
                            else
                            {
                                WorkshopM mgr=new WorkshopM(loginManager);
                                workshopMgrPage(mgr);
                            }
                        }
                        else
                        {
                            System.out.println("Wrong Password");
                            continue;
                        }
                    }
                }
                System.out.println("Incorrect ID\n");
            }
            else if(choice==2)
                return;
        }
    }
    public static void workshopMgrPage(WorkshopM mgr)
    {
        while(true)
        {
            System.out.flush();
            System.out.println("\t\t\nS&JMotors:Workshop Manager\n");
            System.out.println("1. Accept any Service Request\n");
            System.out.println("2. Go Back\n");
            Scanner input=new Scanner(System.in);
            int choice=input.nextInt();
            boolean flag=false;
            if(choice==1)
                mgr.bookService(app);
            else if(choice==2)
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
            System.out.print("4. Request to Book a service \n");
            System.out.print("5. Log Out \n");
            Scanner input=new Scanner(System.in);
            choice=input.nextByte();
            if(choice==1)
            {
                loggedInUser.showMileage(app);
            }
            else if (choice==2)
            {
                loggedInUser.showHistory(app);
            }
            else if (choice == 3)
            {
                loggedInUser.showCostSpent(app);
            }
            else if(choice==4)
                loggedInUser.bookservice();
            else if(choice==5)
                return;
        }
    }
    private static void adminMenu(OutletAdmin loggedInManager)
    {
        int op =0;

        while (op != 6)
        {
            System.out.flush();
            System.out.println("S&JMotors: Outlet Admin\n\n");
            System.out.println("1. Add employee\n");
            System.out.println("2. Remove employee\n");
            System.out.println("3. Assign Task\n");
            System.out.println("4. Show Jobs\n");
            System.out.println("5. Show Employees\n");
            System.out.println("6. Manage Users");
            System.out.println("7. Go Back\n");
            Scanner input = new Scanner(System.in);
            op = input.nextInt();
            if (op == 1)
            {
                loggedInManager.addEmployee(app);
            }
            else if (op == 2)
            {
                loggedInManager.removeEmployee(app);
            }
            else if (op == 3)
            {
                loggedInManager.AssignTask(app);
            }
            else if (op == 4)
            {
                DataDisplay.AllJobs(app);
            }
            else if (op == 5)
            {
                DataDisplay.AllEmployee(app);
            }
            else if(op==6)
            {
                manageUsers(loggedInManager);
            }
            else if(op==7)
                return;
        }
    }
    public static void manageUsers(OutletAdmin admin)
    {
        while(true)
        {
            System.out.flush();
            System.out.print("\n1. Activate User\n");
            System.out.print("2. Transfer User\n");
            System.out.print("3. Deactivate User\n");
            System.out.print("4. Exit\n\n");
            System.out.print("Select an option > ");
            Scanner option = new Scanner(System.in);
            int select = option.nextInt();
            int cnic=0;
            boolean flag=false;
            if (select == 1)
            {
                admin.activateUser(app);
            }
            else if (select == 2)
            {
                admin.transferUser(app);
            }
            else if (select == 3)
            {
                admin.deactivateUser(app);
            }
            else if(select == 4)
                return;
        }
    }
}
