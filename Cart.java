import java.io.Serializable;

public class Cart implements Serializable {
private static final long serialVersionUID = 1L;
private Product prod;
private int quantity;
private double total;
public Cart(Product p, int amount) {
prod = p;
quantity = amount;
total= 0;
}
public Product getProd() {return prod;}
public int getQuantity() {return quantity;}
public void SetQ(int q){quantity = q;}
public double getTotal(){
  total = quantity * prod.getprice();
  return total;}
public boolean checkwait(){
 int remainder= quantity - prod.getquantity();
 if(remainder>0)
    {return true;}
 else
return false;
}
public String toString(){
String string = prod.getName() + " Quantity: " + quantity + " Cost: $" + total;
return string;
}
}
