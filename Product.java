import java.util.*;
import java.text.SimpleDateFormat;
public class Product
{
    private String id;
    private String name;
    private double amount;
    private String origin;
    private int shelfLife;
    private double discount;
    private Calendar date;
    private ArrayList<String[]> sellingOptionList;
    
    /**
     * A default constructor for objects of class Product.
     */
    public Product()
    {
        id = "";
        sellingOptionList = new ArrayList<String[]>();
        name = "";
        amount = 0;
        origin = "";
        shelfLife = 0;
        discount = 0;
        date = Calendar.getInstance();
    }

    /**
     * A constructor for objects of class Product. 
     */
    public Product(String newId,String newName,double newAmount, String newOrigin, int newShelfLife, 
                    double newDiscount, Calendar newDate, ArrayList<String[]> newSellingOptionList)
    {
        id = newId;
        name = newName;
        amount = newAmount;
        origin = newOrigin;
        shelfLife = newShelfLife;
        discount = newDiscount;
        date = newDate;
        sellingOptionList = newSellingOptionList;
    }

    /**
     * Create an addSellingOption method to add generate selling option details.
     */
    public void addSellingOption(String newOption, String newRate, String newPrice)
    {
        sellingOptionList.add(generateSellingOption(newOption, newRate, newPrice));
    }

    /**
     * Create a calToStr method to transform the date format.
     */
    private String calToStr()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String calStr = sdf.format(date.getTime());
        return calStr;
    }
    
    /**
     * Create a countExpiryDate method to count the expiry date.
     */
    private Calendar countExpiryDate()
    {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTime(date.getTime());
        expiryDate.add(Calendar.DATE, shelfLife);
        return expiryDate;
    }
    
    /**
     * Create a displayProduct method to display all product's information.
     */
    public void displayProduct()
    {
        String inventoryOption = "";
        if(sellingOptionList.get(0)[0].toUpperCase().equals("KG") || sellingOptionList.get(0)[0].toUpperCase().equals("BAG") || 
           sellingOptionList.get(0)[0].toUpperCase().equals("BUNCH"))
            inventoryOption = "kg";
        else
            inventoryOption = "wholes";
        System.out.println("Product id: " + id + ", Product name: " + name + ", Category: " + getCategory() + ", Origin: " + origin);
        for(int i = 0; i < sellingOptionList.size(); i++)
        {
            String[] sellingOption = getSellingOption(i);
            String discountPrice = String.format("%.2f", Double.valueOf(sellingOption[2]) * discount);
            System.out.println("Original price: AU$" + Double.valueOf(sellingOption[2]) + ", Price after discount: AU$" + 
                                discountPrice + " for 1 " + sellingOption[0] + "(" + sellingOption[1] + " kg)");
        }
        System.out.println(amount + " " + inventoryOption + " in stoke");
        System.out.println("Shelf life: " + shelfLife + " days, current discount: " + discount + ", Start selling date: " + calToStr());
        if (ifExpired())
            System.out.println("Selling condition: Expired!");  
        else
            System.out.println("Selling condition: On selling!"); 
        System.out.println("------------------------------------------------------------------------------------");
    }
    
    /**
     * Create a generateSellingOption method to input generate selling option details.
     */
    private String[] generateSellingOption(String newOption, String newRate, String newPrice)
    {
        String[] sellingOption = new String[3];
        sellingOption[0] = newOption;
        sellingOption[1] = newRate;
        sellingOption[2] = newPrice;
        return sellingOption;
    }
    
    /**
     * Create a getAmount method to get product amount.
     */
    public double getAmount()
    {
        return amount;
    }

    /**
     * Create a getCategory method to get the category of product.
     */
    public String getCategory()
    {
        if (id.charAt(0) == 'V')
            return "Vegetable";

        return "Fruit";
    }
    
    /**
     * Create a getDate method to get the date.
     */
    public Calendar getDate()
    {
        return date;
    }
    
    /**
     * Create a getDetail method to get the product's information.
     */
    public String getDetail()
    {
        String sellingOption = "";
        int size = sellingOptionList.size();
        for (int i = 0;i < size;i++)
            sellingOption = sellingOption + "," + sellingOptionList.get(i)[0] + "," + sellingOptionList.get(i)[1] + "," + sellingOptionList.get(i)[2];
        String detail = id + "," + name + "," + String.valueOf(amount) + "," + origin + "," + String.valueOf(shelfLife) + "," + 
            String.valueOf(discount) + "," + calToStr() + sellingOption;
        return detail;
    }
    
    /**
     * Create a getDiscount method to get the discount of product.
     */
    public double getDiscount()
    {
        return discount;
    }
    
    /**
     * Create a getId method to get product id.
     */
    public String getId()
    {
        return id;
    }
    
    /**
     * Create a getName method to get product name.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Create a getOrigin method to get product origin.
     */
    public String getOrigin()
    {
        return origin;
    }
    
    /**
     * Create a getSellingOptionList method to get sellingOptionList index.
     */
    public String[] getSellingOption(int index)
    {
        String[] sellingOption= sellingOptionList.get(index);
        return sellingOption;
    }
    
    /**
     * Create a getSellingOptionList method to get selling option list.
     */
    public ArrayList<String[]> getSellingOptionList()
    {
        return sellingOptionList;
    }
    
    /**
     * Create a getShelfLife method to get shelfLife.
     */
    public int getShelfLife()
    {
        return shelfLife;
    }
    
    /**
     * Create a ifExpired method to identify whether the date is expiried or not.
     * true = expired
     */
    public boolean ifExpired()
    {
        Calendar currentDate = Calendar.getInstance();
        int ifExpired = currentDate.compareTo(countExpiryDate());
        if (ifExpired >= 0)
            return true;
        else 
            return false;
    }
    
    /**
     * Create a inventoryUnit method to identify the inventory unit.
     */
    public char inventoryUnit()
    {
        if(sellingOptionList.get(0)[0].toUpperCase().equals("KG") || sellingOptionList.get(0)[0].toUpperCase().equals("BAG") || 
           sellingOptionList.get(0)[0].toUpperCase().equals("BUNCH"))
            return 'A';
        else
            return 'B';
    }
    
    /**
     * Create a setAmount method to set product amount.
     */
    public void setAmount(double newAmount)
    {
        amount = newAmount;
    }
    
    /**
     * Create a getDate method to set the date.
     */
    public void setDate()
    {
        date = Calendar.getInstance();
    }
    
    /**
     * Create a setDiscount method to set the discount of product.
     */
    public void setDiscount(double newDiscount)
    {
        discount = newDiscount;
    }
    
    public void setId(String newId)
    {
        id = newId;
    }
    
    /**
     * Create a setName method to set new product name.
     */
    public void setName(String newName)
    {
        name = newName;
    }
    
    /**
     * Create a setOrigin method to set product origin.
     */
    public void setOrigin(String newOrigin)
    {
        origin = newOrigin;
    }
    
    /**
     * Create a setSellingOptionList method to set selling option list.
     */
    public void setSellingOptionList(ArrayList newSellingOpotionList)
    {
        sellingOptionList = newSellingOpotionList;
    }
    
    /**
     * Create a setShelfLife method to set the new shelfLife.
     */
    public void setShelfLife(int newShelfLife)
    {
        shelfLife = newShelfLife;
    }
}