import javax.swing.plaf.nimbus.State;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.sql.*;
import java.sql.DriverManager;
import java.util.Scanner;
import java.util.Vector;

    public class Server
{
    Connection con;
    public int noofManagers;
    public int noofUsers;
    public int noofJobs;
    public int noofEmployees;
    public int noofAttendances;
    public int noofVehicles;
    public int noofOutlets;
    public int noofInventories;
    public Vector<Manager> managers;
    public Vector<User> users;
    public Vector<Job> jobs;
    public Vector<Staff> employees;
    public Vector<Attendance> records;
    public Vector<Vehicle> vehicles;
    public Vector<Outlet> outlets;
    public Vector<Inventory> inventories;

    static int count;
    //getters
    public static Server createServer()
    {
        if(count==0)
        {
            count++;
            return new Server();
        }
        else
            return null;

    }
    private Server()
    {
        noofManagers=noofAttendances=noofEmployees=noofInventories=noofJobs=noofOutlets=noofUsers=noofVehicles=0;
        con=connectDB();
        loadManagers(con);
        loadUsers(con);
        loadJobs(con);
        loadEmployees(con);
        loadAttendance(con);
        loadVehicles(con);
        loadOutlets(con);
        loadInventories(con);
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

    public Connection connectDB()
    {
//      try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//       }
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/sjmotors", "root", "12345678");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadManagers(Connection con)
    {
        Statement load;
        try {
            load = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet data;
        try {
            data = load.executeQuery("select * from manager");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            //if(data.wasNull())
            //    return;
            //else
            //{
                while(data.next())
                    noofManagers++;
                data=load.executeQuery("select * from manager");
                managers=new Vector<Manager>(noofManagers);
                while(data.next())
                {
                    Manager temp=Manager.addManager(data.getInt(1),data.getString(2),data.getString(3),data.getString(4),data.getString(5),data.getInt(6),data.getString(7));
                    managers.add(temp);
                }
            //}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadUsers(Connection con)
    {
        Statement load= null;
        try {
            load = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet data= null;
        try {
            data = load.executeQuery("select * from user");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            //if(data.wasNull())
            //    return;
            //else
            //{
                while(data.next())
                    noofUsers++;
                data=load.executeQuery("select * from user");
                users=new Vector<User>(noofUsers);
                while(data.next())
                {
                    User temp=new User(data.getInt(1),data.getString(2),data.getString(3),data.getString(4),data.getString(5),data.getInt(6), data.getBoolean(7));
                    users.add(temp);
                }
            //}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadJobs(Connection con)
    {
        Statement load= null;
        try {
            load = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet data= null;
        try {
            data = load.executeQuery("select * from job");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            //if(data.wasNull())
            //    return;
            //else
            //{
                while(data.next())
                    noofJobs++;
                data=load.executeQuery("select * from job");
                jobs=new Vector<Job>(noofJobs);
                while(data.next())
                {
                    Job temp=new Job(data.getInt(1),data.getString(2),data.getInt(3),data.getInt(4),data.getString(5),data.getInt(6),data.getBoolean(7));
                    jobs.add(temp);
                }
            //}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadEmployees(Connection con)
    {
        Statement load= null;
        try {
            load = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet data= null;
        try {
            data = load.executeQuery("select * from staff");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            //if(data.wasNull())
              //  return;
            //else
            //{
                while(data.next())
                    noofEmployees++;
                data=load.executeQuery("select * from staff");
                employees=new Vector<Staff>(noofEmployees);
                while(data.next())
                {
                    Staff temp=new Staff(data.getInt(1),data.getString(2),data.getString(3),data.getString(4),data.getInt(5));
                    employees.add(temp);
                }
            //}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadAttendance(Connection con)
    {
        Statement load= null;
        try {
            load = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet data= null;
        try {
            data = load.executeQuery("select * from attendancerecord");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
          //  if(data.wasNull())
            //    return;
            //else
            //{
                while(data.next())
                    noofAttendances++;
                data=load.executeQuery("select * from attendancerecord");
                records=new Vector<Attendance>(noofAttendances);
                while(data.next())
                {
                    Attendance temp=new Attendance(data.getString(1),data.getBoolean(2),data.getInt(3));
                    records.add(temp);
                }
            //}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadVehicles(Connection con)
    {
        Statement load= null;
        try {
            load = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet data= null;
        try {
            data = load.executeQuery("select * from vehicle");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
           // if(data.wasNull())
            //    return;
            //else
            //{
                while(data.next())
                    noofVehicles++;
                data=load.executeQuery("select * from vehicle");
                vehicles=new Vector<Vehicle>(noofVehicles);
                while(data.next())
                {
                    Vehicle temp=new Vehicle(data.getInt(1),data.getString(2),data.getString(3),data.getString(4),data.getInt(5));
                    vehicles.add(temp);
                }
            //}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadOutlets(Connection con)
    {
        Statement load= null;
        try {
            load = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet data= null;
        try {
            data = load.executeQuery("select * from outlet");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
           // if(data.wasNull())
           //     return;
            //else
            //{
                while(data.next())
                    noofOutlets++;
                data=load.executeQuery("select * from outlet");
                outlets=new Vector<Outlet>(noofOutlets);
                while(data.next())
                {
                    Outlet temp=new Outlet(data.getInt(1),data.getString(2), data.getInt(3));
                    outlets.add(temp);
                }
            //}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadInventories(Connection con)
    {
        Statement load= null;
        try {
            load = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet data= null;
        try {
            data = load.executeQuery("select * from inventory");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
           // if(data.wasNull())
           //     return;
           // else
            //{
                while(data.next())
                    noofInventories++;
                data=load.executeQuery("select * from inventory");
                inventories=new Vector<Inventory>(noofInventories);
                while(data.next())
                {
                    Inventory temp=new Inventory(data.getInt(1),data.getString(2),data.getString(3),data.getString(4),data.getString(5),data.getInt(6));
                    inventories.add(temp);
                }
            //}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadDB()
    {
        File queryFile=new File("DDL Queries.txt");
        Scanner extract;
        try {
            extract=new Scanner(queryFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Statement query;
        try {
            query=con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while(extract.hasNext())
        {
            try {
                query.executeUpdate(extract.nextLine());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        queryFile.deleteOnExit();
    }
}