import java.util.*;
import java.text.SimpleDateFormat;
public class Transaction
{
    private String userEmail;
    private String transactionId;
    private ArrayList<String[]> purchaseList;
    private Calendar date;
    private double totalPrice;
    
    public Transaction()
    {
        userEmail = "";
        transactionId = "";
        purchaseList = new ArrayList<String[]>();
        date = Calendar.getInstance();
        totalPrice = 0;
    }
    
    public Transaction(String newUserEmail, String newTransactionId, ArrayList<String[]> newPurchaseList, Calendar newDate, double newTotalPrice)
    {
        userEmail = newUserEmail;
        transactionId = newTransactionId;
        purchaseList = newPurchaseList;
        date = newDate;
        totalPrice = newTotalPrice;
    }
    
    public String getTransactionId()
    {
        return transactionId;
    }
    
    public void setTransactionId(String newTransactionId)
    {
        transactionId = newTransactionId;
    }
    
    public ArrayList<String[]> getPurchaseList()
    {
        return purchaseList;
    }
    
    public void setPurchaseList(ArrayList<String[]> newPurchaseList)
    {
        purchaseList = newPurchaseList;
    }
    
    public Calendar getDate()
    {
        return date;
    }
    
    public void setDate(Calendar newDate)
    {
        date = newDate;
    }
    
    public double getTotalPrice()
    {
        return totalPrice;
    }
    
    public void setTotalPrice(double newTotalPrice)
    {
        totalPrice = newTotalPrice;
    }
    
    public String getUserEmail()
    {
        return userEmail;
    }
    
    public void setUserEmail(String newUserEmail)
    {
        userEmail = newUserEmail;
    }
    
    public void displayTransaction()
    {
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss dd-MM-yyyy");
        System.out.println("Transaction id: " + transactionId);
        System.out.println("Buyer: " + userEmail + ", order time: " + df.format(date.getTime()) + ", total price: AU$" + totalPrice);
        System.out.println("There are " + purchaseList.size() + " items in the transaction.");
        for (int index = 0; index < purchaseList.size(); index++)
        {
            System.out.println(index + 1 + ". " + purchaseList.get(index)[0] + ", " + purchaseList.get(index)[1] + purchaseList.get(index)[2] + " for AU$" + purchaseList.get(index)[3]);
        }
    }
}