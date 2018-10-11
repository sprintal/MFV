import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
/**
 * Write a description of class Controller here.
 *
 * @author (Pan Qi)
 * @version (a version number or a date)
 */
public class Controller
{
    private ArrayList<Owner> ownerList;
    private ArrayList<Customer> customerList;
    private ArrayList<Product> productList;

    /**
     * Constructor for objects of class Controller
     */
    //Default
    public Controller()
    {
        ownerList = new ArrayList<Owner>();
        customerList = new ArrayList<Customer>();
        productList = new ArrayList<Product>();

    }

    //None default
    public Controller(ArrayList<Owner> ownerList,ArrayList<Customer> customerList,ArrayList<Product> productList,ArrayList<String> idCounter)
    {
        this.ownerList = ownerList;
        this.customerList = customerList;
        this.productList = productList;
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

    public ArrayList<Product> getProductList()
    {
        return productList;
    }

    public void setProductList(ArrayList<Product> newProductList)
    {
        productList = newProductList;
    }

    private int productIndex(String id)
    {
        int size = productList.size();
        for (int i = 0;i < size;i++)
            if (productList.get(i).getId().equals(id))
                return i;
        return -1;
    }

    private void editProductMenu()
    {
        Menu menu = new Menu();
        System.out.println("Please enter the product id which you want edit:");
        Scanner console = new Scanner(System.in);
        String id = console.nextLine().trim();
        int productIndex = productIndex(id);
        if (productIndex == -1)
        {
            System.out.println("Product doesn't exist!");
            enterContinue();
            ownerMenu();
        }
        else
            editProduct(productIndex);
    }

    private void editProduct(int index)
    {
        Menu menu = new Menu();
        char option = menu.editProduct();
        switch(option)
        {
            case 'A': editProductName(index);break;
            //case 'B': editSellingOption();break;
            case 'C': editCategory(index);break;
            case 'D': editAmount(index);break;
            case 'E': editOrigin(index);break;
            case 'F': editShelfLife(index);break;
            case 'G': editDiscount(index);break;
            case 'X': ownerMenu();break;
            default : break;
        }
        editMore(index);
    }

    private void editDiscount(int index)
    {
        Validation valide = new Validation();
        Scanner console = new Scanner(System.in);
        double discount = getProductDiscount();
        productList.get(index).setDiscount(discount);
        System.out.println("Discount edited!");
    }

    private void editShelfLife(int index)
    {
        int shelfLife = getProductShelfLife();
        productList.get(index).setShelfLife(shelfLife);
        System.out.println("Shelf life edited!");
    }

    private void editOrigin(int index)
    {
        String origin = getProductOrigin();
        productList.get(index).setOrigin(origin);
        System.out.println("Origin edited!");
    }

    private void editCategory(int index)
    {
        String category = getProductCategory();
        String oldId = productList.get(index).getId();
        String newId = category.substring(0,1) + oldId.substring(1);
        productList.get(index).setId(newId);
        System.out.println("Category edited!");
    }

    private void editMore(int index)
    {
        System.out.println("Do you want edit this product more?[y/n]");
        Validation valide = new Validation();
        Scanner console = new Scanner(System.in);
        String option = console.nextLine().trim().toUpperCase();
        while (!valide.validateYN(option))
            option = console.nextLine().trim().toUpperCase();

        if (option.equals("Y"))
            editProduct(index);    
        else
            ownerMenu();
    }

    private void editAmount(int index)
    {
        double amount = getProductAmount();
        productList.get(index).setAmount(amount);
        System.out.println("Amount edited!");
    }

    private void editProductName(int index)
    {
        String name = getProductName();
        productList.get(index).setName(name);
        System.out.println("Name edited!");
    }

    private void addProduct()
    {
        int size = productList.size();  
        String id = "";
        String name = getProductName();
        double amount = getProductAmount();
        String origin = getProductOrigin();
        int shelfLife = getProductShelfLife();
        double discount = getProductDiscount();
        String category = getProductCategory();
        category = category.substring(0,1).toUpperCase();
        if (size == 0)
            id = category + String.valueOf(1);
        else 
        {
            String idNumber = productList.get(size - 1).getId().substring(1);
            String newNumber = String.valueOf(Integer.valueOf(idNumber) + 1);
            id = category + newNumber;
        }

        //productList.add(new Product(id,));
    }

    private String getProductCategory()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter product category:");
        String category = console.nextLine().trim();
        while (!valide.validateProductCategory(category))
            category = console.nextLine().trim();

        return category;
    }

    private double getProductDiscount()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter product discount:");
        String discount = console.nextLine().trim();
        while (!valide.validateProductDiscount(discount))
            discount = console.nextLine().trim();

        return Double.valueOf(discount);
    }

    private int getProductShelfLife()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter product shelf life:");
        String shelfLife = console.nextLine().trim();
        while (!valide.validateProductShelfLife(shelfLife))
            shelfLife = console.nextLine().trim();

        return Integer.valueOf(shelfLife);
    }

    private double getProductAmount()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter product amount:");
        String amount = console.nextLine().trim();
        while (!valide.validateProductAmount(amount))
            amount = console.nextLine().trim();

        return Double.valueOf(amount);
    }

    private String getProductOrigin()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter product origin:");
        String origin = console.nextLine().trim();
        while (!valide.validateProductOrigin(origin))
            origin = console.nextLine().trim();

        return origin;
    }

    private boolean ifProductNameSame(String name)
    {
        int size = productList.size();
        for (int i = 0;i < size;i++)
            if (productList.get(i).getName().equals(name))
            {
                System.out.println("Product name same,please change a new name!");
                return true;
            }

        return false;
    }

    private String getProductName()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter product name:");
        String name = console.nextLine().trim();
        while (!valide.validateProductName(name) || ifProductNameSame(name))
            name = console.nextLine().trim();

        return name;
    }

    public void main()
    {
        setList();
        Menu menu = new Menu();
        menu.welcomePage();
        enterContinue();
        mainMenu();
    }

    private void mainMenu()
    {
        Menu menu = new Menu();
        char option = menu.mainMenu();
        switch(option)
        {
            case 'A': ownerLogin();break;
            case 'B': customerLogin();break;
            case 'C': register();break;
            case 'X': menu.exitPage();break;
            default : break;
        }
    }

    private void customerMenu(int index)
    {
        Menu menu = new Menu();
        char option;
        option = menu.customerMenu();
        switch(option)
        {
            case 'A':addProductToCart(index);break;
            //                case 'B':deleteProductFromCart();break;
            //                case 'C':purchaseProduct();break;
            case 'D':customerViewProduct(index);break;
            //                case 'E':exchangeAndRefund();break;
            case 'F':searchProduct();
                     enterContinue();
                     customerMenu(index);
                     break;
            case 'G':customerViewTransaction(index);break;
            case 'H':changeCustomerInformation(index);break;
            case 'I':ungister(index);break;
            //case 'X':returnProduct();
            //         mainMenu();break;
            default:break;
        }
    }

    private void addProductToCart(int index)
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter product id: ");
        String id = console.nextLine().trim();
        int productIndex = verifyProductId(id);
        //if (productIndex >= 0)
            
    }
    
    
    
    private int verifyProductId(String id)
    {
        int size = productList.size();
        int productIndex = 0;
        boolean found = false;
        while (productIndex < size && !found)
        {
            if (productList.get(productIndex).getId().equals(id))
                found = true;
            productIndex++;
        }
        
        if (found)
        {
            productIndex--;
            if (productList.get(productIndex).ifExpired())
            {
                System.out.println("Product expired!");
                return -1;
            }
            else
                return productIndex;
        }
        else 
            return -1;
    }

    private void customerViewTransaction(int index)
    {
        customerList.get(index).displayTransaction();
    }

    private void customerLogin()
    {
        int index = testCustomerLogin();
        if (index >= 0)
            customerMenu(index);
        else 
        {
            enterContinue();
            mainMenu();
        }
    }

    private void ownerMenu()
    {
        Menu menu = new Menu();
        char option = menu.ownerMenu();
        switch(option)
        {
            case 'A':addProduct();break;
            case 'B':deleteProduct();break;
            case 'C':ownerViewProduct();break;
            case 'D':editProductMenu();break;
            case 'E':searchProduct();
                     enterContinue();
                     ownerMenu();
                     break;
            case 'F':viewCustomer();break;
            case 'G':ownerViewTransaction();break;
            case 'H':changeOwnerInformation();break;
            case 'X':mainMenu();break;
            default:break;
        }
    }

    private void searchProduct()
    {
        Menu menu = new Menu();
        char option = menu.searchProductMenu();
        switch(option)
        {
            case 'A':searchByName();break;
            case 'B':searchByCategory();break;
            case 'C':searchByOrigin();break;
            case 'D':searchByDiscount();break;
            case 'X':break;
            default:break;
        }
    }

    private void searchByDiscount()
    {
        Menu menu = new Menu();
        char option = menu.searchByDiscountMenu();
        switch (option)
        {
            case 'A': searchByDiscountLowerThan();break;
            case 'B': searchByDiscountHigherThan();break;
            case 'C': searchByDiscountBetween();break;
            case 'X': searchProduct();break;
            default: break;
        }
    }

    private void searchByDiscountLowerThan()
    {
        System.out.println("Please enter maximum discount(range:(0,1])");
        double discount = getProductDiscount();
        System.out.println("Search result: ");
        for (int index = 0; index < productList.size(); index++)
        {
            if (productList.get(index).getDiscount() < discount)
                productList.get(index).displayProduct();
        }

        enterContinue();
        searchByDiscount();
    }

    private void searchByDiscountHigherThan()
    {
        System.out.println("Please enter minimum discount(range:(0,1]) ");
        double discount = getProductDiscount();
        System.out.println("Search result: ");
        for (int index = 0; index < productList.size(); index++)
        {
            if (productList.get(index).getDiscount() > discount)
                productList.get(index).displayProduct();
        }

        enterContinue();
        searchByDiscount();
    }

    private void searchByDiscountBetween()
    {
        double minimumDiscount = 0.0;
        double maxmumDiscount = 0.0;
        do
        {
            System.out.println("Please enter minimum discount(range:(0,1]) ");
            minimumDiscount = getProductDiscount();
            System.out.println("Please enter maximum discount(range:(0,1]) ");
            maxmumDiscount = getProductDiscount();
            if (minimumDiscount > maxmumDiscount)
                System.out.println("Minimum discount should be lower than maxmum discount");
        }while(minimumDiscount > maxmumDiscount);

        System.out.println("Search result: ");

        for (int index = 0; index < productList.size(); index++)
        {
            if (productList.get(index).getDiscount() <= maxmumDiscount && productList.get(index).getDiscount() >= minimumDiscount)
                productList.get(index).displayProduct();
        }

        enterContinue();
        searchByDiscount();
    }

    private void searchByOrigin()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter product origin: ");
        String origin = console.nextLine().trim();
        int size = productList.size();
        System.out.println("Search result: ");
        for (int i = 0;i < size;i++)
            if (productList.get(i).getOrigin().contains(origin))
                productList.get(i).displayProduct();

        enterContinue();
        searchProduct();
    }

    private void searchByName()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter product name: ");
        String name = console.nextLine().trim();
        int size = productList.size();
        System.out.println("Search result: ");
        for (int i = 0;i < size;i++)
            if (productList.get(i).getName().contains(name))
                productList.get(i).displayProduct();

        enterContinue();
        searchProduct();
    }

    private void searchByCategory()
    { 
        String category = getProductCategory().trim();
        category = category.substring(0,1).toUpperCase();
        int size = productList.size();
        System.out.println("Search result: ");
        for (int i = 0;i < size;i++)
            if (productList.get(i).getId().substring(0,1).equals(category))
                productList.get(i).displayProduct();

        enterContinue();
        searchProduct();
    }

    private void ownerViewTransaction()
    {
        int size = customerList.size();
        for (int i = 0;i < size;i++)
            customerList.get(i).displayTransaction();

        enterContinue();
        ownerMenu();
    }

    private void changeOwnerInformation()
    {
        Menu menu = new Menu();
        char option = menu.changeOwnerInformation();
        switch(option)
        {
            case 'A':changeOwnerName();break;
            case 'B':changeOwnerEmail();break;
            case 'C':changeOwnerPassword();break;
            case 'X':ownerMenu();break;
            default:break;
        }
    }

    private void changeOwnerPassword()
    {
        String password = getUserPassword();
        ownerList.get(0).setPassword(password);
        System.out.println("Password changed!");
        enterContinue();
        changeOwnerInformation();
    }

    private void changeOwnerEmail()
    {
        Validation valide = new Validation();
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter you email:");
        String email = console.nextLine().trim();
        while (!valide.validateUserEmail(email))
            email = console.nextLine().trim(); 

        ownerList.get(0).setEmail(email);
        System.out.println("Email changed!");
        enterContinue();
        changeOwnerInformation();
    }

    private void changeOwnerName()
    {
        String name = getUserName();
        ownerList.get(0).setName(name);
        System.out.println("Name changed!");
        enterContinue();
        changeOwnerInformation();
    }

    private void deleteProduct()
    {
        System.out.println("Please enter the product id which you want delete:");
        Scanner console = new Scanner(System.in);
        String id = console.nextLine().trim();
        int productIndex = productIndex(id);
        if (productIndex == -1)
            System.out.println("Product doesn't exist!");
        else
        {
            customerList.remove(productIndex);
            System.out.println("Product deleted!");
        }
        enterContinue();
        ownerMenu();
    }

    private void ownerLogin()
    {  
        if (testOwnerLogin())
        {
            ownerMenu();
        }
        else 
            mainMenu();
    }

    private boolean testOwnerLogin()
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
        enterContinue();   

        return login;
    }

    private int testCustomerLogin()
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
            System.out.println("No account \"" + email + "\", please register!");
        else
        {
            index = index - 1;            
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

    private boolean verifyPassword(String storedPassword)
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
                    System.out.println("At most try 3 times!");
                    System.out.println("Please enter your password:");
                    password = console.nextLine();
                }
            }
            inputTime++;
        }

        if (verify == true)
            System.out.println("Welcome " + ownerList.get(0).getName() + " !");
        return verify;
    }

    private String getUserName()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter your name:");
        System.out.println("Your name format could be \"Given name\"-\"Family name\",whthout whitespace!");
        System.out.println("Your name can't be less than two characters,at most one \"-\" in the middle.");
        String name = console.nextLine().trim();
        while (!valide.validateUserName(name))
            name = console.nextLine().trim();

        return name;
    }

    private String getUserEmail()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter your email:");
        String email = console.nextLine().trim();
        while (!valide.validateUserEmail(email) || ifEmailSame(email))
            email = console.nextLine().trim();

        return email;
    }

    private String getUserPassword()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter your password:");
        String password = console.nextLine().trim();
        while (!valide.validatePassword(password))
            password = console.nextLine().trim();

        return password;
    }

    private String getUserAddress()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter your address:");
        String address = console.nextLine().trim();
        while (!valide.validateUserAddress(address))
            address = console.nextLine().trim();

        return address;
    }

    private String getUserPhone()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter your phone number:");
        String phone = console.nextLine().trim();
        while (!valide.validateUserPhone(phone))
            phone = console.nextLine().trim();  

        return phone;
    }

    private void register()
    {
        String id = "";

        if(customerList.size() == 0)
            id = "1";
        else
        {
            String currentId = customerList.get(customerList.size() - 1).getId();
            id = String.valueOf(Integer.valueOf(currentId) + 1);
        }
        String name = getUserName();
        String email = getUserEmail();
        String password = getUserPassword();
        String address = getUserAddress();
        String phone = getUserPhone();

        customerList.add(new Customer(name,id,email,password,address,phone,true));
        System.out.println("New customer registered!");
        enterContinue();
        mainMenu();
    }

    private boolean ifEmailSame(String email)
    {
        int size = customerList.size();
        for(int i = 0;i < size;i++)
            if (customerList.get(i).getEmail().equals(email))
            {
                System.out.println("Account has been registered,please change your email!");
                return true;
            }
        return false;
    }

    private void ungister(int index)
    {
        customerList.get(index).setRegisterCondition(false);
    }

    private void changeCustomerName(int index)
    {
        String name = getUserName();
        customerList.get(index).setName(name);
        enterContinue();
        changeCustomerInformation(index);
    }

    private void changeCustomerEmail(int index)
    {
        String email = getUserEmail();
        customerList.get(index).setEmail(email);
        enterContinue();
        changeCustomerInformation(index);
    }

    private void changeCustomerPassword(int index)
    {
        String password = getUserPassword();
        customerList.get(index).setPassword(password);
        enterContinue();
        changeCustomerInformation(index);
    }

    private void changeCustomerAddress(int index)
    {
        String address = getUserAddress();
        customerList.get(index).setAddress(address);
        enterContinue();
        changeCustomerInformation(index);
    }

    private void changeCustomerPhone(int index)
    {
        String phone = getUserPhone();
        customerList.get(index).setPhone(phone);
        enterContinue();
        changeCustomerInformation(index);
    }

    private void changeCustomerInformation(int index)
    {
        Menu menu = new Menu();
        char option = menu.changeCustomerInformation();
        switch(option)
        {
            case 'A':changeCustomerName(index);break;
            case 'B':changeCustomerEmail(index);break;
            case 'C':changeCustomerPassword(index);break;
            case 'D':changeCustomerAddress(index);break;
            case 'E':changeCustomerPhone(index); break;
            case 'X':customerMenu(index);break;
            default:break;
        }
    }

    private void customerViewProduct(int index)
    {
        System.out.println("\u000c");
        System.out.println("                   Product Details");
        System.out.println("------------------------------------------------------------------------------------");
        int size = productList.size();
        for (int i = 0;i < size;i++)
            if (!productList.get(i).ifExpired())
                productList.get(i).displayProduct();
        
        enterContinue();
        customerMenu(index);
    }
    
    private void ownerViewProduct()
    {
        System.out.println("\u000c");
        System.out.println("                   Product Details");
        System.out.println("------------------------------------------------------------------------------------");
        int size = productList.size();
        for (int i = 0;i < size;i++)
            productList.get(i).displayProduct();
        
        enterContinue();
        ownerMenu();
    }

    private void setList()
    {
        ArrayList<String> detailList = readDetails("customerDetails.txt");
        int size = detailList.size();
        for(int i = 0;i < size;i++)
        {
            String[] details = detailList.get(i).split(",");
            boolean condition = true;
            if (details[6].equals("0"))
                condition = false;
            customerList.add(new Customer(details[0],details[1],details[2],details[3],details[4],details[5],condition));
        }

        detailList = readDetails("ownerDetails.txt");
        String[] details = detailList.get(0).split(",");
        ownerList.add(new Owner(details[0],details[1],details[2],details[3]));


        detailList = readDetails("productDetails.txt");
        size = detailList.size();
        for(int i = 0;i < size;i++)
        {
            String[] productDetails = detailList.get(i).split(",");
            Calendar date = strToCal(productDetails[6]);
            int length = productDetails.length;
            int totalIndex = length - 1;
            int currentIndex = 7;
            ArrayList<String[]> sellingOptionList = new ArrayList<String[]>();
            while (currentIndex < totalIndex)
            {
                String[] option = new String[]{productDetails[currentIndex],productDetails[currentIndex + 1],productDetails[currentIndex + 2]};
                sellingOptionList.add(option);
                currentIndex += 3;
            }

            productList.add(new Product(productDetails[0],productDetails[1],Double.valueOf(productDetails[2]),productDetails[3],
                                        Integer.parseInt(productDetails[4]),Double.parseDouble(productDetails[5]),date,sellingOptionList));
        }

        detailList = readDetails("transaction.txt");
        size = detailList.size();
    }
    
    private Calendar strToCal(String str)
    {
        Date date = new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
        try
        {
            date = sdf.parse(str);
        }
        
        catch(Exception e)
        {
            System.out.println("Calendar Exception.");
        }
        finally
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        }
        
    }

    private ArrayList<String> readDetails(String fileName)
    {       
        ArrayList<String> detailList = new ArrayList<String>();
        try
        {
            FileReader inputFile = new FileReader(fileName);//FileReader object.
            try
            {
                Scanner parser = new Scanner(inputFile);
                String detail = parser.nextLine();                

                while (!detail.equals("-1"))
                {
                    detailList.add(detail);
                    detail = parser.nextLine(); 
                }

            }
            catch(Exception exception)
            {
                System.out.println(fileName + " content illegal.Please check file content!");
            }
            finally
            {
                inputFile.close();
            } 
        }
        catch(FileNotFoundException exception)
        {
            System.out.println(fileName + " not found");
        }
        catch(IOException exception)
        {
            System.out.println("Unexpected I/O exception occurs.Please use another file");
        }

        return detailList;
    }

    private void enterContinue()
    {  
        System.out.println("");
        System.out.println("Press enter to continue");
        Scanner console = new Scanner(System.in);
        console.nextLine();
    }
    
    private void viewCustomer()
    {        
        System.out.println("\u000c");
        System.out.println("                     Customer Details");
        System.out.println("---------------------------------------------------------------");
        int size = customerList.size();
        for (int i = 0;i < size;i++)
            customerList.get(i).displayCustomer();
        enterContinue();
        ownerMenu();
    }
    
    private void writeDetails()
    {
        try
        {
            PrintWriter outputFile = new PrintWriter("customerDetails.txt");            
            for (int i = 0;i < customerList.size();i++)
                outputFile.println(customerList.get(i).getDetail()); 
            
            outputFile.println("-1");
            outputFile.close();
            
            outputFile = new PrintWriter("ownerDetails.txt");            
            for (int i = 0;i < ownerList.size();i++)
                outputFile.println(ownerList.get(i).getDetail()); 
            
            outputFile.println("-1");
            outputFile.close();
            
            outputFile = new PrintWriter("productDetails.txt");            
            for (int i = 0;i < productList.size();i++)
                outputFile.println(productList.get(i).getDetail()); 
            
            outputFile.println("-1");
            outputFile.close();
        }
        catch(IOException exception)
        {
            System.out.println("I/O error happend when write file");
        }
        System.out.println("Details written succeed!");
    }    
}
