import java.io.Serializable;

public class ProductSupplier implements Serializable {
private static final long serialVersionUID = 1L;
private Supplier supp;
private Product prod;
private double price;
public ProductSupplier( Supplier s, Product p, double purchase) {
supp = s;
prod = p;
price = purchase;
}
public Supplier getSup() {return supp;}
public Product getProd() {return prod;}
public double getPrice() {return price;}
public String getSID() {return supp.getID();}
public String getPID() {return prod.getID();}
public void setPurchasePrice(double purchase) {
price = purchase;}
public String toString(){
String string = "Supplier: " + supp.getName() + " Product: " + prod.getName() + " Purchase Price: $" + price;
return string;
}
}
