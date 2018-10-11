
/**
 * Write a description of class Owner here.
 *
 * @author (Pan Qi)
 * @version (a version number or a date)
 */
public class Owner extends User
{

    /**
     * Constructor for objects of class Owner
     */
    public Owner(String name,String id,String newEmail,String newPassword)
    {
        super(name,id,newEmail,newPassword);
    }

    public String getDetail()
    {
        String detail = getName() + "," + getId() + "," + getEmail() + "," + getPassword();
        return detail;
    }
    
}
