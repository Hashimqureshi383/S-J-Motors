import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class User implements DataDisplay.ViewStatistics
{
    int cnic;
    String name;
    String phone;
    String password;
    String Address;
    int outletId;
    boolean status;

    public User()
    {

    }
    public User(int cnic,String name,String phone,String password,String Address,int outletId,boolean status)
    {
        setCnic(cnic);
        setName(name);
        setPhone(phone);
        setPassword(password);
        setAddress(Address);
        setOutletId(outletId);
    }
    //setters
    public void setCnic(int cnic)
    {
        this.cnic=cnic;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public void setPhone(String phone)
    {
        this.phone=phone;
    }
    public void setPassword(String password)
    {
        this.password=password;
    }
    public void setAddress(String address)
    {
        Address=address;
    }
    public void setOutletId(int outletId)
    {
        this.outletId=outletId;
    }
    public void setStatus(boolean status)
    {
        this.status=status;
    }
    //getters
    public int getCnic()
    {
        return cnic;
    }
    public String getName()
    {
        return name;
    }
    public String getPhone()
    {
        return phone;
    }
    public String getPassword()
    {
        return password;
    }
    public String getAddress()
    {
        return Address;
    }
    public int getOutletId()
    {
        return outletId;
    }
    public boolean getStatus()
    {
        return status;
    }
    public boolean registerToService(int cnic, String name, String phone, String password, String address)
    {
        setCnic(cnic);
        setName(name);
        setPhone(phone);
        setPassword(password);
        setAddress(address);
        return true;
    }
    public boolean loginToServer(int cnic,String password)
    {
        if(cnic!=this.cnic)
            return false;
        else if(password!=this.password)
            return false;
        else
            return true;
    }
    public void enterData()
    {

    }
    public void bookservice()
    {
        String jobDesc;
        Scanner input=new Scanner(System.in);
        jobDesc=input.nextLine();
        FileWriter req;
        try {
            req=new FileWriter("Service Requests.txt",true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            req.write(this.getCnic());
            req.write(" "+jobDesc);
            req.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean payCharges(float payable,float amount)
    {
        if(amount<payable)
            return false;
        else
            return true;
    }
    public void showHistory(Server app)
    {
        int count=0;
        Iterator<Job> it = app.jobs.iterator();
        while (it.hasNext())
        {
            if((it.next().getCustomerId()==this.getCnic())&&(it.next().getStatus()))
            {
                System.out.println(it.next().getDescription()+" Date "+it.next().getScheduleDate()+"\n");
                count++;
            }
        }
        if (count==0)
            System.out.println("You have not taken any Service yet.\n");
    }
    public void showMileage(Server app)
    {
        System.out.flush();
        System.out.println("Your Vehicles and their Mileages:\n\n");
        int count=0;
        Iterator<Vehicle> it=app.vehicles.iterator();
        while (it.hasNext())
        {
            if (it.next().getRegBy()==this.getCnic())
            {
                System.out.println(it.next().getCompany()+"Model No: "+it.next().getModel()+" Mileage: 100 units\n");
                count++;
            }
        }
        if(count==0)
            System.out.println("You do not own any Vehicle");
    }
    public void showCostSpent(Server app)
    {
        int count=0;
        float cost=0;
        Iterator<Job> it=app.jobs.iterator();
        while (it.hasNext())
        {
            if((it.next().getCustomerId()==this.getCnic())&&(it.next().getStatus()))
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
}
