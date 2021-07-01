
import java.util.*;

public class UserInterface {
    private static UserInterface userInterface;
    private Scanner input = new Scanner(System.in);    
    private static Warehouse warehouse;
    private static final int EXIT = 0;
    private static final int ADD_CLIENT = 1; 
    private static final int ADD_PRODUCT = 2; 
    private static final int ADD_SUPPLIER = 3; 
    private static final int ADD_PRODUCTSUPPLIER = 4;
    private static final int ADD_TOCART = 5; 
    private static final int SHOW_CLIENTS = 6; 
    private static final int SHOW_PRODUCTS = 7;
    private static final int SHOW_SUPPLIERS = 8;
    private static final int SHOW_PROD4SUPP = 9;
    private static final int SHOW_SUPP4PROD = 10;
    private static final int SHOW_ORDERS = 11;
    private static final int SHOW_WAITS = 12;
    private static final int EDITCART = 13;
    private static final int PAYMENT = 14;
    private static final int DEBTOR = 15;
    private static final int PAYBYCL = 16;
    private static final int RECIEVE = 17;
    private static final int HELP = 18;

    private UserInterface() {warehouse = Warehouse.instance();}
    public static UserInterface instance() {
        if (userInterface == null) {
            return userInterface = new UserInterface();
        } else {
            return userInterface;
        }
    }
    public String getStringAnswer(String prompt) {
           String line = "no";
	 try {
              System.out.println(prompt);
               line = input.nextLine();
              } catch (Exception e) 
		{System.exit(0);}
       return line;
   }
   private boolean yesOrNo(String prompt) {
       String more = getStringAnswer(prompt + " (Y|y)[es] or anything else for no");
        if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
            return false;
        }
        return true;
    }
    public int getCommand() {
       int value= 0;    
 	try {
                value = Integer.parseInt(getStringAnswer("Enter command:" + HELP + " for help"));
                if (value >= EXIT && value <= HELP) 
			{return value;}
            } 
	catch (NumberFormatException nfe) 
		{System.out.println("Enter a number");}
     return value;
    }

    public void help() {
        System.out.println("Enter a number between " + EXIT + " and " +  HELP + " as explained below:");
        System.out.println(EXIT + " to Exit\n");
        System.out.println(ADD_CLIENT + " to add a client");
        System.out.println(ADD_PRODUCT + " to add a product");
        System.out.println(ADD_SUPPLIER + " to add a supplier");
	System.out.println(ADD_PRODUCTSUPPLIER + " to add a supplier for a product");
	System.out.println(ADD_TOCART + " to add to your shopping cart");     
        System.out.println(SHOW_CLIENTS + " to print clients");
        System.out.println(SHOW_PRODUCTS + " to  print products");
        System.out.println(SHOW_SUPPLIERS + " to  print suppliers");
	System.out.println(SHOW_PROD4SUPP + " to print products for a individual supplier");
	System.out.println(SHOW_SUPP4PROD + " to print suppliers for a individual product");
	System.out.println(SHOW_WAITS + " to print  all products on a waitlist");
	System.out.println(SHOW_ORDERS + " to print all orders");
	System.out.println(EDITCART + " to edit to your shopping cart");
	System.out.println(PAYMENT + " to make a payment");
	System.out.println(DEBTOR + " to see all clients with an outstanding balance");
	System.out.println(PAYBYCL + " to see all payments for a specific client");
	System.out.println(RECIEVE + " to recieve and process an order from Supplier");
        System.out.println(HELP + " for help");
    }

    public void addClient() {
       Client result;
	 do {
	String name = getStringAnswer("Enter client's name");
        String address = getStringAnswer("Enter address");
        String phone = getStringAnswer("Enter phone");
        result = warehouse.addclient(name, address, phone);
        if (result != null) 
            {System.out.println(result);} 
	else 
	    {System.out.println("Client could not be added");}
        if (!yesOrNo("Add more clients?")) 
	    {break;}
	} while (true);
    }

    public void addProduct() {
        Product result;
        do {
            String name = getStringAnswer("Enter  name of product");
	    int quantity = Integer.parseInt(getStringAnswer("Enter a quantity"));
	    String desc = getStringAnswer("Enter a Description");
	    double price = Double.parseDouble(getStringAnswer("Enter a Sales Price"));
            result = warehouse.addproduct(name, quantity, desc, price);
            if (result != null) 
		{System.out.println(result);} 
	     else 
		{System.out.println("Product could not be added");}
            if (!yesOrNo("Add more products?"))
		{break;}
        } while (true);
    }

    public void addSupplier()   {
       Supplier result;
	do {
        String name = getStringAnswer("Enter  name of supplier");
        String phone = getStringAnswer("Enter phone number");
	String address = getStringAnswer("Enter an Address");
        result = warehouse.addSupplier(name, phone, address);
        if (result != null) 
	    {System.out.println(result);} 
	else 
	    {System.out.println("Supplier could not be added");}
        if (!yesOrNo("Add more suppliers?"))
	    {break;}
 	} while (true);
    }
    public void addProductSupplier()   {
       
	do {
		String s = getStringAnswer("enter a Supplier I.D");
		String p = getStringAnswer("enter a Product I.D");
		double purch = Double.parseDouble(getStringAnswer("Enter a Purchase Price"));
		ProductSupplier result;
		result = warehouse.addPs(s,p, purch);
               if (result != null) 
	    {System.out.println(result);} 
		else 
	    {System.out.println("ProductSupplier could not be added");}
        if (!yesOrNo("Add more Productsuppliers?"))
	    {break;}
 	} while (true);
    }

    public void showProducts() {
        Iterator allProducts = warehouse.getProds();
        while (allProducts.hasNext()){
            Product product = (Product)(allProducts.next());
            System.out.println(product);
        }
    }
    
    public void ShowProd4Supp() {
	String sid = getStringAnswer("enter a Supplier I.D");
	Iterator allProducts = warehouse.P4S(sid);
	 while (allProducts.hasNext()){
	  ProductSupplier ps = (ProductSupplier)(allProducts.next());
	  System.out.println(ps);
	  }
    }
     public void ShowSupp4Prod() {
	String pid = getStringAnswer("enter a Product I.D");
	Iterator allProducts = warehouse.S4P(pid);
	 while (allProducts.hasNext()){
	  ProductSupplier ps = (ProductSupplier)(allProducts.next());
	  System.out.println(ps);
	  }
    }
    public void showSuppliers() {
        Iterator allSuppliers = warehouse.getsupps();
        while (allSuppliers.hasNext()){
            Supplier supplier = (Supplier)(allSuppliers.next());
            System.out.println(supplier);
        }
    }

    public void showClients() {
        Iterator allClients = warehouse.getclients();
        while (allClients.hasNext()){
            Client client = (Client)(allClients.next());
            System.out.println(client);
        }
    }    
    public void showOrders() {
        Iterator allOrders = warehouse.getorders();
        while (allOrders.hasNext()){
            Order order = (Order)(allOrders.next());
            System.out.println(order);
        }
    }
public void RecieveShipment(){
  String d = getStringAnswer("Enter the date in mm/dd/yyyy format");
  String s = getStringAnswer("enter a Supplier I.D");
  Supplier supp = warehouse.verifysupplier(s);
  if(supp!=null)
    {Order o = warehouse.addOrder(supp,d);
	do{ 
	String p = getStringAnswer("enter a Product I.D");
	Product prod = warehouse.verifyPid(p);
	  if(prod!=null)
	    {int howmuch = Integer.parseInt(getStringAnswer("Enter a quantity"));
	     warehouse.AddImport(supp, prod, howmuch, o);
		String order = o.ListImports();
		System.out.println(order);
		o.ProcessOrder();
		warehouse.Processwaiting();
		if (!yesOrNo("Add more Products for this shipment?\n"))
	    				{break;}
	    }
	    System.out.println("Invalid Product name");
	}while(true);
    }
    else
     {System.out.println("Invalid Supplier I.D");}
}

public void PaymentbyClient(){
String c = getStringAnswer("Enter your Client I.D");
Client cl = warehouse.verifyclient(c);  
String l= "";
	if(cl!=null)
	  {l = cl.Allpayments();
	   System.out.println(l);
	  }
	else
	  {System.out.println("Invalid Client I.D\n");}
}
public void ShoppingCart(){
	String c = getStringAnswer("Enter your Client I.D");
	Client cl = warehouse.verifyclient(c);
		if(cl!=null)
		  {do{
		      System.out.println("Here is our selection of products \n");
		      showProducts();
		      String pname = getStringAnswer("\nEnter the name of the product you want to buy");
		      Product prod = warehouse.verifyPname(pname);
			if(prod!=null)
			  {int howmuch = Integer.parseInt(getStringAnswer("Enter a quantity"));
			   warehouse.addtoCart(cl, prod, howmuch);
			   String invoice = cl.invoice()+ warehouse.Sendwait(cl);
			   System.out.println(invoice + "\n");
			   }
			 else
			  {System.out.println("Invalid Product name\n");}
			  if (yesOrNo("Edit Products on shoppingcart?\n"))
	    				{editCart(cl);}
			if (!yesOrNo("Add more Products to shoppingcart?\n"))
	    				{break;}
		       } while (true);
		  }
		else
		{System.out.println("Invalid Client I.D\n");}
    }
public void editCart(Client cl){
	String pname = getStringAnswer("Enter the name of the product you want to edit the amount of\n");
	Product prod = warehouse.verifyPname(pname);
	if(prod!=null)
	   {
	    int newamount = Integer.parseInt(getStringAnswer("\nEnter the new quantity"));
	    Cart c = cl.CheckCart(prod, newamount);
		if(c!=null)
		  {warehouse.EditingCart(c, cl);
	    	   String invoice = cl.invoice()+ warehouse.Sendwait(cl);
	    	   System.out.println(invoice + "\n");
		   }
		else
		  {System.out.println("Invalid Product amount\n");}
	     }
	else
	{System.out.println("Invalid Product name\n");}
}
public void Changecart(){
  String id = getStringAnswer("Enter your Client id\n");
  Client cl = warehouse.verifyclient(id); 
  if(cl!=null)
     {editCart(cl);}
}
public void AcceptPay(){
  String c = getStringAnswer("Enter your Client I.D");
  Client cl = warehouse.verifyclient(c);
	if(cl!=null)
	  {double amount = Double.parseDouble(getStringAnswer("Enter payment amount"));
          String d = getStringAnswer("Enter the date in mm/dd/yyyy format"); 
	  String reciept = warehouse.acceptpayment(cl,amount,d);
	   System.out.println(reciept);
	  }
	else {System.out.println("Invalid client id");}
}
public void ListWaits(){
	String waits= warehouse.Showwait();
	System.out.println(waits);
	}
public void showdebtors() {
        Iterator allClients = warehouse.getclients();
        do{
            Client client = (Client)(allClients.next());
	    if(client.getBal()>0)
	       {System.out.println(client);}
        }while (allClients.hasNext());
    } 	
 public void process() {
        int command;
        help();
        while ((command = getCommand()) != EXIT) {
            switch (command) {
                case ADD_CLIENT:        addClient();
                    break;
                case ADD_PRODUCT:         addProduct();
                    break;
                case ADD_SUPPLIER:        addSupplier();
                    break;
		case ADD_PRODUCTSUPPLIER: addProductSupplier();
		    break;
		case ADD_TOCART:       ShoppingCart();
		    break;
                case SHOW_CLIENTS:	showClients();
                    break;
                case SHOW_PRODUCTS:	showProducts();
                    break;
                case SHOW_SUPPLIERS:     showSuppliers();
		    break;
		case SHOW_PROD4SUPP:      ShowProd4Supp();
		    break;
	        case SHOW_SUPP4PROD:      ShowSupp4Prod();
		    break;
		case SHOW_WAITS:        ListWaits();
		    break;
		case SHOW_ORDERS:         showOrders();
		    break;
		case EDITCART:          Changecart();
		    break;
		case PAYMENT:          AcceptPay();
		    break;
		case DEBTOR:          showdebtors();
		    break;
		case PAYBYCL:          PaymentbyClient();
		    break;
		case RECIEVE:          RecieveShipment();
		    break;
                case HELP:              help();
                    break;
            }
        }
    }
    public static void main(String[] args) {
        UserInterface.instance().process();
    }


}
