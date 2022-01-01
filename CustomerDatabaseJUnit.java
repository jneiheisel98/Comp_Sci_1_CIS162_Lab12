import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.*;
/*******************************************
 * Class to test the CustomerDatabase Lab 12
 *
 * @author - Ana Posada
 * @version - 1.0.0 - October 2020
 ******************************************/
public class CustomerDatabaseJUnit{
    /** object of the CustomerDatabase class */
    private CustomerDatabase database;

    /******************************************************
     * Test constructor
     *****************************************************/
    @Test 
    public void testConstructor()
    {
        database= new CustomerDatabase(); 
        assertEquals("ArrayList should not contain any records at this time",
            0, database.getNumberCustomers ());  
    } 

    /******************************************************
     * Test read file and counts
     *****************************************************/
    @Test
    public void testReadFileAndCounts()
    {
        database= new CustomerDatabase(); 
        database.readCustomerData("CustomerRecords.txt");
        assertEquals("ArrayList should not contain 1000 records",
            1000, database.getNumberCustomers ()); 
    }

    /******************************************************
     * Test searchNames
     *****************************************************/
    @Test
    public void testSearchNames()
    {
        database= new CustomerDatabase(); 
        database.readCustomerData("CustomerRecords.txt");
        Customer c;

        // testing search for a customer that is on file
        c = database.findCustomer("Sandra", "Morgan");
        assertEquals("Looking for Sandra Morgan - this record is on file" +
            "First name should be Sandra","Sandra", c.getFirstName()); 
        assertEquals("Looking for Sandra Morgan - this record is on file" +
            "Last name should be Morgan","Morgan", c.getLastName());

        // testing searh for a customer that is not on file
        c = database.findCustomer("Mickey", "Mouse");
        assertEquals("Mickey Mouse is not on file - method should return null",  null, c); 

    }

    /******************************************************
     * Test search for email domain
     *****************************************************/
    @Test
    public void testSearchEmailDomain() {
        ArrayList<Customer> list;
        database= new CustomerDatabase(); 
        database.readCustomerData("CustomerRecords.txt"); 

        //======= testing for domain ed.gov ( 1 record)
        list = database.findCustomersWithSameEmailDomain("ed.gov");
        assertEquals("only one record should be found for ed.gov" , 1, list.size());

        //record on the list
        assertEquals("First name should be Carl",
            "Carl", list.get(0).getFirstName());   

        assertEquals("Last name should be Gomez",
            "Gomez", list.get(0).getLastName()); 

        //======= testing for domain un.org (3 records)
        list = database.findCustomersWithSameEmailDomain("un.org");

        assertEquals("three records should be found for un.org" , 3, list.size());

        //testing first record on the list of un.org
        assertEquals("First person on the list: First name should be Kelly",
            "Kelly", list.get(0).getFirstName());   

        assertEquals("First person on the list: Last name should be Ward",
            "Ward", list.get(0).getLastName()); 

        //last record on the list un.org
        assertEquals("last person on the list: First name should be Carolyn",
            "Carolyn", list.get(2).getFirstName());   

        assertEquals("Last person on the list: Last name should be Russell",
            "Russell", list.get(2).getLastName());

        // testing a domain not found on the list
        list = database.findCustomersWithSameEmailDomain("xx.com");
        assertEquals("No records found for such email domain: xx.com " ,
            0, list.size());

    }

    /******************************************************
     * Test getDB - get list of all customers
     *****************************************************/
    @Test
    public void testGetAllCustomers() {
        ArrayList<Customer> list;
        database= new CustomerDatabase(); 
        database.readCustomerData("CustomerRecords.txt"); 

        //======= testing getting all customers
        list = database.getDB();
        assertEquals("list should have 1000 records" ,
            1000, list.size());

        //First record on the list
        assertEquals("First name should be Ryan",
            "Ryan", list.get(0).getFirstName());   

        assertEquals("Last name should be Smith",
            "Smith", list.get(0).getLastName()); 

    }
}
