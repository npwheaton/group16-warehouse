
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class OrderIdServer implements Serializable {
  private static final long serialVersionUID = 1L;
  private  int idCounter;
  private static OrderIdServer server;
  private OrderIdServer() {
    idCounter = 1;
  }
  public static OrderIdServer instance() {
    if (server == null) {
      return (server = new OrderIdServer());
    } else {
      return server;
    }
  }
  public int getId() {
    return idCounter++;
  }
public static void retrieve(ObjectInputStream var0) {
        try {
            server = (OrderIdServer)var0.readObject();
        } catch (IOException var2) {
            var2.printStackTrace();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    private void writeObject(ObjectOutputStream var1) throws IOException {
        try {
            var1.defaultWriteObject();
            var1.writeObject(server);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    private void readObject(ObjectInputStream var1) throws IOException, ClassNotFoundException {
        try {
            var1.defaultReadObject();
            if(server == null) {
                server = (OrderIdServer)var1.readObject();
            } else {
                var1.readObject();
            }
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

  public String toString() {
    return ("" + idCounter);
  }

  }