import java.util.*;


public class Managerstate extends WareState {
  private static Warehouse warehouse;
  private static Managerstate manger;
  private ClerkGUI gui;
  private static final int EXIT = 0;
  private static final int ADD_PRODUCT = 1;  
  private static final int ADD_SUPPLIER = 2; 
  private static final int SHOW_SUPPLIERS = 3;
  private static final int SHOW_SUPP4PROD= 4;
  private static final int SHOW_PRODUCT4SUPP = 6;
  private static final int ADD_PRODUCTSUPPLIER = 7;
  private static final int MODIFYPRICE = 8;
  private static final int BECOMESALE = 9;
  private static final int HELP = 10;
  
  private Managerstate() {

        warehouse = Warehouse.instance();
        gui = new ClerkGUI("MANAGER MENU");
	}
  public static Managerstate instance() {
    if (manger == null) {
      manger = new Managerstate();
    }
    return manger;
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
	
	public void addProduct() {
        Product result;
        do {
            String name = getStringAnswer("Enter  name of product");
	    int quantity = Integer.parseInt(getStringAnswer("Enter a quantity"));
	    String desc = getStringAnswer("Enter a Description");
	    double price = Double.parseDouble(getStringAnswer("Enter a Sales Price"));
            result = warehouse.addproduct(name, quantity, desc, price);
            if (result != null) 
		{gui.output(result.toString());} 
	     else 
		{gui.output("Product could not be added");}
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
	    {gui.output(result.toString());} 
	else 
	    {gui.output("Supplier could not be added");}
        if (!yesOrNo("Add more suppliers?"))
	    {break;}
 	} while (true);
    }
	
	public void showSuppliers() {
        Iterator allSuppliers = warehouse.getsupps();
        while (allSuppliers.hasNext()){
            Supplier supplier = (Supplier)(allSuppliers.next());
            gui.output(supplier.toString());
        }
    }
	
	public void ShowSupp4Prod() {
	String pid = getStringAnswer("enter a Product I.D");
	Product prod = warehouse.verifyPid(pid);
	if(prod!=null)
	  {
	   Iterator allProducts = warehouse.S4P(pid);
	    while (allProducts.hasNext()){
	     ProductSupplier ps = (ProductSupplier)(allProducts.next());
	     gui.output(ps.toString());
	     }
	   }
    }
	
	public void ShowProd4Supp() {
	String sid = getStringAnswer("enter a Supplier I.D");
	Supplier supp = warehouse.verifysupplier(sid);
	if(supp!=null)
	  {
	   Iterator allProducts = warehouse.P4S(sid);
	   while (allProducts.hasNext()){
	   ProductSupplier ps = (ProductSupplier)(allProducts.next());
	   gui.output(ps.toString());
	    }
	  }
    }
	
	public void addProductSupplier()   {
       
	do {
		String s = getStringAnswer("enter a Supplier I.D");
		String p = getStringAnswer("enter a Product I.D");
		double purch = getDoubleAnswer("Enter a Purchase Price");
		ProductSupplier result;
		result = warehouse.addPs(s,p, purch);
               if (result != null) 
	    {gui.output(result.toString());} 
		else 
	    {gui.output("ProductSupplier could not be added");}
        if (!yesOrNo("Add more Productsuppliers?"))
	    {break;}
 	} while (true);
    }
	
	//modify
public void Editpurchprice(){
	String p = getStringAnswer("enter a Product I.D");
	String s = getStringAnswer("enter a Supplier I.D");
	 Supplier supp = warehouse.verifysupplier(s);
	 Product prod = warehouse.verifyPid(p);
	 if(prod!=null)
	   {if(supp!=null)
		{double purch = getDoubleAnswer("Enter a Purchase Price");
		 ProductSupplier ps =prod.ChangePurchPrice(supp, purch);
		 if(ps!=null)
		    {gui.output(ps.toString());}
		 }
	    }
	}
	
	//help 
	public void help() {
        gui.output("Enter a number between " + EXIT + " and " +  HELP + " as explained below:");
        gui.output(EXIT + " to Exit\n");
        gui.output(ADD_PRODUCT + " to add a product");
        gui.output(ADD_SUPPLIER + " to add a supplier");
        gui.output(SHOW_SUPPLIERS + " to  print supplier");  
	gui.output(SHOW_SUPP4PROD + " to print suppliers for a individual product");
	gui.output(SHOW_PRODUCT4SUPP + " to print products for a individual supplier");
	gui.output(ADD_PRODUCTSUPPLIER + " to add a supplier for a product");
	gui.output(MODIFYPRICE + " to modify the purchase price for a product");
	gui.output(BECOMESALE + " to switch to clerk menu");
        gui.output(HELP + " for help");
    }
	
	
	public void clerkmenu() {
            WareContext.instance().changeState(0);

    }
	
	private void save() {
        if(Warehouse.instance().save()) {
            gui.output(" The warehouse has been successfully saved in the file WarehouseData \n");
        } else {
            gui.output(" There has been an error in saving \n");
        }

    }	
	public void process() {
        int command;
        gui.display();
        help();
        while ((command = getCommand()) != EXIT) {
            switch (command) {
                case ADD_PRODUCT:        addProduct();
                    break;
                case ADD_SUPPLIER:	 addSupplier();
                    break;
                case SHOW_SUPPLIERS:	showSuppliers();
                    break;
		case SHOW_SUPP4PROD:        ShowSupp4Prod();
		    break;
		case SHOW_PRODUCT4SUPP:          ShowProd4Supp();
		    break;
		case ADD_PRODUCTSUPPLIER:    addProductSupplier();
		    break;
		case MODIFYPRICE:    Editpurchprice();
		    break;		
		case BECOMESALE:  		clerkmenu();
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
	
	public void logout() {
   	 WareContext.instance().changeState(3);
    	}
}
	
	