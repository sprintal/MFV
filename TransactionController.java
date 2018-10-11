import java.util.*;
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
            if (transactionList.get(index).getUserEmail() == userEmail)
            {
                transactionList.get(index).displayTransaction();
                System.out.println("---------------------------------------------------------------------------------------");
            }
        }
    }
}