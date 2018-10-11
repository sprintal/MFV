
/**
 * Write a description of class User here.
 *
 * @author (Pan Qi)
 * @version (a version number or a date)
 */
public class User
{

    private String userName;
    private String userId;
    private String email;
    private String password;

    /**
     * Constructor for objects of class User
     */
    public User()
    {
        userName = "";
        userId = "";
        email = "";
        password = "";
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public User(String name,String id,String newEmail,String newPassword)
    {
        userName = name;
        userId = id;
        email = newEmail;
        password = newPassword;
    }
    
    public void setName(String name)
    {
        userName = name;
    }
    
    public String getName()
    {
        return userName;
    }
    
    public void setId(String id)
    {
        userId = id;
    }
    
    public String getId()
    {
        return userId;
    }
    
    public void setEmail(String newEmail)
    {
        email = newEmail;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setPassword(String newPassword)
    {
        password = newPassword;
    }
    
    public String getPassword()
    {
        return password;
    }
}
