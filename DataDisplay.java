    public class DataDisplay
{
        public interface ViewReport
    {
        public void runReport(Staff member);
    }
        public interface ViewInventory
    {
        public void showConsumptionTrend();
        public void showInventoryLevel(Inventory product);
    }
        public interface ViewStatistics
    {
        public void showHistory();
        public void showMileage();
        public void showCostSpent();
    }
}
