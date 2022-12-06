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
    public boolean payCharges(float payable,float amount)
    {
        if(amount<payable)
            return false;
        else
            return true;
    }
    public void showHistory()
    {

    }
    public void showMileage()
    {

    }
    public void showCostSpent()
    {

    }
}
