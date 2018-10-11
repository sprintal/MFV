import java.util.Scanner;

/**
 * Write a description of class Menu here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Menu
{

    /**
     * Constructor for objects of class Menu
     */
    public Menu()
    {

    }

    public void welcomePage()
    {
        System.out.println("Welcome to Monash Fruit and Vegetable system.");
        System.out.println("Enjoy your shopping time!");
    }

    public void exitPage()
    {
        System.out.println("Thanks for using Monash Fruit and Vegetable system.");
        System.out.println("Have a nice day!");
    }

    public char mainMenu()
    {
        
        System.out.println("Administrator Login Enter \"A\"");
        System.out.println("Customer Login Enter \"B\"");
        System.out.println("Customer Register Enter \"C\"");
        System.out.println("Enter System Enter \"D\"");
        System.out.println("Exit enter \"X\"");
        return getChoice('D');
    }

    public char inventoryUnit()
    {
        System.out.println("Please choose inventory unit(Enter \"X\" to cancel):");
        System.out.println("Inventory Unit by weight enter \"A\"");
        System.out.println("Inventory Unit by amount enter \"B\"");
        return getChoice('B');
    }

    public char sellingOptionByAmount()
    {
        System.out.println("Please choose selling opion by amount: ");
        System.out.println("Sell by whole enter \"A\"");
        System.out.println("Sell by half enter \"B\"");
        return getChoiceWithoutExit('B');
    }

    public char sellingOptionByWeight()
    {
        System.out.println("Please choose selling opion by weight: ");
        System.out.println("Sell by kg enter \"A\"");
        System.out.println("Sell by buntch enter \"B\"");
        System.out.println("Sell by bag enter \"C\"");
        return getChoiceWithoutExit('C');
    }

    public char customerMenu()
    {
        
        System.out.println("Add product to cart enter \"A\"");
        System.out.println("Delete product from cart enter \"B\"");
        System.out.println("Check out enter \"C\"");
        System.out.println("View product enter \"D\"");
        System.out.println("View cart enter \"E\"");
        System.out.println("Search product enter \"F\"");
        System.out.println("View transcation enter \"G\"");
        System.out.println("View and change personal information enter \"H\"");
        System.out.println("Unregister enter \"I\"");
        System.out.println("Exit enter \"X\"");
        return getChoice('I');
    }
    
    public char unloggedInMenu()
    {
        
        System.out.println("Add product to cart enter \"A\"");
        System.out.println("Delete product from cart enter \"B\"");
        System.out.println("Check out enter \"C\"");
        System.out.println("View product enter \"D\"");
        System.out.println("View cart enter \"E\"");
        System.out.println("Search product enter \"F\"");
        System.out.println("Administrator Login Enter \"G\"");
        System.out.println("Customer Login Enter \"H\"");
        System.out.println("Customer Register Enter \"I\"");
        System.out.println("Exit enter \"X\"");
        return getChoice('I');
    }

    public char editProduct()
    {
  
        System.out.println("Edit Product Name Enter \"A\"");
        System.out.println("Edit Selling Option Enter \"B\"");
        System.out.println("Edit Category Enter \"C\"");
        System.out.println("Edit Inventory Amount Enter \"D\"");
        System.out.println("Edit Origin Enter \"E\"");
        System.out.println("Edit Shelf Life Enter \"F\"");
        System.out.println("Edit Discount Enter \"G\"");
        System.out.println("Exit Enter \"X\"");
        return getChoice('G');
    }

    public char ownerMenu()
    {
        
        System.out.println("Add product enter \"A\"");
        System.out.println("Delete product enter \"B\"");
        System.out.println("View product enter \"C\"");
        System.out.println("Edit product enter \"D\"");
        System.out.println("Search product enter \"E\"");
        System.out.println("View customer enter \"F\"");
        System.out.println("View transcation enter \"G\"");
        System.out.println("View and change personal information enter \"H\"");
        System.out.println("Exit enter \"X\"");
        return getChoice('H');
    }

    public char viewProductMenu()
    {
        System.out.println("\u000c");
        System.out.println("View all products \"A\"");
        System.out.println("View products by cateloge \"B\"");
        System.out.println("View products by price \"C\"");
        return getChoice('C');
    }

    public char changeOwnerInformation()
    {
        
        System.out.println("Change name enter \"A\"");
        System.out.println("Change email enter \"B\"");
        System.out.println("Change password enter \"C\"");
        System.out.println("Exist enter \"X\"");
        return getChoice('C');
    }

    public char searchProductMenu()
    {
        System.out.println("\u000c");
        System.out.println("Search By Product Name Enter \"A\"");
        System.out.println("Search By Category Enter \"B\"");
        System.out.println("Search By Origin Enter \"C\"");
        System.out.println("Search By Discount Enter \"D\"");
        System.out.println("Exit Enter \"X\"");
        return getChoice('D');
    }

    public char getChoice(char endOption)
    {
        Scanner console = new Scanner(System.in);
        String option = console.nextLine().toUpperCase().trim();
        while (!(option.length() == 1 && ((option.charAt(0) >= 'A' && option.charAt(0) <= endOption) || option.charAt(0) == 'X')))
        {
            System.out.println("Input invalid. Please choose A..." + endOption + " or X.");
            option = console.nextLine().toUpperCase().trim();
        }

        return option.charAt(0);
    }

    public char getChoiceWithoutExit(char endOption)
    {
        Scanner console = new Scanner(System.in);
        String option = console.nextLine().toUpperCase().trim();
        while (!(option.length() == 1 && ((option.charAt(0) >= 'A' && option.charAt(0) <= endOption))))
        {
            System.out.println("Input invalid. Please choose A..." + endOption);
            option = console.nextLine().toUpperCase().trim();
        }

        return option.charAt(0);
    }

    public char changeCustomerInformation()
    {
        //System.out.println("\u000c");
        System.out.println("Change name enter \"A\"");
        System.out.println("Change email enter \"B\"");
        System.out.println("Change password enter \"C\"");
        System.out.println("Change phone enter \"D\"");
        System.out.println("Change address enter \"E\"");
        System.out.println("Exist enter \"X\"");
        return getChoice('E');
    }
}
