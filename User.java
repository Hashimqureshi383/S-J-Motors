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
    public boolean registerToService(Server app)
    {
        System.out.flush();
        Scanner input=new Scanner(System.in);
        System.out.println("Enter CNIC= ");
        setCnic(input.nextInt());
        Iterator<User> it=app.users.iterator();
        User tempu;
        while(it.hasNext())
        {
            tempu=it.next();
            if(tempu.getCnic()==getCnic())
            {
                System.out.println("The User with this CNIC already exists. Press any key...\n");
                return false;
            }
        }
        System.out.println("Enter Name= ");
        setName(input.next());
        System.out.println("Enter Phone Number= ");
        setPhone(input.next());
        System.out.println("Enter Password= ");
        setPassword(input.next());
        System.out.println("Enter Address= ");
        setAddress(input.next());
        return true;
    }
    public boolean loginToServer(Server app)
    {
        System.out.flush();
        System.out.println("Enter CNIC= ");
        Scanner input=new Scanner(System.in);
        setCnic(input.nextInt());
        Iterator<User> it=app.users.iterator();
        User tempu;
        while(it.hasNext())
        {
            tempu=it.next();
            if(tempu.getCnic()==getCnic())
            {
                System.out.println("Enter Password= ");
                setPassword(input.next());
                if(getPassword().equals(tempu.getPassword()))
                    return true;
                else
                {
                    System.out.println("Wrong Password. Press any key...");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return false;
                }
            }
        }
        System.out.println("Incorrect CNIC. ");
        return false;
    }
    public void enterData()
    {

    }
    public void bookservice()
    {
        System.out.flush();
        String jobDesc;
        Scanner input=new Scanner(System.in);
        System.out.println("Enter Service Description: ");
        jobDesc=input.next();
        FileWriter req;
        try {
            req=new FileWriter("Service Requests.txt",true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            req.write(Integer.toString(this.getCnic()));
            req.write(" "+jobDesc);
            req.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
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
        System.out.flush();
        int count=0;
        Iterator<Job> it = app.jobs.iterator();
        Job tempj;
        while (it.hasNext())
        {
            tempj=it.next();
            if((tempj.getCustomerId()==this.getCnic())&&(tempj.getStatus()))
            {
                System.out.println(tempj.getDescription()+" Date "+tempj.getScheduleDate()+"\n");
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
        Vehicle tempv=null;
        while (it.hasNext())
        {
            tempv=it.next();
            if (tempv.getRegBy()==this.getCnic())
            {
                System.out.println(tempv.getCompany()+"Model No: "+tempv.getModel()+" Mileage: 100 units\n");
                count++;
            }
        }
        if(count==0)
            System.out.println("You do not own any Vehicle");
    }
    public void showCostSpent(Server app)
    {
        System.out.flush();
        int count=0;
        float cost=0;
        Iterator<Job> it=app.jobs.iterator();
        Job tempj;
        while (it.hasNext())
        {
            tempj=it.next();
            if((tempj.getCustomerId()==this.getCnic())&&(tempj.getStatus()))
            {
                System.out.println(tempj.getDescription()+" Date "+tempj.getScheduleDate()+" Costed: 1000 PKR\n");
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
