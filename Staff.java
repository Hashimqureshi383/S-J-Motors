import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

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
    public static boolean checkIn(boolean marked,Server app)
    {
        Scanner input_staff=new Scanner(System.in);
        if(marked)
        {
            System.out.println("Your Attendance is Already Marked. Press Any key to continue...");
            input_staff.nextInt();
            return false;
        }
        else
        {
            int id=input_staff.nextInt();
            Iterator<Staff> it=app.employees.iterator();
            while(it.hasNext())
            {
                if(it.next().getId()==id)
                {
                    System.out.println("Attendance Marked Successfully");
                    marked=true;
                    continue;
                }
            }
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            Attendance obj=new Attendance(date.toString(), true, id);
            app.records.add(obj);
            FileWriter insertq;
            try {
                insertq=new FileWriter("DDL Queries.txt",true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                insertq.write("Insert into attendancerecord (date,attendance,memberid) values (\'"+obj.getDate()+"\',1,"+Integer.toString(id));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
    }
    public boolean checkOut()
    {
        return true;
    }
}
