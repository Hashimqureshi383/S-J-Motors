    public class Vehicle
{
    int regNo;
    String company;
    String color;
    String model;
    int regBy;

    public Vehicle(int regNo,String company,String color,String model,int regBy)
    {
        setRegNo(regNo);
        setCompany(company);
        setColor(color);
        setModel(model);
        setRegBy(regBy);
    }
    //setters
    public void setRegNo(int regNo)
    {
        this.regNo=regNo;
    }
    public void setCompany(String company)
    {
        this.company=company;
    }
    public void setColor(String color)
    {
        this.color=color;
    }
    public void setModel(String model)
    {
        this.model=model;
    }
    public void setRegBy(int regBy)
    {
        this.regBy=regBy;
    }

    //getters
    public int getRegNo()
    {
        return regNo;
    }
    public String getCompany()
    {
        return company;
    }
    public String getColor()
    {
        return color;
    }
    public String getModel()
    {
        return model;
    }
    public int getRegBy()
    {
        return regBy;
    }
}

