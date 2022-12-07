public class InventoryM extends Manager implements DataDisplay.ViewInventory
{
    public InventoryM(int id,String name,String phone,String password,String address,int outletId,String type)
    {
        super(id,name,phone,password,address,outletId,type);
    }
    public InventoryM(Manager obj)
    {
        super(obj);
    }
    public void showConsumptionTrend()
    {

    }
    public void showInventoryLevel(Inventory product)
    {

    }
    public void manageStock()
    {

    }
    public void allocateInventory()
    {

    }
    public void orderShipments()
    {

    }
}
