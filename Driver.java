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
    private static void AdminMenu() {
        byte op = 1;

        while (op != 6) {
            System.out.print("Welcome Admin\n\n");
            System.out.print("1. Add employee\n");
            System.out.print("2. Remove employee\n");
            System.out.print("3. Assign Task\n");
            System.out.print("4. Show Jobs\n");
            System.out.print("5. Show Employees\n");
            System.out.print("6. return\n");
            Scanner input = new Scanner(System.in);
            op = input.nextByte();

            if (op == 1) {
                addEmployee();
            }

            else if (op == 2) {
                removeEmployee();
            }

            else if (op == 3) {
                AssignTask();
            }

            else if (op == 3) {
                AllJobs();
            }

            else if (op == 3) {
                AllEmployee();
            }

        }
    }

    /*
     * User newUser = new User();
     * System.out.println("Enter CNIC= ");
     * newUser.setCnic(input.nextInt());
     * System.out.println("Enter Name= ");
     * newUser.setName(input.nextLine());
     * System.out.println("Enter Phone Number= ");
     * newUser.setPhone(input.nextLine());
     * System.out.println("Enter Password= ");
     * newUser.setPassword(input.nextLine());
     * System.out.println("Enter Address= ");
     * newUser.setAddress(input.nextLine());
     * app.users.add(newUser);
     * 
     * int id;
     * String name;
     * String phone;
     * String Address;
     * int outletId;
     * 
     * 
     * 
     */

    /*
     * int idR = 0;
     * 
     * Scanner sc = new Scanner(System.in);
     * 
     * System.out.println("\n\n\nEnter CNIC/ID to remove= ");
     * idR = sc.nextInt();
     * 
     * Iterator<Staff> it = app.employees.iterator();
     * 
     * int header = 0;
     * 
     * while (it.hasNext() && header == 0) {
     * 
     * if (it.next().id == idR) {
     * it.next().id = 0;
     * it.next().name = null;
     * it.next().outletId = 0;
     * it.next().Address = null;
     * it.next().phone = null;
     * header = 1;
     * }
     * }
     * if (header == 1) {
     * System.out.println("\n\n\nEmployee deactivated \n\n\n ");
     * }
     * 
     * else {
     * System.out.println("\n\n\nEmployee Out Found \n\n\n ");
     * 
     * }
     */

    private static void AllEmployee() {
        System.out.println("\n\n\nAll Employees: \n\n\n");

        Iterator<Staff> it = app.employees.iterator();

        while (it.hasNext()) {
            System.out.println("\nName: " + it.next().name);
            System.out.println("        Id: " + it.next().id);
        }
    }

    private static void AllJobs() {
        System.out.println("\n\n\nAll Jobs: \n\n\n");

        Iterator<Job> it = app.jobs.iterator();

        while (it.hasNext()) {
            System.out.println("\nID: " + it.next().id);
            System.out.println("    Description: " + it.next().description);
            System.out.println("    Date: " + it.next().scheduleDate);
            System.out.println("    StaffID: " + it.next().staffId);
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

    /*
     * 
     * 
     * int id;
     * String description;
     * int customerId;
     * int staffId;
     * String scheduleDate;
     * int outletId;
     * 
     * public Job(int id,String description,int customerId,int staffId,String
     * scheduleDate,int outletId)
     * {
     * setId(id);
     * setDescription(description);
     * setCustomerId(customerId);
     * setStaffId(staffId);
     * setScheduleDate(scheduleDate);
     * setOutletId(outletId);
     * }
     * 
     */

    private static void AssignTask() {
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

        Job newJob = new Job(id, description, customerId, staffId, scheduleDate, outletId);

        app.jobs.add(newJob);

    }

}
}
