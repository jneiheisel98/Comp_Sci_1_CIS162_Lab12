import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerDatabase {
    // FIX ME: define your private ArrayList of customers here. 
    ArrayList<Customer> purchasers;
    public CustomerDatabase() {
        // FIX ME:  initialize an empy ArrayList of customers here.
        purchasers = new ArrayList<Customer>();
    }

    public Customer findCustomer(String firstName, String lastName) {
        // FIX ME:  iterate through your ArrayList of customers and return the 
        // one Customer whose firstName and lastName match the input parameters, or 
        // return a null value if it is not found. 
        Customer particular = null;
          for(Customer client: purchasers){
            if(client.getFirstName().equalsIgnoreCase(firstName) && client.getLastName().equalsIgnoreCase(lastName)){
                particular = client;
            }
        
        
    }
    return particular;
}
    public ArrayList<Customer> findCustomersWithSameEmailDomain(String domain) {
        // FIX ME:  iterate through your ArrayList of customers and return all those whose
        // have the same domain.  For example, if domain is @google, you should return an
        // ArrayList of all the Customers that contain @google in the email
        ArrayList<Customer> emails = new ArrayList<Customer>();
        
        
       
         for(Customer client: purchasers){
                 if(domain.indexOf(".") != -1){       
             if(client.getEmail().contains(domain)){
                emails.add(client);
                }
            }
             if(domain.indexOf("@") ==-1 &&domain.indexOf(".") == -1){
                if(client.getEmail().substring(client.getEmail().indexOf("@")+1, client.getEmail().indexOf(".")).equalsIgnoreCase(domain)){
                       emails.add(client);
                    }   
                }
                if(domain.indexOf("@") ==0&&domain.indexOf(".") == -1){
                if(client.getEmail().substring(client.getEmail().indexOf("@"), client.getEmail().indexOf(".")).equalsIgnoreCase(domain)){
                       emails.add(client);
                    }   
                }
                    
                
                }
                    
    
      
   return emails;
        
    }
    public ArrayList<Customer> getDB() {
        // FIX ME:  return the ArrayList of customers.
        // ONE line of code
        return purchasers;

    }

    public int getNumberCustomers () {
        // FIX ME:  return the number of customers in the 
        // ArrayList - ONE line of code
        return purchasers.size();

    }

    public void readCustomerData(String filename)  {

        // Read the full set of data from a text file
        try{ 

            // open the text file and use a Scanner to read the text
            FileInputStream fileByteStream = new FileInputStream(filename);
            Scanner scnr = new Scanner(fileByteStream);
            scnr.useDelimiter("[,\r\n]+");

            // keep reading as long as there is more data
            while(scnr.hasNext()) {

                // FIX ME: read the firstName, lastName and email
                String firstName = scnr.next();
                String lastName = scnr.next();
                String email = scnr.next();
                
                // discarding the data found in the file after the email - IT is not needed
                scnr.nextLine();
                
                purchasers.add(new Customer( firstName, lastName, email));
                // FIX ME: instantiate a Customer object and add it to the ArrayList
                // You could do this with one or two lines of code.

            }
            fileByteStream.close();
        }
        catch(IOException e) {
            System.out.println("Failed to read the data file: " + filename);
        }
    }

    public static void main(String[] args) {
        CustomerDatabase customers = new CustomerDatabase();
        customers.readCustomerData("Customerrecords.txt");

        System.out.println("\nSearching for Jack King..." + 
            "\n============================");
        Customer jack = customers.findCustomer("Jack", "King");

        if(jack != null) {
            System.out.println("Found record: " + jack);
        } else {
            System.out.println("Could not find Jack King");
        }

        System.out.println("\nSearching for Bill Gates..." + 
            "\n============================");
        Customer bill = customers.findCustomer("Bill", "Gates");

        if(bill != null) {
            System.out.println("Found record: " + bill);
        } else {
            System.out.println("Could not find Bill Gates");
        }

        System.out.println("\nFinding all customers who have a google email account" + 
            "\n=======================================================");
            
        ArrayList<Customer> domainCustomers = 
            customers.findCustomersWithSameEmailDomain("@google") ;
        System.out.println("Found " + domainCustomers.size() + " records total:");
        for(Customer c : domainCustomers) {
            System.out.println(c);
        }

    }

}
