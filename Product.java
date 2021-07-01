import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

public class Product implements Serializable {
private static final long serialVersionUID = 1L;
	private String name;
	private int quantity;
	private String description;
	private double price;
	private String id;
	private LinkedList<ProductSupplier> PS = new LinkedList<ProductSupplier>();
	private LinkedList<Waitingfor> waitlist = new LinkedList<Waitingfor>();
	private static final String prodstring = "P";


	public Product(String name, int quantity, String description, double price){
		this.name = name;
		this.id = prodstring + (ProductIdServer.instance()).getId();
		this.quantity  = quantity;
		this.description = description;
		this.price = price;
	}

	public String getName(){return name;}

	public String getID(){return id;} 

	public int getquantity(){return quantity;}
	public void addquantity(int x){quantity+=x;}

	public String getdescription(){return description;}

	public double getprice(){return price;}

	public void setName(String name) {this.name = name;}

	public void setQuantity(int quantity) {this.quantity = quantity;}

	public void setdesc(String desc) {description = desc;}

	public void setSalesprice(double salesp) {price = salesp;}

	public void insertPS(ProductSupplier s) {PS.add(s);}
    
	public Iterator getPs(){return PS.iterator();}
	
	public ProductSupplier ChangePurchPrice(Supplier supp, double price){
	for (Iterator<ProductSupplier> ps = getPs(); ps.hasNext(); ) {
	  	ProductSupplier s = (ProductSupplier)ps.next();
	  	if (supp==s.getSup())
	      	    {s.setPurchasePrice(price);
		     return s;}
    		}
    	return null;
	}
	
	public void insertwait(Waitingfor w) {waitlist.add(w);}
    
	public Iterator getwaiting(){return waitlist.iterator();}
	
	public String addwaits(Client cl){
    	   String waitinglist= "";
    	   for (Iterator<Waitingfor> waitlist = getwaiting(); waitlist.hasNext(); ) {
	  	Waitingfor w = (Waitingfor)waitlist.next();
	  	if (cl==w.getClient() && w.getQuantity()>0)
	      	    {waitinglist+= "\n" + w.getQuantity() + " " + name;}
    		}
    	return waitinglist;
	}
	public String ShowAllWaits(){
	   String waitinglist= "";
        for (Iterator<Waitingfor> waitlist = getwaiting(); waitlist.hasNext(); ) {
            Waitingfor w = (Waitingfor)waitlist.next();
	    if ( w.getQuantity()>0)
            	{waitinglist+= w +" "+ name + "\n";}
	    }
	return waitinglist;
        }
	public boolean Findwait(Client cl, int x){
    	   for (Iterator<Waitingfor> waitlist = getwaiting(); waitlist.hasNext(); ) {
	  	Waitingfor w = (Waitingfor)waitlist.next();
	  	if (cl==w.getClient())
	      	    {w.AddtoQuant(x);
		    return true;
		    }
    		}
	return false;
	}
public void Fillwaitlist(){
	for (Iterator<Waitingfor> waitlist = getwaiting(); waitlist.hasNext(); ) {
	  	Waitingfor w = (Waitingfor)waitlist.next();
	  	if (quantity-w.getQuantity()>=0)
	      	    {quantity-=	w.getQuantity();
		     w.setQuant(0);
		    }
		else
		  {w.MinusQuant(quantity);
		  quantity=0;
    		  }
	   }
}
public String toString(){
      return "Name: " + name + " Id: " + id + " Quantity: " + quantity + " Description: " + description + " Price: " + price;
	}
	
}
