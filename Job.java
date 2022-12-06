    public class Job
{
    int id;
    String description;
    int customerId;
    int staffId;
    String scheduleDate;
    int outletId;
    boolean status;

    public Job(int id,String description,int customerId,int staffId,String scheduleDate,int outletId,boolean status)
    {
        setId(id);
        setDescription(description);
        setCustomerId(customerId);
        setStaffId(staffId);
        setScheduleDate(scheduleDate);
        setOutletId(outletId);
        setStatus(status);
    }
    //setters
    public void setId(int id)
    {
        this.id=id;
    }
    public void setDescription(String description)
    {
        this.description=description;
    }
    public void setCustomerId(int customerId)
    {
        this.customerId=customerId;
    }
    public void setStaffId(int staffId)
    {
        this.staffId=staffId;
    }
    public void setScheduleDate(String scheduleDate)
    {
        this.scheduleDate=scheduleDate;
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
    public int getId()
    {
        return id;
    }
    public String getDescription()
    {
        return description;
    }
    public int getCustomerId()
    {
        return customerId;
    }
    public int getStaffId()
    {
        return staffId;
    }
    public String getScheduleDate()
    {
        return scheduleDate;
    }
    public int getOutletId()
    {
        return outletId;
    }
    public boolean getStatus()
    {
        return status;
    }
}
