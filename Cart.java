import java.util.*;
public class Cart
{   
    private ArrayList<String[]> purchaseList;

    /**
     * A default constructor for objects of class Cart.
     */
    public Cart()
    {
        purchaseList = new ArrayList<String[]>();
    }

    /**
     * A constructor for objects of class Cart. Transfering the newPurchaseList into purchaseList.
     */
    public Cart(ArrayList<String[]> newPurchaseList)
    {
        purchaseList = newPurchaseList;
    }

    /**
     * Create a addToCart method to add a product into purchaseList,
     * and input its productId, productName, amount sellingOption, price and rate into the array.
     */
    public void addToCart(String productId,String productName,String amount,String sellingOption,String price,String rate)
    {
        String[] item = new String[6];
        item[0] = productId;
        item[1] = productName;
        item[2] = amount;
        item[3] = sellingOption;
        item[4] = price;
        item[5] = rate;
        purchaseList.add(item);
    }
    
    /**
     * Create a caculateTotalPrice to caculate the total price of the purchasing list.
     * Traversal cycle (for loop) to add each value in the ArrayList according to the index into totalPrice.
     */
    public double caculateTotalPrice()
    {
        double totalPrice = 0;
        for (int index = 0; index < purchaseList.size(); index++)
        {
            totalPrice += Double.valueOf(purchaseList.get(index)[4]);
        }
        return totalPrice;
    }
    
    /**
     * Create a displayCart method to display the total number and total price of purchase,
     * and print each product's detailed information. 
     */
    public void displayCart()
    {
        double totalPrice = caculateTotalPrice();
        System.out.println("You are buying " + purchaseList.size() + " items. Total price is AU$" + totalPrice);
        for (int index = 0; index < purchaseList.size(); index++)
        {
            int number = index + 1;
            System.out.println(number + ". " + purchaseList.get(index)[2] + " " + purchaseList.get(index)[3] + " " +  
            purchaseList.get(index)[1] + " for AU$" + purchaseList.get(index)[4] /*+ " per " +purchaseList.get(index)[3]*/);
            System.out.println("---------------------------------------------------------------------------------------");
        }
    }
    
    /**
     * Create a getItem method to get the index.
     */
    public String[] getItem(int index)
    {
        return purchaseList.get(index);
    }
    
    /**
     * Create a getPurchaseList method to get purchaseList.
     */
    public ArrayList<String[]> getPurchaseList()
    {
        return purchaseList;
    }

    /**
     * Create a removeFromCart method to remove the record of purchaseList according to inputting index.
     */
    public void removeFromCart(int index)
    {
        purchaseList.remove(index);
    }
    
    /**
     * Create a setPurchaseList method to input purchaseList.
     */
    public void setPurchaseList(ArrayList<String[]> newPurchaseList)
    {
        purchaseList = newPurchaseList;
    }
}