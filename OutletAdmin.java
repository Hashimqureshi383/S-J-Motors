import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class OutletAdmin extends Manager
{
    public OutletAdmin(int id,String name,String phone,String password,String address,int outletId,String type)
    {
        super(id,name,phone,password,address,outletId,type);
    }
    public OutletAdmin(Manager obj)
    {
        super(obj);
    }
    public void createOutlet()
    {

    }
    public void updateOutlet()
    {

    }
    public void blockOutlet()
    {

    }
    public void deleteOutlet()
    {

    }
    public void addEmployee(Server app)
    {
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
            insertq=new FileWriter("DDL Queries.txt",true);
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

    public void removeEmployee(Server app)
    {
        int idR = 0;

        Scanner sc = new Scanner(System.in);
        DataDisplay.AllEmployee(app);
        System.out.println("\n\n\nEnter ID to remove= ");
        idR = sc.nextInt();
        Iterator<Staff> it = app.employees.iterator();
        int header = 0;
        Staff temp=null;
        while (it.hasNext() && header == 0)
        {
            if (it.next().getId() == idR)
            {
                temp=it.next();
                app.employees.remove(temp);
                header = 1;
            }
        }
        if (header == 1)
        {
            System.out.println("\n\n\nEmployee:"+Integer.toString(temp.getId())+" deactivated \n\n\n ");
            FileWriter deleteq;
            try {
                deleteq=new FileWriter("DDL Queries.txt",true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                deleteq.write("Delete from staff where id="+Integer.toString(temp.getId()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else
        {
            System.out.println("\n\n\nEmployee Not Found \n\n\n ");
        }
    }
    public void AssignTask(Server app)
    {
        System.out.println("\n\n\nAssigning task\n\n\n");
        int id = 0;
        String description = null;
        int customerId = 0;
        int staffId = 0;
        String scheduleDate = null;
        int outletId = 0;
        DataDisplay.AllEmployee(app);
        DataDisplay.AllJobs(app);
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\n\nEnter Job ID= ");
        id = sc.nextInt();
        System.out.println("Enter Description= ");
        description = sc.nextLine(); // reads string.
        System.out.println("Enter customer ID= ");
        customerId = sc.nextInt(); // reads string// .
        System.out.println("Enter Staff ID= ");
        staffId=sc.nextInt();
        System.out.println("Enter scheduleDate= ");
        scheduleDate = sc.nextLine(); // reads string.
        System.out.println("Enter Outlet ID= ");
        outletId = sc.nextInt();
        Job newJob = new Job(id, description, customerId, staffId, scheduleDate, outletId,false);
        app.jobs.add(newJob);
        FileWriter insertq;
        try {
            insertq=new FileWriter("DDL Queries.txt",true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            insertq.write("Insert into job (id,description,customer,staffid,scheduledate,outletid,status) values ("+Integer.toString(id)+",\'"+description+"\',"+Integer.toString(customerId)+","+Integer.toString(staffId)+",\'"+scheduleDate+"\',"+Integer.toString(outletId)+",0");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void activateUser(Server app)
    {
        int cnic=0;
        boolean flag=false;
        Scanner option=new Scanner(System.in);
        DataDisplay.AllUsers(app);
        System.out.println("Enter CNIC= ");
        cnic=option.nextInt();
        Iterator<User> it = app.users.iterator();
        while (it.hasNext())
        {
            if(it.next().getCnic()==cnic)
            {
                it.next().setStatus(true);
                System.out.println("\n\n User activated. Press any key...\n");
                flag=true;
            }
        }
        if(!flag)
            System.out.println("\nUser Not Found. Press any key...\n");
        option.nextInt();
    }
    public void transferUser(Server app)
    {
        boolean flag=false;
        int id=0;
        int cnic=0;
        Scanner option=new Scanner(System.in);
        System.out.println("Enter CNIC= ");
        cnic=option.nextInt();
        Iterator<User> it = app.users.iterator();
        while (it.hasNext())
        {
            if(it.next().getCnic()==cnic)
            {
                System.out.println("\n\nEnter New Outlet ID > ");
                id = option.nextInt();
                Iterator<Outlet> it2=app.outlets.iterator();
                while(it2.hasNext())
                {
                    if(it2.next().getId()==id)
                    {
                        it.next().setOutletId(id);
                        System.out.println("\n\n User transferred. Press any key...\n");
                        flag=true;
                    }
                }
            }
        }
        if(!flag)
            System.out.println("User or Staff not found. Press any key...");
        option.nextInt();
    }
    public void deactivateUser(Server app)
    {
        int cnic=0;
        boolean flag=false;
        Scanner option=new Scanner(System.in);
        DataDisplay.AllUsers(app);
        System.out.println("Enter CNIC= ");
        cnic=option.nextInt();
        Iterator<User> it = app.users.iterator();
        while (it.hasNext())
        {
            if(it.next().getCnic()==cnic)
            {
                it.next().setStatus(false);
                System.out.println("\n\n User deactivated. Press any key...\n");
                flag=true;
            }
        }
        if(!flag)
            System.out.println("\nUser Not Found. Press any key...\n");
        option.nextInt();
    }
}
