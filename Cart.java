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

    public ArrayList getPurchaseList()
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

    public String[] generateItem(String productName, String amount, String sellingOption, String price)
    {
        String[] item = new String[4];
        item[0] = productName;
        item[1] = amount;
        item[2] = sellingOption;
        item[3] = price;
        return item;
    }

    public void addItem(String productName, String amount, String sellingOption, String price)
    {
        purchaseList.add(generateItem(productName, amount, sellingOption, price));
    }

    public void addToCart(String productName, String[] sellingOption, double amount, double discount)
    {
        double price = Double.valueOf(sellingOption[2]) * amount * discount;
        addItem(productName, String.valueOf(amount), sellingOption[0], String.valueOf(price));
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
            totalPrice += Double.valueOf(purchaseList.get(index)[3]);
        }
        return totalPrice;
    }

    public void displayCart()
    {
        double totalPrice = caculateTotalPrice();
        System.out.println("You are buying " + purchaseList.size() + " items. Total price is " + totalPrice);
        for (int index = 0; index < purchaseList.size(); index++)
        {
            System.out.println(index + 1 + ". " + purchaseList.get(index)[0] + ", " + purchaseList.get(index)[1] + purchaseList.get(index)[2] + " for AU$" + purchaseList.get(index)[3]);
            System.out.println("---------------------------------------------------------------------------------------");
        }
    }
}