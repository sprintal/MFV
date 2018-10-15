import java.util.ArrayList;
/**
 * Write a description of class Customer here.
 *
 * @author (Pan Qi)
 * @version (a version number or a date)
 */
public class Customer extends User
{
    private String address;
    private String phone;
    private boolean registerCondition;
    
    /**
     * A defualt constructor for objects of class Customer.
     */
    public Customer()
    {
        super();
        address = "";
        phone = "";
        registerCondition = true;
    }

    /**
     * A constructor for objects of class Customer.
     * Passing parameter name, id, email, password, address, phone, condition.
     */
    public Customer(String name,String id,String email,String password,String address,String phone,boolean condition)
    {
        super(name,id,email,password);
        this.address = address;
        this.phone = phone;
        registerCondition = condition;
    }

    /**
     * Create a displayCustomer method to display the customer's information.
     * It invokes getId(), getName() and getEmail() method.
     */
    public void displayCustomer()
    {
        System.out.println("Customer ID: " + getId());
        System.out.println("Customer Name: " + getName());
        System.out.println("Phone Number: " + phone);
        System.out.println("Email: " + getEmail());
        System.out.println("Address: " + address);
        System.out.println("---------------------------------------------------------------");
    }
    
    /**
     * Create a getAddress method to get the address.
     */
    public String getAddress()
    {
        return address;
    }
    
    /**
     * Create a getDetail method to judge whether the user register, if register, it will print the user's details.
     */
    public String getDetail()
    {
        String registerConditionString = "";
        if (registerCondition == true)
            registerConditionString = "1";
        else
            registerConditionString = "0";
        String detail = getName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + address + "," + phone + "," + registerConditionString;
        return detail;
    }
    
    /**
     * Create a getPhone method to get the phone.
     */
    public String getPhone()
    {
        return phone;
    }
    
    /**
     * Create a getRegisterCondition method to get the registerCondition.
     */
    public boolean getRegisterCondition()
    {
        return registerCondition;
    }
    
    /**
     * Create a setAddress method to transfer newAddress to address.
     */
    public void setAddress(String newAddress)
    {
        address = newAddress;
    }
    
    /**
     * Create a setPhone method to transfer newPhone to phone.
     */
    public void setPhone(String newPhone)
    {
        phone = newPhone;
    }
    
    /**
     * Create a setRegisterCondition method to transfer condition to registerCondition.
     */
    public void setRegisterCondition(boolean condition)
    {
        registerCondition = condition;
    }
}