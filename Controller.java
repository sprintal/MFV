import java.io.*;
import java.util.*;
/**
 * Write a description of class NewController here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Controller
{
    private UserController userController;
    private ProductController productController;
    private TransactionController transactionController;
    private Cart cart;
    /**
     * Constructor for objects of class NewController
     */
    public Controller()
    {
        userController = new UserController();
        productController = new ProductController();
        transactionController = new TransactionController();
        cart = new Cart();
    }

    public Controller(UserController userController,ProductController productController,Cart cart)
    {
        this.userController = userController;
        this.productController = productController;
        this.cart = cart;
    }

    public void main()
    {
        Scanner console = new Scanner(System.in);
        Menu menu = new Menu();
        setList();
        menu.welcomePage();
        System.out.println("Please press enter to continue!");
        console.nextLine();
        mainMenu();
        //writeDetails();
    }

    private void mainMenu()
    {
        Menu menu = new Menu();
        char option = menu.mainMenu();
        switch(option)
        {
            case 'A': ownerLogin();break;
            case 'B': customerLogin();break;
            case 'C': register();
            mainMenu();break;
            case 'X': menu.exitPage();break;
            default : break;
        }
    }

    private void customerLogin()
    {
        int index = userController.testCustomerLogin();
        if (index >= 0)
            customerMenu(index);
        else 
        {
            System.out.println("Please press enter to continue!");
            Scanner console = new Scanner(System.in);
            console.nextLine();
            mainMenu();
        }
    }

    private void customerMenu(int index)
    {
        Menu menu = new Menu();
        char option = menu.customerMenu();
        switch(option)
        {
            case 'A':addProductToCart();
            customerMenu(index);break;
            case 'B':deleteProductFromCart(); customerMenu(index); break;
            case 'C':purchaseProduct(index);break;
            case 'D':productController.viewProduct();
            customerMenu(index);break;
            case 'E':cart.displayCart();
            customerMenu(index);break;
            case 'F':searchProduct();
            customerMenu(index);break;
            case 'G':customerViewTransaction(index);break;
            case 'H':changeCustomerInformation(index); customerMenu(index); break;
            case 'I':userController.ungister(index);break;
            case 'X':clearCart(index);
                     mainMenu();break;
            default:break;
        }
    }
    
    private void clearCart(int index)
    {
        for (int i = 0; i < cart.getPurchaseList().size(); i++)
        {
            returnToProduct(i);
        }
        cart = new Cart();
    }
    
    private void customerViewTransaction(int index)
    {
        Scanner console = new Scanner(System.in);
        String userEmail = userController.getCustomerList().get(index).getEmail();
        transactionController.customerViewTransaction(userEmail);
        System.out.println("Please press enter to continue! ");
        console.nextLine();
        customerMenu(index);
    }
    
    private void purchaseProduct(int index)
    {
        Scanner console = new Scanner(System.in);
        ReadInput read = new ReadInput();
        String userEmail = userController.getCustomerList().get(index).getEmail();
        System.out.println("\u000c");
        if (cart.getPurchaseList().size() == 0)
        {
            System.out.println("There is no item in cart, please add some products in cart and try again!");
            System.out.println("Please press enter to continue! ");
            console.nextLine();
            customerMenu(index);
        }
        else
        {
            cart.displayCart();
            System.out.println("Please confirm the above cart detail, do you want to proceed the purchase?(Y/N)");
            String option = read.readYNOption();
            if (option.trim().toLowerCase().equals("y"))
            {
                System.out.println("\u000c");
                System.out.println("Payment successful!");
                System.out.println("System has generated your transaction, detail shown below: ");
                transactionController.generateTransaction(userEmail, cart.getPurchaseList(), cart.caculateTotalPrice());
                cart = new Cart();
                System.out.println("Please press enter to continue! ");
                console.nextLine();
                customerMenu(index);
            }
            else
                customerMenu(index);
        }
    }

    private void deleteProductFromCart()
    {
        System.out.println("\u000c");
        cart.displayCart();
        System.out.println("Please choose the item to delete!");
        int index = chooseOption(cart.getPurchaseList().size()) - 1;
        returnToProduct(index);
        cart.removeFromCart(index);
    }
    
    private void returnToProduct(int index)
    {
        String productId = cart.getPurchaseList().get(index)[0];
        String sellingOption = cart.getPurchaseList().get(index)[3];
        double amount = Double.parseDouble(cart.getPurchaseList().get(index)[2]);
        for (int i = 0; i < productController.getProductList().size(); i++)
        {
            if (productController.getProductList().get(i).getId().equals(productId))
            {
                for (int j = 0; j < productController.getProductList().get(i).getSellingOptionList().size(); j++)
                {
                    if (productController.getProductList().get(i).getSellingOptionList().get(j)[0].equals(sellingOption))
                    {
                        double rate = Double.parseDouble(productController.getProductList().get(i).getSellingOptionList().get(j)[1]);
                        double totalAmount = productController.getProductList().get(i).getAmount();
                        totalAmount += rate * amount;
                        productController.getProductList().get(i).setAmount(totalAmount);
                        break;
                    }
                }
                break;
            }
        }
    }
    
    private void addProductToCart()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter product id: ");
        String id = console.nextLine().trim();
        int productIndex = productController.verifyProductId(id);
        if (productIndex >= 0)
        {
            int optionSize = productController.showSellingOption(productIndex);
            System.out.println("Please enter the number of selling option:");
            int option = chooseOption(optionSize);
            String name = productController.getProductList().get(productIndex).getName();
            String optionName = productController.getProductList().get(productIndex).getSellingOption(option - 1)[0];
            String rate = productController.getProductList().get(productIndex).getSellingOption(option - 1)[1];
            double amount = chooseAmount(productIndex,rate);
            //productController.getProductList().get(productIndex).setAmount(productController.getProductList().get(productIndex).getAmount() - amount);
            double discount = productController.getProductList().get(productIndex).getDiscount();
            String price = productController.getProductList().get(productIndex).getSellingOption(option - 1)[2];
            price = String.valueOf(discount * Double.parseDouble(price) * amount);
            cart.addToCart(id,name,String.valueOf(amount),optionName,price,rate);
            System.out.println("Item added into cart!");
            double totalAmount = productController.getProductList().get(productIndex).getAmount();
            productController.getProductList().get(productIndex).setAmount(totalAmount - amount * Double.parseDouble(rate));
        }
        else
            System.out.println("Product not found,please view product to check product id!");
        System.out.println("Please press enter to continue! ");
        console.nextLine();
    }

    private double chooseAmount(int index,String rate)
    {
        ReadInput read = new ReadInput();
        double amount = read.readProductAmount();
        double amountRate = Double.parseDouble(rate);
        while(amount * amountRate > productController.getProductList().get(index).getAmount())
        {
            System.out.println("The amount in stock is " + productController.getProductList().get(index).getAmount());
            System.out.println("Amount higher than in stock,please enter again!");
            amount = read.readProductAmount();
        }
        return amount;
    }
    
    private int chooseOption(int size)
    {
        Scanner console = new Scanner(System.in);
        String option = console.nextLine().trim();
        String str = String.valueOf(size);
        char cr = str.charAt(0);
        while (!(option.length() == 1 && ((option.charAt(0) >= '1' && option.charAt(0) <= cr))))
        {
            System.out.println("Input invalid. Please choose 1..." + size + ":");
            option = console.nextLine().trim();
        }
        return Integer.parseInt(String.valueOf(option.charAt(0)));
    }

    private void changeCustomerInformation(int index)
    {
        System.out.println("\u000c");
        Menu menu = new Menu();
        userController.getCustomerList().get(index).displayCustomer();
        ReadInput read = new ReadInput();
        char option = menu.changeCustomerInformation();
        switch(option)
        {
            case 'A':userController.changeCustomerName(index,read.readUserName());break;
            case 'B':userController.changeCustomerEmail(index,read.readUserEmail());break;
            case 'C':userController.changeCustomerPassword(index,read.readUserPassword());break;
            case 'D':userController.changeCustomerAddress(index,read.readUserAddress());break;
            case 'E':userController.changeCustomerPhone(index,read.readUserPhone()); break;
            case 'X':customerMenu(index);break;
            default:break;
        }
    }

    private void register()
    {
        ReadInput read = new ReadInput();
        String name = read.readUserName();
        String email = read.readUserEmail();
        if (!userController.ifEmailSame(email))
        {    
            String password = read.readUserPassword();
            String address = read.readUserAddress();
            String phone = read.readUserPhone();
            userController.register(name,email,password,address,phone);
            System.out.println("New customer registered!");
        }
        System.out.println("Please press enter to continue!");
        Scanner console = new Scanner(System.in);
        console.nextLine();
    }

    private void ownerLogin()
    {  
        if (userController.testOwnerLogin())
            ownerMenu();
        else 
            mainMenu();
    }

    private void ownerMenu()
    {
        Menu menu = new Menu();
        Scanner console = new Scanner(System.in);
        char option = menu.ownerMenu();
        switch(option)
        {
            case 'A':addProduct();
            System.out.println("Please press enter to continue!");
            console.nextLine();
            ownerMenu();break;
            case 'B':productController.deleteProduct();
            System.out.println("Please press enter to continue!");
            console.nextLine();
            ownerMenu();break;
            case 'C':productController.viewProduct();
            ownerMenu();break;
            case 'D':editProduct();break;
            case 'E':searchProduct();break;
            case 'F':userController.viewCustomer();
            System.out.println("Please press enter to continue!");
            console.nextLine();
            ownerMenu();break;
            case 'G':ownerViewTransaction();break;
            case 'H':changeOwnerInformation();break;
            case 'X':mainMenu();break;
            default:break;
        }
    }
    
    private void ownerViewTransaction()
    {
        for (int index = 0; index < transactionController.getTransactionList().size(); index++)
        {
            transactionController.getTransactionList().get(index).displayTransaction();
            System.out.println("------------------------------------------------------------------------------------");
        }
    }

    private void changeOwnerInformation()
    {
        Menu menu = new Menu();
        ReadInput read = new ReadInput();
        char option = menu.changeOwnerInformation();
        switch(option)
        {
            case 'A':userController.changeOwnerName(read.readUserName());
            changeOwnerInformation();break;
            case 'B':userController.changeOwnerEmail(read.readUserEmail());
            changeOwnerInformation();break;
            case 'C':userController.changeOwnerPassword(read.readUserPassword());
            changeOwnerInformation();break;
            case 'X':ownerMenu();break;
            default:break;
        }
    }

    private void searchProduct()
    {
        Menu menu = new Menu();
        ReadInput read = new ReadInput();
        char option = menu.searchProductMenu();
        switch(option)
        {
            case 'A': productController.searchProductByName();
            searchProduct();break;
            case 'B': productController.searchByCategory(read.readProductCategory());
            searchProduct();break;
            case 'C': productController.searchByOrigin();
            searchProduct();break;
            case 'D': searchByDiscount();
            searchProduct();break;
            case 'x': ownerMenu();break;
            default: break;
        }
    }

    private void searchByDiscount()
    {
        Menu menu = new Menu();
        ReadInput read = new ReadInput();
        char option = menu.searchProductMenu();
        System.out.println("Please enter minimum discount(range:(0,1]) ");
        double minDiscount = read.readProductDiscount();
        System.out.println("Please enter maximum discount(range:(0,1]) ");
        double maxDiscount = read.readProductDiscount();
        while(minDiscount > maxDiscount)
        {
            System.out.println("Minimum discount can't be larger than maximum discount,please input again!");
            System.out.println("Please enter minimum discount(range:(0,1]) ");
            minDiscount = read.readProductDiscount();
            System.out.println("Please enter maximum discount(range:(0,1]) ");
            maxDiscount = read.readProductDiscount();
        }
        productController.searchByDiscount(minDiscount,maxDiscount);
    }

    private void addProduct()
    {
        Menu menu = new Menu();
        ReadInput read = new ReadInput();
        System.out.println("Please input product details :");
        String name = read.readProductName();
        char unit = menu.inventoryUnit();
        double amount = read.readProductAmount();
        String origin = read.readProductOrigin();
        int shelfLife = read.readProductShelfLife();
        double discount = read.readProductDiscount();
        String category = read.readProductCategory();
        ArrayList<String[]> sellingOption = chooseSellingOption(unit);
        productController.addProduct(name,amount,origin,shelfLife,discount,category,sellingOption);
    }

    private ArrayList<String[]> chooseSellingOption(char unit)
    {
        Menu menu = new Menu();
        ReadInput read = new ReadInput();
        ArrayList<String[]> sellingOption = new ArrayList<String[]>();
        if (unit == 'A')
        {
            do 
            {
                char option = menu.sellingOptionByWeight();
                switch(option)
                {
                    case 'A':sellingOption.add(new String[]{"kg","1",read.readProductPrice()});break;
                    case 'B':sellingOption.add(new String[]{"buntch",read.readProductExchangeAmount("buntch"),read.readProductPrice()});break;
                    case 'C':sellingOption.add(new String[]{"bag",read.readProductExchangeAmount("bag"),read.readProductPrice()});break;
                    default:break;
                }
                System.out.println("Do you want add more selling option?[y/n]");
            }
            while(read.readYNOption().equals("Y"));
        }
        else
        {
            do 
            {
                char option = menu.sellingOptionByAmount();
                switch(option)
                {
                    case 'A':sellingOption.add(new String[]{"whole","1",read.readProductPrice()});break;
                    case 'B':sellingOption.add(new String[]{"half",read.readProductExchangeAmount("half"),read.readProductPrice()});break;
                    default:break;
                }
                System.out.println("Do you want add more selling option?[y/n]");
            }
            while(read.readYNOption().equals("Y"));
        }
        return sellingOption;
    }

    public void editProduct()
    {
        System.out.println("Please enter the product id which you want edit:");
        Scanner console = new Scanner(System.in);
        String id = console.nextLine().trim();
        int productIndex = productController.productIndex(id);
        if (productIndex == -1)
        {
            System.out.println("Product doesn't exist!");
            System.out.println("Please press enter to continue!");
            console.nextLine();
            ownerMenu();
        }
        else
            editProduct(productIndex);
    }

    private void editProduct(int index)
    {
        Menu menu = new Menu();
        ReadInput read = new ReadInput();
        char option = menu.editProduct();
        switch(option)
        {
            case 'A': productController.editProductName(index,read.readProductName());
            editProduct(index);break;
            case 'B': editSellingOption(index);
            editProduct(index);break;
            case 'C': productController.editCategory(index,read.readProductCategory());
            editProduct(index);break;
            case 'D': productController.editAmount(index,read.readProductAmount());
            editProduct(index);break;
            case 'E': productController.editOrigin(index,read.readProductOrigin());
            editProduct(index);break;
            case 'F': productController.editShelfLife(index,read.readProductShelfLife());
            editProduct(index);break;
            case 'G': productController.editDiscount(index,read.readProductDiscount());
            editProduct(index);break;
            case 'X': ownerMenu();break;
            default : break;
        }
    }

    private void editSellingOption(int index)
    {
        char unit = productController.getInventoryUnit(index);
        ArrayList<String[]> sellingOption = chooseSellingOption(unit);
        productController.editSellingOption(index,sellingOption);
    }

    private void setList()
    {
        ArrayList<String> ownerDetails = readDetails("ownerDetails.txt");
        ArrayList<String> customerDetails = readDetails("customerDetails.txt");
        userController.setUserList(ownerDetails,customerDetails);
        ArrayList<String> productDetails = readDetails("productDetails.txt");
        productController.initialProductList(productDetails);        
        ArrayList<String> transactionDetails = readDetails("transactions.txt");
        //size = detailList.size();
    }

    private ArrayList<String> readDetails(String fileName)
    {       
        ArrayList<String> detailList = new ArrayList<String>();
        try
        {
            FileReader inputFile = new FileReader(fileName);
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

    private void writeDetails()
    {
        try
        {
            PrintWriter outputFile = new PrintWriter("customerDetails.txt");            
            for (int i = 0;i < userController.getCustomerList().size();i++)
                outputFile.println(userController.getCustomerList().get(i).getDetail()); 

            outputFile.println("-1");
            outputFile.close();

            outputFile = new PrintWriter("ownerDetails.txt");            
            for (int i = 0;i < userController.getOwnerList().size();i++)
                outputFile.println(userController.getOwnerList().get(i).getDetail()); 

            outputFile.println("-1");
            outputFile.close();

            outputFile = new PrintWriter("productDetails.txt");            
            for (int i = 0;i < productController.getProductList().size();i++)
                outputFile.println(productController.getProductList().get(i).getDetail()); 

            outputFile.println("-1");
            outputFile.close();
        }
        catch(IOException exception)
        {
            System.out.println("I/O error happend when write file");
        }
    }    

}
