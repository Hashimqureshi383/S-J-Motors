    public class Outlet
{
    int id;
    String location;
    int adminId;

    public Outlet(int id,String location,int adminId)
    {
        setId(id);
        setLocation(location);
        setAdminId(adminId);
    }
    //setters
    public void setId(int id)
    {
        this.id=id;
    }
    public void setLocation(String location)
    {
        location=location;
    }
    public void setAdminId(int adminId)
    {
        adminId=adminId;
    }

    //getters
    public int getId()
    {
        return id;
    }
    public String getLocation()
    {
        return location;
    }
    public int getAdminId()
    {
        return adminId;
    }
}
