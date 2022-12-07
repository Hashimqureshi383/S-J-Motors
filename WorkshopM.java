import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class WorkshopM extends Manager implements DataDisplay.ViewReport
{
    public WorkshopM(int id,String name,String phone,String password,String address,int outletId,String type)
    {
        super(id,name,phone,password,address,outletId,type);
    }
    public WorkshopM(Manager obj)
    {
        super(obj);
    }
    public void runReport(Staff member)
    {

    }
    public void bookService(Server app)
    {
        boolean flag=false;
        int cnic=0;
        Scanner input=new Scanner(System.in);
        System.out.println("\nFollowing are the request made by Users: \n");
        int noOfReq=DataDisplay.printRequests();
        if(noOfReq>0)
        {
            int[] ids=new int[noOfReq];
            String[] desc=new String[noOfReq];
            File readreq;
            readreq=new File("Service Requests.txt");
            Scanner readf;
            try {
                readf=new Scanner(readreq);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            for(int index=0;readf.hasNext();index++)
            {
                ids[index]=readf.nextInt();
                desc[index]=readf.next();
            }
            System.out.println("\nEnter CNIC of user: ");
            cnic=input.nextInt();
            for(int index=0;index<noOfReq;index++)
            {
                if(cnic==ids[index])
                {
                    long millis = System.currentTimeMillis();
                    java.sql.Date date = new java.sql.Date(millis);
                    Job newJob=new Job(app.getJobs(),desc[index],cnic,0,date.toString(),0,false);
                    app.jobs.add(newJob);
                    app.noofJobs++;
                    FileWriter insertq;
                    try {
                        insertq=new FileWriter("DDL Queries.txt",true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        insertq.write("Insert into job (id,description,customer,staffid,scheduledate,outletid,status) values ("+Integer.toString(app.getJobs())+",\'"+desc[index]+"\',"+Integer.toString(ids[index])+",0,\'"+date.toString()+"\',0,0)\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        insertq.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    ids[index]=0;
                    desc[index]="";
                    readreq.delete();
                    FileWriter rewrite;
                    try {
                        rewrite=new FileWriter("Service Requests.txt",true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    for(int index2=0;index2<noOfReq;index2++)
                    {
                        if(ids[index2]!=0)
                        {
                            try {
                                rewrite.write(ids[index]);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                rewrite.write(" ");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                rewrite.write(desc[index]+"\n");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    try {
                        rewrite.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    flag=true;
                }
            }
            if(!flag)
                System.out.println("No such Request exists.\n");
            readf.close();
        }
        else
            System.out.println("There are no requests currently.\n");
    }
    public void manageJobSchedule()
    {

    }
    public void predictJobLoad()
    {

    }
}
