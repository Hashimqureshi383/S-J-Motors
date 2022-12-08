import java.io.File;
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
        Iterator<Staff> it=app.employees.iterator();
        Staff temps;
        while(it.hasNext())
        {
            temps=it.next();
            if(temps.getId()==id)
            {
                System.out.println("Staff already exists.\n");
                return;
            }
        }
        System.out.println("Enter Name= ");
        name = sc.next(); // reads string.
        System.out.println("Enter Phone Number= ");
        phone = sc.next(); // reads string.
        System.out.println("Enter Address= ");
        Address = sc.next(); // reads string.
        Staff newEmployee = new Staff(id, name, phone, Address, this.outletId);
        app.employees.add(newEmployee);
        app.noofEmployees++;
        FileWriter insertq;
        try {
            insertq=new FileWriter("DDL Queries.txt",true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            insertq.write("Insert into staff (id,name,phone,address,outletid) values ("+Integer.toString(id)+",\'"+name+"\',\'"+phone+"\',\'"+Address+"\',"+Integer.toString(this.outletId)+")\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            insertq.close();
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
        Staff temps=null;
        while (it.hasNext() && header == 0)
        {
            temps=it.next();
            if (temps.getId()==idR)
            {
                app.employees.remove(temps);
                app.noofEmployees--;
                header = 1;
            }
        }
        if (header == 1)
        {
            System.out.println("\n\n\nEmployee:"+Integer.toString(temps.getId())+" deactivated \n\n\n ");
            FileWriter deleteq;
            try {
                deleteq=new FileWriter("DDL Queries.txt",true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                deleteq.write("Delete from staff where id="+Integer.toString(temps.getId())+"\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                deleteq.close();
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
        int jobId=0;
        int staffId = 0;
        int outletId = 0;
        boolean flag=false;
        DataDisplay.AllEmployee(app);
        DataDisplay.AllJobs(app);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Job ID= ");
        jobId=sc.nextInt();
        Iterator<Job> it3=app.jobs.iterator();
        Job tempj=null;
        while(it3.hasNext())
        {
            tempj=it3.next();
            if(tempj.getId()==jobId)
            {
                flag = true;
                break;
            }
        }
        if(!flag)
        {
            System.out.println("No such Job Exists.\n");
            return;
        }
        System.out.println("Enter Staff ID= ");
        staffId=sc.nextInt();
        Iterator<Staff> it=app.employees.iterator();
        Staff temps;
        while(it.hasNext())
        {
            temps=it.next();
            if(temps.getId()==staffId)
            {
                System.out.println("Enter Outlet ID= ");
                outletId = sc.nextInt();
                Iterator<Outlet> it2=app.outlets.iterator();
                Outlet tempo;
                while(it2.hasNext())
                {
                    tempo=it2.next();
                    if(tempo.getId()==outletId)
                    {
                        tempj.setStaffId(staffId);
                        tempj.setOutletId(outletId);
                        FileWriter updateq;
                        try {
                            updateq=new FileWriter("DDL Queries.txt",true);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            updateq.write("Update table job set outletid="+Integer.toString(outletId)+",staffid="+Integer.toString(staffId)+" where id="+Integer.toString(jobId)+"\n");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("\n\n User transferred. Press any key...\n");
                        flag=true;
                    }
                }
            }
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
        User tempu;
        while (it.hasNext())
        {
            tempu=it.next();
            if(tempu.getCnic()==cnic)
            {
                tempu.setStatus(true);
                FileWriter updateq;
                try {
                    updateq=new FileWriter("DDL Queries.txt",true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    updateq.write("Update table user set status=1 where cnic="+Integer.toString(tempu.getCnic())+"\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("\n\n User activated. Press any key...\n");
                flag=true;
            }
        }
        if(!flag)
            System.out.println("\nUser Not Found.\n");
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
        User tempu;
        Outlet tempo;
        while (it.hasNext())
        {
            tempu=it.next();
            if(tempu.getCnic()==cnic)
            {
                System.out.println("\n\nEnter New Outlet ID= ");
                id = option.nextInt();
                Iterator<Outlet> it2=app.outlets.iterator();
                while(it2.hasNext())
                {
                    tempo=it2.next();
                    if(tempo.getId()==id)
                    {
                        tempu.setOutletId(id);
                        FileWriter updateq;
                        try {
                            updateq=new FileWriter("DDL Queries.txt",true);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            updateq.write("Update table user set outletid="+Integer.toString(tempo.getId())+" where cnic="+Integer.toString(tempu.getCnic())+"\n");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("\n\n User transferred. Press any key...\n");
                        flag=true;
                    }
                }
            }
        }
        if(!flag)
            System.out.println("User or Staff not found.");
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
        User tempu;
        while (it.hasNext())
        {
            tempu=it.next();
            if(tempu.getCnic()==cnic)
            {
                tempu.setStatus(false);
                FileWriter updateq;
                try {
                    updateq=new FileWriter("DDL Queries.txt",true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    updateq.write("Update table user set status=0 where cnic="+Integer.toString(tempu.getCnic())+"\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("\n\n User deactivated. Press any key...\n");
                flag=true;
            }
        }
        if(!flag)
            System.out.println("\nUser Not Found.\n");
    }
}
