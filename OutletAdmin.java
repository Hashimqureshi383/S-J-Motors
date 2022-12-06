import java.util.Scanner;
import java.util.Vector;

public class OutletAdmin extends Manager
{
    public OutletAdmin(int id,String name,String phone,String password,String address,int outletId)
    {
        super(id,name,phone,password,address,outletId);
    }
    public void createOutlet()
    {

    }
    public void updateOutlet()
    {

    }
    public void blockOutlet()
    {

    }
    public void deleteOutlet()
    {

    }
    //Activate a deactivate user
    public void activateUser(User u)
    {
        u.setStatus(true);
    }
    public void transferUser(User u, int new_outlet_id)
    {
        u.setOutletId(new_outlet_id);
    }

    //Deactivate an active user
    public void deactivateUser(User u)
    {
        u.setStatus(false);
    }

    public void displayUsers()
    {
            Server s = new Server();

                int i = 1;
                for (User user : s.users) {
                    System.out.print(i + " - ");
                    System.out.print(user.cnic);
                    System.out.print("\n");
                    i++;
                }

                i--;

                System.out.print("Select an option > ");

                Scanner option = new Scanner(System.in);
                int selec = option.nextInt();

                //Checks
                while (selec > i) {
                    System.out.print("Select correct option > ");
                    selec = option.nextInt();
                }

                //Checks
                while (selec < 1) {
                    System.out.print("Select correct option > ");
                    selec = option.nextInt();
                }

                //shift to manage user menu
                manageUsers(s.users.get(selec - 1));

    }

    public void manageUsers(User u)
    {
        while(true) {
            System.out.print("\n1. Activate User\n");
            System.out.print("2. Transfer User\n");
            System.out.print("3. Deactivate User\n");
            System.out.print("4. Exit\n\n");

            System.out.print("Select an option > ");

            Scanner option = new Scanner(System.in);
            int selec = option.nextInt();

            if (selec == 1)
            {
                activateUser(u);
                System.out.println("\n\n user activated. Press 0 to exit ");
                //System.out.println(u.status);
                selec = option.nextInt();
                break;
            }
            else if (selec == 2)
            {
                System.out.println("\n\nEnter New Outlet ID > ");
                selec = option.nextInt();

                //transfer user after taking new outlet as input
                transferUser(u, selec);
                //System.out.println(u.outletId);

                System.out.println("\n\n user transferred. Press 0 to exit ");
                selec = option.nextInt();
                break;
            }
            else if (selec == 3)
            {
                deactivateUser(u);
                System.out.println("\n\n user deactivated. Press 0 to exit ");
                //System.out.println(u.status);
                selec = option.nextInt();
                break;
            }
            else if(selec == 4) break;
        }
    }
}
