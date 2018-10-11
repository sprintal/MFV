import java.util.regex.*;
/**
 * Write a description of class Validation here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Validation
{

    /**
     * Constructor for objects of class Validation
     */
    public Validation()
    {

    }

    public boolean validateProductAmount(String amount)
    {
        if (amount.length() == 0)
        {
            System.out.println("No amount was entered");
            return false;
        }
        else if (amount.matches("^[0-9]+$") || amount.matches("^[0-9]*.[0-9]*$"))
        {
            if (Double.valueOf(amount) > 0)
                return true;
            else
            {
                System.out.println("Amount should be a number larger than 0");
                return false;
            }
        }
        else
        {
            System.out.println("Amount should be a number larger than 0");
            return false;
        }
    }

    public boolean validateProductDiscount(String productDiscount)
    {
        int a = 0;
        productDiscount = productDiscount.trim();
        if (productDiscount.length() == 0)
        {   
            System.out.println("No discount was entered.");
            return false;
        }

        for (int i=0; i < productDiscount.length(); i++)
        {
            if(!Character.isDigit(productDiscount.charAt(i)))
            {
                if((productDiscount.charAt(i)) != ('.'))
                {
                    System.out.println("Please input a valid product discount in digits."); 
                    return false;
                }
                else if ((productDiscount.charAt(i)) == ('.'))
                    a++;
            }

        }

        if (a > 1)
        {
            System.out.println("There should be only one decimal point.");
            return false;
        }

        double d = Double.parseDouble(productDiscount);

        if (d <= 0 || d > 1)
        {   
            System.out.println("Please input a valid product discount which is between 0 to 1."); 
            return false;
        }
        else
            return true;
    }

    public boolean validateProductShelfLife(String productShelfLife)
    {
        productShelfLife = productShelfLife.trim();
        if (productShelfLife.length() == 0)
        {
            System.out.println("No shelf life was entered.");
            return false;
        }

        for (int i = 0; i < productShelfLife.length(); i++)
        {
            if (!Character.isDigit(productShelfLife.charAt(i)))
            {
                System.out.println("Please input a valid product shelf life in digits."); 
                return false;
            }
            else 
                return true;
        }

        int i = Integer.parseInt(productShelfLife);        

        if (i <= 0 )
        {   
            System.out.println("Please input a valid product shelf life which is higer than 0."); 
            return false;
        }
        else
            return true;

    }

    public boolean validateProductOrigin(String productOrigin)
    {
        productOrigin = productOrigin.trim();
        if (productOrigin.length() < 2 ||
        productOrigin.length() > 20)
        {
            System.out.println("Please input a valid product origin.");            
            System.out.println("The length of product origin should be between 2 to 20.");
            return false;
        }
        else if (productOrigin.matches(".*[a-zA-Z]{2,}.*")==false)
        {
            System.out.println("Please input a valid product origin.");            
            System.out.println("There should be at least two adjacent letters in the product origin.");
            return false;
        }
        else
            return true;
    }

    public boolean validateUserName(String userName)
    {
        int hyphenNumber = 0;//The number of hyphen in the player name.
        int alphabetNumber = 0;//The number of alphabet in the player name.
        for (int i = 0;i < userName.length();i++)
        {
            if (userName.toUpperCase().charAt(i) >= 'A' && userName.toUpperCase().charAt(i) <= 'Z')       
                alphabetNumber++;

            if (userName.charAt(i) == '-')
                hyphenNumber++;
        }           

        if (alphabetNumber + hyphenNumber != userName.length())
        {
            System.out.println("Error!Player name can contain only alphabetical characters and hyphen!");
            return false;
        }

        if (alphabetNumber < 2)
        {
            System.out.println("Error!At least two alphabetical characters should be used!");
            return false;
        }

        if (alphabetNumber + hyphenNumber > 20)
        {
            System.out.println("Error!Name length can't be longer than 20!");
            return false;            
        }

        if (hyphenNumber > 1 || userName.charAt(0) == '-' || userName.charAt(userName.length() - 1) == '-')
        {
            System.out.println("Error!Hyphen can't be at begin or end of the name!At most one hyphen can be used!");
            return false;
        }

        return true;   
    }

    public boolean validateUserEmail(String email)
    {
        email = email.trim();
        if (email.length() == 0)
        {
            System.out.println("No email was entered");
            return false;
        }
        String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if (!m.matches())
        {
            System.out.println("Please enter the correct email format");
            System.out.println("Example: someone@somesite.com");
            return false;
        }

        return true;   
    }

    public boolean validateUserAddress(String address)
    {
        address = address.trim();
        if(address.length() == 0)
        {
            System.out.println("No address was entered");
            return false;
        }
        else if (address.length() >= 50)
        {
            System.out.println("The length of address should less than 50");
            return false;
        }
        else if (address.matches(".*[a-zA-Z]{2,}.*") == false)
        {
            System.out.println("There should be at least two adjacent letters in address.");

            return false;
        }

        return true;
    }

    public boolean validateUserPhone(String phone)
    {
        phone = phone.trim();
        if (phone.length() == 0)
        {
            System.out.println("No phone was entered");
            return false;
        }
        else if (phone.length() >= 20)
        {
            System.out.println("The length of phone should less than 20");
            return false;
        }
        else if(phone.replaceAll("[_\\W]","").length() != phone.length())
        {
            System.out.println("The phone number cannot contain special characters");
            return false;
        }
        else if (phone.matches(".*[a-zA-Z]{1,}.*"))
        {
            System.out.println("The phone number cannot contain letters");
            return false;
        }

        return true;
    }

    public boolean validateProductCategory(String category)
    {
        category = category.trim();
        if(category.toLowerCase().equals("fruit") || category.toLowerCase().equals("vegetable"))
            return true;
        else
        {
            System.out.println("Please enter fruit or vegetable");
            return false;
        }
    }

    public boolean validateProductName(String productName)
    {
        productName = productName.trim();
        if(productName.length() == 0)
        {
            System.out.println("No product name was entered");
            return false;
        }
        else if (productName.length() >= 15)
        {
            System.out.println("The length of product name should between 1 to 15");
            return false;
        }
        else if (productName.matches(".*[a-zA-Z]{2,}.*") == false)
        {
            System.out.println("Please enter the correct product name");
            System.out.println("There should be at least two adjacent letters in product name.");
            return false;
        }

        return true;
    }

    public boolean validatePassword(String password)
    {
        password = password.trim();
        if (password.length() < 8 || password.length() > 20)
        {
            System.out.println("Please input a valid password.");            
            System.out.println("The length of password should be between 8 to 20."); 
            return false;
        }

        int index = 0;

        if (password.matches(".*[a-z]{1,}.*"))
            index++;
        else
        {
            System.out.println("Please input a valid password.");            
            System.out.println("There should be at least one lowercase in the password.");
            return false;
        }

        if (password.matches(".*[A-Z]{1,}.*"))
            index++;
        else
        {
            System.out.println("Please input a valid password.");            
            System.out.println("There should be at least one uppercase in the password.");
            return false;
        }

        if (password.matches(".*\\d{1,}.*"))
            index++;
        else
        {
            System.out.println("Please input a valid password.");            
            System.out.println("There should be at least one digit in the password.");
            return false;
        }

        if (password.matches(".*[_\\W]{1,}.*"))
            index++;
        else
        {
            System.out.println("Please input a valid password.");            
            System.out.println("There should be at least one special character in the password.");             
            return false;
        }

        if (index == 4)
            return true;
        else
        {    
            System.out.println("Please input a valid password."); 
            System.out.println("There should be at least one special character, one digit, one uppercase and one lowercase in the password.");
            return false;             
        }
    }

    // public boolean validateAmount(String amount)
    // {
        // amount = amount.trim();
        // if (amount.length() == 0)
        // {
            // System.out.println("No amount was entered");
            // return false;
        // }
        // else if (amount.matches("^[0-9]+$") || amount.matches("^[0-9]*.[0-9]*$"))
        // {
            // //double a = Double.valueOf(amount);
            // if (Double.valueOf(amount) > 0)
                // return true;
            // else
            // {
                // System.out.println("Amount should be a number larger than 0");
                // return false;
            // }
        // }
        // else
        // {
            // System.out.println("Amount should be a number larger than 0");
            // return false;
        // }
    // }
    
    public boolean validateYN(String option)
    {
        option = option.trim();
        if (option.equals("Y") || option.equals("N"))
            return true;
        else
        {
            System.out.println("You can only choose \"y\" or \"n\"");
            return false;
        }
    }
}
