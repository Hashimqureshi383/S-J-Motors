    public class Staff
{
    int id;
    String name;
    String phone;
    String Address;
    int outletId;

    public Staff(int id,String name,String phone,String Address,int outletId)
    {
        setId(id);
        setName(name);
        setPhone(phone);
        setAddress(Address);
        setOutletId(outletId);
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
    public void setAddress(String address)
    {
        Address=address;
    }
    public void setOutletId(int outletId)
    {
        this.outletId=outletId;
    }

    //getters
    public int getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public String getPhone()
    {
        return phone;
    }
    public String getAddress()
    {
        return Address;
    }
    public int getOutletId()
    {
        return outletId;
    }
    public boolean checkIn()
    {
        return true;
    }
    public boolean checkOut()
    {
        return true;
    }
}
