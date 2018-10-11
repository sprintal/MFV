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

    public String getId()
    {
        return id;
    }

    public void setId(String newId)
    {
        id = newId;
    }

    public ArrayList<String[]> getSellingOptionList()
    {
        return sellingOptionList;
    }

    public void setSellingOptionList(ArrayList newSellingOpotionList)
    {
        sellingOptionList = newSellingOpotionList;
    }

    public String[] getSellingOption(int index)
    {
        String[] sellingOption= sellingOptionList.get(index);
        return sellingOption;
    }
    
    private String[] generateSellingOption(String newOption, String newRate, String newPrice)
    {
        String[] sellingOption = new String[3];
        sellingOption[0] = newOption;
        sellingOption[1] = newRate;
        sellingOption[2] = newPrice;
        return sellingOption;
    }

    public void addSellingOption(String newOption, String newRate, String newPrice)
    {
        sellingOptionList.add(generateSellingOption(newOption, newRate, newPrice));
    }

    public String getName()
    {
        return name;
    }

    public void setName(String newName)
    {
        name = newName;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double newAmount)
    {
        amount = newAmount;
    }

    public String getOrigin()
    {
        return origin;
    }

    public void setOrigin(String newOrigin)
    {
        origin = newOrigin;
    }

    public int getShelfLife()
    {
        return shelfLife;
    }

    public void setShelfLife(int newShelfLife)
    {
        shelfLife = newShelfLife;
    }

    public double getDiscount()
    {
        return discount;
    }

    public void setDiscount(double newDiscount)
    {
        discount = newDiscount;
    }

    public Calendar getDate()
    {
        return date;
    }

    public void setDate()
    {
        date = Calendar.getInstance();
    }

    private Calendar countExpiryDate()
    {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTime(date.getTime());
        expiryDate.add(Calendar.DATE, shelfLife);
        return expiryDate;
    }

    //true = expired
    public boolean ifExpired()
    {
        Calendar currentDate = Calendar.getInstance();
        int ifExpired = currentDate.compareTo(countExpiryDate());
        if (ifExpired >= 0)
            return true;
        else 
            return false;
    }

    public String getCategory()
    {
        if (id.charAt(0) == 'V')
            return "Vegetable";

        return "Fruit";
    }   

    private String calToStr()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String calStr = sdf.format(date.getTime());
        return calStr;
    }
    
    public char inventoryUnit()
    {
        if(sellingOptionList.get(0)[0].toUpperCase().equals("KG") || sellingOptionList.get(0)[0].toUpperCase().equals("BAG") || 
           sellingOptionList.get(0)[0].toUpperCase().equals("BUNCH"))
            return 'A';
        else
            return 'B';
    }

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
            System.out.println("Original price: AU$" + Double.valueOf(sellingOption[2]) + " Price after discount: AU$" + 
                                discountPrice + " for 1 " + sellingOption[0] + "(" + sellingOption[1] + " kg)");
        }
        System.out.println(amount + inventoryOption + " in stoke");
        System.out.println("Shelf life: " + shelfLife + " days, current discount: " + discount + ", Start selling date: " + calToStr());
        if (ifExpired())
            System.out.println("Selling condition: Expired!");  
        else
            System.out.println("Selling condition: On selling!"); 
        System.out.println("------------------------------------------------------------------------------------");
    }

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
    
}