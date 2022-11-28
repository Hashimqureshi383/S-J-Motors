    public class Inventory
{
    int id;
    String name;
    String mfgDate;
    String Model;
    String Brand;
    int outletId;

    public Inventory(int id,String name,String mfgDate,String brand,String model,int outletId)
    {
        addRecord(id,name,mfgDate,brand,model,outletId);
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
    public void setMfgDate(String mfgDate)
    {
        this.mfgDate=mfgDate;
    }
    public void setModel(String model)
    {
        Model=model;
    }
    public void setBrand(String brand)
    {
        Brand=brand;
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
    public String getMfgDate()
    {
        return mfgDate;
    }
    public String getModel()
    {
        return Model;
    }
    public String getBrand()
    {
        return Brand;
    }
    public int getOutletId()
    {
        return outletId;
    }

    public void addRecord(int id, String Name, String mfgDate, String brand, String model,int outletId)
    {
        setId(id);
        setName(name);
        setMfgDate(mfgDate);
        setBrand(brand);
        setModel(model);
        setOutletId(outletId);
    }
    public void removeRecord()
    {

    }
    public void editRecord(int id,String Name,String mfgDate,String brand,String model,int outletId)
    {
        setId(id);
        setName(name);
        setMfgDate(mfgDate);
        setBrand(brand);
        setModel(model);
        setOutletId(outletId);
    }
}
