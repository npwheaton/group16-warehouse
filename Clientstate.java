import java.util.*;

public class Clientstate extends WareState {
    private static Clientstate clientstate;
    private static Warehouse warehouse;
    private ClerkGUI gui;
    private static final int EXIT = 0;
    private static final int SHOW_CLIENTDETAILS = 1;
    private static final int SHOW_PRODUCTS = 2;
    private static final int TRANSACTION = 3;
    private static final int ADD_TOCART = 4;
    private static final int EDITCART = 5;
    private static final int SHOW_WAITS = 6;
    private static final int HELP = 7;
    private Clientstate() {
        warehouse = Warehouse.instance();
        gui = new ClerkGUI("CLIENT MENU");
  }

    public static Clientstate instance() {
        if (clientstate == null) {
            return clientstate = new Clientstate();
    }   else {
            return clientstate;
    }
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

  public void help() {
    gui.output("Enter a number between " + EXIT + " and " +  HELP + " as explained below:");
    gui.output(SHOW_CLIENTDETAILS + " to show client details.");
    gui.output(SHOW_PRODUCTS + " to show products.");
    gui.output(TRANSACTION + " to see all transactions made.");
    gui.output(ADD_TOCART + " to add products to your shopping cart and to edit your shopping cart.");
    gui.output(EDITCART + " to edit your shopping cart.");
    gui.output(SHOW_WAITS + " to show your wait list.");
    gui.output(HELP + " for help");
  }
    
   public void showClientDetails() {
           String c = WareContext.instance().getClient();
	   Client cl = warehouse.verifyclient(c);
     String temp= cl.toString() + " " +"$ -" +cl.getBal();
     gui.output(temp);
        }
    
   public void showProducts() {
        Iterator allProducts = warehouse.getProds();
        while (allProducts.hasNext()){
            Product product = (Product)(allProducts.next());
            gui.output(product.toString());
        }
    }

public void PaymentbyClient(){
	String c = WareContext.instance().getClient();
	Client cl = warehouse.verifyclient(c);  
	String l= "";
	if(cl!=null)
	  {l = cl.Allpayments();
	   gui.output(l);
	  }
	else
	  {gui.output("Invalid Client I.D\n");}
}
    
     public void ShoppingCart(){
	String c = WareContext.instance().getClient();
	Client cl = warehouse.verifyclient(c);
		if(cl!=null)
		  {do{
		      gui.output("Here is our selection of products \n");
		      showProducts();
		      String pname = getStringAnswer("\nEnter the name of the product you want to buy");
		      Product prod = warehouse.verifyPname(pname);
			if(prod!=null)
			  {int howmuch = Integer.parseInt(getStringAnswer("Enter a quantity"));
			   warehouse.addtoCart(cl, prod, howmuch);
			   String invoice = cl.invoice()+ warehouse.Sendwait(cl);
			   gui.output(invoice + "\n");
			   }
			 else
			  {gui.output("Invalid Product name\n");}
			  if (yesOrNo("Edit Products on shoppingcart?\n"))
	    				{editCart(cl);}
			if (!yesOrNo("Add more Products to shoppingcart?\n"))
	    				{break;}
		       } while (true);
		  }
		else
		{gui.output("Invalid Client I.D\n");}
    }
public void Changecart(){
  String id = WareContext.instance().getClient();
  Client cl = warehouse.verifyclient(id); 
  if(cl!=null)
     {editCart(cl);}
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
	    	   gui.output(invoice + "\n");
		   }
		else
		  {gui.output("Invalid Product amount\n");}
	     }
	else
	{gui.output("Invalid Product name\n");}
}
    
public void ClientWaits(){
	String id = WareContext.instance().getClient();
  	Client cl = warehouse.verifyclient(id);
	String waits= warehouse.Sendwait(cl);
	gui.output(waits);
	}
    
  public void process() {
    int command;
    gui.display();
    help();
    while ((command = getCommand()) != EXIT) {
      switch (command) {

          case SHOW_CLIENTDETAILS: showClientDetails();
              break;
          case SHOW_PRODUCTS: showProducts();
              break;
          case TRANSACTION: PaymentbyClient();
              break;
          case ADD_TOCART: ShoppingCart();
              break;
  	  case EDITCART: Changecart();
              break;
          case SHOW_WAITS: ClientWaits();
              break;
        case HELP:              help();
              break;
      }
    }
    save();
    logout();
  }
    public void run() {
    process();
  }
private void save() {
        if(Warehouse.save()) {
          gui.output(" The warehouse has been successfully saved in the file WarehouseData \n");
        } else {
          gui.output(" There has been an error in saving \n");
        }

    }
  public void logout()
  {
    if ((WareContext.instance()).getLogin() == WareContext.IsClient)
       {(WareContext.instance()).changeState(3);}
    else 
       (WareContext.instance()).changeState(0);
  }
}
