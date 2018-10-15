/**
 * Write a description of class Owner here.
 *
 * @author (Pan Qi)
 * @version (a version number or a date)
 */
public class Owner extends User
{
    /**
     * A constructor for objects of class Owner.
     */
    public Owner(String name,String id,String newEmail,String newPassword)
    {
        super(name,id,newEmail,newPassword);
    }

    /**
     * Create a displayOwner method to display the owner's details.
     * It invokes getId(), getName() and getEmail() method.
     */
    public void displayOwner()
    {
        System.out.println("Owner ID: " + getId());
        System.out.println("Owner Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("---------------------------------------------------------------");
    }
    
    /**
     * Create a getDetail method to input user's detail. 
     * It invokes getName(), getId() , getEmail() and getPassword() method to tranfer the user's detail into detail.
     * Then return String detail.
     */
    public String getDetail()
    {
        String detail = getName() + "," + getId() + "," + getEmail() + "," + getPassword();
        return detail;
    }
}