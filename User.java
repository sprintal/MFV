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
     * A defualt constructor for objects of class User
     */
    public User()
    {
        userName = "";
        userId = "";
        email = "";
        password = "";
    }

    /**
     * A constructor for objects of class User.
     * Passing parameter name, id, newEmail, newPassword.
     */
    public User(String name,String id,String newEmail,String newPassword)
    {
        userName = name;
        userId = id;
        email = newEmail;
        password = newPassword;
    }
    
    /**
     * Create a getEmail method to get the user email.
     */
    public String getEmail()
    {
        return email;
    }
    
    /**
     * Create a getId method to get the user's id.
     */
    public String getId()
    {
        return userId;
    }
    
    /**
     * Create a getName method to get the user name.
     */
    public String getName()
    {
        return userName;
    }
    
    /**
     * Create a getPassword method to get the user's password
     */
    public String getPassword()
    {
        return password;
    }
    
    /**
     * Create a setEmail method to set the user email.
     */
    public void setEmail(String newEmail)
    {
        email = newEmail;
    }
    
    /**
     * Create a setId method to set user's id.
     */
    public void setId(String id)
    {
        userId = id;
    }
    
    /**
     * Create a setName method to set the user name.
     */
    public void setName(String name)
    {
        userName = name;
    }
    
    /**
     * Create a setPassword method to set the user's password.
     */
    public void setPassword(String newPassword)
    {
        password = newPassword;
    }
}