import java.sql.*;
import java.util.Vector;

    public class Server
{
    Connection con;
    int noofManagers;
    int noofUsers;
    int noofJobs;
    int noofEmployees;
    int noofAttendances;
    int noofVehicles;
    int noofOutlets;
    int noofInventories;
    public Vector<Manager> managers;
    public Vector<User> users;
    public Vector<Job> jobs;
    public Vector<Staff> employees;
    public Vector<Attendance> records;
    public Vector<Vehicle> vehicles;
    public Vector<Outlet> outlets;
    public Vector<Inventory> inventories;

    //getters
    public Server()
    {
        noofManagers=noofAttendances=noofEmployees=noofInventories=noofJobs=noofOutlets=noofUsers=noofVehicles=0;
        //con=connectDB();
        //loadManagers(con);
        //loadUsers(con);
        //loadJobs(con);
        //loadEmployees(con);
        //loadAttendance(con);
        //loadVehicles(con);
        //loadOutlets(con);
        //loadInventories(con);
    }
    public int getManagers()
    {
        return noofManagers;
    }
    public int getUsers()
    {
        return noofUsers;
    }
    public int getJobs()
    {
        return noofJobs;
    }
    public int getEmployees()
    {
        return noofEmployees;
    }
    public int getAttendances()
    {
        return noofAttendances;
    }
    public int getVehicles()
    {
        return noofVehicles;
    }
    public int getOutlets()
    {
        return noofOutlets;
    }
    public int getInventories()
    {
        return noofInventories;
    }
    //Below is the code to connect the database with program ro retrieve and store data into it.

   /* public Connection connectDB()
    {
        //Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/sjmotors", "root", "12345678");
    }
    public void loadManagers(Connection con)
    {
        Statement load=con.createStatement();
        ResultSet data=load.executeQuery("select * from manager");
        if(data.wasNull())
            return;
        else
        {
            while(data.next())
                noofManagers++;
            data=load.executeQuery("select * from manager");
            managers=new Vector<Manager>(noofManagers);
            while(data.next())
            {
                Manager temp=Manager.addManager(data.getInt(1),data.getString(2),data.getString(3),data.getString(4),data.getString(5),data.getInt(6),data.getString(7));
                managers.add(temp);
            }
        }
    }
    public void loadUsers(Connection con)
    {
        Statement load=con.createStatement();
        ResultSet data=load.executeQuery("select * from user");
        if(data.wasNull())
            return;
        else
        {
            while(data.next())
                noofUsers++;
            data=load.executeQuery("select * from user");
            users=new Vector<User>(noofUsers);
            while(data.next())
            {
                User temp=new User(data.getInt(1),data.getString(2),data.getString(3),data.getString(4),data.getString(5),data.getInt(6));
                users.add(temp);
            }
        }
    }
    public void loadJobs(Connection con)
    {
        Statement load=con.createStatement();
        ResultSet data=load.executeQuery("select * from job");
        if(data.wasNull())
            return;
        else
        {
            while(data.next())
                noofJobs++;
            data=load.executeQuery("select * from job");
            jobs=new Vector<Job>(noofJobs);
            while(data.next())
            {
                Job temp=new Job(data.getInt(1),data.getString(2),data.getInt(3),data.getInt(4),data.getString(5),data.getInt(6));
                jobs.add(temp);
            }
        }
    }
    public void loadEmployees(Connection con)
    {
        Statement load=con.createStatement();
        ResultSet data=load.executeQuery("select * from staff");
        if(data.wasNull())
            return;
        else
        {
            while(data.next())
                noofEmployees++;
            data=load.executeQuery("select * from staff");
            employees=new Vector<Staff>(noofEmployees);
            while(data.next())
            {
                Staff temp=new Staff(data.getInt(1),data.getString(2),data.getString(3),data.getString(4),data.getInt(5));
                employees.add(temp);
            }
        }
    }
    public void loadAttendance(Connection con)
    {
        Statement load=con.createStatement();
        ResultSet data=load.executeQuery("select * from attendancerecord");
        if(data.wasNull())
            return;
        else
        {
            while(data.next())
                noofAttendances++;
            data=load.executeQuery("select * from attendancerecord");
            records=new Vector<Attendance>(noofAttendances);
            while(data.next())
            {
                Attendance temp=new Attendance(data.getString(1),data.getBoolean(2),data.getInt(3));
                records.add(temp);
            }
        }
    }
    public void loadVehicles(Connection con)
    {
        Statement load=con.createStatement();
        ResultSet data=load.executeQuery("select * from vehicle");
        if(data.wasNull())
            return;
        else
        {
            while(data.next())
                noofVehicles++;
            data=load.executeQuery("select * from vehicle");
            vehicles=new Vector<Vehicle>(noofVehicles);
            while(data.next())
            {
                Vehicle temp=new Vehicle(data.getInt(1),data.getString(2),data.getString(3),data.getString(4),data.getInt(5));
                vehicles.add(temp);
            }
        }
    }
    public void loadOutlets(Connection con)
    {
        Statement load=con.createStatement();
        ResultSet data=load.executeQuery("select * from outlet");
        if(data.wasNull())
            return;
        else
        {
            while(data.next())
                noofOutlets++;
            data=load.executeQuery("select * from outlet");
            outlets=new Vector<Outlet>(noofOutlets);
            while(data.next())
            {
                Outlet temp=new Outlet(data.getInt(1),data.getString(2), data.getInt(3));
                outlets.add(temp);
            }
        }
    }
    public void loadInventories(Connection con)
    {
        Statement load=con.createStatement();
        ResultSet data=load.executeQuery("select * from inventory");
        if(data.wasNull())
            return;
        else
        {
            while(data.next())
                noofInventories++;
            data=load.executeQuery("select * from inventory");
            inventories=new Vector<Inventory>(noofInventories);
            while(data.next())
            {
                Inventory temp=new Inventory(data.getInt(1),data.getString(2),data.getString(3),data.getString(4),data.getString(5),data.getInt(6));
                inventories.add(temp);
            }
        }
    }
*/
}