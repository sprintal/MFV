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
    
    private String calToStr()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
        String calStr = sdf.format(date.getTime());
        return calStr;
    }
    
    public void displayTransaction()
    {
        String inventoryOption = new String();
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
        System.out.println("Transaction id: " + transactionId);
        System.out.println("Buyer: " + userEmail + ", order time: " + df.format(date.getTime()) + ", total price: AU$" + totalPrice);
        System.out.println("There are " + purchaseList.size() + " items in the transaction.");
        for (int index = 0; index < purchaseList.size(); index++)
        {
            if (purchaseList.get(index)[3].toUpperCase().equals("BAG") || purchaseList.get(index)[3].toUpperCase().equals("KG") ||
                purchaseList.get(index)[3].toUpperCase().equals("BUNCH"))
                inventoryOption = "kg";
            else
                inventoryOption = "wholes";
            System.out.println(index + 1 + ". " + "Product ID: " + purchaseList.get(index)[0] + ", Product name: " + purchaseList.get(index)[1]);
            System.out.println(purchaseList.get(index)[2] + " " + purchaseList.get(index)[3] + " for AU$" + purchaseList.get(index)[4] + "(" + purchaseList.get(index)[5] + " " + inventoryOption + ")");
        }
    }
    
    public String getDetail()
    {
        String purchase = "";
        int size = purchaseList.size();
        for (int i = 0;i < size;i++)
            purchase = purchase + "," + purchaseList.get(i)[0] + "," + purchaseList.get(i)[1] + "," + purchaseList.get(i)[2] + ","
                       + purchaseList.get(i)[3] + "," + purchaseList.get(i)[4] + "," + purchaseList.get(i)[5];
        String detail = userEmail + "," + transactionId + "," + calToStr() + "," + String.valueOf(totalPrice) + "," + purchase;
        return detail;
    }
}