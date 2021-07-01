import java.util.*;
import java.io.*;
public class Warehouse implements Serializable {
private static final long serialVersionUID = 1L;
private ClientList clist;
private SupplierList slist;
private ProductList plist;
private OrderList olist;
private static Warehouse system;

private Warehouse(){
clist = ClientList.instance();
slist = SupplierList.instance();
plist = ProductList.instance();
olist = OrderList.instance();
}
public static Warehouse instance(){
if (system==null)
	{ClientServer.instance();
	ProductIdServer.instance();
	SupplierIdServer.instance();
	OrderIdServer.instance();
	return (system = new Warehouse());
	}
	else
	{return system;}
}
public Product addproduct(String name, int quantity, String description, double price){
    Product prod = new Product( name, quantity, description, price);
    if (plist.insertProduct(prod)) 
    { return (prod);}
    return null;
}
public Client addclient(String name, String address, String phone) {
    Client cl = new Client(name, address, phone);
    if (clist.insertClient(cl)) 
    {return (cl);}
    return null;
}
public Supplier addSupplier(String name, String phone, String address)   {
        Supplier supp = new Supplier(name, phone, address);
        if (slist.insertSupplier(supp))
        {return (supp);}
        return null;
}
public Client verifyclient(String id){
Client cl = clist.findClient(id);
 if(cl!=null)
    {return cl;}
return cl;
}
public Supplier verifysupplier(String s){
Supplier supp = slist.findSupplier(s);
  if(supp!=null)
	{return supp;}
return supp;
}
public Product verifyPname(String p){
Product prod = plist.findProdByName(p);
 if(prod!=null)
    {return prod;}
return prod;
}
public Product verifyPid(String p){
Product prod = plist.findProduct(p);
 if(prod!=null)
    {return prod;}
return prod;
}
public Cart addtoCart( Client cl, Product prod, int amount){
Cart c =cl.CheckCart(prod, amount);
   if(c==null)
     {Cart x = new Cart(prod, amount);
      cl.intocart(x);
     	if(x.checkwait())
	  {int r = amount - prod.getquantity();
	  prod.setQuantity(0);
	    if(!prod.Findwait(cl, r))
	      {Waitingfor w = new Waitingfor(cl, r);
	       prod.insertwait(w);
	      }
	  
      	  }
    	else
     	{int r =prod.getquantity() - amount;
      	prod.setQuantity(r);
     	}
	return x;
     } 
return c;
}
public Cart EditingCart( Cart c, Client cl){
     Product prod = c.getProd();
	if(c.checkwait())
	  {int r = c.getQuantity() - prod.getquantity();
	  prod.setQuantity(0);
	    if(!prod.Findwait(cl, r))
	      {Waitingfor w = new Waitingfor(cl, r);
	       prod.insertwait(w);
	      }
	}
     else
	{int r = prod.getquantity() - c.getQuantity();
	 prod.setQuantity(r);
	} 
return c;
}
public void AddImport(Supplier s, Product p, int a, Order o){
Import i = new Import(s,p,a);
o.insertImport(i);

}


public String Sendwait(Client cl){
	String w= "";
	w = "\nList of products on a waitlist:" + plist.showWaits(cl);
	return w;
}
public String Showwait(){
	String w= "";
	w = "\nList of products on a waitlist:" + plist.findWaits();
	return w;
}
public void Processwaiting(){plist.ProcessWaits();}

public Order addOrder(Supplier s,String d){
Order o = new Order(s,d);
olist.insertOrder(o);
return o;

}
public ProductSupplier addPs(String s, String p, double price)   {
        Supplier supp = slist.findSupplier(s);
	Product prod = plist.findProduct(p);
	
        if (supp == null)
	{return null;}
	if (prod== null)
	{return null;}
	ProductSupplier result = new ProductSupplier(supp, prod, price);
	supp.insertPS(result);
	prod.insertPS(result);
	return result;
}

public Iterator P4S(String sid) {
	Iterator all = null;
	Supplier supp = slist.findSupplier(sid);
		if(supp!=null)
		  {all= supp.getPs();
		  return all;}
	return all;
}
public Iterator S4P(String pid) {
	Iterator all = null;
	Product prod = plist.findProduct(pid);
		if(prod!=null)
		  {all= prod.getPs();
		   return all;}
	return all;
}

public String acceptpayment(Client cl, double pay, String d){
Transaction t = new Transaction(pay, d, cl);
cl.ADDtrans(t);
String reciept= "";
reciept = t+ " was processed. You currently owe $"+ cl.getBal();
return reciept;
}


public Iterator getProds(){return plist.getproducts();}
public Iterator getclients(){return clist.getClients();}
public Iterator getsupps(){ return slist.getSuppliers();}
public Iterator getorders(){ return olist.getOrders();}

public static Warehouse retrieve() {
    try {
      FileInputStream file = new FileInputStream("WarehouseData");
      ObjectInputStream input = new ObjectInputStream(file);
      input.readObject();
      ClientServer.retrieve(input);
	  ProductIdServer.retrieve(input);
	  SupplierIdServer.retrieve(input);
	  OrderIdServer.retrieve(input);
	  return system;
    } catch(IOException ioe) {
      ioe.printStackTrace();
      return null;
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
      return null;
    }
  }
  public static  boolean save() {
    try {
      FileOutputStream file = new FileOutputStream("WarehouseData");
      ObjectOutputStream output = new ObjectOutputStream(file);
      output.writeObject(system);
      output.writeObject( ClientServer.instance());
	  output.writeObject(ProductIdServer.instance());
	  output.writeObject(SupplierIdServer.instance());
    output.writeObject(OrderIdServer.instance());
    output.close();
      return true;
    } catch(IOException ioe) {
      ioe.printStackTrace();
      return false;
    }
  }
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(system);
    } catch(IOException ioe) {
      System.out.println(ioe);
    }
  }
  private void readObject(java.io.ObjectInputStream input) {
    try {
      input.defaultReadObject();
      if (system == null) {
        system = (Warehouse) input.readObject();
      } else {
        input.readObject();
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  public String toString() {
    return plist + "\n" + clist + "\n" + slist;
  }
}
