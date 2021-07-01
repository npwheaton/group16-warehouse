import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

public class Order implements Serializable {
private static final long serialVersionUID = 1L;
  private String id;
  private Supplier supp;
  private String date;
  private LinkedList<Import> imports = new LinkedList<Import>();
  private static final String ORDER_STRING = "O";
  public Order(Supplier s, String d){
  supp = s;
  date = d;
  id = ORDER_STRING + (OrderIdServer.instance()).getId();
  }
 
  public Supplier getSupplier() {
    return supp;
  }
  public String getId() {
    return id;
  }
  public Iterator getImports(){
    return imports.iterator();
  }
  public boolean equals(String id) {
    return this.id.equals(id);
  }
  public String toString() {
    String string = "Order ID: " + id + " Order Date: " + date + "\nSupplier: " + supp +"\n";
    return string;
  }
  public void insertImport(Import i){
    imports.add(i);
  }

public String ListImports() {
	String w= "";
       for (Iterator<Import> all = getImports(); all.hasNext(); ) {
      Import i = (Import) all.next();
      w+=i+ "\n";
      } 
    return w;
 } 
public void ProcessOrder() {
       for (Iterator<Import> all = getImports(); all.hasNext(); ) {
      Import i = (Import) all.next();
      Product p = i.getProd();
      int temp = i.getamount();
      p.addquantity(temp);
      } 

 } 
}
