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

    /**
     * A default constructor for objects of class UserController.
     */
    public UserController()
    {
        ownerList = new ArrayList<Owner>();
        customerList = new ArrayList<Customer>();
    }

    /**
     * A constructor for objects of class UserController.
     */
    public UserController(ArrayList<Owner> ownerList,ArrayList<Customer> customerList)
    {
        this.ownerList = ownerList;
        this.customerList = customerList;
    }

    /**
     * Create a getOwnweList method to get the list of owner (ownerList).
     */
    public ArrayList<Owner> getOwnerList()
    {
        return ownerList;
    }

    /**
     * Create a setOwnerList to set the new list of owner.
     */
    public void setOwnerList(ArrayList<Owner> newOwnerList)
    {
        ownerList = newOwnerList;
    }

    /**
     * Create a getOwnerList to get the list of customer (customerList).
     */
    public ArrayList<Customer> getCustomerList()
    {
        return customerList;
    }

    /**
     * Create a setCustomerList to set the new list of customer.
     */
    public void setCustomerList(ArrayList<Customer> newCustomerList)
    {
        customerList = newCustomerList;
    }

    /**
     * Create a viewCustomer method to display all customer's detailed information that in the customerList.
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
    
    /**
     * Create a testCustomerLogin method to test whether the customer is login or not based on the email.
     */
    public int testCustomerLogin()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter you email(Enter \"X\" to cancel):");
        String email = console.nextLine().trim();
        int size = customerList.size();
        boolean found = false;
        int index = 0;
        if (email.equals("x") || email.equals("X"))
        {
            return -1;
        }
        while(index < size && !found)
        {
            if (customerList.get(index).getEmail().equals(email))
                found = true;
            index++;
        }

        if (!found)
        {
            System.out.println("Account \"" + email + "\" not found, please register!");
            System.out.println("Please press enter to continue!");
            console.nextLine();
        }
        else
        {
            index -= 1;            
            if (!customerList.get(index).getRegisterCondition())
            {
                System.out.println("Account \"" + email + "\" has been ungistered, please register a new account!");
                System.out.println("Please press enter to continue!");
                console.nextLine();
            }
            else
            {
                String storedPassword = customerList.get(index).getPassword();
                if (verifyPassword(storedPassword))
                    return index;
            }
        }
        
        return -1;
    } 
    
    /**
     * Create a testOwnerLogin method to test whether the onwer is login or not based on the email.
     */
    public boolean testOwnerLogin()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter you email(Enter \"X\" to cancel):");
        String email = console.nextLine().trim();
        boolean login = false;
        if (email.equals("x") || email.equals("X"))
        {
            return false;
        }
        else
        {
            if (ownerList.get(0).getEmail().equals(email))
            {
                String storedPassword = ownerList.get(0).getPassword();
                login = verifyPassword(storedPassword);
            }
            else
            {
                System.out.println("Owner email incorrect,please comfirm!");
                System.out.println("Please press enter to continue!");
                console.nextLine();
            }
        }
        return login;
    }

    /**
     * Create a verifyPassword method to verify the password.
     */
    public boolean verifyPassword(String storedPassword)
    {
        System.out.println("Please enter your password(Enter \"X\" to cancel):");
        Scanner console = new Scanner(System.in);
        String password = console.nextLine();
        int inputTime = 1;
        boolean verify = false;
        while (inputTime <= 3 && !verify)
        {
            if (password.equals("X") || password.equals("x"))
                return false;
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
                    System.out.println("Please enter your password(Enter \"X\" to cancel):");
                    password = console.nextLine();
                }
            }
            inputTime++;
        }
        return verify;
    }

    /**
     * Create a register method to register a customer (adding it to customerList). 
     */
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
    
    /**
     * Create a unregister method to unregister a cunstomer(set registerCondition to false).
     */
    public void unregister(int index)
    {
        customerList.get(index).setRegisterCondition(false);
    }
    
    /**
     * Create a changeCustomerAddress method to change the customer's address.
     */
    public void changeCustomerAddress(int index,String address)
    {
        customerList.get(index).setAddress(address);
        System.out.println("Address changed!");
        System.out.println("Please press enter to continue!");
        Scanner console = new Scanner(System.in);
        console.nextLine();
    }
    
    /**
     * Create a changeCustomerEmail method to change the customer's email.
     */
    public boolean changeCustomerEmail(int index,String email)
    {
        if (ifEmailSame(email))
        {
            System.out.println("The inputted email already exist, please try another one.");
            return false;
        }
        else
        {
            customerList.get(index).setEmail(email);
            System.out.println("Email changed!");
            System.out.println("Please press enter to continue!");
            Scanner console = new Scanner(System.in);
            console.nextLine();
            return true;
        }
    }
    
    /**
     * Create a changeCustomerName method to change the customer's name.
     */
    public void changeCustomerName(int index,String name)
    {
        customerList.get(index).setName(name);
        System.out.println("Name changed!");
        System.out.println("Please press enter to continue!");
        Scanner console = new Scanner(System.in);
        console.nextLine();
    }
    
    /**
     * Create a changeCustomerPassword method to change the customer's password.
     */
    public void changeCustomerPassword(int index,String password)
    {
        customerList.get(index).setPassword(password);
        System.out.println("Password changed!");
        System.out.println("Please press enter to continue!");
        Scanner console = new Scanner(System.in);
        console.nextLine();
    }

    /**
     * Create a changeCustomerPhone method to change customer's phone number.
     */
    public void changeCustomerPhone(int index,String phone)
    {
        customerList.get(index).setPhone(phone);
        System.out.println("Phone number changed!");
        System.out.println("Please press enter to continue!");
        Scanner console = new Scanner(System.in);
        console.nextLine();
    }
    
    /**
     * Create a changeOwnerEmail method to change the owner's Email.
     */
    public void changeOwnerEmail(String email)
    {
        ownerList.get(0).setEmail(email);
        System.out.println("Email changed!");
        System.out.println("Please press enter to continue!");
        Scanner console = new Scanner(System.in);
        console.nextLine();
    }
    
    /**
     * Create a changeOwnerName method to change the owner's name.
     */
    public void changeOwnerName(String name)
    {
        ownerList.get(0).setName(name);
        System.out.println("Name changed!");
        System.out.println("Please press enter to continue!");
        Scanner console = new Scanner(System.in);
        console.nextLine();
    }
    
    /**
     * Create a changeOwnerPassword method to change the owner's password.
     */
    public void changeOwnerPassword(String password)
    {
        ownerList.get(0).setPassword(password);
        System.out.println("Password changed!");
        System.out.println("Please press enter to continue!");
        Scanner console = new Scanner(System.in);
        console.nextLine();
    }

    /**
     * Create a ifEmailSame method to check whether the user's email already exists.
     */
    public boolean ifEmailSame(String email)
    {
        int size = customerList.size();
        for(int i = 0;i < size;i++)
            if (customerList.get(i).getEmail().equals(email))
                return true;
        return false;
    }
    
    /**
     * Create a setUserList method to set the user's list.
     */
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
}