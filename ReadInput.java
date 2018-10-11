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
        System.out.println("Please enter your name(Enter \"X\" to cancel):");
        System.out.println("Your name format could be \"Given name\"-\"Family name\",whthout whitespace!");
        System.out.println("Your name can't be less than two characters,at most one \"-\" in the middle.");
        String name = console.nextLine().trim();
        if (name.equals("X") || name.equals("x"))
        {
            return "X";
        }
        while (!valide.validateUserName(name))
        {
            name = console.nextLine().trim();
            if (name.equals("X") || name.equals("x"))
                return "X";
        }
        return name;
    }

    public String readUserEmail()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter your email(Enter \"X\" to cancel):");
        String email = console.nextLine().trim();
        if (email.equals("x") || email.equals("X"))
            return "X";
        while (!valide.validateUserEmail(email))
        {
            email = console.nextLine().trim();
            if (email.equals("x") || email.equals("X"))
                return "X";
        }
        return email;
    }

    public String readProductPrice()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter price(Enter \"X\" to cancel):");
        String price = console.nextLine().trim();
        if (price.equals("x") || price.equals("X"))
            return "X";
        while (!valide.validateProductPrice(price))
        {
            price = console.nextLine().trim();
            if (price.equals("x") || price.equals("X"))
                return "X";
        }
        return price;
    }

    public String readProductCategory()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter product category(fruit or vagetable)(Enter \"X\" to cancel):");
        String category = console.nextLine().trim();
        if (category.equals("x") || category.equals("X"))
            return "X";
        while (!valide.validateProductCategory(category))
        {
            category = console.nextLine().trim();
            if (category.equals("x") || category.equals("X"))
                return "X";
        }
        return category;
    }

    public double readProductDiscount()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter product discount(Enter \"X\" to cancel):");
        String discount = console.nextLine().trim();
        if (discount.equals("x") || discount.equals("X"))
            return -1;
        while (!valide.validateProductDiscount(discount))
        {
            discount = console.nextLine().trim();
            if (discount.equals("x") || discount.equals("X"))
                return -1;
        }
        return Double.valueOf(discount);
    }

    public int readProductShelfLife()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter product shelf life(Enter \"X\" to cancel):");
        String shelfLife = console.nextLine().trim();
        if (shelfLife.equals("x") || shelfLife.equals("X"))
            return -1;
        while (!valide.validateProductShelfLife(shelfLife))
        {
            shelfLife = console.nextLine().trim();
            if (shelfLife.equals("x") || shelfLife.equals("X"))
                return -1;
        }
        return Integer.valueOf(shelfLife);
    }
    
    public double readProductAmount()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter product amount(Enter \"X\" to cancel):");
        String amount = console.nextLine().trim();
        if (amount.equals("x") || amount.equals("X"))
            return -1;
        while (!valide.validateProductAmount(amount))
        {
            amount = console.nextLine().trim();
            if (amount.equals("x") || amount.equals("X"))
                return -1;
        }
        return Double.valueOf(amount);
    }
    
    public String readProductExchangeAmount(String unit)
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter product exchange amount(1 " + unit + " equals to how many inventory unit)(Enter \"X\" to cancel):");
        String amount = console.nextLine().trim();
        if (amount.equals("x") || amount.equals("X"))
            return "X";
        while (!valide.validateProductAmount(amount))
        {
            amount = console.nextLine().trim();
            if (amount.equals("x") || amount.equals("X"))
                return "X";
        }
        return amount;
    }

    public String readProductOrigin()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter product origin(Enter \"X\" to cancel):");
        String origin = console.nextLine().trim();
        if (origin.equals("x") || origin.equals("X"))
            return "X";
        while (!valide.validateProductOrigin(origin))
        {
            origin = console.nextLine().trim();
            if (origin.equals("x") || origin.equals("X"))
                return "X";
        }
        return origin;
    }

    public String readUserPassword()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter your password(Enter \"X\" to cancel):");
        String password = console.nextLine().trim();
        if (password.equals("x") || password.equals("X"))
            return "X";
        while (!valide.validatePassword(password))
        {
            password = console.nextLine().trim();
            if (password.equals("x") || password.equals("X"))
                return "X";
        }
        return password;
    }

    public String readUserAddress()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter your address(Enter \"X\" to cancel):");
        String address = console.nextLine().trim();
        if (address.equals("x") || address.equals("X"))
            return "X";
        while (!valide.validateUserAddress(address))
        {
            address = console.nextLine().trim();
            if (address.equals("x") || address.equals("X"))
                return "X";
        }

        return address;
    }

    public String readUserPhone()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter your phone number(Enter \"X\" to cancel):");
        String phone = console.nextLine().trim();
        if (phone.equals("x") || phone.equals("X"))
            return "X";
        while (!valide.validateUserPhone(phone))
        {
            phone = console.nextLine().trim();
            if (phone.equals("x") || phone.equals("X"))
                return "X";
        }
        return phone;
    }

    public String readProductName()
    {
        Scanner console = new Scanner(System.in);
        Validation valide = new Validation();
        System.out.println("Please enter product name(Enter \"X\" to cancel):");
        String name = console.nextLine().trim();
        if (name.equals("x") || name.equals("X"))
            return "X";
        while (!valide.validateProductName(name))
        {
            name = console.nextLine().trim();
            if (name.equals("x") || name.equals("X"))
                return "X";
        }
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
