import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Write a description of class ProductController here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ProductController
{
    private ArrayList<Product> productList;
    /**
     * Constructor for objects of class ProductController
     */
    public ProductController()
    {
        productList = new ArrayList<Product>();
    }

    public ProductController(ArrayList<Product> productList)
    {
        this.productList = productList;
    }

    public ArrayList<Product> getProductList()
    {
        return productList;
    }
    
    public void setProductList(ArrayList<Product> newProductList)
    {
        productList = newProductList;
    }
    
    public void searchByOrigin()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter product origin: ");
        String origin = console.nextLine().trim();
        int count = 0;
        System.out.println("                                Search Result  ");
        System.out.println("------------------------------------------------------------------------------------");
        for (int i = 0;i < productList.size();i++)
        {
            if (productList.get(i).getOrigin().toUpperCase().contains(origin.toUpperCase()))
            {
                productList.get(i).displayProduct();
                count++;
            }
        }
        System.out.println("Total " + count + " results!");
        System.out.println("Please press enter to continue!");
        console.nextLine();
    }
    
    public void searchByDiscount(double minimumDiscount,double maxmumDiscount)
    {
        Scanner console = new Scanner(System.in);
        System.out.println("                                Search Result  ");
        System.out.println("------------------------------------------------------------------------------------");
        int count = 0;
        for (int index = 0; index < productList.size(); index++)
        {
            if (productList.get(index).getDiscount() <= maxmumDiscount && productList.get(index).getDiscount() >= minimumDiscount)
            {    
                productList.get(index).displayProduct();
                count ++;
            }
        }
        System.out.println("Total " + count + " results!");
        System.out.println("Please press enter to continue!");
        console.nextLine();
    }
    
    public char getInventoryUnit(int index)
    {
        return productList.get(index).inventoryUnit();
    }
    
    public void searchByCategory(String category)
    { 
        Scanner console = new Scanner(System.in);
        category = category.substring(0,1).toUpperCase();
        int count = 0;
        System.out.println("                                Search Result  ");
        System.out.println("------------------------------------------------------------------------------------");
        for (int i = 0;i < productList.size();i++)
        {
            if (productList.get(i).getId().substring(0,1).equals(category))
            {
                productList.get(i).displayProduct();
                count ++;
            }
        }
        System.out.println("Total " + count + " results!");
        System.out.println("Please press enter to continue!");
        console.nextLine();
    }
    
    public void searchProductByName()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter product name: ");
        String name = console.nextLine().trim().toUpperCase();
        int count = 0;
        System.out.println("                                Search Result  ");
        System.out.println("------------------------------------------------------------------------------------");
        for (int i = 0;i < productList.size();i++)
        {
            if (productList.get(i).getName().toUpperCase().contains(name))
            {
                productList.get(i).displayProduct();
                count++;
            }
        }
        System.out.println("Total " + count + " results!");
        System.out.println("Please press enter to continue!");
        console.nextLine();
    }

    public void viewProduct()
    {
        System.out.println("\u000c");
        System.out.println("                       Product Details");
        System.out.println("------------------------------------------------------------------------------------");
        int size = productList.size();
        for (int i = 0;i < size;i++)
            productList.get(i).displayProduct();
        System.out.println("Total " + size + " results!");    
        Scanner console = new Scanner(System.in);
        System.out.println("Please press enter to continue!");
        console.nextLine();
    }
    
    public void deleteProduct()
    {
        System.out.println("Please enter the product id which you want delete:");
        Scanner console = new Scanner(System.in);
        String id = console.nextLine().trim();
        int productIndex = productIndex(id);
        if (productIndex == -1)
            System.out.println("Product doesn't exist!");
        else
        {
            productList.remove(productIndex);
            System.out.println("Product deleted!");
        }
    }  
    
    public int productIndex(String id)
    {
        int size = productList.size();
        for (int i = 0;i < size;i++)
            if (productList.get(i).getId().toUpperCase().equals(id.toUpperCase()))
                return i;
        return -1;
    }

    public void editAmount(int index,double amount)
    {
        productList.get(index).setAmount(amount);
        System.out.println("Amount edited!");
        Scanner console = new Scanner(System.in);
        System.out.println("Please press enter to continue!");
        console.nextLine();
    }
    
    public void editProductName(int index,String name)
    {
        productList.get(index).setName(name);
        System.out.println("Name edited!");
        Scanner console = new Scanner(System.in);
        System.out.println("Please press enter to continue!");
        console.nextLine();
    }
    
    public void editOrigin(int index,String origin)
    {
        productList.get(index).setOrigin(origin);
        System.out.println("Origin edited!");
        Scanner console = new Scanner(System.in);
        System.out.println("Please press enter to continue!");
        console.nextLine();
    }

    public void editCategory(int index,String category)
    {
        String oldId = productList.get(index).getId();
        String newId = category.substring(0,1) + oldId.substring(1);
        productList.get(index).setId(newId);
        System.out.println("Category edited!");
        Scanner console = new Scanner(System.in);
        System.out.println("Please press enter to continue!");
        console.nextLine();
    }
    
    public void editDiscount(int index,double discount)
    {
        productList.get(index).setDiscount(discount);
        System.out.println("Discount edited!");
        Scanner console = new Scanner(System.in);
        System.out.println("Please press enter to continue!");
        console.nextLine();
    }

    public void editShelfLife(int index,int shelfLife)
    {
        productList.get(index).setShelfLife(shelfLife);
        System.out.println("Shelf life edited!");
        Scanner console = new Scanner(System.in);
        System.out.println("Please press enter to continue!");
        console.nextLine();
    }
    
    public void editSellingOption(int index,ArrayList<String[]> sellingOption)
    {
        productList.get(index).setSellingOptionList(sellingOption);
        System.out.println("Selling option edited!");
        Scanner console = new Scanner(System.in);
        System.out.println("Please press enter to continue!");
        console.nextLine();
    }
    
    public int showSellingOption(int index)
    {
        ArrayList<String[]> option = productList.get(index).getSellingOptionList();
        int size = option.size();
        double discount = productList.get(index).getDiscount();
        System.out.println("The discount is " + discount);
        for(int i = 0;i < size;i++)
        {
            int optionIndex = i + 1;
            System.out.println("Selling option : " + optionIndex);
            String[] sellingOption = option.get(i);
            String discountPrice = String.format("%.2f", Double.valueOf(sellingOption[2]) * discount);
            System.out.println("Original price: AU$" + Double.valueOf(sellingOption[2]) + " Price after discount: AU$" + 
                                discountPrice + " for 1 " + sellingOption[0] + "(" + sellingOption[1] + " kg)");
        }
        return size;
    }
    
    public int verifyProductId(String id)
    {
        int size = productList.size();
        int productIndex = 0;
        boolean found = false;
        while (productIndex < size && !found)
        {
            if (productList.get(productIndex).getId().toUpperCase().equals(id.toUpperCase()))
                found = true;
            productIndex++;
        }
        
        if (found)
        {
            productIndex--;
            if (productList.get(productIndex).ifExpired())
            {
                System.out.println("Product expired!");
                return -1;
            }
            else
                return productIndex;
        }
        else 
            return -1;
    }
    
    private boolean ifProductNameSame(String name)
    {
        int size = productList.size();
        for (int i = 0;i < size;i++)
            if (productList.get(i).getName().equals(name))
            {
                System.out.println("Product name same,please change a new name!");
                return true;
            }

        return false;
    }
    
    public void initialProductList(ArrayList<String> detailList)
    {
        int size = detailList.size();
        for(int i = 0;i < size;i++)
        {
            String[] productDetails = detailList.get(i).split(",");
            Calendar date = strToCal(productDetails[6]);
            int length = productDetails.length - 1;
            int currentIndex = 7;
            ArrayList<String[]> sellingOptionList = new ArrayList<String[]>();
            while (currentIndex < length)
            {
                String[] option = new String[]{productDetails[currentIndex],productDetails[currentIndex + 1],productDetails[currentIndex + 2]};
                sellingOptionList.add(option);
                currentIndex += 3;
            }

            productList.add(new Product(productDetails[0],productDetails[1],Double.valueOf(productDetails[2]),productDetails[3],
                    Integer.parseInt(productDetails[4]),Double.parseDouble(productDetails[5]),date,sellingOptionList));
        }

    }
    
    public void addProduct(String name,double amount,String origin,int shelfLife,double discount,String category,ArrayList<String[]> sellingOptionList)
    {
        int size = productList.size();  
        String id = ""; 
        category = category.substring(0,1).toUpperCase();
        if (size == 0)
            id = category + String.valueOf(1);
        else 
        {
            String idNumber = productList.get(size - 1).getId().substring(1);
            String newNumber = String.valueOf(Integer.valueOf(idNumber) + 1);
            id = category + newNumber;
        }
        Calendar date = Calendar.getInstance();
        productList.add(new Product(id,name,amount,origin,shelfLife,discount,date,sellingOptionList));
        System.out.println("New product added.");
    }
      
    private Calendar strToCal(String str)
    {
        Date date = new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
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
