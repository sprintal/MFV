import java.util.*;
import java.text.SimpleDateFormat;
public class TransactionController
{
    private ArrayList<Transaction> transactionList;
    
    public TransactionController()
    {
        transactionList = new ArrayList<Transaction>();
    }
    
    public TransactionController(ArrayList<Transaction> newTransactionList)
    {
        transactionList = newTransactionList;
    }
    
    public ArrayList<Transaction> getTransactionList()
    {
        return transactionList;
    }
    
    public void setTransactionList(ArrayList<Transaction> newTransactionList)
    {
        transactionList = newTransactionList;
    }
    
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