    public class Job
{
    int id;
    String description;
    int customerId;
    int staffId;
    String scheduleDate;
    int outletId;

    public Job(int id,String description,int customerId,int staffId,String scheduleDate,int outletId)
    {
        setId(id);
        setDescription(description);
        setCustomerId(customerId);
        setStaffId(staffId);
        setScheduleDate(scheduleDate);
        setOutletId(outletId);
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
}
