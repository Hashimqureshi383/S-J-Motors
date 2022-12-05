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
