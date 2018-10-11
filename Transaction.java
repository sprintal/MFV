import java.util.*;
public class Transaction
{
    private String transactionId;
    private ArrayList<String[]> purchaseList;
    private Calendar date;
    private double totalPrice;
    
    public Transaction()
    {
        transactionId = "";
        purchaseList = new ArrayList<String[]>();
        date = Calendar.getInstance();
        totalPrice = 0;
    }
    
    public Transaction(String newTransactionId, ArrayList<String[]> newPurchaseList, Calendar newDate, double newTotalPrice)
    {
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
    
    public void displayTransaction()
    {
        System.out.println("Transaction id: " + transactionId + ", order time: " + date.get(Calendar.HOUR_OF_DAY) + ":" + date.get(Calendar.MINUTE) + ":" + date.get(Calendar.SECOND) + ", " + date.get(Calendar.DATE) + "/" + date.get(Calendar.MONTH) + "/" + date.get(Calendar.YEAR) + ", total price: AU$" + totalPrice);
        System.out.println("There are " + purchaseList.size() + " items in the transaction.");
        for (int index = 0; index < purchaseList.size(); index++)
        {
            System.out.println(index + 1 + ". " + purchaseList.get(index)[0] + ", " + purchaseList.get(index)[1] + purchaseList.get(index)[2] + " for AU$" + purchaseList.get(index)[3]);
        }
    }
}