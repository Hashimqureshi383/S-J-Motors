import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class DataDisplay
{
    public static int printRequests()
    {
        int count=0;
        File readreq;
        readreq=new File("Service Requests.txt");
        if(!readreq.exists())
            return 0;
        Scanner readf;
        try {
            readf=new Scanner(readreq);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while(readf.hasNext())
        {
            System.out.println("\nUser Id: "+readf.nextInt()+" Description: "+readf.next()+"\n");
            count++;
        }
        return count;
    }
    public static void AllEmployee(Server app)
    {
        System.out.println("\n\n\nAll Employees: \n\n\n");
        Iterator<Staff> it = app.employees.iterator();
        Staff temps;
        while (it.hasNext())
        {
            temps=it.next();
            System.out.println("\nName: " + temps.getName());
            System.out.println(" Id: " + temps.getId());
        }
    }
    public static void AllUsers(Server app)
    {
        System.out.println("\n\n\nAll Employees: \n\n\n");
        Iterator<User> it = app.users.iterator();
        User tempu;
        while (it.hasNext())
        {
            tempu=it.next();
            System.out.println("\nCNIC: " + tempu.getCnic());
            System.out.println(" Name: " + tempu.getName());
        }
    }
    public static void AllJobs(Server app)
    {
        System.out.println("\n\n\nAll Jobs: \n\n\n");

        Iterator<Job> it = app.jobs.iterator();
        Job tempu;
        while (it.hasNext())
        {
            tempu=it.next();
            System.out.println("\nID: " + tempu.getId());
            System.out.println(" Description: " + tempu.getDescription());
            System.out.println(" Date: " + tempu.getScheduleDate());
            System.out.println(" StaffID: " + tempu.getStaffId());
            System.out.println(" CustomerID: "+ tempu.getCustomerId());
        }

    }
    public interface ViewReport
    {
        public void runReport(Staff member);
    }
        public interface ViewInventory
    {
        public void showConsumptionTrend();
        public void showInventoryLevel(Inventory product);
    }
        public interface ViewStatistics
    {
        public void showHistory(Server app);
        public void showMileage(Server app);
        public void showCostSpent(Server app);
    }
}
