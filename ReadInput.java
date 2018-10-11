import java.util.*;
/**
 * Write a description of class ReadInput here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ReadInput
{
    /**
     * Constructor for objects of class ReadInput
     */
    public ReadInput()
    {

    }

    public String readUserName()
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

    public String readUserEmail()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter your email:");
        String email = console.nextLine().trim();
        while (!valide.validateUserEmail(email))
            email = console.nextLine().trim();

        return email;
    }

    public String readProductPrice()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter price:");
        String price = console.nextLine().trim();
        while (!valide.validateProductPrice(price))
            price = console.nextLine().trim();
        return price;
    }

    public String readProductCategory()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter product category(fruit or vagetable):");
        String category = console.nextLine().trim();
        while (!valide.validateProductCategory(category))
            category = console.nextLine().trim();

        return category;
    }

    public double readProductDiscount()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter product discount:");
        String discount = console.nextLine().trim();
        while (!valide.validateProductDiscount(discount))
            discount = console.nextLine().trim();

        return Double.valueOf(discount);
    }

    public int readProductShelfLife()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter product shelf life:");
        String shelfLife = console.nextLine().trim();
        while (!valide.validateProductShelfLife(shelfLife))
            shelfLife = console.nextLine().trim();

        return Integer.valueOf(shelfLife);
    }
    
    public double readProductAmount()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter product amount:");
        String amount = console.nextLine().trim();
        while (!valide.validateProductAmount(amount))
            amount = console.nextLine().trim();

        return Double.valueOf(amount);
    }
    
    public String readProductExchangeAmount(String unit)
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter product exchange amount(1 " + unit + " equals to how many inventory unit):");
        String amount = console.nextLine().trim();
        while (!valide.validateProductAmount(amount))
            amount = console.nextLine().trim();

        return amount;
    }

    public String readProductOrigin()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter product origin:");
        String origin = console.nextLine().trim();
        while (!valide.validateProductOrigin(origin))
            origin = console.nextLine().trim();

        return origin;
    }

    public String readUserPassword()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter your password:");
        String password = console.nextLine().trim();
        while (!valide.validatePassword(password))
            password = console.nextLine().trim();

        return password;
    }

    public String readUserAddress()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter your address:");
        String address = console.nextLine().trim();
        while (!valide.validateUserAddress(address))
            address = console.nextLine().trim();

        return address;
    }

    public String readUserPhone()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter your phone number:");
        String phone = console.nextLine().trim();
        while (!valide.validateUserPhone(phone))
            phone = console.nextLine().trim();  

        return phone;
    }

    public String readProductName()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter product name:");
        String name = console.nextLine().trim();
        while (!valide.validateProductName(name))
            name = console.nextLine().trim();

        return name;
    }
    
    public String readYNOption()
    {
        Validation valide = new Validation();
        Scanner console = new Scanner(System.in);
        String option = console.nextLine().trim().toUpperCase();
        while (!valide.validateYN(option))
            option = console.nextLine().trim().toUpperCase();

        return option;
    }
}
