import java.util.Iterator;

public class Clerkstate extends WareState {
  private static Warehouse warehouse;
  private static Clerkstate clerk;
  private ClerkGUI gui;
  private static final int EXIT = 0;
  private static final int ADD_CLIENT = 1;  
  private static final int SHOW_CLIENTS = 2; 
  private static final int SHOW_PRODUCTS = 3;
  private static final int SHOW_WAITS4P = 4;
  private static final int PAYMENT = 6;
  private static final int DEBTOR = 7;
  private static final int BECOMECL = 8;
  private static final int HELP = 9;
  
  private Clerkstate() { 
      warehouse = Warehouse.instance();
      gui = new ClerkGUI("CLERK MENU");
  }
  public static Clerkstate instance() {
    if (clerk == null) {
      clerk = new Clerkstate();
    }
    return clerk;
  }
  public String getStringAnswer(String prompt) {
           String line = "no";
	 try {
               line = gui.input(prompt);
              } catch (Exception e) 
		{System.exit(0);}
       return line;
   }
   
   public int getintAnswer(String prompt) {
           int line = 0;
	   String temp= "";
	 try {
               temp = gui.input(prompt);
		line = Integer.parseInt(temp);
              } catch (NumberFormatException nfe) 
		{System.exit(0);}
       return line;
   }
   public double getDoubleAnswer(String prompt) {
           Double line = 0.00;
	   String temp= "";
	 try {
               temp = gui.input(prompt);
		line = Double.parseDouble(temp);
              } catch (NumberFormatException nfe) 
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
		{gui.output("Enter a valid number");}
     return value;
    }
	public void addClient() {
       Client result;
	 do {
	String name = getStringAnswer("Enter client's name");
        String address = getStringAnswer("Enter address");
        String phone = getStringAnswer("Enter phone");
        result = warehouse.addclient(name, address, phone);
        if (result != null) 
            {gui.output(result.toString());} 
	else 
	    {gui.output("Client could not be added");}
        if (!yesOrNo("Add more clients?")) 
	    {break;}
	} while (true);
    }
	
	public void showClients() {
        Iterator allClients = warehouse.getclients();
        while (allClients.hasNext()){
            Client client = (Client)(allClients.next());
            gui.output(client.toString());
        }
    }
	
	public void showProducts() {
        Iterator allProducts = warehouse.getProds();
        while (allProducts.hasNext()){
            Product product = (Product)(allProducts.next());
            gui.output(product.toString());
        }
    }
	
	public void AcceptPay(){
	String c = getStringAnswer("Enter the Client I.D");
	Client cl = warehouse.verifyclient(c);
	if(cl!=null)
	  {String temp =cl+" Amount owed: $"+ cl.getBal();
	   gui.output(temp);
	      double amount = getDoubleAnswer("\nEnter payment amount");
          String d = getStringAnswer("Enter the date in mm/dd/yyyy format"); 
	  String reciept = warehouse.acceptpayment(cl,amount,d);
	   gui.output(reciept);
	  }
	else {gui.output("Invalid client id");}
	}
	public void showdebtors() {
        Iterator allClients = warehouse.getclients();
        while (allClients.hasNext()){
            Client client = (Client)(allClients.next());
	    if(client.getBal()>0)
	       {gui.output(client.toString());}
        }
    } 
	public void WaitsByProd(){
		String p = getStringAnswer("Enter the Product I.D");
		Product prod = warehouse.verifyPid(p);
		if(prod!=null)
	  {String waits = prod.ShowAllWaits();
	   gui.output(waits);
	  }
	  else
		gui.output("Invalid Product I.D");
	}
	
	public void help() {
        gui.output("Enter a number between " + EXIT + " and " +  HELP + " as explained below:");
        gui.output(EXIT + " to Exit\n");
        gui.output(ADD_CLIENT + " to add a client");
        gui.output(SHOW_CLIENTS + " to print clients");
        gui.output(SHOW_PRODUCTS + " to  print products");  
	gui.output(SHOW_WAITS4P + " to print the waitlist for a product");
	gui.output(PAYMENT + " to process a client payment");
	gui.output(DEBTOR + " to see all clients with an outstanding balance");
	gui.output(BECOMECL + " to switch to client menu");
        gui.output(HELP + " for help");
    }
	public void logout() {
        if(WareContext.instance().getLogin() == 0) {
            WareContext.instance().changeState(3);
        } else {
            WareContext.instance().changeState(2);
        }

    }
private void save() {
        if(Warehouse.instance().save()) {
            gui.output(" The warehouse has been successfully saved in the file WarehouseData \n");
        } else {
            gui.output(" There has been an error in saving \n");
        }

    }
	public void clientmenu() {
        String c = getStringAnswer("Enter the Client I.D");
		Client cl = Warehouse.instance().verifyclient(c);  
        if(cl != null) {
            WareContext.instance().setClient(c);
            WareContext.instance().changeState(1);
           } else {
            gui.output("Invalid user id.");
           }
        }
	public void process() {
        int command;
        gui.display();
        help();
        while ((command = getCommand()) != EXIT) {
            switch (command) {
                case ADD_CLIENT:        addClient();
                    break;
                case SHOW_CLIENTS:	showClients();
                    break;
                case SHOW_PRODUCTS:	showProducts();
                    break;
		case SHOW_WAITS4P:        WaitsByProd();
		    break;
		case PAYMENT:          AcceptPay();
		    break;
		case DEBTOR:          showdebtors();
		    break;
		case BECOMECL:         clientmenu();
		    break;
		case HELP:              help();
                    break;
            }
        }
	save();
	logout();
    }
	public void run() {
        this.process();
    }
	
}