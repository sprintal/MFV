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
    private ArrayList<Product> searchResult;
    
    /**
     * A default constructor for objects of class ProductController.
     */
    public ProductController()
    {
        productList = new ArrayList<Product>();
        searchResult = new ArrayList<Product>();
    }

    /**
     * A constructor for objects of class ProductController.
     */
    public ProductController(ArrayList<Product> productList, ArrayList<Product> searchResult)
    {
        this.productList = productList;
        this.searchResult = searchResult;
    }

    /**
     * Create a addProduct method to add the product.
     */
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
    
    /**
     * Create a deleteProduct method to delete the product.
     */
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
    
    /**
     * Create a editAmount method to edit the product amount.
     */
    public void editAmount(int index,double amount)
    {
        productList.get(index).setAmount(amount);
        System.out.println("Amount edited!");
        Scanner console = new Scanner(System.in);
        System.out.println("Please press enter to continue!");
        console.nextLine();
    }
    
    /**
     * Create a editCategory method to edit the product category.
     */
    public void editCategory(int index,String category)
    {
        String oldId = productList.get(index).getId();
        String newId = category.substring(0,1).toUpperCase() + oldId.substring(1);
        productList.get(index).setId(newId);
        System.out.println("Category edited!");
        Scanner console = new Scanner(System.in);
        System.out.println("Please press enter to continue!");
        console.nextLine();
    }
    
    /**
     * Create a editProductName method to edit the product name.
     */
    public void editProductName(int index,String name)
    {
        productList.get(index).setName(name);
        System.out.println("Name edited!");
        Scanner console = new Scanner(System.in);
        System.out.println("Please press enter to continue!");
        console.nextLine();
    }
    
    /**
     * Create a editDiscount method to edit the product discount.
     */
    public void editDiscount(int index,double discount)
    {
        productList.get(index).setDiscount(discount);
        System.out.println("Discount edited!");
        Scanner console = new Scanner(System.in);
        System.out.println("Please press enter to continue!");
        console.nextLine();
    }
    
    /**
     * Create a editOrigin method to edit the product origin.
     */
    public void editOrigin(int index,String origin)
    {
        productList.get(index).setOrigin(origin);
        System.out.println("Origin edited!");
        Scanner console = new Scanner(System.in);
        System.out.println("Please press enter to continue!");
        console.nextLine();
    }
    
    /**
     * Create a editSellingOption method to edit the selling option.
     */
    public void editSellingOption(int index,ArrayList<String[]> sellingOption)
    {
        productList.get(index).setSellingOptionList(sellingOption);
        System.out.println("Selling option edited!");
        Scanner console = new Scanner(System.in);
        System.out.println("Please press enter to continue!");
        console.nextLine();
    }
    
    /**
     * Create a editShelfLife method to edit the shelf life.
     */
    public void editShelfLife(int index,int shelfLife)
    {
        productList.get(index).setShelfLife(shelfLife);
        System.out.println("Shelf life edited!");
        Scanner console = new Scanner(System.in);
        System.out.println("Please press enter to continue!");
        console.nextLine();
    }
    
    /**
     * Create a getInventoryUnit method to get the inventory unit.
     */
    public char getInventoryUnit(int index)
    {
        return productList.get(index).inventoryUnit();
    }
    
    /**
     * Create a getProductList method to get the product's list.
     */
    public ArrayList<Product> getProductList()
    {
        return productList;
    }
    
    /**
     * Create a getSearchResult method to get the search result.
     */
    public ArrayList<Product> getSearchResult()
    {
        return searchResult;
    }
    
    /**
     * Create a ifProductNameSame method to verify whether the product name already exist or not.
     */
    private boolean ifProductNameSame(String name)
    {
        int size = productList.size();
        for (int i = 0;i < size;i++)
            if (productList.get(i).getName().equals(name))
            {
                System.out.println("Product name already exist,please change a new name!");
                return true;
            }
        return false;
    }
    
    /**
     * Create a initialProductList method to get the initial product list.
     */
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
    
    /**
     * Create a productIndex method to get the product index.
     */
    public int productIndex(String id)
    {
        int size = productList.size();
        for (int i = 0;i < size;i++)
            if (productList.get(i).getId().toUpperCase().equals(id.toUpperCase()))
                return i;
        return -1;
    }
    
    /**
     * Create a searchByCategory method to get the result searched by category.
     */
    public int searchByCategory(String category)
    {
        searchResult = new ArrayList<Product>();
        Scanner console = new Scanner(System.in);
        category = category.substring(0,1).toUpperCase();
        if (category.equals("X"))
            return -1;
        int count = 0;
        System.out.println("                                Search Result  ");
        System.out.println("------------------------------------------------------------------------------------");
        for (int i = 0;i < productList.size();i++)
        {
            if (productList.get(i).getId().substring(0,1).equals(category))
            {
                System.out.print(count + 1 +". ");
                productList.get(i).displayProduct();
                searchResult.add(productList.get(i));
                count ++;
            }
        }
        System.out.println("Total " + count + " results!");
        return count;
    }
    
    /**
     * Create a searchByDiscount method to get the result searched by discount.
     */
    public int searchByDiscount(double minimumDiscount,double maxmumDiscount)
    {
        searchResult = new ArrayList<Product>();
        Scanner console = new Scanner(System.in);
        System.out.println("                                Search Result  ");
        System.out.println("------------------------------------------------------------------------------------");
        int count = 0;
        for (int index = 0; index < productList.size(); index++)
        {
            if (productList.get(index).getDiscount() <= maxmumDiscount && productList.get(index).getDiscount() >= minimumDiscount)
            {
                System.out.print(index + 1 +". ");
                productList.get(index).displayProduct();
                searchResult.add(productList.get(index));
                count ++;
            }
        }
        System.out.println("Total " + count + " results!");
        return count;
    }
    
    /**
     * Create a searchByOrigin method to get the result searched by product origin.
     */
    public int searchByOrigin()
    {
        searchResult = new ArrayList<Product>();
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter product origin(Enter \"X\" to cancel): ");
        String origin = console.nextLine().trim();
        if (origin.equals("x") || origin.equals("X"))
            return -1;
        int count = 0;
        System.out.println("                                Search Result  ");
        System.out.println("------------------------------------------------------------------------------------");
        for (int i = 0;i < productList.size();i++)
        {
            if (productList.get(i).getOrigin().toUpperCase().contains(origin.toUpperCase()))
            {
                System.out.print(count + 1 +". ");
                productList.get(i).displayProduct();
                searchResult.add(productList.get(i));
                count++;
            }
        }
        System.out.println("Total " + count + " results!");
        return count;
    }
    
    /**
     * Create a searchProductByName method to get the result searched by the product's name.
     */
    public int searchProductByName()
    {
        searchResult = new ArrayList<Product>();
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter product name(Enter \"X\" to cancel): ");
        String name = console.nextLine().trim().toUpperCase();
        if (name.equals("X"))
            return -1;
        int count = 0;
        System.out.println("                                Search Result  ");
        System.out.println("------------------------------------------------------------------------------------");
        for (int i = 0;i < productList.size();i++)
        {
            if (productList.get(i).getName().toUpperCase().contains(name))
            {
                System.out.print(count + 1 +". ");
                productList.get(i).displayProduct();
                searchResult.add(productList.get(i));
                count++;
            }
        }
        System.out.println("Total " + count + " results!");
        //System.out.println("Please press enter to continue!");
        //console.nextLine();
        return count;
    }
    
    /**
     * Create a setProductList method to set the new product's list.
     */
    public void setProductList(ArrayList<Product> newProductList)
    {
        productList = newProductList;
    }
    
    /**
     * Create a setSearchResult method to transfer the search result (newSearchResult) into the searchResult.
     */
    public void setSearchResult(ArrayList<Product> newSearchResult)
    {
        searchResult = newSearchResult;
    }
    
    /**
     * Create a showSellingOption method to show the selling option.
     */
    public int showSellingOption(int index)
    {
        ArrayList<String[]> option = productList.get(index).getSellingOptionList();
        int size = option.size();
        double discount = productList.get(index).getDiscount();
        System.out.println("You are buying " + productList.get(index).getName() + ", The discount is " + discount);
        for(int i = 0;i < size;i++)
        {
            int optionIndex = i + 1;
            System.out.println("Selling option " + optionIndex);
            String[] sellingOption = option.get(i);
            String discountPrice = String.format("%.2f", Double.valueOf(sellingOption[2]) * discount);
            System.out.println("Original price: AU$" + Double.valueOf(sellingOption[2]) + ", Price after discount: AU$" + 
                                discountPrice + " for 1 " + sellingOption[0] + "(" + sellingOption[1] + " kg)");
        }
        return size;
    }
    
    /**
     * Create a strToCal method to get date.
     */
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
    
    /**
     * Create a verifyProductId method to verify the product id.
     */
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
                return -2;
            else
                return productIndex;
        }
        else 
            return -1;
    }
    
    /**
     * Create a viewProduct method to display the product details.
     */
    public int viewProduct()
    {
        System.out.println("\u000c");
        System.out.println("                       Product Details");
        System.out.println("------------------------------------------------------------------------------------");
        int size = productList.size();
        for (int i = 0;i < size;i++)
        {
            System.out.print(i + 1 + ". ");
            productList.get(i).displayProduct();
        }
        System.out.println("Total " + size + " results!");    
        Scanner console = new Scanner(System.in);
        return size;
    }
}
