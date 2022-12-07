import java.util.Iterator;
import java.util.Scanner;

    public class Manager
{
    int id;
    String name;
    String phone;
    String password;
    String address;
    int outletId;
    String type;

    public Manager()
    {

    }
    public Manager(int id,String name,String phone,String password,String address,int outletId,String type)
    {
        setId(id);
        setName(name);
        setPhone(phone);
        setPassword(password);
        setAddress(address);
        setOutletId(outletId);
        setType(type);
    }
    public Manager(final Manager obj)
    {
        setId(obj.getId());
        setName(obj.getName());
        setPhone(obj.getPhone());
        setPassword(obj.getPassword());
        setAddress(obj.getAddress());
        setOutletId(obj.getOutletId());
        setType(obj.getType());
    }
    //setters
    public void setId(int id)
    {
        this.id=id;
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
        this.address=address;
    }
    public void setOutletId(int outletId)
    {
        this.outletId=outletId;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    //getters
    public int getId()
    {
        return id;
    }
    public String getAddress()
    {
        return address;
    }
    public String getName()
    {
        return name;
    }
    public String getPassword()
    {
        return password;
    }
    public String getPhone()
    {
        return phone;
    }
    public int getOutletId()
    {
        return outletId;
    }
    public String getType()
    {
        return type;
    }
    public static Manager addManager(int id,String name,String phone,String password,String address,int outletId,String type)
    {
        if(type=="Floor")
            return new FloorM(id,name,phone,password,address,outletId,type);
        else if(type=="Inventory")
            return new InventoryM(id,name,phone,password,address,outletId,type);
        else if(type=="Workshop")
            return new WorkshopM(id,name,phone,password,address,outletId,type);
        else
            return new OutletAdmin(id,name,phone,password,address,outletId,type);
    }
    public int login(Server app)
    {
        System.out.flush();
        System.out.println("Enter id= ");
        Scanner input=new Scanner(System.in);
        setId(input.nextInt());
        Iterator<Manager> it=app.managers.iterator();
        Manager tempm;
        while(it.hasNext())
        {
            tempm=it.next();
            if(tempm.getId()==getId())
            {
                System.out.println("Enter Password= ");
                setPassword(input.next());
                if(getPassword().equals(tempm.getPassword()))
                {
                    if(tempm.getType()=="Outlet")
                        return 2;
                    else
                        return 1;
                }
                else
                {
                    System.out.println("Wrong Password. Press Any key...");
                    input.nextInt();
                    return 0;
                }
            }
        }
        System.out.println("Incorrect ID. Press Any key...\n");
        input.nextInt();
        return 0;
    }
}
