//Bikash Timalsina
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Iterator;

public class ProductList implements Serializable {
  private static final long serialVersionUID = 1L;
  private LinkedList<Product> products = new LinkedList<Product>();
  private static ProductList ProductList;
  private ProductList() {
  }
  public static ProductList instance() {
    if (ProductList == null) {
      return (ProductList = new ProductList());
    } else {
      return ProductList;
    }
  }
  
  public boolean insertProduct(Product product) {
    products.add(product);
    return true;
  }
  public Iterator getproducts() {
    return products.iterator();
  }
public Product findProduct(String prodId) {
       for (Iterator<Product> all = products.iterator(); all.hasNext(); ) {
      Product prod = (Product) all.next();
      if (prod.getID().equals(prodId)) {
        return prod;
      }
    } return null;
}
public Product findProdByName(String prodname) {
       for (Iterator<Product> all = products.iterator(); all.hasNext(); ) {
      Product prod = (Product) all.next();
      if (prod.getName().equals(prodname)) {
        return prod;
      }
    } return null;
}
public String showWaits(Client cl) {
	String w= "";
       for (Iterator<Product> all = products.iterator(); all.hasNext(); ) {
      Product prod = (Product) all.next();
      w+=prod.addwaits(cl);
    } return w;
}
public String findWaits() {
	String w= "";
       for (Iterator<Product> all = products.iterator(); all.hasNext(); ) {
      Product prod = (Product) all.next();
      w+=prod.ShowAllWaits();
    } return w;
}
public void ProcessWaits() {
       for (Iterator<Product> all = products.iterator(); all.hasNext(); ) {
      Product prod = (Product) all.next();
      prod.Fillwaitlist();
    } 
} private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(ProductList);
    } catch(IOException ioe) {
      System.out.println(ioe);
    }
  }
  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (ProductList != null) {
        return;
      } else {
        input.defaultReadObject();
        if (ProductList == null) {
          ProductList = (ProductList) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch(IOException ioe) {
      System.out.println("in ProductList readObject \n" + ioe);
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }
   public String toString() {
    return products.toString();
  }
}
