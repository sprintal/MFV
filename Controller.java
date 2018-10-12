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
        userController = new UserController();
        productController = new ProductController();
        transactionController = new TransactionController();
        System.out.println("\u000c");
        setList();
        menu.welcomePage();
        System.out.println("Please press enter to continue!");
        console.nextLine();
        mainMenu();
        //writeDetails();
    }

    private void mainMenu()
    {
        System.out.println("\u000c");
        System.out.println("Hello, guest user!");
        Menu menu = new Menu();
        char option = menu.mainMenu();
        switch(option)
        {
            case 'A': ownerLogin();break;
            case 'B': customerLogin();break;
            case 'C': register(); mainMenu(); break;
            case 'D': unloggedInMenu(-1);break;
            case 'X': menu.exitPage();
                      writeDetails();
                      break;
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
            //System.out.println("Please press enter to continue!");
            //Scanner console = new Scanner(System.in);
            //console.nextLine();
            mainMenu();
        }
    }

    private void unloggedInMenu(int index)
    {
        System.out.println("\u000c");
        System.out.println("Hello, guest user!");
        Menu menu = new Menu();
        char option = menu.unloggedInMenu();
        Scanner console = new Scanner(System.in);
        switch(option)
        {
            case 'A':addProductToCartChoosedFromMenu(); unloggedInMenu(index); break;
            case 'B':deleteProductFromCart(); unloggedInMenu(index); break;
            case 'C':purchaseProduct(index); break;
            case 'D':viewProduct(); unloggedInMenu(index); break;
            case 'E':
                     {
                         cart.displayCart();
                         System.out.println("Please press enter to continue!");
                         console.nextLine();
                         unloggedInMenu(index);
                         break;
                     }
            case 'F':searchProduct(); unloggedInMenu(index); break;
            case 'G':ownerLogin(); break;
            case 'H':customerLogin(); break;
            case 'I':register(); mainMenu(); break;
            case 'X':clearCart(); mainMenu(); break;
            default:break;
        }
    }
    
    private void viewProduct()
    {
        ReadInput read = new ReadInput();
        int size = productController.viewProduct();
        System.out.println("Do you want to add product to cart?(y/n):");
        String option = read.readYNOption();
        if (option.equals("Y"))
        {
            System.out.println("Please choose item from above list(Enter \"X\" to cancel):");
            int choice = chooseOption(size);
            if (choice < 0)
                return;
            String id = productController.getProductList().get(choice - 1).getId();
            addProductToCart(id);
        }
    }
    
    private void customerMenu(int index)
    {
        System.out.println("\u000c");
        System.out.println("Hello, customer " + userController.getCustomerList().get(index).getEmail() + "!");
        Menu menu = new Menu();
        char option = menu.customerMenu();
        Scanner console = new Scanner(System.in);
        switch(option)
        {
            case 'A':addProductToCartChoosedFromMenu(); customerMenu(index); break;
            case 'B':deleteProductFromCart(); customerMenu(index); break;
            case 'C':purchaseProduct(index); break;
            case 'D':viewProduct(); customerMenu(index); break;
            case 'E':
                     {
                         cart.displayCart();
                         System.out.println("Please press enter to continue!");
                         console.nextLine();
                         customerMenu(index);
                         break;
                     }
            case 'F':searchProduct(); customerMenu(index); break;
            case 'G':customerViewTransaction(index); break;
            case 'H':changeCustomerInformation(index); customerMenu(index); break;
            case 'I':unregister(index); break;
            case 'X':clearCart(); mainMenu(); break;
            default:break;
        }
    }
    
    private void unregister(int index)
    {
        System.out.println("***Worning! You are tring to unregister***");
        System.out.println("After unregistered, you will not be able to access your account anymore.");
        System.out.println("If you really want to unregister, please type in the word \"unregister\" or type in anything else to cancel:");
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();
        if (input.equals("unregister"))
        {
            userController.ungister(index);
            System.out.println("You have successfully unregistered. Press enter to continue.");
            console.nextLine();
            mainMenu();
        }
        else
            customerMenu(index);
    }
    
    private void clearCart()
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
        System.out.println("\u000c");
        if (index == -1)
        {
            System.out.println("You are not logged in, please log in or register first!");
            System.out.println("Please press enter to continue! ");
            console.nextLine();
            mainMenu();
        }
        else
        {
            String userEmail = userController.getCustomerList().get(index).getEmail();
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
    }

    private void deleteProductFromCart()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("\u000c");
        if (cart.getPurchaseList().size() == 0)
        {
            System.out.println("You don't have any item in cart!");
        }
        else
        {
            cart.displayCart();
            System.out.println("Please choose the item to delete(Enter \"X\" to cancel):");
            int index = chooseOption(cart.getPurchaseList().size()) - 1;
            if (index < 0)
                return;
            returnToProduct(index);
            cart.removeFromCart(index);
            System.out.println("Item reomved from cart!");
        }
        System.out.println("Please press enter to continue! ");
        console.nextLine();
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
    
    private void addProductToCartChoosedFromMenu()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter product id(Enter \"X\" to cancel): ");
        String id = console.nextLine().trim();
        if (id.equals("x") || id.equals("X"))
            return;
        addProductToCart(id);
    }
    
    private void addProductToCart(String id)
    {
        Scanner console = new Scanner(System.in);
        //System.out.println("Please enter product id: ");
        //String id = console.nextLine().trim();
        int productIndex = productController.verifyProductId(id);
        if (productIndex >= 0)
        {
            int optionSize = productController.showSellingOption(productIndex);
            System.out.println("Please enter the number of selling option(Enter \"X\" to cancel):");
            int option = chooseOption(optionSize);
            if (option == -1)
                return;
            String name = productController.getProductList().get(productIndex).getName();
            String optionName = productController.getProductList().get(productIndex).getSellingOption(option - 1)[0];
            String rate = productController.getProductList().get(productIndex).getSellingOption(option - 1)[1];
            double amount = chooseAmount(productIndex,rate);
            if (amount == -1)
                return;
            //productController.getProductList().get(productIndex).setAmount(productController.getProductList().get(productIndex).getAmount() - amount);
            double discount = productController.getProductList().get(productIndex).getDiscount();
            String price = productController.getProductList().get(productIndex).getSellingOption(option - 1)[2];
            price = String.valueOf(discount * Double.parseDouble(price) * amount);
            cart.addToCart(id,name,String.valueOf(amount),optionName,price,rate);
            System.out.println("Item added into cart!");
            double totalAmount = productController.getProductList().get(productIndex).getAmount();
            productController.getProductList().get(productIndex).setAmount(totalAmount - amount * Double.parseDouble(rate));
        }
        else if (productIndex == -2)
        {
            System.out.println("Product expired!");
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
        if (amount == -1)
            return -1;
        double amountRate = Double.parseDouble(rate);
        while(amount * amountRate > productController.getProductList().get(index).getAmount())
        {
            System.out.println("The amount in stock is " + productController.getProductList().get(index).getAmount());
            System.out.println("Amount higher than in stock,please enter again(Enter \"X\" to cancel):");
            amount = read.readProductAmount();
            if (amount == -1)
                return -1;
        }
        return amount;
    }
    
    private int chooseOption(int size)
    {
        ReadInput read = new ReadInput();
        String option = read.readPurchaseOption();
        if (option.equals("X") || option.equals("x"))
            return -1;

        int optionValue = Integer.parseInt(option);
        while (optionValue > size)
        {
            System.out.println("Input invalid. Please choose 1..." + size + "(Enter \"X\" to cancel):");
            option = read.readPurchaseOption();
            if (option.equals("X") || option.equals("x"))
                return -1;
            optionValue = Integer.parseInt(option);
        }
        return optionValue;
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
            case 'B':boolean ifChanged = false;
                     do
                     {
                         ifChanged = userController.changeCustomerEmail(index,read.readUserEmail());
                     }while (ifChanged == false);
                     break;
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
        if (!name.equals("X"))
        {
            String email = read.readUserEmail();
            if (email.equals("X"))
                return;
            if (!userController.ifEmailSame(email))
            {    
                String password = read.readUserPassword();
                if (password.equals("X"))
                    return;
                String address = read.readUserAddress();
                if (address.equals("X"))
                    return;
                String phone = read.readUserPhone();
                if (phone.equals("X"))
                    return;
                userController.register(name, email, password, address, phone);
                System.out.println("New customer registered!");
            }
            else if (userController.ifEmailSame(email))
            {
                System.out.println("Account has been registered, login with this email or register with an new email!");
            }
            System.out.println("Please press enter to continue!");
            Scanner console = new Scanner(System.in);
            console.nextLine();
        }
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
        System.out.println("\u000c");
        System.out.println("Hello, owner " + userController.getOwnerList().get(0).getEmail() + "!");
        Menu menu = new Menu();
        Scanner console = new Scanner(System.in);
        char option = menu.ownerMenu();
        switch(option)
        {
            case 'A':addProduct();
            
            ownerMenu();break;
            case 'B':productController.deleteProduct();
                     System.out.println("Please press enter to continue!");
                     console.nextLine();
                     ownerMenu();
                     break;
            case 'C':ownerViewProduct(); ownerMenu(); break;
            case 'D':editProduct();break;
            case 'E':ownerSearchProduct(); ownerMenu(); break;
            case 'F':userController.viewCustomer();
                     System.out.println("Please press enter to continue!");
                     console.nextLine();
                     ownerMenu();
                     break;
            case 'G':ownerViewTransaction();break;
            case 'H':changeOwnerInformation();break;
            case 'X':mainMenu();break;
            default:break;
        }
    }
    
    private void ownerViewTransaction()
    {
        Scanner console = new Scanner(System.in);
        for (int index = 0; index < transactionController.getTransactionList().size(); index++)
        {
            transactionController.getTransactionList().get(index).displayTransaction();
            System.out.println("------------------------------------------------------------------------------------");
        }
        console.nextLine();
        ownerMenu();
    }

    private void changeOwnerInformation()
    {
        Menu menu = new Menu();
        ReadInput read = new ReadInput();
        System.out.println("\u000c");
        userController.getOwnerList().get(0).displayOwner();
        char option = menu.changeOwnerInformation();
        switch(option)
        {
            case 'A':
                     {
                         String name = read.readUserName();
                         if (name.equals("X"))
                            break;
                         userController.changeOwnerName(name);
                         break;
                     }
            case 'B':
                     {
                         String email = read.readUserEmail();
                         if (email.equals("X"))
                            break;
                         userController.changeOwnerEmail(email);
                         break;
                     }
            case 'C':
                     {
                         String password = read.readUserPassword();
                         if (password.equals("X"))
                            break;
                         userController.changeOwnerPassword(password);
                         break;
                     }
            case 'X':ownerMenu();break;
            default:break;
        }
        changeOwnerInformation();
    }

    private void searchProduct()
    {
        Menu menu = new Menu();
        Scanner console = new Scanner(System.in);
        ReadInput read = new ReadInput();
        char option = menu.searchProductMenu();
        int size = 0;
        switch(option)
        {
            case 'A':
                     {
                         size = productController.searchProductByName();
                         if (size == -1)
                            return;
                         break;
                     }
            case 'B':
                     {
                         size = productController.searchByCategory(read.readProductCategory());
                         if (size == -1)
                            return;
                         break;
                     }
            case 'C':
                     {
                         size = productController.searchByOrigin();
                         if (size == -1)
                            return;
                         break;
                     }
            case 'D':
                     {
                         size = searchByDiscount();
                         if (size == -1)
                            return;
                         break;
                     }
            case 'X': break;
            default: break;
        }
        if (productController.getSearchResult().size() == 0)
        {
            System.out.println("Please press enter to continue!");
            console.nextLine();
            return;
        }
        if (option != 'X')
        {
            System.out.println("Do you want to add product to cart?(y/n):");
            String o = read.readYNOption();
            if (o.equals("Y"))
            {
                System.out.println("Please choose product from above list(Enter \"X\" to cancel):");
                int choice = chooseOption(size);
                if (choice < 0)
                    return;
                String id = productController.getSearchResult().get(choice - 1).getId();
                addProductToCart(id);
            }
        }
    }

    private void ownerSearchProduct()
    {
        Menu menu = new Menu();
        Scanner console = new Scanner(System.in);
        ReadInput read = new ReadInput();
        char option = menu.searchProductMenu();
        int size = 0;
        switch(option)
        {
            case 'A':
                     {
                         size = productController.searchProductByName();
                         if (size == -1)
                            return;
                         break;
                     }
            case 'B':
                     {
                         size = productController.searchByCategory(read.readProductCategory());
                         if (size == -1)
                            return;
                         break;
                     }
            case 'C':
                     {
                         size = productController.searchByOrigin();
                         if (size == -1)
                            return;
                         break;
                     }
            case 'D':
                     {
                         size = searchByDiscount();
                         if (size == -1)
                            return;
                         break;
                     }
            case 'X': break;
            default: break;
        }
        if (productController.getSearchResult().size() == 0)
        {
            System.out.println("Please press enter to continue!");
            console.nextLine();
            return;
        }
        if (option != 'X')
        {
            System.out.println("Do you want to eidt product?(y/n):");
            String o = read.readYNOption();
            if (o.equals("Y"))
            {
                System.out.println("Please choose product from above list(Enter \"X\" to cancel):");
                int choice = chooseOption(size);
                if (choice < 0)
                    return;
                String id = productController.getSearchResult().get(choice - 1).getId();
                int index = productController.productIndex(id);
                editProduct(index);
            }
        }
    }
    
    private int searchByDiscount()
    {
        Menu menu = new Menu();
        ReadInput read = new ReadInput();
        char option = menu.searchProductMenu();
        System.out.println("Please enter minimum discount(range:(0,1])(Enter \"X\" to cancel): ");
        double minDiscount = read.readProductDiscount();
        if (minDiscount == -1)
            return -1;
        System.out.println("Please enter maximum discount(range:(0,1])(Enter \"X\" to cancel): ");
        double maxDiscount = read.readProductDiscount();
        if (maxDiscount == -1)
            return -1;
        while(minDiscount > maxDiscount)
        {
            System.out.println("Minimum discount can't be larger than maximum discount,please input again!");
            System.out.println("Please enter minimum discount(range:(0,1])(Enter \"X\" to cancel): ");
            minDiscount = read.readProductDiscount();
            if (minDiscount == -1)
                return -1;
            System.out.println("Please enter maximum discount(range:(0,1])(Enter \"X\" to cancel): ");
            maxDiscount = read.readProductDiscount();
            if (maxDiscount == -1)
                return -1;
        }
        int count = productController.searchByDiscount(minDiscount,maxDiscount);
        return count;
    }

    private void addProduct()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("\u000c");
        Menu menu = new Menu();
        ReadInput read = new ReadInput();
        System.out.println("Adding new product");
        String name = read.readProductName();
        if (name.equals("X"))
            return;
        char unit = menu.inventoryUnit();
        if (unit == 'X')
            return;
        double amount = read.readProductAmount();
        if (amount == -1)
            return;
        String origin = read.readProductOrigin();
        if (origin.equals("X"))
            return;
        int shelfLife = read.readProductShelfLife();
        if (shelfLife == -1)
            return;
        double discount = read.readProductDiscount();
        if (discount == -1)
            return;
        String category = read.readProductCategory();
        if (category.equals("X"))
            return;
        ArrayList<String[]> sellingOption = chooseSellingOption(unit);
        if (sellingOption.size() == 0)
            return;
        productController.addProduct(name, amount, origin, shelfLife, discount, category, sellingOption);
        System.out.println("Please press enter to continue!");
        console.nextLine();
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
                    case 'A':
                             {
                                 String price = read.readProductPrice();
                                 if (price.equals("X"))
                                    return new ArrayList<String[]>();
                                 sellingOption.add(new String[]{"kg", "1", price});
                                 break;
                             }
                    case 'B':
                             {
                                 String exchangeAmount = read.readProductExchangeAmount("buntch");
                                 if (exchangeAmount.equals("X"))
                                    return new ArrayList<String[]>();
                                 String price = read.readProductPrice();
                                 if (price.equals("X"))
                                    return new ArrayList<String[]>();
                                 sellingOption.add(new String[]{"buntch", exchangeAmount, price});
                                 break;
                             }
                    case 'C':
                             {
                                 String exchangeAmount = read.readProductExchangeAmount("bag");
                                 if (exchangeAmount.equals("X"))
                                    return new ArrayList<String[]>();
                                 String price = read.readProductPrice();
                                 if (price.equals("X"))
                                    return new ArrayList<String[]>();
                                 sellingOption.add(new String[]{"bag", exchangeAmount, price});
                                 break;
                             }
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
                    case 'A':
                             {
                                 String price = read.readProductPrice();
                                 if (price.equals("X"))
                                    return new ArrayList<String[]>();
                                 sellingOption.add(new String[]{"whole","1",price});
                                 break;
                             }
                    case 'B':
                             {
                                 String exchangeAmount = read.readProductExchangeAmount("half");
                                 if (exchangeAmount.equals("X"))
                                    return new ArrayList<String[]>();
                                 String price = read.readProductPrice();
                                 if (price.equals("X"))
                                    return new ArrayList<String[]>();
                                 sellingOption.add(new String[]{"half", exchangeAmount, price});
                                 break;
                             }
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
        System.out.println("Please enter the product id which you want edit(Enter \"X\" to cancel):");
        Scanner console = new Scanner(System.in);
        String id = console.nextLine().trim();
        if (id.equals("x") || id.equals("X"))
            return;
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

    private void ownerViewProduct()
    {
        ReadInput read = new ReadInput();
        int size = productController.viewProduct();
        System.out.println("Do you want to edit product?(y/n):");
        String option = read.readYNOption();
        if (option.equals("Y"))
        {
            System.out.println("Please choose product from above list(Enter \"X\" to cancel):");
            int choice = chooseOption(size);
            if (choice < 0)
                return;
            //String id = productController.getProductList().get(choice - 1).getId();
            editProduct(choice - 1);
        }
    }
    
    private void editProduct(int index)
    {
        Menu menu = new Menu();
        ReadInput read = new ReadInput();
        System.out.println("\u000c");
        productController.getProductList().get(index).displayProduct();
        char option = menu.editProduct();
        switch(option)
        {
            case 'A':
                     {
                         String name = read.readProductName();
                         if (name.equals("x") || name.equals("X"))
                            break;
                         productController.editProductName(index, name);
                         break;
                     }
            case 'B':
                     {
                         editSellingOption(index);
                         break;
                     }
            case 'C':
                     {
                         String category = read.readProductCategory();
                         if (category.equals("x") || category.equals("X"))
                            break;
                         productController.editCategory(index, category);
                         break;
                     }
            case 'D':
                     {
                         double amount = read.readProductAmount();
                         if (amount == -1)
                            break;
                         productController.editAmount(index, amount);
                         break;
                     }
            case 'E':
                     {
                         String origin = read.readProductOrigin();
                         if (origin.equals("x") || origin.equals("X"))
                            break;
                         productController.editOrigin(index, origin);
                         break;
                     }
            case 'F':
                     {
                         int shelfLife = read.readProductShelfLife();
                         if (shelfLife == -1)
                            break;
                         productController.editShelfLife(index, shelfLife);
                         break;
                     }
            case 'G':
                     {
                         double discount = read.readProductDiscount();
                         if (discount == -1)
                            break;
                         productController.editDiscount(index, discount);
                         break;
                     }
            case 'X': ownerMenu();break;
            default : break;
        }
        editProduct(index);
    }

    private void editSellingOption(int index)
    {
        char unit = productController.getInventoryUnit(index);
        ArrayList<String[]> sellingOption = chooseSellingOption(unit);
        if (sellingOption.size() == 0)
            return;
        productController.editSellingOption(index,sellingOption);
    }

    private void setList()
    {
        ArrayList<String> ownerDetails = readDetails("ownerDetails.txt");
        ArrayList<String> customerDetails = readDetails("customerDetails.txt");
        userController.setUserList(ownerDetails,customerDetails);
        ArrayList<String> productDetails = readDetails("productDetails.txt");
        productController.initialProductList(productDetails);        
        ArrayList<String> transactionDetails = readDetails("transactionDetails.txt");
        transactionController.initialTransactionList(transactionDetails);
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
            
            outputFile = new PrintWriter("transactionDetails.txt");            
            for (int i = 0;i < transactionController.getTransactionList().size();i++)
                outputFile.println(transactionController.getTransactionList().get(i).getDetail()); 

            outputFile.println("-1");
            outputFile.close();
        }
        catch(IOException exception)
        {
            System.out.println("I/O error happend when write file");
        }
    }    

}
