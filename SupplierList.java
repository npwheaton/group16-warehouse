import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Iterator;

public class SupplierList implements Serializable {
    private static final long serialVersionUID = 1L;
    private LinkedList<Supplier> suppliers = new LinkedList<Supplier>();
    private static SupplierList supplierList;
    private SupplierList() {
    }
    public static SupplierList instance() {
        if (supplierList == null) {
            return (supplierList = new SupplierList());
        } else {
            return supplierList;
        }
    }

    public boolean insertSupplier(Supplier supplier) {
        suppliers.add(supplier);
        return true;
    }

    public Iterator getSuppliers(){
        return suppliers.iterator();
    }

    public Supplier findSupplier(String suppId) {
	for (Iterator<Supplier> all = suppliers.iterator(); all.hasNext(); ) {
      Supplier supp = (Supplier) all.next();
      if (supp.getID().equals(suppId)) {
        return supp;
      }
    } return null;
}    
private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(supplierList);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (supplierList != null) {
        return;
      } else {
        input.defaultReadObject();
        if (supplierList == null) {
          supplierList = (SupplierList) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }
    public String toString() {
        return suppliers.toString();
    }
}