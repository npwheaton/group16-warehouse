import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;


public class Supplier implements Serializable {
private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String phone;
	private String address;
	private LinkedList<ProductSupplier> PS = new LinkedList<ProductSupplier>();
	private static final String SUPPLIER_STRING = "S";
		
	Supplier (String name, String phone, String address)
	{
		this.name = name;
		this.phone = phone;
		this.address = address;
		id = SUPPLIER_STRING +
		(SupplierIdServer.instance()).getID();
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getID() {
        return id;
    }

    public String getAddress() {
        return address;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

     public void insertPS(ProductSupplier s) {PS.add(s);}
    public Iterator getPs(){return PS.iterator();}
    public String toString() {
        return "Id: "+ id + " Name: "+ name + " Phone: " + phone + " Address: " + address;
    }
}