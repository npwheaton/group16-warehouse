import java.io.Serializable;

public class Import implements Serializable {
private static final long serialVersionUID = 1L;
private Supplier supp;
private Product prod;
private  int amount;
public Import(Supplier s, Product p, int a) {
supp = s;
prod = p;
amount = a;
}
public Product getProd(){return prod;}
public int getamount(){return amount;}
public Supplier getSupplier(){return supp;}
public String toString(){
String string = supp.getName() + " Quantity: " + amount + " " + prod.getName();
return string;
}
}
