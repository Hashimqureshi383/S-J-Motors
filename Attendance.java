
  public class Attendance
{
    String date;
    boolean mark;
    int memberId;

    public Attendance(String date,boolean mark,int memberId)
    {
        setDate(date);
        setMark(mark);
        setMemberId(memberId);
    }

    //constructer to add current date automatically
    public Attendance(boolean mark,int memberId)
    {
        setDate(getCurrentDate());
        setMark(mark);
        setMemberId(memberId);
    }
    //setters

    public void setDate(String date)
    {
        this.date=date;
    }
    public void setMark(boolean mark)
    {
        this.mark=mark;
    }
    public void setMemberId(int memberId)
    {
        this.memberId=memberId;
    }

    //getters

    //This function returns the current date and time to be added to the attendance of the employee
    public String getCurrentDate()
    {
        return java.time.LocalDate.now().toString();  
    }
    public String getDate()
    {
        return date;
    }
    public boolean isMark()
    {
        return mark;
    }
    public int getMemberId()
    {
        return memberId;
    }
}
