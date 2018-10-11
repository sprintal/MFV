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
    //private Cart cart;
    //private ArrayList<Transaction> transactionList;
    /**
     * Constructor for objects of class Customer
     */
    public Customer()
    {
        super();
        address = "";
        phone = "";
        registerCondition = true;
        //cart = new Cart();
        //transactionList = new ArrayList<Transaction>();
    }

    public Customer(String name,String id,String email,String password,String address,String phone,boolean condition)
    {
        super(name,id,email,password);
        this.address = address;
        this.phone = phone;
        registerCondition = condition;
        //cart = new Cart();
        //transactionList = new ArrayList<Transaction>();
    }

    public void setRegisterCondition(boolean condition)
    {
        registerCondition = condition;
    }

    public boolean getRegisterCondition()
    {
        return registerCondition;
    }

    public void setAddress(String newAddress)
    {
        address = newAddress;
    }

    public String getAddress()
    {
        return address;
    }

    public void setPhone(String newPhone)
    {
        phone = newPhone;
    }

    public String getName()
    {
        return phone;
    }

    /*
    public Cart getCart()
    {
    return cart;
    }

    public void setCart(Cart newCart)
    {
    cart = newCart;
    }

    public ArrayList<Transaction> getTransactionList()
    {
    return transactionList;
    }

    public void setTransactionList(ArrayList<Transaction> newTransactionList)
    {
    transactionList = newTransactionList;
    }

    public void displayTransaction()
    {
    int size = transactionList.size();
    for (int i = 0;i < size;i++)
    transactionList.get(i).displayTransaction();
    }
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
    
    public String getDetail()
    {
        String detail = getName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + address + "," + phone;
        return detail;
    }
}
