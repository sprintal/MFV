import java.util.*;
public class Cart
{   
    private ArrayList<String[]> purchaseList;

    public Cart()
    {
        purchaseList = new ArrayList<String[]>();
    }

    public Cart(ArrayList<String[]> newPurchaseList)
    {
        purchaseList = newPurchaseList;
    }

    public ArrayList<String[]> getPurchaseList()
    {
        return purchaseList;
    }

    public void setPurchaseList(ArrayList<String[]> newPurchaseList)
    {
        purchaseList = newPurchaseList;
    }

    public String[] getItem(int index)
    {
        return purchaseList.get(index);
    }

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

    public void removeFromCart(int index)
    {
        purchaseList.remove(index);
    }

    public double caculateTotalPrice()
    {
        double totalPrice = 0;
        for (int index = 0; index < purchaseList.size(); index++)
        {
            totalPrice += Double.valueOf(purchaseList.get(index)[4]);
        }
        return totalPrice;
    }
    
    public void displayCart()
    {
        double totalPrice = caculateTotalPrice();
        System.out.println("You are buying " + purchaseList.size() + " items. Total price is " + totalPrice);
        for (int index = 0; index < purchaseList.size(); index++)
        {
            int number = index + 1;
            System.out.println(number + ". " + purchaseList.get(index)[2] + " " + purchaseList.get(index)[3] + " " +  
            purchaseList.get(index)[1] + " for AU$" + purchaseList.get(index)[4] /*+ " per " +purchaseList.get(index)[3]*/);
            System.out.println("---------------------------------------------------------------------------------------");
        }
        // Scanner console = new Scanner(System.in);
        // System.out.println("Please press enter to continue!");
        // console.nextLine();
    }
}