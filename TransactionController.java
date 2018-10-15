import java.util.*;
import java.text.SimpleDateFormat;
public class TransactionController
{
    private ArrayList<Transaction> transactionList;
    
    /**
     * A default constructor for objects of class TransactionController.
     */
    public TransactionController()
    {
        transactionList = new ArrayList<Transaction>();
    }
    
    /**
     * A constructor for objects of class TransactionController.
     */
    public TransactionController(ArrayList<Transaction> newTransactionList)
    {
        transactionList = newTransactionList;
    }
    
    /**
     * Create a customerViewTransaction method to display the transactions.
     */
    public void customerViewTransaction(String userEmail)
    {
        for (int index = 0; index < transactionList.size(); index++)
        {
            if (transactionList.get(index).getUserEmail().equals(userEmail))
            {
                transactionList.get(index).displayTransaction();
                System.out.println("---------------------------------------------------------------------------------------");
            }
        }
    }
    
    /**
     * Create a generateId method to get generate transaction id.
     */
    private String generateId()
    {
        String id = new String();
        if (transactionList.size() == 0)
            id = "T1";
        else if (transactionList.size() > 0)
        {
            String previousId = transactionList.get(transactionList.size() - 1).getTransactionId();
            id = "T" + (Integer.parseInt(previousId.substring(1)) + 1);
        }
        return id;
    }
    
    /**
     * Create a generateTransaction method to get generate TransactionList detailed.
     */
    public void generateTransaction(String userEmail, ArrayList<String[]> purchaseList, double totalPrice)
    {
        Transaction transaction = new Transaction();
        transaction.setUserEmail(userEmail);
        transaction.setTransactionId(generateId());
        transaction.setPurchaseList(purchaseList);
        transaction.setTotalPrice(totalPrice);
        transaction.displayTransaction();
        transactionList.add(transaction);
    }
    
    /**
     * Create a getTransactionList method to get TransactionList.
     */
    public ArrayList<Transaction> getTransactionList()
    {
        return transactionList;
    }
    
    /**
     * Create a initialTransactionList method to initial Transaction List.
     */
    public void initialTransactionList(ArrayList<String> detailList)
    {
        int size = detailList.size();
        for(int i = 0;i < size;i++)
        {
            String[] transactionDetails = detailList.get(i).split(",");
            String userEmail = transactionDetails[0];
            String transactionId = transactionDetails[1];
            Calendar date = strToCal(transactionDetails[2]);
            double price = Double.parseDouble(transactionDetails[3]);
            int length = transactionDetails.length - 4;
            int currentIndex = 4;
            ArrayList<String[]> purchaseList = new ArrayList<String[]>();
            while (currentIndex < length)
            {
                String[] purchase = new String[]{transactionDetails[currentIndex],transactionDetails[currentIndex + 1],transactionDetails[currentIndex + 2],
                                                 transactionDetails[currentIndex + 3],transactionDetails[currentIndex + 4],transactionDetails[currentIndex + 5]};
                purchaseList.add(purchase);
                currentIndex += 6;
            }
            transactionList.add(new Transaction(userEmail, transactionId, purchaseList, date, price));
        }
    }
    
    /**
     * Create a setTransactionList method to set TransactionList.
     */
    public void setTransactionList(ArrayList<Transaction> newTransactionList)
    {
        transactionList = newTransactionList;
    }
    
    /**
     * Create a strToCal method to get the date.
     */
    private Calendar strToCal(String str)
    {
        Date date = new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
        try
        {
            date = sdf.parse(str);
        }
        
        catch(Exception e)
        {
            System.out.println("Calendar Exception.");
        }
        finally
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        }
    }
}