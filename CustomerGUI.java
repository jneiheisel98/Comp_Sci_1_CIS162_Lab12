import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.text.*;

/***********************************************************************
 * GUI front end for lab12 - A customer database
 * 
 * @author Ana Posada
 * @version October 2016
 **********************************************************************/
public class CustomerGUI extends JFrame  implements ActionListener{

    /** results box */
    private JTextArea resultsArea;

    /** object of the CustomerDatabase class */
    private CustomerDatabase db;

    /** JButtons  */
    private JButton byNameButton;
    private JButton byEmailDomainButton;

    /** JTextFields */
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField emailTextField;

    /** menu items */
    private JMenuBar menus;
    private JMenu fileMenu;
    private JMenu reportsMenu;
    private JMenuItem quitItem;
    private JMenuItem openItem; 
    private JMenuItem countItem;
    private JMenuItem allItem;

    /*********************************************************************
    Main Method
     *********************************************************************/
    public static void main(String arg[]){
        CustomerGUI gui = new CustomerGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Customer Database");
        gui.pack();
        gui.setVisible(true);

    }

    /*********************************************************************
    Constructor - instantiates and displays all of the GUI commponents
     *********************************************************************/
    public CustomerGUI(){
        db = new CustomerDatabase();
        setupGUI();
        setupMenus();
    }

    /*********************************************************************
     * set up the GUI commponents
     *********************************************************************/
    private void setupGUI () {
        // create the Gridbag layout
        setLayout(new GridBagLayout());
        GridBagConstraints position = new GridBagConstraints();

        // create the Results Text Area (5 x 10 cells)
        resultsArea = new JTextArea(20,40);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        position.gridx = 0;
        position.gridy = 0;
        position.gridheight = 10;
        position.gridwidth = 5;   
        position.insets =  new Insets(20,20,0,0);       
        add(scrollPane, position);  

        //first name
        position.insets =  new Insets(10,10,10,10);  
        position.gridy = 10;
        position.gridheight = 1;
        position.gridwidth = 1; 
        position.anchor = GridBagConstraints.LINE_START;
        add(new JLabel("First Name"), position);

        position.gridy = 11; 
        firstNameTextField = new JTextField(15);  
        add(firstNameTextField, position);

        //Last name   
        position.gridx = 1;
        position.gridy = 10;  
        add(new JLabel("Last Name"), position);

        position.gridy++;
        lastNameTextField = new JTextField(15);  
        add(lastNameTextField, position);

        // email domain 
        position.gridx++;
        position.gridy = 10;  
        add(new JLabel("Email Domain"), position);

        position.gridy++;
        emailTextField = new JTextField(10);  
        add(emailTextField, position);

        // Add buttons and labels on right side
        // selections
        position.insets =  new Insets(30,5,5,5);   
        position.gridx = 6;
        position.gridy = 0; 
        add(new JLabel("Selections"), position);

        position.gridy++;
        position.insets =  new Insets(0,5,5,5);  
        position.anchor = GridBagConstraints.LINE_START;
        byNameButton = new JButton("Search names");  
        add(byNameButton, position);

        position.gridy++;
        byEmailDomainButton = new JButton("Search Email domain");  
        add(byEmailDomainButton, position);

        // setting actions listeners
        byNameButton.addActionListener(this);
        byEmailDomainButton.addActionListener(this);
    }

    /*********************************************************************
     * Respond to menu selections and button clicks
     *
     *  @param e the button or menu item that was selected
     *********************************************************************/
    public void actionPerformed(ActionEvent e){
        // either open a file or warn the user
        if (e.getSource() == openItem){
            openFile();     
        }
        if(db.getNumberCustomers() == 0){
            resultsArea.setText("Did you forget to open a file?");
            return;    
        } 

        // menu item - quit
        if (e.getSource() == quitItem){
            System.exit(1);
        }

        // Count menu item - display number of customers  
        if (e.getSource() == countItem){
            displayCounts();
        }
        // all menu item - display ALL customers  
        if (e.getSource() == allItem){
            displayCustomers (db.getDB());
        }

        // findCustByName  
        if (e.getSource() == byNameButton){
            processFindCustName();
        }

        // findCustomersEmailDomain 
        if (e.getSource() == byEmailDomainButton){ 
            processFindCustomersEmailDomain();  
        }

    }  

    /*********************************************************************
     * List all entries given an ArrayList of customers.  Include a final
     * line with the number of customers listed
     *
     *  @param ArrayList <Customer>  list of customers
     *********************************************************************/
    private void displayCustomers(ArrayList <Customer> list){
        resultsArea.setText("");
        for(Customer c : list){
            resultsArea.append("\n" + c.toString());
        }
        resultsArea.append ("\nNumber of Customers: " + list.size());
    }

    /************************************************
     * displays the counts in the resultArea
     ************************************************/
    private void displayCounts () { 
        resultsArea.setText("\nNumber of Customers: " + db.getNumberCustomers ( ));
    }

    /************************************************
     * process - finds custumers with same email domain
     ************************************************/
    private void processFindCustomersEmailDomain() {
        if (emailTextField.getText().length() > 0 )
            displayCustomers(db.findCustomersWithSameEmailDomain(emailTextField.getText()));
        else 
            JOptionPane.showMessageDialog(null, "Enter email domain to be able to search");
    }

    /************************************************
     * process - finds custumers with same email domain
     ************************************************/
    private void processFindCustName() {      
        if (firstNameTextField.getText().length() > 0 && lastNameTextField.getText().length() > 0) 
            resultsArea.setText("\nSearch by names " + 
                db.findCustomer(firstNameTextField.getText(), lastNameTextField.getText()));
        else
            JOptionPane.showMessageDialog(null, "Enter first name and last name to be able to search");
    }

    /*********************************************************************
    In response to the menu selection - open a data file
     *********************************************************************/
    private void openFile(){
        JFileChooser fc = new JFileChooser(new File(System.getProperty("user.dir")));
        int returnVal = fc.showOpenDialog(this);

        // did the user select a file?
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getName();
            db.readCustomerData(filename);          
        }
    }

    /*********************************************************************
    Set up the menu items
     *********************************************************************/
    private void setupMenus(){
        // create menu components
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        openItem = new JMenuItem("Open...");
        reportsMenu = new JMenu("Reports");
        countItem = new JMenuItem("Counts");
        allItem = new JMenuItem("All Customers");

        // assign action listeners
        quitItem.addActionListener(this);
        openItem.addActionListener(this);
        countItem.addActionListener(this);
        allItem.addActionListener(this);

        // display menu components
        fileMenu.add(openItem);
        fileMenu.add(quitItem);
        reportsMenu.add(countItem); 
        reportsMenu.add(allItem); 
        menus = new JMenuBar();

        menus.add(fileMenu);
        menus.add(reportsMenu);
        setJMenuBar(menus);
    }      
}