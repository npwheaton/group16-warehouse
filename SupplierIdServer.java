import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class SupplierIdServer implements Serializable {
  private static final long serialVersionUID = 1L;
    private  int idCounter;
    private static SupplierIdServer server;
    private SupplierIdServer() {
        idCounter = 1;
    }
    public static SupplierIdServer instance() {
        if (server == null) {
            return (server = new SupplierIdServer());
        } else {
            return server;
        }
    }
    public int getID() {
        return idCounter++;
    }
public static void retrieve(ObjectInputStream input) {
    try {
      server = (SupplierIdServer) input.readObject();
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(Exception cnfe) {
      cnfe.printStackTrace();
    }
  }
  private void writeObject(java.io.ObjectOutputStream output) throws IOException {
    try {
      output.defaultWriteObject();
      output.writeObject(server);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
  private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
    try {
      input.defaultReadObject();
      if (server == null) {
        server = (SupplierIdServer) input.readObject();
      } else {
        input.readObject();
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }

    public String toString() {
        return (" " + idCounter);
    }
    
}