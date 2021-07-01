import java.io.Serializable;

public class Waitingfor implements Serializable {
private static final long serialVersionUID = 1L;
private Client cl;
private int quantity;
public Waitingfor(Client c, int amount) {
cl = c;
quantity = amount;
}
public Client getClient() {return cl;}
public int getQuantity() {return quantity;}
public void AddtoQuant(int x){quantity+=x;}
public void MinusQuant(int x){quantity-=x;}
public void setQuant(int x){quantity=x;}
public String toString(){
String string = "Client I.D: " +cl.getid() +" Name: "+ cl.getname() + " Quantity: " + quantity;
return string;
}
}
