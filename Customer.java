
/**
 * Write a description of class Customer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Customer
{
    // instance variables - replace the example below with your own
    private String firstName;
    private String lastName;
    private String email;

    /**
     * Constructor for objects of class Customer
     */
    public Customer( String first, String last, String email)
    {
        firstName = first;
        lastName = last;
        this.email = email;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String getFirstName()
    {
        // put your code here
        return firstName;
    }
    
    public String getLastName()
    {
        // put your code here
        return lastName;
    }
    
    public String getEmail()
    {
        // put your code here
        return email;
    }
    
    public String toString()
    {
        // put your code here
        return firstName+" "+ lastName+": "+email;
    }
}
