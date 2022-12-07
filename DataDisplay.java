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
        while (it.hasNext()) {
            System.out.println("\nName: " + it.next().getName());
            System.out.println(" Id: " + it.next().getId());
        }
    }
    public static void AllUsers(Server app)
    {
        System.out.println("\n\n\nAll Employees: \n\n\n");
        Iterator<User> it = app.users.iterator();
        while (it.hasNext()) {
            System.out.println("\nCNIC: " + it.next().getCnic());
            System.out.println(" Name: " + it.next().getName());
        }
    }
    public static void AllJobs(Server app)
    {
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
