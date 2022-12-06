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
        boolean marked=false;
        while(true)
        {
            System.out.flush();
            System.out.println("S&JMotors:Staff\n\n");
            System.out.println("1. Mark Attendance\n");
            System.out.println("2. Go Back");
            Scanner input_staff=new Scanner(System.in);
            int option=0;
            option =input_staff.nextInt();
            if(option==1)
            {
                if(marked)
                {
                    System.out.println("Your Attendance is Already Marked. Press Any key to continue...");
                    input_staff.nextInt();
                }
                else
                {
                    int id=input_staff.nextInt();
                    Iterator<Staff> it=app.employees.iterator();
                    while(it.hasNext())
                    {
                        if(it.next().getId()==id)
                        {
                            System.out.println("Attendance Marked Successfully");
                            marked=true;
                            continue;
                        }
                    }
                    long millis = System.currentTimeMillis();
                    java.sql.Date date = new java.sql.Date(millis);
                    Attendance obj=new Attendance(date.toString(), true, id);
                    app.records.add(obj);
                    FileWriter insertq;
                    try {
                        insertq=new FileWriter("DDL Queries.txt");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        insertq.write("Insert into attendancerecord (date,attendance,memberid) values (\'"+obj.getDate()+"\',1,"+Integer.toString(id));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            else if(option==2)
            {
                System.out.println("GoodBye\n");
                input_staff.close();
                return;
            }
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
                            adminMenu(loginManager);
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
                bookRequest(loggedInUser);
            else if(choice==5)
                return;
        }
    }
    private static void adminMenu(Manager loggedInManager)
    {
        int op =0;

        while (op != 6)
        {
            System.out.flush();
            System.out.print("S&JMotors: Outlet Admin\n\n");
            System.out.print("1. Add employee\n");
            System.out.print("2. Remove employee\n");
            System.out.print("3. Assign Task\n");
            System.out.print("4. Show Jobs\n");
            System.out.print("5. Show Employees\n");
            System.out.print("6. Go Back\n");
            Scanner input = new Scanner(System.in);
            op = input.nextInt();
            if (op == 1)
            {
                addEmployee();
            }
            else if (op == 2)
            {
                removeEmployee();
            }
            else if (op == 3)
            {
                AssignTask();
            }
            else if (op == 4)
            {
                AllJobs();
            }
            else if (op == 5) {
                AllEmployee();
            }

        }
    }

    private static void AllEmployee()
    {
        System.out.println("\n\n\nAll Employees: \n\n\n");
        Iterator<Staff> it = app.employees.iterator();
        while (it.hasNext()) {
            System.out.println("\nName: " + it.next().getName());
            System.out.println(" Id: " + it.next().getId());
        }
    }

    private static void AllJobs() {
        System.out.println("\n\n\nAll Jobs: \n\n\n");

        Iterator<Job> it = app.jobs.iterator();

        while (it.hasNext()) {
            System.out.println("\nID: " + it.next().getId());
            System.out.println(" Description: " + it.next().getDescription());
            System.out.println(" Date: " + it.next().getScheduleDate());
            System.out.println(" StaffID: " + it.next().getStaffId());
            System.out.println(" CustomerID: "+ it.next().getCustomerId());
        }

    }

    private static void addEmployee() {
        int id = 0;
        String name = null;
        String phone = null;
        String Address = null;
        int outletId = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println("\n\n\nEnter CNIC= ");
        id = sc.nextInt();
        System.out.println("Enter Name= ");
        name = sc.nextLine(); // reads string.
        System.out.println("Enter Phone Number= ");
        phone = sc.nextLine(); // reads string.
        System.out.println("Enter Address= ");
        Address = sc.nextLine(); // reads string.
        System.out.println("Enter Outlet ID= ");
        outletId = sc.nextInt();

        Staff newEmployee = new Staff(id, name, phone, Address, outletId);
        app.employees.add(newEmployee);
        FileWriter insertq;
        try {
            insertq=new FileWriter("DDL Queries.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            insertq.write("Insert into staff (id,name,phone,address,outletid) values ("+Integer.toString(id)+",\'"+name+"\',\'"+phone+"\',\'"+Address+"\',"+Integer.toString(outletId));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // System.out.println("Employee Added");

    }

    private static void removeEmployee() {
        int idR = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println("\n\n\nEnter CNIC/ID to remove= ");
        idR = sc.nextInt();

        Iterator<Staff> it = app.employees.iterator();

        int header = 0;

        while (it.hasNext() && header == 0) {

            if (it.next().id == idR) {
                it.next().id = 0;
                it.next().name = null;
                it.next().outletId = 0;
                it.next().Address = null;
                it.next().phone = null;
                header = 1;
            }
        }
        if (header == 1) {
            System.out.println("\n\n\nEmployee deactivated \n\n\n ");
        }

        else {
            System.out.println("\n\n\nEmployee Out Found \n\n\n ");

        }
    }
    private static void AssignTask()
    {
        System.out.println("\n\n\nAssigning task\n\n\n");
        int id = 0;
        String description = null;
        int customerId = 0;
        int staffId = 0;
        String scheduleDate = null;
        int outletId = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println("\n\n\nEnter Job ID= ");
        id = sc.nextInt();
        System.out.println("Enter Desprictions= ");
        description = sc.nextLine(); // reads string.
        System.out.println("Enter customer ID= ");
        customerId = sc.nextInt(); // reads string.
        System.out.println("Enter scheduleDate= ");
        scheduleDate = sc.nextLine(); // reads string.
        System.out.println("Enter Outlet ID= ");
        outletId = sc.nextInt();

        Job newJob = new Job(id, description, customerId, staffId, scheduleDate, outletId,false);

        app.jobs.add(newJob);

    }

}
}
