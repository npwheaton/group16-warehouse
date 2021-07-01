import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

public class Client implements Serializable {
private static final long serialVersionUID = 1L;
private String cname;
private String caddress;
private String cphone;
private String cid;
private Double balance;
private LinkedList<Transaction> transactions = new LinkedList<Transaction>();
private LinkedList<Cart> shoppingcart = new LinkedList<Cart>();
private static final String clientstring = "C";

public Client (String name, String address, String phone){
cname = name;
caddress = address;
cphone = phone;
balance = 0.00;
cid = clientstring + (ClientServer.instance()).getid();
}

public String getname() {return cname;}
public String getaddress() {return caddress;}
public String getid() {return cid;}
public String getphone(){return cphone;}
public void UpdateBal(double b){balance-=b;}
public void setName(String name) {cname = name;}
public void setAddress(String address) {caddress = address;}
public void setPhone(String phone) {cphone = phone;}
public void intocart(Cart c) {shoppingcart.add(c);}
public Iterator getCart(){return shoppingcart.iterator();}
public double calcTotal(){
    Iterator<Cart> shop = getCart();
    double total= 0;
    while(shop.hasNext()){
	  Cart c = (Cart)(shop.next());
	  total+= c.getTotal();
    }
    return total;
}
public String invoice(){
    Iterator<Cart> shop = getCart();
    String reciept = cname;
    do{
	  Cart c = (Cart)(shop.next());
	  if(c.getQuantity()>0)
	    {
	     Product p = c.getProd();
	     reciept+= "\n" + c.getQuantity();
	     reciept+=" " + p.getName();
	     reciept+=" $" + c.getTotal();
	    }
      }while(shop.hasNext());
    return reciept+ " \nTotal: $" +calcTotal();
}
public Cart CheckCart(Product prod, int x){
     for (Iterator<Cart> all = shoppingcart.iterator(); all.hasNext(); ) {
	  Cart c = (Cart)(all.next());
	    if (c.getProd()==prod)
	      	    {c.SetQ(x);
		    return c;
		    }
    		}
	return null;
	}
public Iterator getTrans(){return transactions.iterator();}
public void ADDtrans(Transaction t) {transactions.add(t);}
public double getBal(){
balance = calcTotal(); 
   Iterator<Transaction> all = getTrans();
	while(all.hasNext()){
	  Transaction t = (Transaction)(all.next());
	    double temp = t.getTrans();
	   balance-=temp;
      	}
   if(balance<0.00)
	{balance=0.00;}
return balance;
}
public String Allpayments(){
String d ="";
   Iterator<Transaction> all = getTrans();
    while(all.hasNext()){
	  Transaction t = (Transaction)(all.next());
	   d+= "\n" + t;
      }

return d;
}
public String toString(){
 String string = "Client Name: " + cname + " Address: " + caddress + " Id: " + cid + " Phone: " + cphone;
 return string;
  }
}