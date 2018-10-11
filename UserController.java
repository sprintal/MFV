import java.util.*;

/**
 * Write a description of class UserController here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UserController
{
    private ArrayList<Owner> ownerList;
    private ArrayList<Customer> customerList;

    public UserController()
    {
        ownerList = new ArrayList<Owner>();
        customerList = new ArrayList<Customer>();
    }

    public UserController(ArrayList<Owner> ownerList,ArrayList<Customer> customerList)
    {
        this.ownerList = ownerList;
        this.customerList = customerList;
    }

    public ArrayList<Owner> getOwnerList()
    {
        return ownerList;
    }

    public void setOwnerList(ArrayList<Owner> newOwnerList)
    {
        ownerList = newOwnerList;
    }

    public ArrayList<Customer> getCustomerList()
    {
        return customerList;
    }

    public void setCustomerList(ArrayList<Customer> newCustomerList)
    {
        customerList = newCustomerList;
    }
    
    /*
    private void ownerViewTransaction()
    {
        int size = customerList.size();
        for (int i = 0;i < size;i++)
            customerList.get(i).displayTransaction();
    } 
    
    private void viewCart(int index)
    {
        customerList.get(index).displayCart();
    }    
    
    private void customerViewTransaction(int index)
    {
        customerList.get(index).displayTransaction();
    }
    */

    public void viewCustomer()
    {        
        System.out.println("\u000c");
        System.out.println("                     Customer Details");
        System.out.println("---------------------------------------------------------------");
        int size = customerList.size();
        for (int i = 0;i < size;i++)
            customerList.get(i).displayCustomer();
    }
    
    public int testCustomerLogin()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter you email:");
        String email = console.nextLine().trim();
        int size = customerList.size();
        boolean found = false;
        int index = 0;
        while(index < size && !found)
        {
            if (customerList.get(index).getEmail().equals(email))
                found = true;
            index++;
        }

        if (!found)
            System.out.println("Account \"" + email + "\" not found, please register!");
        else
        {
            index -= 1;            
            if (!customerList.get(index).getRegisterCondition())
                System.out.println("Account \"" + email + "\" has been ungistered, please register a new account!");
            else
            {
                String storedPassword = customerList.get(index).getPassword();
                if (verifyPassword(storedPassword))
                    return index;
            }
        }

        return -1;
    } 
    
    public boolean testOwnerLogin()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter you email:");
        String email = console.nextLine();
        boolean login = false;
        if (ownerList.get(0).getEmail().equals(email))
        {
            String storedPassword = ownerList.get(0).getPassword();
            login = verifyPassword(storedPassword);
        }
        else
            System.out.println("Owner email incorrect,please comfirm!");
        System.out.println("Please press enter to continue!");
        console.nextLine();
        if (login == true)
            System.out.println("Welcome " + ownerList.get(0).getName() + " !");
        return login;
    }

    public boolean verifyPassword(String storedPassword)
    {
        System.out.println("Please enter your password:");
        Scanner console = new Scanner(System.in);
        String password = console.nextLine();
        int inputTime = 1;
        boolean verify = false;
        while (inputTime <= 3 && !verify)
        {
            if (password.equals(storedPassword))
                verify = true;

            else
            {
                System.out.println("Wrong password!");
                System.out.println("You have entered password " + inputTime + " time(s).");
                if (inputTime == 3)
                    System.out.println("Please confirm your password!");
                else
                {
                    System.out.println("You can try at most 3 times!");
                    System.out.println("Please enter your password:");
                    password = console.nextLine();
                }
            }
            inputTime++;
        }
        return verify;
    }

    public void register(String name,String email,String password,String address,String phone)
    {
        String id = "";

        if(customerList.size() == 0)
            id = "1";
        else
        {
            String currentId = customerList.get(customerList.size() - 1).getId();
            id = String.valueOf(Integer.valueOf(currentId) + 1);
        }
        customerList.add(new Customer(name,id,email,password,address,phone,true));
    }
    
    public void ungister(int index)
    {
        customerList.get(index).setRegisterCondition(false);
    }
    
    public void changeCustomerPhone(int index,String phone)
    {
        customerList.get(index).setPhone(phone);
        System.out.println("Phone number changed!");
        System.out.println("Please press enter to continue!");
        Scanner console = new Scanner(System.in);
        console.nextLine();
    }
    
    public void changeCustomerName(int index,String name)
    {
        customerList.get(index).setName(name);
        System.out.println("Name changed!");
        System.out.println("Please press enter to continue!");
        Scanner console = new Scanner(System.in);
        console.nextLine();
    }

    public void changeCustomerEmail(int index,String email)
    {
        customerList.get(index).setEmail(email);
        System.out.println("Email changed!");
        System.out.println("Please press enter to continue!");
        Scanner console = new Scanner(System.in);
        console.nextLine();
    }

    public void changeCustomerPassword(int index,String password)
    {
        customerList.get(index).setPassword(password);
        System.out.println("Password changed!");
        System.out.println("Please press enter to continue!");
        Scanner console = new Scanner(System.in);
        console.nextLine();
    }

    public void changeCustomerAddress(int index,String address)
    {
        customerList.get(index).setAddress(address);
        System.out.println("Address changed!");
        System.out.println("Please press enter to continue!");
        Scanner console = new Scanner(System.in);
        console.nextLine();
    }

    public void changeOwnerPassword(String password)
    {
        ownerList.get(0).setPassword(password);
        System.out.println("Password changed!");
        System.out.println("Please press enter to continue!");
        Scanner console = new Scanner(System.in);
        console.nextLine();
    }

    public void changeOwnerEmail(String email)
    {
        ownerList.get(0).setEmail(email);
        System.out.println("Email changed!");
        System.out.println("Please press enter to continue!");
        Scanner console = new Scanner(System.in);
        console.nextLine();
    }

    public void changeOwnerName(String name)
    {
        ownerList.get(0).setName(name);
        System.out.println("Name changed!");
        System.out.println("Please press enter to continue!");
        Scanner console = new Scanner(System.in);
        console.nextLine();
    }

    public void setUserList(ArrayList<String> ownerDetails,ArrayList<String> customerDetsils)
    {
        int size = customerDetsils.size();
        for(int i = 0;i < size;i++)
        {
            String[] details = customerDetsils.get(i).split(",");
            boolean condition = true;
            if (details[6].equals("0"))
                condition = false;
            customerList.add(new Customer(details[0],details[1],details[2],details[3],details[4],details[5],condition));
        }

        String[] details = ownerDetails.get(0).split(",");
        ownerList.add(new Owner(details[0],details[1],details[2],details[3]));
    }

    public boolean ifEmailSame(String email)
    {
        int size = customerList.size();
        for(int i = 0;i < size;i++)
            if (customerList.get(i).getEmail().equals(email))
            {
                System.out.println("Account has been registered,login with this email or register with an new email!");
                return true;
            }
        return false;
    }

}
